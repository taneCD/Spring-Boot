package com.example.demo_db.repository.Interfaces;

import com.example.demo_db.model.Ingredient;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface IngredientRepository {
    List<Ingredient> getAllIngredients();

    boolean addNewIngredient(Ingredient ingredient);

    public Ingredient getIngredientById(UUID id);

    public Ingredient getIdByName(String name);

    public void setPriceById(UUID id, double currentPrice);

    public void setNameById(UUID id, String name);

    public Ingredient getNameById(UUID id);

    public void deleteIngredientById(UUID id);
}
