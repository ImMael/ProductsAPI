package com.coding.ProductsAPI.Controller;

import com.coding.ProductsAPI.DAO.ProductsDAO;
import com.coding.ProductsAPI.models.Products;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ProductsController {

    @Autowired
    ProductsDAO productsService;

    @GetMapping("/products")
    public @ResponseBody List<Products> index(){
        return productsService.readAll();
    }

    @GetMapping("/products/{id}")
    public @ResponseBody List<Products> getProduct(@PathVariable("id") Integer id){
        return productsService.readById(id);
    }

    @PutMapping("/products/{id}")
    public @ResponseBody List<Products> editProduct(@RequestBody Products body, @PathVariable("id") Integer id){
        productsService.update(id, body);
        return productsService.readById(id);
    }
    @DeleteMapping("/products/{id}")
    public @ResponseBody List<Products> deleteProduct(@PathVariable("id") Integer id){
        productsService.delete(id);
        return productsService.readAll();
    }

    @PostMapping("/products")
    public @ResponseBody Products addProduct(@RequestBody Products body){
        productsService.add(body);
        return body;
    }
}
