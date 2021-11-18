package com.coding.ProductsAPI.Controller;

import com.coding.ProductsAPI.DAO.ProductsDAO;
import com.coding.ProductsAPI.models.Products;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Properties;

@RestController
@RequestMapping("/products")
public class ProductsController {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    ProductsDAO productsService;

    @GetMapping("")
    public @ResponseBody List<Products> index(@RequestParam(value= "range", required = false) String range,
                                              @RequestParam(value= "asc", required = false) String asc,
                                              @RequestParam(value= "desc", required = false) String desc){
       if(range != null){
           return productsService.pagingAll(range);
       }
       if(asc != null || desc != null){
            return productsService.sortAll(desc,asc);
       }
       else {
           return productsService.readAll();
           }
    }

    @GetMapping("/{id}")
    public Products getProduct(@PathVariable("id") Integer id){
        return productsService.readById(id);
    }

    @PutMapping("/{id}")
    public HttpStatus editProduct(@RequestBody Products body, @PathVariable("id") Integer id){
        Integer res = productsService.update(id, body);
        if(res == 1){
            return HttpStatus.OK;
        } else{
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }
    @DeleteMapping("/{id}")
    public HttpStatus deleteProduct(@PathVariable("id") Integer id){
        Integer res = productsService.delete(id);
        if(res == 1){
            return HttpStatus.NO_CONTENT;
        } else{
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }

    @PostMapping("")
    public HttpStatus addProduct(@RequestBody Products body){
        Integer res = productsService.add(body);
        if(res == 1){
            return HttpStatus.CREATED;
        } else{
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }

    @GetMapping("/search")
    public List<Products> search(@RequestParam Map<String,String> allParams){
        if(allParams!= null){
            if(allParams.containsKey("type") || allParams.containsKey("name") || allParams.containsKey("rating") || allParams.containsKey("sort")){
                return productsService.searchProducts(allParams);
            }
        }
        return productsService.readAll();
    }
}
