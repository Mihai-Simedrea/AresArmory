package com.aresarmory.aresarmory.serviceImpl;

import com.aresarmory.aresarmory.POJO.Cart;
import com.aresarmory.aresarmory.POJO.User;
import com.aresarmory.aresarmory.constants.ArmoryConstants;
import com.aresarmory.aresarmory.dao.CartDao;
import com.aresarmory.aresarmory.dao.UserDao;
import com.aresarmory.aresarmory.service.CartService;
import com.aresarmory.aresarmory.utils.ArmoryUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    CartDao cartDao;
    @Autowired
    UserDao userDao;
    @Override
    public ResponseEntity<String> getCartByUser(String email) {
        try{
            User user = userDao.findByEmail(email);
            Cart cart = cartDao.getByUser(user.getId());
            Map<String, String> cartInfo = new HashMap<>();
            ObjectMapper objectMapper = new ObjectMapper();
            if(Objects.isNull(cart))
            {
                Cart newCart = new Cart();
                newCart.setUser(user);
                cartDao.save(newCart);
                cartInfo.put("id", newCart.getId().toString());
                return new ResponseEntity<>(objectMapper.writeValueAsString(cartInfo), HttpStatus.OK);
            }
            else
            {
                cartInfo.put("id", cart.getId().toString());
                return new ResponseEntity<>(objectMapper.writeValueAsString(cartInfo), HttpStatus.OK);
            }
        }catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return ArmoryUtils.getResponseEntity(ArmoryConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
