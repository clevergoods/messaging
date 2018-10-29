package com.wf.messaging.amqpService;

import com.wf.messaging.domain.Message;
import com.wf.messaging.service.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@Scope("prototype")
public class MessageWorker implements Runnable {

    @Autowired
    private MessageService messageService;

    private Message message;

    MessageWorker(Message message) {
        this.message = message;
    }

    @Override
    public void run() {
        messageService.processMessage(message);
    }
}
