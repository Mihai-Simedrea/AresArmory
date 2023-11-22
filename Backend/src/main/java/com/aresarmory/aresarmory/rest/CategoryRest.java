package com.aresarmory.aresarmory.rest;

import com.aresarmory.aresarmory.POJO.Category;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping(path = "/category")
public interface CategoryRest {
    @PostMapping(path = "/add")
    ResponseEntity<String> addNewCategory(@RequestBody(required = true) Map<String, String> requestMap);

    @GetMapping(path = "/get")
    ResponseEntity<List<Category>> getAllCategory();

    @PutMapping(path = "/update")
    ResponseEntity<String> updateCategory(@RequestBody(required = true) Map<String, String> requestMap);

    @DeleteMapping(path = "/delete/{id}")
    ResponseEntity<String> deleteCategory(@PathVariable Integer id);
}
