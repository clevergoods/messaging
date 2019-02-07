package by.vlasov.messaging.service;

import by.vlasov.messaging.domain.Message;
import by.vlasov.messaging.model.ProcessedMessageResponse;
import by.vlasov.messaging.model.StatisticsResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public interface MessageService {
    void putMessageToQueue(Message message) throws InterruptedException;
    void processMessage(Message message);

    StatisticsResponse getMessageStatistics(String uuid);

    ProcessedMessageResponse getProcessedMessage(String uuid);

    HttpStatus deleteMessage(String uuid);

    Page<Message> findAllMessages(Pageable pageable);
}
