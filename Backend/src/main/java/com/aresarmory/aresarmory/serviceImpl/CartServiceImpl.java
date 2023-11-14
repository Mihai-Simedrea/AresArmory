package com.aresarmory.aresarmory.serviceImpl;

import com.aresarmory.aresarmory.POJO.Cart;
import com.aresarmory.aresarmory.POJO.User;
import com.aresarmory.aresarmory.dao.CartDao;
import com.aresarmory.aresarmory.dao.UserDao;
import com.aresarmory.aresarmory.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    CartDao cartDao;
    @Autowired
    UserDao userDao;
    @Override
    public ResponseEntity<Cart> getCartByUser(String email) {
        try{
            User user = userDao.findByEmail(email);
            Cart cart = cartDao.findByUser(user.getId());
            if(Objects.isNull(cart))
            {
                Cart newCart = new Cart();
                newCart.setUser(user);
                cartDao.save(newCart);
                return new ResponseEntity<>(newCart, HttpStatus.OK);
            }
            else
                return new ResponseEntity<>(cart, HttpStatus.OK);
        }catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new Cart(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
