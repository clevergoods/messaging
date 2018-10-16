package com.wf.messaging.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class MessageResponse {
    private HttpStatus status;
}
