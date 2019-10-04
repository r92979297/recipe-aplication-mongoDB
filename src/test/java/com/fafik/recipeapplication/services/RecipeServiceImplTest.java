package com.fafik.recipeapplication.services;

import com.fafik.recipeapplication.converters.RecipeCommandToRecipe;
import com.fafik.recipeapplication.converters.RecipeToRecipeCommand;
import com.fafik.recipeapplication.domain.Recipe;
import com.fafik.recipeapplication.repositories.reactive.RecipeReactiveRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

public class RecipeServiceImplTest {

    RecipeServiceImpl recipeService;

    @Mock
    RecipeReactiveRepository recipeReactiveRepository;

    @Mock
    RecipeToRecipeCommand recipeToRecipeCommand;

    @Mock
    RecipeCommandToRecipe recipeCommandToRecipe;
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        recipeService = new RecipeServiceImpl(recipeReactiveRepository, recipeToRecipeCommand, recipeCommandToRecipe);
    }

    @Test
    public void getRecipeById() throws Exception{
        Recipe recipe = new Recipe();
        recipe.setId("1");

        when(recipeReactiveRepository.findById(anyString())).thenReturn(Mono.just(recipe));

        Recipe recipeReturned = recipeService.findById("1").block();

        assertNotNull(recipeReturned);

        verify(recipeReactiveRepository, times(1)).findById(anyString());
        verify(recipeReactiveRepository, never()).findAll();
    }


    @Test
    public void getRecipesTest() throws Exception{
        Recipe recipe = new Recipe();

        when(recipeService.getRecipes()).thenReturn(Flux.just(recipe));

        List<Recipe> recipes = recipeService.getRecipes().collectList().block();

        assertEquals(recipes.size(),1);
        verify(recipeReactiveRepository,times(1)).findAll();
        verify(recipeReactiveRepository,never()).findById(anyString());
    }

    @Test
    public void testDeleteByID()throws Exception{
        String idToDelete = "2";
        when(recipeReactiveRepository.deleteById(anyString())).thenReturn(Mono.empty())  ;

        recipeService.deleteById(idToDelete);
        verify(recipeReactiveRepository,times(1)).deleteById(anyString());
    }
}