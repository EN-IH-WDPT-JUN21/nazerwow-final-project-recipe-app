package com.ironhack.recipeservice.services;

import com.ironhack.recipeservice.dao.Recipe;
import com.ironhack.recipeservice.dto.CreateRecipeDTO;
import com.ironhack.recipeservice.dto.RecipeDTO;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

public interface RecipeService {

    List<Recipe> findAll();

    Recipe findById(Long id) throws ResponseStatusException;

    void deleteRecipe(Long id) throws ResponseStatusException;

    Recipe addRecipe(CreateRecipeDTO createRecipeDTO) throws ResponseStatusException;

    Recipe updateRecipe(RecipeDTO recipeDTO) throws ResponseStatusException;

    List<Recipe> findByUserId(Long id);
}
