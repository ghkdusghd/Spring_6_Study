package com.study.spring6.week6.v1.controller;

import com.study.spring6.week6.entity.Products;
import com.study.spring6.week6.v1.service.ProductsService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ProductsController {

    private final ProductsService productsService;
    public ProductsController(ProductsService productsService) {
        this.productsService = productsService;
    }

    @GetMapping("/products")
    public ResponseEntity<List<Products>> getAllProducts() {
        List<Products> products = productsService.findAll();
        return ResponseEntity.ok().body(products);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Products> getProductById(@PathVariable Long id) {
        Products product;
        try {
            product = productsService.findById(id);
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.status(200).body(product);
    }

    @PostMapping("/products")
    public ResponseEntity<Void> insertProduct(@RequestBody Products products) {
        try {
            productsService.save(products);
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(409).build();
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
        return ResponseEntity.status(201).build();
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<Void> updateProduct(@PathVariable Long id, @RequestBody Products products) {
        try {
            products.setId(id);
            productsService.save(products);
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(409).build();
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productsService.delete(id);
        return ResponseEntity.status(200).build();
    }

}
