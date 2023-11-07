package com.aresarmory.aresarmory.service;

import com.aresarmory.aresarmory.POJO.User;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface UserService {

    ResponseEntity<String> signUp(Map<String, String> requestMap);

    ResponseEntity<String> login(Map<String, String> requestMap);
}
