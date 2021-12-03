package com.ironhack.ratingservice.services.impl;

import com.ironhack.ratingservice.dao.Rating;
import com.ironhack.ratingservice.dto.RatingDTO;
import com.ironhack.ratingservice.dto.RecipeDTO;
import com.ironhack.ratingservice.dto.TopRecipeDTO;
import com.ironhack.ratingservice.exceptions.RatingNotFoundException;
import com.ironhack.ratingservice.proxies.RecipeServiceProxy;
import com.ironhack.ratingservice.repositories.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
    public List<RecipeDTO> getTop10Recipes(int limit) {
        List<TopRecipeDTO> topRecipeDTOS = convertRepoResultToFavRecipeCountDTOList(limit);
        if (topRecipeDTOS.size() == 0) throw new RatingNotFoundException("No recipes have been rated yet");
        return getRecipeDTOSFromTopRecipeDTOList(topRecipeDTOS);
    }

    @Override
    public List<RecipeDTO> getTop10RecipesForUser(Long userId) {
        List<TopRecipeDTO> topRecipeDTOS = convertUsersTopRecipesToFavRecipeCountDTOList(userId, 10);
        if (topRecipeDTOS.size() == 0) throw new RatingNotFoundException("You have not rated any recipes");
        return getRecipeDTOSFromTopRecipeDTOList(topRecipeDTOS);
    }

    @Override
    public boolean userPreviouslyRatedRecipe(Long recipeId, Long userId) {
        Optional<Rating> rating = ratingRepository.findByRecipeIdAndUserId(recipeId, userId);
        return rating.isPresent();
    }

    @Override
    public Rating rateRecipe(RatingDTO ratingDTO) {
        if(userPreviouslyRatedRecipe(ratingDTO.getRecipeId(), ratingDTO.getUserId())) {
            Rating rating = ratingRepository.findByRecipeIdAndUserId(ratingDTO.getRecipeId(), ratingDTO.getUserId()).get();
            rating.setRating(ratingDTO.getRating());
            return ratingRepository.save(rating);
        } else {
            Rating rating = new Rating(
                    ratingDTO.getRating(),
                    ratingDTO.getRecipeId(),
                    ratingDTO.getUserId());
            return ratingRepository.save(rating);
        }
    }

    private List<RecipeDTO> getRecipeDTOSFromRatingList(List<Rating> ratingList) {
        List<RecipeDTO> recipeDTOList = new ArrayList<>();
        for(Rating rating : ratingList){
            recipeDTOList.add(recipeServiceProxy.findById(rating.getRecipeId()));
        }
        return recipeDTOList;
    }

    private List<RecipeDTO> getRecipeDTOSFromTopRecipeDTOList(List<TopRecipeDTO> topRecipeDTOS) {
        List<RecipeDTO> recipeDTOList = new ArrayList<>();
        for(TopRecipeDTO topRecipeDTO : topRecipeDTOS){
            recipeDTOList.add(recipeServiceProxy.findById(topRecipeDTO.getRecipeId()));
        }
        return recipeDTOList;
    }

    private List<TopRecipeDTO> convertRepoResultToFavRecipeCountDTOList(int limit) {
        List<Long[]> results = ratingRepository.getTopRatedRecipesLimitBy(limit);
        if(results.size() == 0) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Ratings have been completed");
        List<TopRecipeDTO> topRecipeDTOS = new ArrayList<>();
        for(Long[] result : results){
            topRecipeDTOS.add(new TopRecipeDTO(result[0]));
        }
        return topRecipeDTOS;
    }
    private List<TopRecipeDTO> convertUsersTopRecipesToFavRecipeCountDTOList(Long userid, int limit) {
        List<Long[]> results = ratingRepository.getTopRatedRecipesByUserIdLimitBy(userid, limit);
        if(results.size() == 0) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Ratings have been completed");
        List<TopRecipeDTO> topRecipeDTOS = new ArrayList<>();
        for(Long[] result : results){
            topRecipeDTOS.add(new TopRecipeDTO(result[0]));
        }
        return topRecipeDTOS;
    }
}
