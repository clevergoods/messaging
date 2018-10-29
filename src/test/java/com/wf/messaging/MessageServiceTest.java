package com.wf.messaging;

import com.wf.messaging.domain.Message;
import com.wf.messaging.domain.MessageStatistics;
import com.wf.messaging.repository.MessageRepository;
import com.wf.messaging.service.impl.MessageServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.test.util.ReflectionTestUtils;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class MessageServiceTest {

    @InjectMocks
    private MessageServiceImpl service;

    @Mock
    private RabbitTemplate rabbitTemplate;

    @Mock
    private MessageRepository messageRepository;


    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        ReflectionTestUtils.setField(service, "topicExchangeName", "topicExchangeName");
    }

    @Test
    public void ShouldPutMessageToQueue() {
        //given
        Message message = Message.builder().uuid("uuid").build();
        //when
        service.putMessageToQueue(message);
        //then
        verify(rabbitTemplate).convertAndSend("topicExchangeName", "wf.messaging.events", message);
    }

    @Test
    public void ShouldProcessMessage() {
        //given
        MessageStatistics statistics = MessageStatistics.builder().build();
        Message message = Message.builder().uuid("uuid").statistics(statistics).build();
        //when
        service.processMessage(message);
        //then
        verify(messageRepository).save(message);
    }
}
