package com.fafik.recipeapplication.services;

import com.fafik.recipeapplication.command.RecipeCommand;
import com.fafik.recipeapplication.domain.Recipe;

import java.util.Set;


public interface RecipeService {

    Set<Recipe> getRecipes();
    Recipe findById(Long id);
    RecipeCommand saveRecipeCommand(RecipeCommand command);
    RecipeCommand findCommandById(Long id);
    void deleteById(Long id);
}
