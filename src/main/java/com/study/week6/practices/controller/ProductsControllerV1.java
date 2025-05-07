package com.study.week6.practices.controller;

import com.study.week6.practices.dto.ApiResponse;
import com.study.week6.practices.dto.ProductDTO;
import com.study.week6.practices.service.ProductsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class ProductsControllerV1 {

    private final ProductsService productsService;
    public ProductsControllerV1(ProductsService productsService) {
        this.productsService = productsService;
    }

    @GetMapping("/products")
    public ResponseEntity<?> getAllProducts() {
        return ApiResponse.OK(productsService.findAll());
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<?> getProductById(@PathVariable Long id) {
        return ApiResponse.OK(productsService.findById(id));
    }

    @PostMapping("/products")
    public ResponseEntity<?> createProduct(@RequestBody ProductDTO productDTO) {
        return ApiResponse.CREATED(productsService.save(productDTO));
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable Long id, @RequestBody ProductDTO productDTO) {
        productsService.update(id, productDTO);
        return ApiResponse.NO_CONTENT();
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        productsService.delete(id);
        return ApiResponse.NO_CONTENT();
    }

//    // GlobalExceptionHandler 클래스로 이동
//    @ExceptionHandler(ProductNotFoundException.class)
//    public ResponseEntity<?> handleProductNotFound(ProductNotFoundException ex) {
//        ErrorResponse response = new ErrorResponse(ex.getMessage(), ex.getLocalDateTime(), ex.getHttpStatus());
//        return ResponseEntity.status(ex.getHttpStatus()).body(response);
//    }

}
