package com.aresarmory.aresarmory.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;


public interface CategoryService {
    ResponseEntity<String> addNewCategory(Map<String, String> requestMap, String email);
}