package com.study.week6.practices.entity;

import com.study.week6.practices.dto.ProductDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String category;

    private String productName;

    private int price;

    private int stock;

    private String description;

    public Products() {}

    public Products(Long id, String category, String productName, int price, int stock, String description) {
        this.id = id;
        this.category = category;
        this.productName = productName;
        this.price = price;
        this.stock = stock;
        this.description = description;
    }

    public ProductDTO toDto() {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setCategory(this.category);
        productDTO.setProductName(this.productName);
        productDTO.setPrice(this.price);
        productDTO.setStock(this.stock);
        return productDTO;
    }

}
