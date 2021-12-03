package com.ironhack.ratingservice.controller;

import com.ironhack.ratingservice.dto.RecipeDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

public interface RatingController {
    @GetMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    List<RecipeDTO> findByUserId(@PathVariable(name = "userId") Long userId);

    @GetMapping("/{recipeId}/{userId}")
    @ResponseStatus(HttpStatus.OK)
    boolean userPreviouslyRatedRecipe(@PathVariable(name = "recipeId") Long recipeId,
                                      @PathVariable(name = "userId") Long userId);

    @GetMapping("/recipe/{recipeId}")
    @ResponseStatus(HttpStatus.OK)
    Double getAverageRatingForRecipe(@PathVariable(name = "recipeId") Long recipeId);

    @GetMapping("/top10recipes")
    @ResponseStatus(HttpStatus.OK)
    List<RecipeDTO> getTop10Recipes();

    @GetMapping("/top10foruser/{userId}")
    @ResponseStatus(HttpStatus.OK)
    List<RecipeDTO> getTop10RecipesForUser(@PathVariable(name = "userId") Long userId);
}
