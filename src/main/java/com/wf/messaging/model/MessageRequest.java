package com.wf.messaging.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MessageRequest implements Serializable {
    @NotNull
    private String messageType;
    @NotNull
    private String messageFrom;
    @NotNull
    private String messageContent;
}
