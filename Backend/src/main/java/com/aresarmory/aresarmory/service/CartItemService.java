package com.aresarmory.aresarmory.service;

import com.aresarmory.aresarmory.POJO.CartItem;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface CartItemService {
    ResponseEntity<List<CartItem>> GetCartItemByCart(Integer id);

    ResponseEntity<String> addCartItem(Map<String, String> requestMap);

    ResponseEntity<String> deleteCartItem(Integer id);
}
