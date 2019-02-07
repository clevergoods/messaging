package by.vlasov.messaging.amqpService;

import by.vlasov.messaging.domain.Message;
import by.vlasov.messaging.service.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MessageListener {

    @Autowired
    private TaskExecutor taskExecutor;

    @Autowired
    private MessageService messageService;

    @RabbitListener(queues = "${amqp.queue_name}")
    public void receiveMessage(Message message) {
        MessageWorker messageWorker = MessageWorker.builder().message(message).messageService(messageService).build();
        taskExecutor.execute(messageWorker);
    }
}
