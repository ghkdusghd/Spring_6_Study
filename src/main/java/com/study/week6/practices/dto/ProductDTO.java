package com.study.week6.practices.dto;

import com.study.week6.practices.entity.Products;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ProductDTO {

    private String category;

    private String productName;

    private int price;

    private int stock;

    public Products toEntity() {
        Products products = new Products();
        products.setCategory(this.category);
        products.setProductName(this.productName);
        products.setPrice(this.price);
        products.setStock(this.stock);
        return products;
    }

}
