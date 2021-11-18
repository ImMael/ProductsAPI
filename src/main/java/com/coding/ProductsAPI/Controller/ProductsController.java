package com.coding.ProductsAPI.Controller;

import com.coding.ProductsAPI.DAO.ProductsDAO;
import com.coding.ProductsAPI.models.Products;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Properties;

@RestController
@RequestMapping("/products")
public class ProductsController {

    @Autowired
    ProductsDAO productsService;

    @GetMapping("")
    public @ResponseBody List<Products> index(){
        return productsService.readAll();
    }

    @GetMapping("/{id}")
    public @ResponseBody Products getProduct(@PathVariable("id") Integer id){
        return productsService.readById(id);
    }

    @PutMapping("/{id}")
    public @ResponseBody HttpStatus editProduct(@RequestBody Products body, @PathVariable("id") Integer id){
        Integer res = productsService.update(id, body);
        if(res == 1){
            return HttpStatus.OK;
        } else{
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }
    @DeleteMapping("/{id}")
    public @ResponseBody
    HttpStatus deleteProduct(@PathVariable("id") Integer id){
        Integer res = productsService.delete(id);
        if(res == 1){
            return HttpStatus.NO_CONTENT;
        } else{
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }

    @PostMapping("")
    public @ResponseBody HttpStatus addProduct(@RequestBody Products body){
        Integer res = productsService.add(body);
        if(res == 1){
            return HttpStatus.CREATED;
        } else{
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }
}
