package com.ironhack.recipeservice.controllers;

import com.ironhack.recipeservice.dao.Recipe;
import com.ironhack.recipeservice.dto.CreateRecipeDTO;
import com.ironhack.recipeservice.dto.RecipeDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface RecipeController {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    List<Recipe> findAll();

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    Recipe findById(@PathVariable(name = "id") Long id);

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteRecipe(@PathVariable(name = "id") Long id);

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    Recipe addRecipe(@RequestBody CreateRecipeDTO createRecipeDTO);

    @PutMapping("edit")
    @ResponseStatus(HttpStatus.ACCEPTED)
    Recipe updateRecipe(@RequestBody RecipeDTO recipeDTO);

    @GetMapping("/user/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<Recipe> findByUserId(@PathVariable(name = "id") Long id);

}
