package com.wf.messaging.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Metainformation implements Serializable {

    @SerializedName("os")
    @JsonProperty("os")
    private String osName;

    @SerializedName("ip")
    @JsonProperty("ip")
    private String ipAddress;

    @SerializedName("browserName")
    @JsonProperty("browserName")
    private String browserName;

    @SerializedName("browserVersion")
    @JsonProperty("browserVersion")
    private String browserVersion;

    @SerializedName("dataSize")
    @JsonProperty("dataSize")
    private Long contentLength;
}
