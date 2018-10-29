package com.wf.messaging.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import com.wf.messaging.domain.Message;
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
    @SerializedName("status")
    @JsonProperty("status")
    private HttpStatus status;

    @SerializedName("message")
    @JsonProperty("message")
    private Message message;
}