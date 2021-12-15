package com.ironhack.reviewservice.service;

import com.ironhack.reviewservice.dao.Review;

import java.util.List;

public interface ReviewService {

    List<Review> getByRecipeId(Long recipeId);

    List<Review> getByUserId(Long userId);

    Review getByid(Long id);
}
