package com.ironhack.ratingservice.services;

import com.ironhack.ratingservice.dao.Rating;
import com.ironhack.ratingservice.dto.RatingDTO;
import com.ironhack.ratingservice.dto.RecipeDTO;

import java.util.List;

public interface RatingService {

    List<RecipeDTO> findByUserId(Long userId);

    Double getAverageRatingForRecipe(Long recipeId);

    List<RecipeDTO> getTop10Recipes(int i);

    List<RecipeDTO> getTop10RecipesForUser(Long userId);

    boolean userPreviouslyRatedRecipe(Long recipeId, Long userId);

    Rating rateRecipe(RatingDTO ratingDTO);
}
