package com.aresarmory.aresarmory.rest;

import com.aresarmory.aresarmory.POJO.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequestMapping(path = "/user")
public interface UserRest {

    @PostMapping(path = "/signup")
    public ResponseEntity<String> signUp(@RequestBody(required = true) Map<String, String> requestMap);

    @PostMapping(path = "/login")
    public ResponseEntity<String> login(@RequestBody(required = true) Map<String, String> requestMap);

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Integer id);
}
