package com.aresarmory.aresarmory.dao;

import com.aresarmory.aresarmory.POJO.Cart;
import com.aresarmory.aresarmory.POJO.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface CartDao extends JpaRepository<Cart, Integer> {
    Cart findByUser(@Param("id") Integer id);
}
