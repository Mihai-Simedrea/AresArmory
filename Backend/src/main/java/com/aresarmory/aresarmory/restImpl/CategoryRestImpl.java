package com.aresarmory.aresarmory.restImpl;

import com.aresarmory.aresarmory.constants.ArmoryConstants;
import com.aresarmory.aresarmory.rest.CategoryRest;
import com.aresarmory.aresarmory.service.CategoryService;
import com.aresarmory.aresarmory.utils.ArmoryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class CategoryRestImpl implements CategoryRest {
    @Autowired
    CategoryService categoryService;

    @Override
    public ResponseEntity<String> addNewCategory(Map<String, String> requestMap, String email){
        try
        {
            return categoryService.addNewCategory(requestMap, email);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return ArmoryUtils.getResponseEntity(ArmoryConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}