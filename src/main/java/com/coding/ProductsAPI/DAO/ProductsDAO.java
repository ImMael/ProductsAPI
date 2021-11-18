package com.coding.ProductsAPI.DAO;

import com.coding.ProductsAPI.models.Products;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

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

    public List<Products> searchProducts(Map<String, String> allParams) {
        // Alban
        String rating = "";
        String type = "";
        String name = "";
        String sort = "id";
        String sql;

        ArrayList value = new ArrayList() ;
        ArrayList condition = new ArrayList() ;

        if(allParams.containsKey("rating")){
            rating = allParams.get("rating");
            condition.add("rating = ?");
            value.add(rating);
        }
        if(allParams.containsKey("type")){
            type = allParams.get("type");
            condition.add("type = ?");
            value.add(type);
        }
        if(allParams.containsKey("name")){
            name = allParams.get("name");
            condition.add("name = ?");
            value.add(name);
        }
        if(allParams.containsKey("sort")){
            sort = allParams.get("sort");
        }

        List<Products> list = new ArrayList<>();
        int valueSize = value.size();
        switch (valueSize){
            case 1:
                sql = "SELECT * FROM Products WHERE "+condition.get(0) + "ORDER BY "+sort;
                list = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Products.class), value.get(0));
                break;
            case 2:
                sql = "SELECT * FROM Products WHERE "+condition.get(0)+" AND "+condition.get(1) + "ORDER BY "+sort;
                list = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Products.class),value.get(0),value.get(1));
                break;
            case 3:
                sql = "SELECT * FROM Products WHERE "+condition.get(0)+" AND "+condition.get(1)+" AND "+condition.get(2) + "ORDER BY "+sort;
                list = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Products.class),value.get(0),value.get(1),value.get(2));
                break;
        }
        return list;
    }
}
