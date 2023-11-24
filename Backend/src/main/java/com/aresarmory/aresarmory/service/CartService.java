package com.aresarmory.aresarmory.service;

import com.aresarmory.aresarmory.POJO.Cart;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface CartService {
    ResponseEntity<String> getCartByUser(String email);
    ResponseEntity<String> deleteCartByUser(String email);
}
