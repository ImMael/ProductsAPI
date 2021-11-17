package com.coding.ProductsAPI.DAO;

import com.coding.ProductsAPI.models.Products;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ProductsDAO {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Products> readAll(){
        return jdbcTemplate.query("SELECT * FROM Products", BeanPropertyRowMapper.newInstance(Products.class));
    }

    public List<Products> readById(Integer id) {
        String sql = "SELECT * FROM Products WHERE id = ?" ;
        return jdbcTemplate.query(sql,BeanPropertyRowMapper.newInstance(Products.class),id);
    }

    public void update(Integer id, Products prod) {
        String sql = "UPDATE Products SET type = ?, rating = ?, name = ?, categoryId = ? WHERE id = ?;";
        jdbcTemplate.update(sql, prod.getType(), prod.getRating(), prod.getName(), prod.getCategoryId(), id);
    }

    public void delete(Integer id){
        String sql = "DELETE FROM Products WHERE id = ?;";
        jdbcTemplate.update(sql, id);
    }

    public void add(Products prod) {
        String sql = "INSERT INTO Products (type, rating, name, categoryId) VALUES (?, ?, ?, ?);";
        jdbcTemplate.update(sql, prod.getType(), prod.getRating(), prod.getName(), prod.getCategoryId());
    }
}
