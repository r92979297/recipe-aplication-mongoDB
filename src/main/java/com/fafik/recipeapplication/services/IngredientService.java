package com.fafik.recipeapplication.services;

import com.fafik.recipeapplication.command.IngredientCommand;

public interface IngredientService {
    IngredientCommand findByRecipeIdAndIngredientId(String recipeId, String ingredientId);
    IngredientCommand saveIngredientCommand(IngredientCommand command);
    void  deleteById(String recipeId, String ingredientId);
}
