package com.aresarmory.aresarmory.dao;

import com.aresarmory.aresarmory.POJO.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface CartDao extends JpaRepository<Cart, Integer> {
    Cart getByUser(@Param("id") Integer id);
    Cart getById(@Param("id") Integer id);
}
