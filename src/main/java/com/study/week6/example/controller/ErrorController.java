package com.study.week6.example.controller;

import com.study.week6.practices.exception.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class ErrorController {

    @GetMapping("/errorTest")
    public ResponseEntity<?> errorTest() {
        throw new ProductNotFoundException("Error Test : Product is not found", LocalDateTime.now(), HttpStatus.NOT_FOUND);
    }

}
