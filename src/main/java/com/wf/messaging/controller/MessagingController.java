package com.wf.messaging.controller;

import com.wf.messaging.domain.Message;
import com.wf.messaging.domain.Metainformation;
import com.wf.messaging.model.MessageRequest;
import com.wf.messaging.model.ProcessedMessageResponse;
import com.wf.messaging.model.ReceivedMessageResponse;
import com.wf.messaging.model.StatisticsResponse;
import com.wf.messaging.service.MessageService;
import com.wf.messaging.utils.RequestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("api")
public class MessagingController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private RequestUtils requestUtils;

    @Autowired
    private HttpServletRequest request;

    @PostMapping(path = "loadMessage", produces = "application/json")
    public ResponseEntity<ReceivedMessageResponse> load(@Valid @RequestBody MessageRequest messageRequest) throws NoSuchMethodException,
            SecurityException, MethodArgumentNotValidException, InterruptedException {
        Metainformation metainformation = requestUtils.generateMetainformation(request);
        Message message = requestUtils.createMessageFromRequest(messageRequest, metainformation);
        messageService.putMessageToQueue(message);
        ReceivedMessageResponse messageResponse =
                ReceivedMessageResponse.builder().uuid(message.getUuid()).status(HttpStatus.OK).build();
        return ResponseEntity.ok().body(messageResponse);
    }

    @GetMapping(path = "{uuid}/statisics", produces = {"application/json"})
    public @ResponseBody
    ResponseEntity<StatisticsResponse> getStatistics(@PathVariable("uuid") String uuid) {
        StatisticsResponse result = messageService.getMessageStatistics(uuid);
        return ResponseEntity.status(result.getStatus()).body(result);
    }

    @GetMapping(path = "{uuid}/message", produces = {"application/json"})
    public @ResponseBody
    ResponseEntity<ProcessedMessageResponse> getMessage(@PathVariable("uuid") String uuid) {
        ProcessedMessageResponse result = messageService.getProcessedMessage(uuid);
        return ResponseEntity.status(result.getStatus()).body(result);
    }

    @DeleteMapping(path = "{uuid}/delete")
    public ResponseEntity<?> delMessage(@PathVariable("uuid") String uuid) {
        HttpStatus status = messageService.deleteMessage(uuid);
        return ResponseEntity.status(status).body(null);
    }

    @GetMapping(path = "/allMessages")
    public Page<Message> findAllMessages(Pageable pageRequest) {
        return messageService.findAllMessages(pageRequest);
    }
}
