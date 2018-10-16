package com.wf.messaging.amqpService;

import com.wf.messaging.model.MessageRequest;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

@Component
public class MessageReceiver {

    private CountDownLatch latch = new CountDownLatch(1);

    public void receiveMessage(MessageRequest message) {
        System.out.println("Received <" + message.getMessageContent() + ">");
        latch.countDown();
    }

    public CountDownLatch getLatch() {
        return latch;
    }

}

