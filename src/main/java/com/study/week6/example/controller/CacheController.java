package com.study.week6.example.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.week6.practices.dto.ProductDTO;
import com.study.week6.practices.service.ProductsService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
public class CacheController {

    private final ProductsService productsService;
    public CacheController(ProductsService productsService) {
        this.productsService = productsService;
    }
    private final ObjectMapper objectMapper = new ObjectMapper();

    private String generateSHA256ETag(String data) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(data.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(hash);
    }

    @GetMapping("/cache")
    public ResponseEntity<?> getAllProducts(HttpHeaders headers) {
        try {
            // 데이터 조회
            List<ProductDTO> products = productsService.findAll();

            // ETag 생성 (SHA-256 해싱)
            String data = objectMapper.writeValueAsString(products);
            String etag = "\"" + generateSHA256ETag(data) + "\"";

            // If-None-Match 헤더와 비교하여 304 응답
            if (etag.equals(headers.getFirst(HttpHeaders.IF_NONE_MATCH))) {
                return ResponseEntity
                        .status(HttpStatus.NOT_MODIFIED)
                        .contentType(MediaType.APPLICATION_JSON)
                        .eTag(etag)
                        .cacheControl(CacheControl.maxAge(10, TimeUnit.MINUTES))
                        .build();
            }

            // 새로운 데이터를 반환
            return ResponseEntity.status(200)
                    .eTag(etag)
                    .cacheControl(CacheControl.maxAge(10, TimeUnit.MINUTES))
                    .body(products);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }

}
