package com.aresarmory.aresarmory.restImpl;

import com.aresarmory.aresarmory.constants.ArmoryConstants;
import com.aresarmory.aresarmory.rest.UserRest;
import com.aresarmory.aresarmory.service.UserService;
import com.aresarmory.aresarmory.utils.ArmoryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class UserRestImpl implements UserRest {

    @Autowired
    UserService userService;

    @Override
    public ResponseEntity<String> signUp(Map<String, String> requestMap) {
        try {
            return userService.signUp(requestMap);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return ArmoryUtils.getResponseEntity(ArmoryConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> login(Map<String, String> requestMap) {
        try {
            return userService.login(requestMap);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return ArmoryUtils.getResponseEntity(ArmoryConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> deleteUser(Integer id) {
        try{
            return userService.deleteUser(id);
        }catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return ArmoryUtils.getResponseEntity(ArmoryConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
