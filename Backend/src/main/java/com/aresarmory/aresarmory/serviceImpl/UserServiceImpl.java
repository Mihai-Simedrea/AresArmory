package com.aresarmory.aresarmory.serviceImpl;

import com.aresarmory.aresarmory.POJO.User;
import com.aresarmory.aresarmory.constants.ArmoryConstants;
import com.aresarmory.aresarmory.dao.UserDao;
import com.aresarmory.aresarmory.service.UserService;
import com.aresarmory.aresarmory.utils.ArmoryUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;

    @Override
    public ResponseEntity<String> signUp(Map<String, String> requestMap) {
        try {
            if (validateSignUpMap(requestMap)) {
                User user = userDao.findByEmailId(requestMap.get("email"));
                if (Objects.isNull(user)) {
                    userDao.save(getUserFromMap(requestMap));
                    return ArmoryUtils.getResponseEntity("Successfully Registered", HttpStatus.OK);
                } else {
                    return ArmoryUtils.getResponseEntity("Email already exists", HttpStatus.BAD_REQUEST);
                }
            } else
                return ArmoryUtils.getResponseEntity(ArmoryConstants.INVALID_DATA, HttpStatus.BAD_REQUEST);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return ArmoryUtils.getResponseEntity(ArmoryConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> login(Map<String, String> requestMap) {
        try {
            if (validateLoginMap(requestMap)) {
                User user = userDao.findByEmailId(requestMap.get("email"));
                if (!Objects.isNull(user) && user.getEmail().equals(requestMap.get("email")) &&
                user.getPassword().equals(requestMap.get("password"))) {
                    ObjectMapper objectMapper = new ObjectMapper();
                    String jsonString = objectMapper.writeValueAsString(getUserInfo(user));
                    return ResponseEntity.ok().body(jsonString);
                } else
                    return ArmoryUtils.getResponseEntity("Account not found", HttpStatus.BAD_REQUEST);
            } else
                return ArmoryUtils.getResponseEntity(ArmoryConstants.INVALID_DATA, HttpStatus.BAD_REQUEST);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return ArmoryUtils.getResponseEntity(ArmoryConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    private Map<String, String> getUserInfo(User user) {
        Map<String, String> userInfo = new HashMap<>();
        userInfo.put("username", user.getName());
        userInfo.put("email", user.getEmail());
        userInfo.put("contactNumber", user.getContactNumber());
        userInfo.put("role", user.getRole());
        userInfo.put("id", user.getId().toString());
        return userInfo;
    }

    @Override
    public ResponseEntity<String> deleteUser(Integer id) {
        try{
            Optional optional = userDao.findById(id);
            if(optional.isPresent())
            {
                userDao.deleteById(id);
                return ArmoryUtils.getResponseEntity("User Deleted Successfully", HttpStatus.OK);
            }
            return ArmoryUtils.getResponseEntity("User id does not exist", HttpStatus.OK);
        }catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return ArmoryUtils.getResponseEntity(ArmoryConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private boolean validateSignUpMap(Map<String, String> requestMap) {
        return requestMap.containsKey("name") && requestMap.containsKey("contactNumber")
                && requestMap.containsKey("email") && requestMap.containsKey("password");
    }

    private boolean validateLoginMap(Map<String, String> requestMap) {
        return requestMap.containsKey("email") && requestMap.containsKey("password");
    }

    private User getUserFromMap(Map<String, String> requestMap) {
        User user = new User();
        user.setName(requestMap.get("name"));
        user.setContactNumber(requestMap.get("contactNumber"));
        user.setEmail(requestMap.get("email"));
        user.setPassword(requestMap.get("password"));
        user.setStatus("false");
        user.setRole("user");
        return user;
    }
}
