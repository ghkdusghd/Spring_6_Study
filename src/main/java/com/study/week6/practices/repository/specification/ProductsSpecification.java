package com.study.week6.practices.repository.specification;

import com.study.week6.practices.entity.Products;
import org.springframework.data.jpa.domain.Specification;

public class ProductsSpecification {

    // gte 쿼리 (이상)
    public static Specification<Products> priceGte(Integer minPrice) {
        return (root, query, cb) ->
                minPrice == null ? null : cb.greaterThanOrEqualTo(root.get("price"), minPrice);
    }

    // lte 쿼리 (이하)
    public static Specification<Products> priceLte(Integer maxPrice) {
        return (root, query, cb) ->
                maxPrice == null ? null : cb.lessThanOrEqualTo(root.get("price"), maxPrice);
    }

    // equal 쿼리 (일치)
    public static Specification<Products> categoryEquals(String category) {
        return (root, query, cb) ->
                category == null ? null : cb.equal(root.get("category"), category);
    }

    // like 쿼리 (포함)
    public static Specification<Products> nameLike(String keyword) {
        return (root, query, cb) ->
                keyword == null ? null : cb.like(cb.lower(root.get("name")), "%" + keyword.toLowerCase() + "%");
    }

}
