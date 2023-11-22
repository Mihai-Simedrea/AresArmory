package com.aresarmory.aresarmory.service;

import com.aresarmory.aresarmory.POJO.Category;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;


public interface CategoryService {
    ResponseEntity<String> addNewCategory(Map<String, String> requestMap);

    ResponseEntity<List<Category>> getAllCategory();

    ResponseEntity<String> updateCategory(Map<String, String> requestMap);

    ResponseEntity<String> deleteCategory(Integer id);
}
