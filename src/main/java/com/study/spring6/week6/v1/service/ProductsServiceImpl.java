package com.study.spring6.week6.v1.service;

import com.study.spring6.week6.entity.Products;
import com.study.spring6.week6.v1.repository.ProductsRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
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
        List<Products> products = productsRepository.findAll();
        return products;
    }

    @Override
    @Cacheable(value = "products", key = "#id")
    public Products findById(Long id) {
        return productsRepository.findById(id).orElse(null);
    }

    @Override
    @CacheEvict(value = "products", key = "#products.id")
    public void save(Products products) {
        productsRepository.save(products);
    }

    @Override
    @CacheEvict(value = "products", key = "#products.id")
    public void update(Products products) {
        productsRepository.save(products);
    }

    @Override
    @CacheEvict(value = "products", key = "#id")
    public void delete(Long id) {
        productsRepository.deleteById(id);
    }

}
