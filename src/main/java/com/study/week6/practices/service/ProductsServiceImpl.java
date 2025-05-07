package com.study.week6.practices.service;

import com.study.week6.practices.dto.CursorPageDTO;
import com.study.week6.practices.dto.ProductDTO;
import com.study.week6.practices.entity.Products;
import com.study.week6.practices.exception.ProductNotFoundException;
import com.study.week6.practices.repository.ProductsRepository;
import com.study.week6.practices.repository.specification.ProductsSpecification;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductsServiceImpl implements ProductsService {

    private final ProductsRepository productsRepository;
    public ProductsServiceImpl(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    @Override
    public List<Products> searchProducts(Integer priceGte, Integer priceLte, String category, String productName) {
        Specification<Products> spec = Specification
                .where(ProductsSpecification.priceGte(priceGte))
                .and(ProductsSpecification.priceLte(priceLte))
                .and(ProductsSpecification.categoryEquals(category))
                .and(ProductsSpecification.nameLike(productName));
        return productsRepository.findAll(spec);
    }

    @Override
    public Page<Products> getOffsetPages(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
        Page<Products> result = productsRepository.findAll(pageable);
        return result;
    }

    @Override
    public CursorPageDTO<Products> getCursorPages(Long cursor, int size) {
        // 처음 요청할 때, 가장 최신 데이터부터 조회하기 위해 초기값을 MAX 로 세팅
        if (cursor == null) {
            cursor = Long.MAX_VALUE;
        }

        // 주어진 cursor 값으로 데이터 조회
        Pageable pageable = PageRequest.of(0, size);
        List<Products> products = productsRepository.findByIdLessThanOrderByIdDesc(cursor, pageable);

        // 조회한 데이터의 마지막 객체의 id 를 조회하여 nextCursor 값으로 세팅
        Long nextCursor = products.isEmpty() ? null : products.get(products.size() - 1).getId();
        CursorPageDTO<Products> result = new CursorPageDTO<>(products, nextCursor);

        return result;
    }

    @Override
    public List<ProductDTO> findAll() {
        List<Products> products = productsRepository.findAll();
        List<ProductDTO> productDTOList = products.stream().map(Products::toDto).toList();
        return productDTOList;
    }

    @Override
    public ProductDTO findById(Long id) {
        Products products = productsRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(
                        "Product id [" + id + "] is not found", LocalDateTime.now(), HttpStatus.NOT_FOUND
                ));
        return products.toDto();
    }

    @Override
    public ProductDTO save(ProductDTO productDTO) {
        Products products = productsRepository.save(productDTO.toEntity());
        ProductDTO result = products.toDto();
        return result;
    }

    @Override
    public void update(Long id, ProductDTO productDTO) {
        Products products = productDTO.toEntity();
        products.setId(id);
        productsRepository.save(products);
    }

    @Override
    public void delete(Long id) {
        productsRepository.deleteById(id);
    }

}