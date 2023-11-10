package com.aresarmory.aresarmory.serviceImpl;

import com.aresarmory.aresarmory.POJO.Category;
import com.aresarmory.aresarmory.POJO.Product;
import com.aresarmory.aresarmory.POJO.User;
import com.aresarmory.aresarmory.constants.ArmoryConstants;
import com.aresarmory.aresarmory.dao.ProductDao;
import com.aresarmory.aresarmory.dao.UserDao;
import com.aresarmory.aresarmory.service.ProductService;
import com.aresarmory.aresarmory.utils.ArmoryUtils;
import com.aresarmory.aresarmory.wrapper.ProductWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductDao productDao;
    @Autowired
    UserDao userDao;
    @Override
    public ResponseEntity<String> addNewProduct(Map<String, String> requestMap) {
        try{
            if(validateProductMap(requestMap, false)){
                productDao.save(getProductFromMap(requestMap, false));
                return ArmoryUtils.getResponseEntity("Product Added Successfully", HttpStatus.OK);
            }
            return ArmoryUtils.getResponseEntity(ArmoryConstants.INVALID_DATA, HttpStatus.BAD_REQUEST);

        }catch (Exception ex) {
            ex.printStackTrace();
        }
        return ArmoryUtils.getResponseEntity(ArmoryConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<List<ProductWrapper>> getAllProduct() {
        try{
            return new ResponseEntity<>(productDao.getAllProduct(), HttpStatus.OK);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> updateProduct(Map<String, String> requestMap) {
        try{
                if(validateProductMap(requestMap, true)){
                    Optional<Product> optional = productDao.findById(Integer.parseInt(requestMap.get("id")));
                    if(optional.isPresent()){
                        Product product = getProductFromMap(requestMap, true);
                        product.setStatus(optional.get().getStatus());
                        productDao.save(product);
                        return ArmoryUtils.getResponseEntity("Product Updated Successfully", HttpStatus.OK);
                    }
                    else
                        return ArmoryUtils.getResponseEntity("Product id does not exist", HttpStatus.OK);
                }
                else
                    return ArmoryUtils.getResponseEntity(ArmoryConstants.INVALID_DATA, HttpStatus.BAD_REQUEST);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return ArmoryUtils.getResponseEntity(ArmoryConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> deleteProduct(Integer id) {
        try{
            Optional optional = productDao.findById(id);
            if(optional.isPresent()){
                productDao.deleteById(id);
                return ArmoryUtils.getResponseEntity("Product Deleted Successfully", HttpStatus.OK);
            }
            else
                return ArmoryUtils.getResponseEntity("Product id does not exist", HttpStatus.OK);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return ArmoryUtils.getResponseEntity(ArmoryConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    private boolean validateProductMap(Map<String, String> requestMap, boolean validateId) {
        if(requestMap.containsKey("name") && requestMap.containsKey("description") && requestMap.containsKey("price")){
            if(requestMap.containsKey("id") && validateId)
                return true;
            else if(!validateId)
                return true;
        }
        return false;
    }

    private Product getProductFromMap(Map<String, String> requestMap, boolean isAdd) {
        Category category = new Category();
        category.setId(Integer.parseInt(requestMap.get("categoryId")));
        Product product = new Product();
        if(isAdd){
            product.setId(Integer.parseInt(requestMap.get("id")));
        }
        else {
            product.setStatus("true");
        }
        product.setCategory(category);
        product.setName(requestMap.get("name"));
        product.setDescription(requestMap.get("description"));
        product.setPrice(Integer.parseInt(requestMap.get("price")));
        return product;
    }
}
