package com.wf.messaging.service.impl;

import com.wf.messaging.amqpService.MessageReceiver;
import com.wf.messaging.domain.Message;
import com.wf.messaging.model.MessageRequest;
import com.wf.messaging.service.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class MessageServiceImpl implements MessageService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private MessageReceiver receiver;

    @Value("${amqp.topic_exchangeName}")
    private String topicExchangeName;


    /*@Bean
    public RabbitTemplate rubeExchangeTemplate() {
        RabbitTemplate r = new RabbitTemplate(rabbitConnectionFactory);
        r.setExchange("rmq.rube.exchange");
        r.setRoutingKey("rube.key");
        r.setConnectionFactory(rabbitConnectionFactory);
        return r;
    }
*/
    @Override
    public void putMessageToQueue(MessageRequest message) throws InterruptedException {
        System.out.println("Sending message...");

        // messageSender.sendMessage(rabbitTemplate, exchange, routingKey, user);
        rabbitTemplate.convertAndSend(topicExchangeName, "wf.messaging.events", message);
        try {
            receiver.getLatch().await(1000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException ex) {
            log.error("Receiving exception for message '{}': {}. ", message.getMessageContent(), ex.getLocalizedMessage());
            throw new InterruptedException(ex.getLocalizedMessage());
        }
    }
}
