package com.study.week6.practices.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter @Setter
public class ProductNotFoundException extends RuntimeException {

    private String message;
    private LocalDateTime localDateTime;
    private HttpStatus httpStatus;

    public ProductNotFoundException(String message, LocalDateTime localDateTime, HttpStatus httpStatus) {
        this.message = message;
        this.localDateTime = localDateTime;
        this.httpStatus = httpStatus;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
