package com.fafik.recipeapplication.services;

import com.fafik.recipeapplication.domain.Recipe;

import java.util.Set;


public interface RecipeService {

    Set<Recipe> getRecipes();
}
