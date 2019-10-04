package com.fafik.recipeapplication.services;

import com.fafik.recipeapplication.command.IngredientCommand;
import reactor.core.publisher.Mono;

public interface IngredientService {

    Mono<IngredientCommand> findByRecipeIdAndIngredientId(String recipeId, String ingredientId);
    Mono<IngredientCommand> saveIngredientCommand(IngredientCommand command);
    Mono<Void>  deleteById(String recipeId, String ingredientId);
}
