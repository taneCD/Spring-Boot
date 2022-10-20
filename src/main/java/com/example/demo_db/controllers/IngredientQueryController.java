package com.example.demo_db.controllers;
import com.example.demo_db.model.Ingredient;
import com.example.demo_db.repository.Interfaces.IngredientRepository;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class IngredientQueryController {
    private IngredientRepository ingredientRepository;
    @Autowired
    public IngredientQueryController(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }
    @GetMapping("/api/ingredients")
    public List<Ingredient> getAllIngredients(){
        return ingredientRepository.getAllIngredients();
    }
    @RequestMapping(path="/api/ingredients/addIngredients",method=RequestMethod.POST)
    public ResponseEntity<Boolean> addNewIngredient(@RequestBody Ingredient ingredient) {
        boolean uspelo = ingredientRepository.addNewIngredient(ingredient);
        if(uspelo){
            return new ResponseEntity<>(true, HttpStatus.OK);
        } else{
            return new ResponseEntity<>(false,HttpStatus.CONFLICT);
        }
    }
    @GetMapping("/api/ingredients/{id}")
    public ResponseEntity<Ingredient> getIngredientById(@PathVariable UUID id){
        Ingredient sastojak = ingredientRepository.getIngredientById(id);
        if(sastojak==null){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } else{
            return new ResponseEntity<>(sastojak, HttpStatus.OK);
        }
    }
    @GetMapping("/api/ingredients/{name}/id")
    public ResponseEntity<UUID> getIdbyName(@PathVariable String name){
        Ingredient sastojak = ingredientRepository.getIdByName(name);
        if(sastojak==null){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } else{
            return new ResponseEntity<>(sastojak.getId(), HttpStatus.OK);
        }
    }
    @PatchMapping("/api/ingredients/{id}/{currentPrice}")
    public void setPriceById(@PathVariable UUID id, @PathVariable double currentPrice){
        ingredientRepository.setPriceById(id, currentPrice);
    }

    @PatchMapping("/api/ingredients/aaa/{id}/{name}")
    public void setNameById(@PathVariable UUID id, @PathVariable String name){
        ingredientRepository.setNameById(id, name);
    }
    @GetMapping("/api/ingredients/{id}/name")
    public ResponseEntity<String> getNameById(@PathVariable UUID id){
        Ingredient sastojak=ingredientRepository.getNameById(id);
        if(sastojak==null){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } else{
            return new ResponseEntity<>(sastojak.getName(), HttpStatus.OK);
        }
    }
    @RequestMapping(path="/api/ingredients/{id}",method = RequestMethod.DELETE)
    public void deleteIngredientById(@PathVariable UUID id){
        ingredientRepository.deleteIngredientById(id);
    }
}
