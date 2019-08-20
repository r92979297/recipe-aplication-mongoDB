package com.fafik.recipeapplication.converters;

import com.fafik.recipeapplication.command.IngredientCommand;
import com.fafik.recipeapplication.command.UnitOfMeasureCommand;
import com.fafik.recipeapplication.domain.Ingredient;
import com.fafik.recipeapplication.domain.Recipe;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class IngredientCommandToIngredientTest {

    public static final Recipe RECIPE = new Recipe();
    public static final BigDecimal AMOUNT = new BigDecimal("1");
    public static final String DESCRIPTION = "Cheesburger";
    public static final String ID_VALUE = "1";
    public static final String UOM_ID = "2";

    IngredientCommandToIngredient converter;
    @Before
    public void setUp() throws Exception {
        converter = new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure());
    }

    @Test
    public void testNullObject() throws  Exception{
        assertNull(converter.convert(null));
    }
    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(converter.convert(new IngredientCommand()));
    }
    @Test
    public void convert() {
        //given
        IngredientCommand command = new IngredientCommand();
        command.setId(ID_VALUE);
        command.setAmount(AMOUNT);

        command.setDescription(DESCRIPTION);
        UnitOfMeasureCommand uomCommand = new UnitOfMeasureCommand();
        uomCommand.setId(UOM_ID);
        command.setUom(uomCommand);

        //when
        Ingredient ingredient = converter.convert(command);

        //then
        assertNotNull(ingredient);
        assertNotNull(ingredient.getUom());
        assertEquals(ID_VALUE,ingredient.getId());
        assertEquals(AMOUNT,ingredient.getAmount());
        assertEquals(DESCRIPTION,ingredient.getDescription());
        assertEquals(UOM_ID,ingredient.getUom().getId());
    }
    @Test
    public void convertWithNullUOM() {
        //given
        IngredientCommand command = new IngredientCommand();
        command.setId(ID_VALUE);
        command.setAmount(AMOUNT);
        command.setDescription(DESCRIPTION);
        command.setUom(null);

        //when
        Ingredient ingredient = converter.convert(command);

        //then
        assertNotNull(ingredient);
        assertNull(ingredient.getUom());
        assertEquals(ID_VALUE,ingredient.getId());
        assertEquals(AMOUNT,ingredient.getAmount());
        assertEquals(DESCRIPTION,ingredient.getDescription());
    }
}