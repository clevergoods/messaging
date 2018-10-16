package com.wf.messaging.controller;

import com.wf.messaging.model.MessageRequest;
import com.wf.messaging.model.MessageResponse;
import com.wf.messaging.service.impl.MessageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("api/messaging")
public class MessagingController {

    @Autowired
    private MessageServiceImpl messageService;

    @PostMapping(path = "loadMessage", produces = "application/json")
    public ResponseEntity<MessageResponse> load(@Valid @RequestBody MessageRequest request) throws InterruptedException {

        messageService.putMessageToQueue(request);
        MessageResponse messageResponse = MessageResponse.builder().status(HttpStatus.OK).build();
        return ResponseEntity.ok().body(messageResponse);
    }
}
