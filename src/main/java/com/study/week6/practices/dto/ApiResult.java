package com.study.week6.practices.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter @Setter
@AllArgsConstructor
public class ApiResult<T> {

    private T data;
    private HttpStatus status;
    private String message;
    private LocalDateTime dateTime;

}
