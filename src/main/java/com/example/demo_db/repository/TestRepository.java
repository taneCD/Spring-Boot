package com.example.demo_db.repository;

import com.example.demo_db.model.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TestRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public String getRandomName(){
        String query="SELECT id, name, currentPrice FROM kuhinja";
        List<Ingredient> kuhinja=jdbcTemplate.query(query, BeanPropertyRowMapper.newInstance(Ingredient.class));

        return kuhinja.get(0).getName();
    }
}
