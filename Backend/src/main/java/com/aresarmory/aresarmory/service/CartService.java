package com.aresarmory.aresarmory.service;

import com.aresarmory.aresarmory.POJO.Cart;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;

public interface CartService {
    ResponseEntity<Cart> getCartByUser(String email);
}
