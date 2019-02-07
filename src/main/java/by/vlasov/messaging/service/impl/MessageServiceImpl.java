package by.vlasov.messaging.service.impl;

import by.vlasov.messaging.domain.Message;
import by.vlasov.messaging.domain.MessageStatistics;
import by.vlasov.messaging.model.ProcessedMessageResponse;
import by.vlasov.messaging.model.StatisticsResponse;
import by.vlasov.messaging.repository.MessageRepository;
import by.vlasov.messaging.service.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Date;

@Slf4j
@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    private MessageRepository messageRepository;

    @Value("${amqp.topic_exchangeName}")
    private String topicExchangeName;

    @Autowired
    public void init(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public void putMessageToQueue(Message message) {
        MessageStatistics messageStatistics = MessageStatistics.builder().receivedDate(new Date()).build();
        message.setStatistics(messageStatistics);
        log.info("Sending message uuid: {}", message.getUuid());
        rabbitTemplate.convertAndSend(topicExchangeName, "wf.messaging.events", message);
    }

    @Override
    public void processMessage(Message message) {
        String threadName = Thread.currentThread().getName();
        MessageStatistics statistics = message.getStatistics();
        statistics.setProcessName(threadName);
        messageRepository.save(message);
        log.info("Processed message uuid: {} in thread {}", message.getUuid(), threadName);
    }

    @Override
    public StatisticsResponse getMessageStatistics(String uuid) {
        Message message = messageRepository.findByUuid(uuid);
        if (message == null) {
            log.error("Getting of message statistics: Message uuid:{} doesn't exist.", uuid);
            return StatisticsResponse.builder().status(HttpStatus.BAD_REQUEST).build();
        }
        if (message.getStatistics() == null) {
            log.warn("Getting of message statistics: Message uuid:{} has not processed yet.", uuid);
            return StatisticsResponse.builder().status(HttpStatus.NO_CONTENT).build();
        }
        MessageStatistics statistics = message.getStatistics();
        return StatisticsResponse.builder()
                .status(HttpStatus.OK)
                .messageType(message.getType().getTypeName())
                .uuid(message.getUuid())
                .receivedDate(statistics.getReceivedDate())
                .processingTime(statistics.getProccessingTime())
                .processedDate(statistics.getProcessedDate())
                .processName(statistics.getProcessName())
                .build();
    }

    @Override
    public ProcessedMessageResponse getProcessedMessage(String uuid) {
        Message message = messageRepository.findByUuid(uuid);
        if (message == null) {
            log.error("Getting of processed message: Message uuid:{} doesn't exist.", uuid);
            return ProcessedMessageResponse.builder().status(HttpStatus.BAD_REQUEST).build();
        }
        return ProcessedMessageResponse.builder()
                .status(HttpStatus.OK)
                .message(message)
                .build();
    }

    @Override
    public HttpStatus deleteMessage(String uuid) {
        Message message = messageRepository.findByUuid(uuid);
        if (message == null) {
            log.error("Deleting of message: Message uuid:{} doesn't exist.", uuid);
            return HttpStatus.BAD_REQUEST;
        }
        messageRepository.delete(message.getId());
        return HttpStatus.OK;
    }

    @Override
    public Page<Message> findAllMessages(Pageable pageable) {
        return messageRepository.findAll(pageable);
    }
}
