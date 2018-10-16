package com.wf.messaging.service;

import com.wf.messaging.model.MessageRequest;

public interface MessageService {

    void putMessageToQueue(MessageRequest message) throws InterruptedException;
}
