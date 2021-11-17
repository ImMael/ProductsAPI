package com.coding.ProductsAPI.DAO;

import com.coding.ProductsAPI.models.Products;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductsDAO {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Products> readAll(){
        return jdbcTemplate.query("SELECT * FROM Products", BeanPropertyRowMapper.newInstance(Products.class));
    }
}
