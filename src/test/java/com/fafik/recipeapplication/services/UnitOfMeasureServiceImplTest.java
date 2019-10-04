package com.fafik.recipeapplication.services;

import com.fafik.recipeapplication.command.UnitOfMeasureCommand;
import com.fafik.recipeapplication.converters.UnitOfMeasureToUnitOfMeasureCommand;
import com.fafik.recipeapplication.domain.UnitOfMeasure;
import com.fafik.recipeapplication.repositories.reactive.UnitOfMeasureReactiveRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Flux;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class UnitOfMeasureServiceImplTest {
    UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand = new UnitOfMeasureToUnitOfMeasureCommand();
    UnitOfMeasureService service;

    @Mock
    UnitOfMeasureReactiveRepository unitOfMeasureReactiveRepository;

    @Before
    public void setUp() throws Exception{
        MockitoAnnotations.initMocks(this);
        service= new UnitOfMeasureServiceImpl(unitOfMeasureReactiveRepository,unitOfMeasureToUnitOfMeasureCommand);
    }

    @Test
    public void listAllUoms() throws Exception{


        UnitOfMeasure uom1 = new UnitOfMeasure();
        uom1.setId("1");

        UnitOfMeasure uom2 = new UnitOfMeasure();
        uom2.setId("2");

        when(unitOfMeasureReactiveRepository.findAll()).thenReturn(Flux.just(uom1,uom2));

        //when
        List<UnitOfMeasureCommand> commands = service.listAllUoms().collectList().block();

        assertEquals(2,commands.size());
        verify(unitOfMeasureReactiveRepository,times(1)).findAll();
    }
}