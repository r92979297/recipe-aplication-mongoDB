package com.fafik.recipeapplication.repositories;

import com.fafik.recipeapplication.domain.Recipe;
import org.springframework.data.repository.CrudRepository;

public interface RecipeRepository extends CrudRepository<Recipe,Long> {

}
