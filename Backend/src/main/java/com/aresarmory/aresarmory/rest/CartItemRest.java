package com.aresarmory.aresarmory.rest;

import com.aresarmory.aresarmory.POJO.CartItem;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping("/cart_item")
public interface CartItemRest {
    @GetMapping(path = "/get")
    ResponseEntity<List<CartItem>> getCartItemByCart(@RequestParam Integer id);

    @PostMapping(path = "/add")
    ResponseEntity<String> addCartItem(@RequestBody Map<String, String> requestMap);

    @DeleteMapping(path = "/delete/{id}")
    ResponseEntity<String> deleteCartItem(@PathVariable Integer id);
}
