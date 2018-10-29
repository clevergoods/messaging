package com.wf.messaging;

import com.wf.messaging.domain.Message;
import com.wf.messaging.domain.MessageStatistics;
import com.wf.messaging.domain.Metainformation;
import com.wf.messaging.model.MessageRequest;
import com.wf.messaging.repository.MessageRepository;
import com.wf.messaging.repository.MessageStatisticsRepository;
import com.wf.messaging.repository.MessageTypeRepository;
import com.wf.messaging.service.impl.MessageServiceImpl;
import com.wf.messaging.utils.RequestUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("junit")
public class MessageServiceIntegrationTest {

    @InjectMocks
    private MessageServiceImpl messageService;

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private MessageStatisticsRepository messageStatisticsRepository;

    @Mock
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private RequestUtils requestUtils;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        messageService.init(messageRepository);
    }

    @Test
    public void ShouldProcessMessage() throws NoSuchMethodException, MethodArgumentNotValidException {
        //given
        Metainformation metainformation = Metainformation.builder().osName("o").build();
        MessageRequest messageRequest = MessageRequest.builder().author("user1").data("data").organization("o")
                .type("error").build();
        Message message = requestUtils.createMessageFromRequest(messageRequest, metainformation);
        messageService.putMessageToQueue(message);
        //when
        messageService.processMessage(message);
        //then
        List<Message> messages = (List<Message>) messageRepository.findAll();
        List<MessageStatistics> statistics = (List<MessageStatistics>) messageStatisticsRepository.findAll();
        Assert.assertEquals(1, statistics.size());
        Assert.assertEquals(1, messages.size());
    }


    @Test(expected = MethodArgumentNotValidException.class)
    public void ShouldThrowExceptionForNotValidAuthor() throws NoSuchMethodException, MethodArgumentNotValidException {
        //given
        Metainformation metainformation = Metainformation.builder().osName("o").build();
        MessageRequest messageRequest = MessageRequest.builder().author("invaliduser").data("data").organization("o")
                .type("error").build();
        requestUtils.createMessageFromRequest(messageRequest, metainformation);
    }

    @Test(expected = MethodArgumentNotValidException.class)
    public void ShouldThrowExceptionForNotValidType() throws NoSuchMethodException, MethodArgumentNotValidException {
        //given
        Metainformation metainformation = Metainformation.builder().osName("o").build();
        MessageRequest messageRequest = MessageRequest.builder().author("user1").data("data").organization("o")
                .type("invalidtype").build();
        requestUtils.createMessageFromRequest(messageRequest, metainformation);
    }
}
