package com.study.spring6.week6.v1.repository;

import com.study.spring6.week6.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductsRepository extends JpaRepository<Products, Long> {

}
