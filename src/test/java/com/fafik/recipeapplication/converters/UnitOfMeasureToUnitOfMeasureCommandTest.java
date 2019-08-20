package com.fafik.recipeapplication.converters;

import com.fafik.recipeapplication.command.UnitOfMeasureCommand;
import com.fafik.recipeapplication.domain.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UnitOfMeasureToUnitOfMeasureCommandTest {

    public static final String LONG_VALUE= "1";
    public static final String DESCRIPTION  = "description";

    UnitOfMeasureToUnitOfMeasureCommand converter;

    @Before
    public void setUp() throws Exception {
        converter = new UnitOfMeasureToUnitOfMeasureCommand();
    }

    @Test
    public void testNullParameter() throws Exception{
      assertNull(converter.convert(null));
    }
    @Test
    public void testEmptyParameter() throws Exception{
        assertNotNull(converter.convert(new UnitOfMeasure()));
    }
    @Test
    public void convert() {
        UnitOfMeasure uom = new UnitOfMeasure();
        uom.setId(LONG_VALUE);
        uom.setDescription(DESCRIPTION);

        UnitOfMeasureCommand command = converter.convert(uom);

        assertNotNull(command);
        assertEquals(LONG_VALUE,command.getId());
        assertEquals(DESCRIPTION,command.getDescription());


    }
}