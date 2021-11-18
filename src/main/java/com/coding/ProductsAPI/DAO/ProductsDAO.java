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
    public List<Products> sortAll(String desc,String asc){

        String sql = "SELECT * FROM Products ORDER BY "+ desc +" desc , "+asc+" asc";
        System.out.println(sql);
        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Products.class));

    }


    public List<Products> pagingAll(String range){

            String[] TbValue = range.split("-");
            int valeur1 = Integer.parseInt(TbValue[1]);
            int valeur2 = Integer.parseInt(TbValue[0]);

            return jdbcTemplate.query("SELECT * FROM Products LIMIT ? OFFSET ?", BeanPropertyRowMapper.newInstance(Products.class), valeur1 - 1, valeur2 - 1);
    }

    public Products readById(Integer id) {
        String sql = "SELECT * FROM Products WHERE id=? ;";
        return jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(Products.class), id);
    }

    public int update(Integer id, Products prod) {
        String sql = "UPDATE Products SET type = ?, rating = ?, name = ?, categoryId = ? WHERE id = ?;";
        return jdbcTemplate.update(sql, prod.getType(), prod.getRating(), prod.getName(), prod.getCategoryId(), id);
    }

    public int delete(Integer id){
        String sql = "DELETE FROM Products WHERE id = ?;";
        return jdbcTemplate.update(sql, id);
    }

    public int add(Products prod) {
        String sql = "INSERT INTO Products (type, rating, name, categoryId) VALUES (?, ?, ?, ?);";
        return jdbcTemplate.update(sql, prod.getType(), prod.getRating(), prod.getName(), prod.getCategoryId());
    }
}
