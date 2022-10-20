package com.example.demo_db.repository;

import com.example.demo_db.model.Ingredient;
import com.example.demo_db.repository.Interfaces.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.UUID;

@Repository
public class SqlIngredientRepository implements IngredientRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public List<Ingredient> getAllIngredients() {
        String query="SELECT id, name, currentPrice FROM KuhinjaSastojci";
        List<Ingredient> ingredients=jdbcTemplate.query(query, BeanPropertyRowMapper.newInstance(Ingredient.class));

        return ingredients;
    }
    @Override
    public boolean addNewIngredient(Ingredient ingredient){
        String query="SELECT id, name, currentPrice FROM KuhinjaSastojci";
//        String query2="SELECT id, name, currentPrice FROM KuhinjaSastojci WHERE name= "+ingredient.getName()+";";

        List<Ingredient> ingredients=jdbcTemplate.query(query, BeanPropertyRowMapper.newInstance(Ingredient.class));
        boolean sastojakPostoji=false;
        for(var el:ingredients){
            if(el.getName().equals(ingredient.getName())){
                sastojakPostoji=true;
                System.out.println("Nije uspelo");
                break;
            }
        }
        if(!sastojakPostoji){
            ingredient.generateRandomId();
            ingredients.add(ingredient);
            jdbcTemplate.update("INSERT INTO KuhinjaSastojci (id, name, currentPrice) VALUES (?, ?, ?)",ingredient.getId(),ingredient.getName(),ingredient.getCurrentPrice());

            System.out.println("Uspelo");
            return true;
        }
        return false;
    }

    public Ingredient getIngredientById(UUID id) {
        String query = "SELECT id, name, currentPrice FROM KuhinjaSastojci";
        List<Ingredient> ingredients = jdbcTemplate.query(query, BeanPropertyRowMapper.newInstance(Ingredient.class));
        for(var el: ingredients){
            if(el.getId().equals(id)){
                System.out.println("Pronasao!");
                return el;
            }
        }
        System.out.println("Nije pronasao u bazi");
        return null;
    }
    @Override
    public Ingredient getIdByName(String name) {
        String query = "SELECT id, name, currentPrice FROM KuhinjaSastojci";
        List<Ingredient> ingredients = jdbcTemplate.query(query, BeanPropertyRowMapper.newInstance(Ingredient.class));
        for(var el: ingredients){
            if(el.getName().equals(name)){
                System.out.println("Pronasao!");
                return el;
            }
        }
        System.out.println("Nije pronasao u bazi");
        return null;
    }
    public void setPriceById(UUID id, double currentPrice){
        jdbcTemplate.update("UPDATE KuhinjaSastojci SET currentPrice = ? WHERE id=?", currentPrice, id);
    }

    public void setNameById(UUID id, String name){
        jdbcTemplate.update("UPDATE KuhinjaSastojci SET name = ? WHERE id=?", name, id);
    }
    public Ingredient getNameById(UUID id){
        String query = "SELECT id, currentPrice, name FROM KuhinjaSastojci";
        List<Ingredient> ingredients = jdbcTemplate.query(query, BeanPropertyRowMapper.newInstance(Ingredient.class));
        for(var el: ingredients){
            if(el.getId().equals(id)){
                System.out.println("Pronasao!");
                return el;
            }
        }
        System.out.println("Nije pronasao u bazi");
        return null;
    }
    @Override
    public void deleteIngredientById(UUID id) {
//        String query="SELECT id, name, currentPrice FROM KuhinjaSastojci";
//        List<Ingredient> ingredients = jdbcTemplate.query(query, BeanPropertyRowMapper.newInstance(Ingredient.class));
//        for(var el: ingredients){
//            if(el.getId().equals(id)){
//                ingredients.remove(el);
//                System.out.println("Pronasao i obrisao!");
//                jdbcTemplate.update("DELETE FROM KuhinjaSastojci WHERE id=?", id);
//                return;
//            }
//        }
//        System.out.println("Nije pronasao u bazi");

        jdbcTemplate.update("DELETE FROM KuhinjaSastojci WHERE id=?", id); //komanda koja update-uje bazu
    }
}
