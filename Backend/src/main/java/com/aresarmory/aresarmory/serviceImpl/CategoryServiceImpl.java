package com.aresarmory.aresarmory.serviceImpl;

import com.aresarmory.aresarmory.POJO.Category;
import com.aresarmory.aresarmory.POJO.User;
import com.aresarmory.aresarmory.constants.ArmoryConstants;
import com.aresarmory.aresarmory.dao.CategoryDao;
import com.aresarmory.aresarmory.dao.UserDao;
import com.aresarmory.aresarmory.service.CategoryService;
import com.aresarmory.aresarmory.utils.ArmoryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CategoryServiceImpl  implements CategoryService {
    @Autowired
    CategoryDao categoryDao;
    @Autowired
    UserDao userDao;

    @Override
    public ResponseEntity<String> addNewCategory(Map<String, String> requestMap)
    {
        try{
            User user = userDao.findByEmailId(requestMap.get("email"));
            if(user.getRole().equals("admin"))
            {
                if(validateCategoryMap(requestMap, false)){
                    categoryDao.save(getCategoryFromMap(requestMap, false));
                    return ArmoryUtils.getResponseEntity("Category Added Successfully", HttpStatus.OK);
                }
            }
            else
                return ArmoryUtils.getResponseEntity(ArmoryConstants.UNAUTHORIZED_ACCESS, HttpStatus.INTERNAL_SERVER_ERROR);
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
}
