package com.aresarmory.aresarmory.restImpl;

import com.aresarmory.aresarmory.POJO.Cart;
import com.aresarmory.aresarmory.constants.ArmoryConstants;
import com.aresarmory.aresarmory.rest.CartRest;
import com.aresarmory.aresarmory.service.CartService;
import com.aresarmory.aresarmory.utils.ArmoryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CartRestImpl implements CartRest {
    @Autowired
    CartService cartService;
    @Override
    public ResponseEntity<Cart> getCartByUser(String email) {
        try{
            return cartService.getCartByUser(email);
        }catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new Cart(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
