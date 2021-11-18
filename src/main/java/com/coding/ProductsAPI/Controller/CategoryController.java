package com.coding.ProductsAPI.Controller;

import com.coding.ProductsAPI.DAO.CategoryDAO;
import com.coding.ProductsAPI.models.Category;
import com.coding.ProductsAPI.models.Products;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CategoryController {

    @Autowired
    CategoryDAO categoryService;

    @GetMapping("/category")
    public @ResponseBody
    List<Category> index(){
        return categoryService.readAll();
    }

    @GetMapping("/category/{id}")
    public @ResponseBody Category getProduct(@PathVariable("id") Integer id){
        return categoryService.readById(id);
    }

    @PutMapping("/category/{id}")
    public @ResponseBody
    HttpStatus editProduct(@RequestBody Category body, @PathVariable("id") Integer id){
        Integer res = categoryService.update(id, body);
        if(res == 1){
            return HttpStatus.OK;
        } else{
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }
    @DeleteMapping("/category/{id}")
    public @ResponseBody
    HttpStatus deleteProduct(@PathVariable("id") Integer id){
        Integer res = categoryService.delete(id);
        if(res == 1){
            return HttpStatus.NO_CONTENT;
        } else{
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }

    @PostMapping("/category")
    public @ResponseBody HttpStatus addProduct(@RequestBody Category body){
        Integer res = categoryService.add(body);
        if(res == 1){
            return HttpStatus.CREATED;
        } else{
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }
}
