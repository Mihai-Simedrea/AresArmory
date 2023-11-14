package com.aresarmory.aresarmory.dao;

import com.aresarmory.aresarmory.POJO.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemDao extends JpaRepository<CartItem, Integer> {
}
