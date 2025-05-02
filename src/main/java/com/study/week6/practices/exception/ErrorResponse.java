package com.study.week6.practices.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter @Setter
public class ErrorResponse {

    private String message;
    private LocalDateTime timestamp;
    private HttpStatus status;

    public ErrorResponse(String message, LocalDateTime timestamp, HttpStatus status) {
        this.message = message;
        this.timestamp = timestamp;
        this.status = status;
    }

}
