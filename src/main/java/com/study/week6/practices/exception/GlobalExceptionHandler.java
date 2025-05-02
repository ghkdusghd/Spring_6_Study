package com.study.week6.practices.exception;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Hidden
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<?> handleProductNotFound(ProductNotFoundException ex) {
        ErrorResponse response = new ErrorResponse(ex.getMessage(), ex.getLocalDateTime(), ex.getHttpStatus());
        return ResponseEntity.status(ex.getHttpStatus()).body(response);
    }

}
