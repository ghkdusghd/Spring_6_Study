package com.study.week6.practices.service;

import com.study.week6.practices.dto.CursorPageDTO;
import com.study.week6.practices.dto.ProductDTO;
import com.study.week6.practices.entity.Products;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductsService {

    List<ProductDTO> findAll();

    ProductDTO findById(Long id);

    void save(ProductDTO productDTO);

    void update(Long id, ProductDTO productDTO);

    void delete(Long id);

    // 페이지네이션 추가
    Page<Products> getOffsetPages(int page, int size);

    CursorPageDTO<Products> getCursorPages(Long cursor, int size);

    // 검색 기능 추가
    List<Products> searchProducts(Integer priceGte, Integer priceLte, String category, String productName);

}