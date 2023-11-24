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

import java.util.Map;

@RestController
public class CartRestImpl implements CartRest {
    @Autowired
    CartService cartService;
    @Override
    public ResponseEntity<String> getCartByUser(String email) {
        try{
            return cartService.getCartByUser(email);
        }catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return ArmoryUtils.getResponseEntity(ArmoryConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> deleteCartByUser(String email) {
        try {
            return cartService.deleteCartByUser(email);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return ArmoryUtils.getResponseEntity(ArmoryConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
