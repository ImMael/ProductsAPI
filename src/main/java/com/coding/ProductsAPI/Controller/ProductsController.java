package com.coding.ProductsAPI.Controller;

import com.coding.ProductsAPI.DAO.ProductsDAO;
import com.coding.ProductsAPI.models.Products;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ProductsController {

    @Autowired
    ProductsDAO productsService;

    @RequestMapping("/products")
    public @ResponseBody List<Products> index(){
        return productsService.readAll();
    }
}
