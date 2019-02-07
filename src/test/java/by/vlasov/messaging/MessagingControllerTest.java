package by.vlasov.messaging;

import by.vlasov.messaging.domain.Message;
import by.vlasov.messaging.domain.Metainformation;
import by.vlasov.messaging.model.MessageRequest;
import by.vlasov.messaging.service.MessageService;
import by.vlasov.messaging.utils.RequestUtils;
import com.google.gson.Gson;
import by.vlasov.messaging.controller.MessagingController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

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