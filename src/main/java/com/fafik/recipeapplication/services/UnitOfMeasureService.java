package com.fafik.recipeapplication.services;

import com.fafik.recipeapplication.command.UnitOfMeasureCommand;

import java.util.List;
import java.util.Set;

public interface UnitOfMeasureService {
    Set<UnitOfMeasureCommand> listAllUoms();

}
