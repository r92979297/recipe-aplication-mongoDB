package com.fafik.recipeapplication.controllers;

import com.fafik.recipeapplication.command.IngredientCommand;
import com.fafik.recipeapplication.command.RecipeCommand;
import com.fafik.recipeapplication.command.UnitOfMeasureCommand;
import com.fafik.recipeapplication.domain.Recipe;
import com.fafik.recipeapplication.services.IngredientService;
import com.fafik.recipeapplication.services.RecipeService;
import com.fafik.recipeapplication.services.UnitOfMeasureService;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.pl.REGON;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.stream.StreamSupport;

@Slf4j
@Controller
public class IngredientController<model> {
    private final RecipeService recipeService;
    private final IngredientService ingredientService;
    private final UnitOfMeasureService unitOfMeasureService;

    public IngredientController(RecipeService recipeService, IngredientService ingredientService, UnitOfMeasureService unitOfMeasureService) {
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
        this.unitOfMeasureService = unitOfMeasureService;
    }

    @GetMapping
    @RequestMapping("/recipe/{recipeId}/ingredients")
    public String listIngredients(@PathVariable String recipeId, Model model){
        log.debug("Getting ingredients list for recipe id: "+recipeId);

        model.addAttribute("recipe",recipeService.findCommandById(recipeId));

        return"recipe/ingredient/list";
    }
    @GetMapping
    @RequestMapping("recipe/{recipeId}/ingredient/{id}/show")
    public String showRecipeIngredient(@PathVariable String recipeId,
                                       @PathVariable String id, Model model){
        model.addAttribute("ingredient",ingredientService.findByRecipeIdAndIngredientId(recipeId,id));
        return "recipe/ingredient/show";
    }

    @GetMapping
    @RequestMapping("recipe/{recipeId}/ingredient/new")
    public String newRecipe(@PathVariable String recipeId,Model model){

        RecipeCommand recipeCommand = recipeService.findCommandById(recipeId);
        // todo raise exception if null
        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setRecipeId(recipeId);
        model.addAttribute("ingredient",ingredientCommand);

        ingredientCommand.setUom(new UnitOfMeasureCommand());
        model.addAttribute("uomList", unitOfMeasureService.listAllUoms());
        return "recipe/ingredient/ingredientform";
    }


    @PostMapping
    @RequestMapping("recipe/{recipeId}/ingredient")
    public String saveOrUpdate(@ModelAttribute IngredientCommand command){
        IngredientCommand savedCommand = ingredientService.saveIngredientCommand(command);

        log.debug("saved recipe id: "+savedCommand.getRecipeId());
        log.debug("saved ingedient id: "+savedCommand.getId());

        return "redirect:/recipe/"+savedCommand.getRecipeId()+"/ingredient/"+savedCommand.getId()+"/show";
    }

    @GetMapping
    @RequestMapping("recipe/{recipeId}/ingredient/{id}/update")
    public String updateRecipeIngredient(@PathVariable String recipeId,
                                         @PathVariable String id, Model model){
        model.addAttribute("ingredient",ingredientService.findByRecipeIdAndIngredientId(recipeId,id));
        model.addAttribute("uomList",unitOfMeasureService.listAllUoms());

        return "recipe/ingredient/ingredientform";
    }

    @GetMapping
    @RequestMapping("recipe/{recipeId}/ingredient/{id}/delete")
    public String deleteRecipeIngredient(@PathVariable String recipeId,
                                           @PathVariable String id){
        log.debug("Deleting ingredient id:" +id);

        ingredientService.deleteById(recipeId,id);
        return"redirect:/recipe/"+recipeId+"/ingredients";
    }
}
