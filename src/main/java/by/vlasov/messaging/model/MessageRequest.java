package by.vlasov.messaging.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageRequest {
    @NotNull(message = "Type must be specified")
    @NotEmpty(message = "Type must be specified")
    private String type;

    @NotNull(message = "Author must be specified")
    @NotEmpty(message = "Author must be specified")
    private String author;

    @NotNull(message = "Message must be specified")
    @NotEmpty(message = "Message must be specified")
    private String data;

    @NotNull(message = "Organization must be specified")
    @NotEmpty(message = "Organization must be specified")
    private String organization;
}
