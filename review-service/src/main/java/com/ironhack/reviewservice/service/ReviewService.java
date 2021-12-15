package com.ironhack.reviewservice.service;

import com.ironhack.reviewservice.dao.Review;
import com.ironhack.reviewservice.dto.ReviewResponse;

import java.util.List;

public interface ReviewService {

    List<ReviewResponse> getByRecipeId(Long recipeId);

    List<ReviewResponse> getByUserId(Long userId);

    Review getByid(Long id);
}
