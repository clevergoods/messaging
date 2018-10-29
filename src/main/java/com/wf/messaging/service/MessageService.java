package com.wf.messaging.service;

import com.wf.messaging.domain.Message;
import com.wf.messaging.domain.Metainformation;
import com.wf.messaging.model.MessageRequest;
import com.wf.messaging.model.ProcessedMessageResponse;
import com.wf.messaging.model.StatisticsResponse;
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
