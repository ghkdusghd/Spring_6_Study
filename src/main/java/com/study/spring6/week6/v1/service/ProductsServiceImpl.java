package com.study.spring6.week6.v1.service;

import com.study.spring6.week6.entity.Products;
import com.study.spring6.week6.v1.repository.ProductsRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductsServiceImpl implements ProductsService {

    private final ProductsRepository productsRepository;
    public ProductsServiceImpl(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    @Override
    public List<Products> findAll() {
        return productsRepository.findAll();
    }

    @Override
    public Products findById(Long id) {
        return productsRepository.findById(id).orElse(null);
    }

    @Override
    public boolean save(Products products) {
        Products result = productsRepository.save(products);
        if (result == null) {
            return false;
        }
        return true;
    }

    @Override
    public boolean update(Products products) {
        Products result = productsRepository.save(products);
        if (result == null) {
            return false;
        }
        return true;
    }

    @Override
    public void delete(Long id) {
        productsRepository.deleteById(id);
    }

}
