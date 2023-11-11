package com.aresarmory.aresarmory.serviceImpl;

import com.aresarmory.aresarmory.POJO.Category;
import com.aresarmory.aresarmory.POJO.User;
import com.aresarmory.aresarmory.constants.ArmoryConstants;
import com.aresarmory.aresarmory.dao.CategoryDao;
import com.aresarmory.aresarmory.dao.ProductDao;
import com.aresarmory.aresarmory.dao.UserDao;
import com.aresarmory.aresarmory.service.CategoryService;
import com.aresarmory.aresarmory.utils.ArmoryUtils;
import com.aresarmory.aresarmory.wrapper.ProductWrapper;
import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CategoryServiceImpl  implements CategoryService {
    @Autowired
    CategoryDao categoryDao;

    @Override
    public ResponseEntity<String> addNewCategory(Map<String, String> requestMap)
    {
        try{
            if(validateCategoryMap(requestMap, false)){
                categoryDao.save(getCategoryFromMap(requestMap, false));
                return ArmoryUtils.getResponseEntity("Category Added Successfully", HttpStatus.OK);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }

        return ArmoryUtils.getResponseEntity(ArmoryConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private boolean validateCategoryMap(Map<String, String> requestMap, boolean validateId) {
        if(requestMap.containsKey("name")){
            if(requestMap.containsKey("id") && validateId){
                return true;
            }else if(!validateId){
                return true;
            }
        }
        return false;
    }

    private Category getCategoryFromMap(Map<String, String> requestMap, Boolean isAdd){
        Category category = new Category();
        if(isAdd){
            category.setId(Integer.parseInt(requestMap.get("id")));
        }
        category.setName((requestMap.get("name")));
        return category;
    }

    @Override
    public ResponseEntity<List<Category>> getAllCategory(String filterValue) {
        try{
            if(!Strings.isNullOrEmpty(filterValue) && filterValue.equalsIgnoreCase("true")) {
                return new ResponseEntity<List<Category>>(categoryDao.getAllCategory(), HttpStatus.OK);
            }
            return new ResponseEntity<>(categoryDao.findAll(), HttpStatus.OK);
        }catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return new ResponseEntity<List<Category>>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> updateCategory(Map<String, String> requestMap) {
        try{
            if(validateCategoryMap(requestMap, true)) {
                Optional optional = categoryDao.findById(Integer.parseInt(requestMap.get("id")));
                if(optional.isPresent()){
                    categoryDao.save(getCategoryFromMap(requestMap, true));
                    return ArmoryUtils.getResponseEntity("Category Updated Successfully", HttpStatus.OK);
                }
                else{
                    return ArmoryUtils.getResponseEntity("Category id does not exist", HttpStatus.OK);
                }
            }
            return ArmoryUtils.getResponseEntity(ArmoryConstants.INVALID_DATA, HttpStatus.BAD_REQUEST);
        }catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return ArmoryUtils.getResponseEntity(ArmoryConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> deleteCategory(Integer id) {
        try{
            Optional optional = categoryDao.findById(id);
            if(optional.isPresent())
            {
                categoryDao.deleteById(id);
                return ArmoryUtils.getResponseEntity("Successfully Deleted Category", HttpStatus.OK);
            }
            else
                return ArmoryUtils.getResponseEntity("Category id does not exist", HttpStatus.OK);
        }catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return ArmoryUtils.getResponseEntity(ArmoryConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
