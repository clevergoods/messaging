package com.wf.messaging.model;

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
    @SerializedName("status")
    @JsonProperty("status")
    private HttpStatus status;

    @SerializedName("uuid")
    @JsonProperty("uuid")
    private String uuid;

    @SerializedName("messageType")
    @JsonProperty("messageType")
    private String messageType;

    @SerializedName("receivedDate")
    @JsonProperty("receivedDate")
    @JsonFormat(pattern="yyyy-mm-dd hh:mm:ss")
    private Date receivedDate;

    @SerializedName("processingTime")
    @JsonProperty("processingTime")
    private Long processingTime;

    @SerializedName("processedDate")
    @JsonProperty("processedDate")
    @JsonFormat(pattern="yyyy-mm-dd hh:mm:ss")
    private Date processedDate;

    @SerializedName("processName")
    @JsonProperty("processName")
    private String processName;
}
