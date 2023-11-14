package com.aresarmory.aresarmory.rest;

import com.aresarmory.aresarmory.POJO.Cart;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(path = "/cart")
public interface CartRest {
    @GetMapping(path = "/get")
    public ResponseEntity<Cart> getCartByUser(@RequestParam String email);
}
