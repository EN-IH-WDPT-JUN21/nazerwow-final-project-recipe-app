package com.ironhack.ratingservice.services.impl;

import com.ironhack.ratingservice.dao.Rating;
import com.ironhack.ratingservice.dto.RatingDTO;
import com.ironhack.ratingservice.dto.RecipeDTO;
import com.ironhack.ratingservice.exceptions.RatingNotFoundException;
import com.ironhack.ratingservice.proxies.RecipeServiceProxy;
import com.ironhack.ratingservice.repositories.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RatingServiceImpl implements com.ironhack.ratingservice.services.RatingService {

    @Autowired
    private RatingRepository ratingRepository;
    @Autowired
    private RecipeServiceProxy recipeServiceProxy;


    @Override
    public List<RecipeDTO> findByUserId(Long userId) {
        List<Rating> ratingList = ratingRepository.findByUserId(userId);
        if (ratingList.size() == 0) throw new RatingNotFoundException("You have not rated any recipes");
        return getRecipeDTOSFromRatingList(ratingList);
    }


    @Override
    public Double getAverageRatingForRecipe(Long recipeId) {
        return ratingRepository.getAverageRatingForRecipe(recipeId).orElseThrow(() -> new RatingNotFoundException("This recipe has yet to be rated"));
    }

    @Override
    public List<RecipeDTO> getTop10Recipes(int i) {
        List<Rating> ratingList = ratingRepository.getTopRatedRecipesLimitBy(i);
        if (ratingList.size() == 0) throw new RatingNotFoundException("No recipes have been rated yet");
        return getRecipeDTOSFromRatingList(ratingList);
    }

    @Override
    public List<RecipeDTO> getTop10RecipesForUser(Long userId) {
        List<Rating> ratingList = ratingRepository.getTopRatedRecipesByUserIdLimitBy(userId, 10);
        if (ratingList.size() == 0) throw new RatingNotFoundException("You have not rated any recipes");
        return getRecipeDTOSFromRatingList(ratingList);
    }

    @Override
    public boolean userPreviouslyRatedRecipe(Long recipeId, Long userId) {
        Optional<Rating> rating = ratingRepository.findByRecipeIdAndUserId(recipeId, userId);
        return rating.isPresent();
    }

    private List<RecipeDTO> getRecipeDTOSFromRatingList(List<Rating> ratingList) {
        List<RecipeDTO> recipeDTOList = new ArrayList<>();
        for(Rating rating : ratingList){
            recipeDTOList.add(recipeServiceProxy.findById(rating.getRecipeId()));
        }
        return recipeDTOList;
    }


}
