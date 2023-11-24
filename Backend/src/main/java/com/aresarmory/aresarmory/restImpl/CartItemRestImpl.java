package com.aresarmory.aresarmory.restImpl;

import com.aresarmory.aresarmory.POJO.CartItem;
import com.aresarmory.aresarmory.constants.ArmoryConstants;
import com.aresarmory.aresarmory.rest.CartItemRest;
import com.aresarmory.aresarmory.service.CartItemService;
import com.aresarmory.aresarmory.utils.ArmoryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class CartItemRestImpl implements CartItemRest {
    @Autowired
    CartItemService cartItemService;

    @Override
    public ResponseEntity<List<CartItem>> getCartItem(Integer id) {
        try{
            return cartItemService.getCartItem(id);
        }catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> addCartItem(Map<String, String> requestMap) {
        try{
            return cartItemService.addCartItem(requestMap);
        }catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return ArmoryUtils.getResponseEntity(ArmoryConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> deleteCartItem(Integer id, Integer cartId) {
        try{
            return cartItemService.deleteCartItem(id, cartId);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return ArmoryUtils.getResponseEntity(ArmoryConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
