package com.aresarmory.aresarmory.dao;

import com.aresarmory.aresarmory.POJO.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CartItemDao extends JpaRepository<CartItem, Integer> {
    List<CartItem> getCartItemByCart(@Param("id") Integer id);
}
