package com.fafik.recipeapplication.converters;

import com.fafik.recipeapplication.command.IngredientCommand;
import com.fafik.recipeapplication.domain.Ingredient;
import com.fafik.recipeapplication.domain.Recipe;
import com.fafik.recipeapplication.domain.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class IngredientToIngredientCommandTest {

    private static final Recipe RECIPE = new Recipe();
    private static final BigDecimal AMOUNT = new BigDecimal("1");
    private static final String DESCRIPTION = "Cheeseburger";
    private static final Long UOM_ID = 2L;
    private static final Long ID_VALUE = 1L;

    IngredientToIngredientCommand converter;
    @Before
    public void setUp() throws Exception {
        converter = new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());
    }

    @Test
    public void testNullObject() throws  Exception{
        assertNull(converter.convert(null));
    }
    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(converter.convert(new Ingredient()));
    }
    @Test
    public void convert() {
        //given
        Ingredient ingredient = new Ingredient();
        ingredient.setId(ID_VALUE);
        ingredient.setRecipe(RECIPE);
        ingredient.setAmount(AMOUNT);
        ingredient.setDescription(DESCRIPTION);
        UnitOfMeasure uom = new UnitOfMeasure();
        uom.setId(UOM_ID);
        ingredient.setUom(uom);

        //when
        IngredientCommand ingredientCommand = converter.convert(ingredient);

        //then
        assertNotNull(ingredientCommand);
        assertNotNull(ingredientCommand.getUom());
        assertEquals(ID_VALUE,ingredientCommand.getId());
        assertEquals(AMOUNT,ingredientCommand.getAmount());
        assertEquals(DESCRIPTION,ingredientCommand.getDescription());
        assertEquals(UOM_ID,ingredientCommand.getUom().getId());
    }
    @Test
    public void convertWithNullUOM() {
        //given
        Ingredient ingredient = new Ingredient();
        ingredient.setId(ID_VALUE);
        ingredient.setRecipe(RECIPE);
        ingredient.setAmount(AMOUNT);
        ingredient.setDescription(DESCRIPTION);
        ingredient.setUom(null);

        //when
        IngredientCommand ingredientCommand = converter.convert(ingredient);

        //then
        assertNotNull(ingredientCommand);
        assertNull(ingredientCommand.getUom());
        assertEquals(ID_VALUE,ingredientCommand.getId());
        assertEquals(AMOUNT,ingredientCommand.getAmount());
        assertEquals(DESCRIPTION,ingredientCommand.getDescription());
    }
}