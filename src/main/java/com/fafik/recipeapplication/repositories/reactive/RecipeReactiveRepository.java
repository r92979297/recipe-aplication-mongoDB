package com.fafik.recipeapplication.repositories.reactive;

import com.fafik.recipeapplication.domain.Recipe;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface RecipeReactiveRepository extends ReactiveMongoRepository<Recipe, String> {
}
