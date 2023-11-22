package com.aresarmory.aresarmory.rest;

import com.aresarmory.aresarmory.POJO.Cart;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequestMapping(path = "/cart")
public interface CartRest {
    @GetMapping(path = "/get/{email}")
    ResponseEntity<String> getCartByUser(@PathVariable String email);
}
