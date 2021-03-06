package by.vlasov.messaging.domain;

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
    private String osName;

    @SerializedName("ip")
    private String ipAddress;

    @SerializedName("browserName")
    private String browserName;

    @SerializedName("browserVersion")
    private String browserVersion;

    @SerializedName("dataSize")
    private Long contentLength;
}
