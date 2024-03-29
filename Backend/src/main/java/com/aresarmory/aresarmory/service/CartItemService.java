package com.aresarmory.aresarmory.service;

import com.aresarmory.aresarmory.POJO.CartItem;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface CartItemService {

    ResponseEntity<String> addCartItem(Map<String, String> requestMap);

    ResponseEntity<String> deleteCartItem(Integer id, Integer cartId);

    ResponseEntity<List<CartItem>> getCartItem(Integer id);
}
