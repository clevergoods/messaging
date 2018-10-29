package com.wf.messaging.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class ReceivedMessageResponse {
    @SerializedName("status")
    @JsonProperty("status")
    private HttpStatus status;

    @SerializedName("uuid")
    @JsonProperty("uuid")
    private String uuid;
}
