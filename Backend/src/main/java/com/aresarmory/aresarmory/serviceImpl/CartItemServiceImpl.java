package com.aresarmory.aresarmory.serviceImpl;

import com.aresarmory.aresarmory.POJO.Cart;
import com.aresarmory.aresarmory.POJO.CartItem;
import com.aresarmory.aresarmory.constants.ArmoryConstants;
import com.aresarmory.aresarmory.dao.CartDao;
import com.aresarmory.aresarmory.dao.CartItemDao;
import com.aresarmory.aresarmory.dao.ProductDao;
import com.aresarmory.aresarmory.service.CartItemService;
import com.aresarmory.aresarmory.utils.ArmoryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CartItemServiceImpl implements CartItemService {
    @Autowired
    CartDao cartDao;

    @Autowired
    CartItemDao cartItemDao;

    @Autowired
    ProductDao productDao;

    @Override
    public ResponseEntity<String> addCartItem(Map<String, String> requestMap) {
        try{
            CartItem newCartItem = new CartItem();
            newCartItem.setCart(cartDao.getById(Integer.parseInt(requestMap.get("cart_id"))));
            newCartItem.setProduct(productDao.getById(Integer.parseInt(requestMap.get("product_id"))));
            cartItemDao.save(newCartItem);
            return ArmoryUtils.getResponseEntity("Successfully Added Product to Cart", HttpStatus.OK);
        }catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return ArmoryUtils.getResponseEntity(ArmoryConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> deleteCartItem(Integer id, Integer cartId) {
        try {
            Cart cart = cartDao.getById(cartId);
            List<CartItem> cartItems = cartItemDao.getCartItemByCart(cart.getId());

            for (CartItem cartItem : cartItems) {
                if (cartItem.getProduct().getId().equals(id)) {
                    cartItemDao.delete(cartItem);
                    return ArmoryUtils.getResponseEntity("Successfully Removed Product from Cart", HttpStatus.OK);
                }
            }

            return ArmoryUtils.getResponseEntity("Product not in Cart", HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return ArmoryUtils.getResponseEntity(ArmoryConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @Override
    public ResponseEntity<List<CartItem>> getCartItem(Integer id) {
        try{
            Cart cart = cartDao.getById(id);
            return new ResponseEntity<>(cartItemDao.getCartItemByCart(cart.getId()), HttpStatus.OK);
        }catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
