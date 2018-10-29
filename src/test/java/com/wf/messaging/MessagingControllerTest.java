package com.wf.messaging;

import com.google.gson.Gson;
import com.wf.messaging.controller.MessagingController;
import com.wf.messaging.domain.Message;
import com.wf.messaging.domain.Metainformation;
import com.wf.messaging.model.MessageRequest;
import com.wf.messaging.model.ReceivedMessageResponse;
import com.wf.messaging.service.MessageService;
import com.wf.messaging.utils.RequestUtils;
import eu.bitwalker.useragentutils.UserAgent;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.HttpServletRequest;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(MessagingController.class)
public class MessagingControllerTest {

    @MockBean
    private MessageService messageService;

    @MockBean
    private RequestUtils requestUtils;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void loadMessageShouldReturnUuid() throws Exception {
        //given
        MessageRequest messageRequest =
                MessageRequest.builder().author("user1").data("message").organization("o").type("event").build();
        Message message = Message.builder().uuid("uuid").build();
        when(requestUtils.createMessageFromRequest(any(MessageRequest.class), any(Metainformation.class))).thenReturn(message);
        String requestJson = new Gson().toJson(messageRequest);
        //when
        mockMvc.perform(MockMvcRequestBuilders.post("/api/loadMessage")
                .accept(MediaType.APPLICATION_JSON)
                .content(requestJson)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"status\":\"OK\",\"uuid\":\"uuid\"}"));
    }
}