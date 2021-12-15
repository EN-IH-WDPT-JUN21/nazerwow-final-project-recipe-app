package com.ironhack.reviewservice.controller.impl;

import com.ironhack.reviewservice.controller.ReviewController;
import com.ironhack.reviewservice.dao.Review;
import com.ironhack.reviewservice.dto.ReviewResponse;
import com.ironhack.reviewservice.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@Controller
@RequestMapping("/api/v1/reviews")
public class ReviewControllerImpl implements ReviewController {

    @Autowired
    private ReviewService reviewService;

    @Override
    @GetMapping("/recipe/{recipeId}")
    @ResponseStatus(HttpStatus.OK)
    public List<ReviewResponse> getByRecipeId(@PathVariable(name = "recipeId") Long recipeId){
        return reviewService.getByRecipeId(recipeId);
    }

    @Override
    @GetMapping("/user/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public List<ReviewResponse> getByUserId(@PathVariable(name = "userId") Long userId){
        return reviewService.getByUserId(userId);
    }

    @Override
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Review getById(@PathVariable(name = "id") Long id){
        return reviewService.getById(id);
    }


}
