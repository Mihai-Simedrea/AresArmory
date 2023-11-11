package com.aresarmory.aresarmory.dao;

import com.aresarmory.aresarmory.POJO.Product;
import com.aresarmory.aresarmory.wrapper.ProductWrapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductDao extends JpaRepository<Product, Integer> {
    List<ProductWrapper> getAllProduct();

    List<ProductWrapper> getProductByCategory(@Param("id") Integer id);
}
