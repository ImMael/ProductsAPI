package com.coding.ProductsAPI.DAO;

import com.coding.ProductsAPI.models.Category;
import com.coding.ProductsAPI.models.Products;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryDAO {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Category> readAll(){
        return jdbcTemplate.query("SELECT * FROM Category", BeanPropertyRowMapper.newInstance(Category.class));
    }

    public Category readById(Integer id) {
        String sql = "SELECT * FROM Category WHERE id=? ;";
        return jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(Category.class), id);
    }

    public int update(Integer id, Category cat) {
        String sql = "UPDATE Category SET name = ? WHERE id = ?;";
        return jdbcTemplate.update(sql, cat.getName(), id);
    }

    public int delete(Integer id){
        String sql = "DELETE FROM Category WHERE id = ?;";
        return jdbcTemplate.update(sql, id);
    }
    public int add(Category cat) {
        String sql = "INSERT INTO Category (name) VALUES (?);";
        return jdbcTemplate.update(sql, cat.getName());
    }

}
