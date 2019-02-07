package by.vlasov.messaging.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.Date;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class StatisticsResponse {
    @JsonProperty("status")
    private HttpStatus status;

    @JsonProperty("uuid")
    private String uuid;

    @JsonProperty("messageType")
    private String messageType;

    @JsonProperty("receivedDate")
    @JsonFormat(pattern="yyyy-mm-dd hh:mm:ss")
    private Date receivedDate;

    @JsonProperty("processingTime")
    private Long processingTime;

    @JsonProperty("processedDate")
    @JsonFormat(pattern="yyyy-mm-dd hh:mm:ss")
    private Date processedDate;

    @JsonProperty("processName")
    private String processName;
}
