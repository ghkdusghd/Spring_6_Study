package com.study.spring6.week6.v1.service;

import com.study.spring6.week6.entity.Products;

import java.util.List;

public interface ProductsService {

    List<Products> findAll();

    Products findById(Long id);

    boolean save(Products products);

    boolean update(Products products);

    void delete(Long id);

}
