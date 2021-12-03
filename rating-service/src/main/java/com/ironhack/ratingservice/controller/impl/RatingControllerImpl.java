package com.ironhack.ratingservice.controller.impl;

import com.ironhack.ratingservice.dao.Rating;
import com.ironhack.ratingservice.dto.RatingDTO;
import com.ironhack.ratingservice.dto.RecipeDTO;
import com.ironhack.ratingservice.services.RatingService;
import com.ironhack.ratingservice.services.impl.RatingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/ratings")
public class RatingControllerImpl implements com.ironhack.ratingservice.controller.RatingController {

    @Autowired
    private RatingService ratingService;

    @Override
    @GetMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public List<RecipeDTO> findByUserId(@PathVariable(name = "userId") Long userId){
        return ratingService.findByUserId(userId);
    }

    @Override
    @GetMapping("/{recipeId}/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public boolean userPreviouslyRatedRecipe(@PathVariable(name = "recipeId") Long recipeId,
                                             @PathVariable(name = "userId") Long userId){
        return ratingService.userPreviouslyRatedRecipe(recipeId, userId);
    }

    @Override
    @GetMapping("/recipe/{recipeId}")
    @ResponseStatus(HttpStatus.OK)
    public Double getAverageRatingForRecipe(@PathVariable(name = "recipeId") Long recipeId){
        return ratingService.getAverageRatingForRecipe(recipeId);
    }

    @Override
    @GetMapping("/top10recipes")
    @ResponseStatus(HttpStatus.OK)
    public List<RecipeDTO> getTop10Recipes() {
        return ratingService.getTop10Recipes(10);
    }

    @Override
    @GetMapping("/top10recipes/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public List<RecipeDTO> getTop10RecipesForUser(@PathVariable(name = "userId") Long userId){
        return ratingService.getTop10RecipesForUser(userId);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Rating rateRecipe(@RequestBody RatingDTO ratingDTO){
        return ratingService.rateRecipe(ratingDTO);
    }



}
