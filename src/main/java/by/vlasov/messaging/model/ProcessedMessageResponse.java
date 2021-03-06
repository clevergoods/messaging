package by.vlasov.messaging.model;

import by.vlasov.messaging.domain.Message;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class ProcessedMessageResponse {

    @JsonProperty("status")
    private HttpStatus status;

    @JsonProperty("message")
    private Message message;
}
