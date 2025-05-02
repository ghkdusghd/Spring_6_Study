package com.study.week6.practices.controller;

import com.study.week6.practices.dto.ProductDTO;
import com.study.week6.practices.service.ProductsService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/v1")
public class ProductsControllerV1 {

    private final ProductsService productsService;
    public ProductsControllerV1(ProductsService productsService) {
        this.productsService = productsService;
    }

    @ResponseBody
    @GetMapping("/products")
    public ResponseEntity<?> getAllProducts() {
        return ResponseEntity.status(200).body(productsService.findAll());
    }

    @ResponseBody
    @GetMapping("/products/{id}")
    public ResponseEntity<?> getProductById(@PathVariable Long id) {
        return ResponseEntity.status(200).body(productsService.findById(id));
    }

    @ResponseBody
    @PostMapping("/products")
    public ResponseEntity<?> createProduct(@RequestBody ProductDTO productDTO) {
        productsService.save(productDTO);
        return ResponseEntity.status(201).body(productDTO);
    }

    @ResponseBody
    @PutMapping("/products/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable Long id, @RequestBody ProductDTO productDTO) {
        productsService.update(id, productDTO);
        return ResponseEntity.status(200).build();
    }

    @ResponseBody
    @DeleteMapping("/products/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        productsService.delete(id);
        return ResponseEntity.status(200).build();
    }

//    // GlobalExceptionHandler 클래스로 이동
//    @ExceptionHandler(ProductNotFoundException.class)
//    public ResponseEntity<?> handleProductNotFound(ProductNotFoundException ex) {
//        ErrorResponse response = new ErrorResponse(ex.getMessage(), ex.getLocalDateTime(), ex.getHttpStatus());
//        return ResponseEntity.status(ex.getHttpStatus()).body(response);
//    }

}
