package com.study.week6.practices.controller;

import com.study.week6.practices.entity.Products;
import com.study.week6.practices.service.ProductsService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v2")
public class ProductsControllerV2 {

    private final ProductsService productsService;
    public ProductsControllerV2(ProductsService productsService) {
        this.productsService = productsService;
    }

    @GetMapping("/products/offset")
    public ResponseEntity<?> getOffsetPages(@RequestParam int page, @RequestParam int size) {
        Page<Products> productsPages = productsService.getOffsetPages(page, size);
        return ResponseEntity.status(200).body(productsPages);
    }

    @GetMapping("/products/cursor")
    public ResponseEntity<?> getCursorPages(@RequestParam Long cursor, @RequestParam int size) {
        return ResponseEntity.status(200).body(productsService.getCursorPages(cursor, size));
    }

    @GetMapping("/products/specification")
    public ResponseEntity<?> searchProducts(@RequestParam(required = false) Integer price_gte,
                                            @RequestParam(required = false) Integer price_lte,
                                            @RequestParam(required = false) String category,
                                            @RequestParam(required = false) String productName) {
        List<Products> result = productsService.searchProducts(price_gte, price_lte, category, productName);
        return ResponseEntity.status(200).body(result);
    }

}
