package com.fafik.recipeapplication.services;

import com.fafik.recipeapplication.command.UnitOfMeasureCommand;
import reactor.core.publisher.Flux;

public interface UnitOfMeasureService {
    Flux<UnitOfMeasureCommand> listAllUoms();

}
