package com.aresarmory.aresarmory.rest;

import com.aresarmory.aresarmory.POJO.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@RequestMapping(path = "/user")
public interface UserRest {

    @PostMapping(path = "/signup")
    public ResponseEntity<String> signUp(@RequestBody(required = true) Map<String, String> requestMap);

    @GetMapping(path = "/login")
    public ResponseEntity<User> login(@RequestBody(required = true) Map<String, String> requestMap);
}
