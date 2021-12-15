package com.ironhack.reviewservice.service.impl;

import com.ironhack.reviewservice.dao.Review;
import com.ironhack.reviewservice.dto.ReviewResponse;
import com.ironhack.reviewservice.dto.ReviewResponseDTO;
import com.ironhack.reviewservice.repositories.ReviewRepository;
import com.ironhack.reviewservice.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Override
    public List<ReviewResponse> getByRecipeId(Long recipeId) {
        return reviewRepository.getReviewResponseByRecipeId(recipeId);
    }

    @Override
    public List<ReviewResponse> getByUserId(Long userId) {
        return reviewRepository.getReviewResponseByUserId(userId);
    }


    @Override
    public Review getById(Long id) {
        Optional<Review> review = reviewRepository.findById(id);
        if(review.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No review found with id: " + id);
        return review.get();
    }





}
