package com.ironhack.reviewservice.controller;

import com.ironhack.reviewservice.dao.Review;
import com.ironhack.reviewservice.dto.ReviewDTO;
import com.ironhack.reviewservice.dto.ReviewResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface ReviewController {

    @GetMapping("/recipe/{recipeId}")
    @ResponseStatus(HttpStatus.OK)
    List<ReviewResponse> getByRecipeId(@PathVariable(name = "recipeId") Long recipeId);

    @GetMapping("/user/{userid}")
    @ResponseStatus(HttpStatus.OK)
    List<ReviewResponse> getByUserId(@PathVariable(name = "userId") Long userId);

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    Review getById(@PathVariable(name = "id") Long id);

    @PutMapping("/edit")
    @ResponseStatus(HttpStatus.ACCEPTED)
    Review editReview(@RequestBody ReviewDTO reviewDTO);

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.ACCEPTED)
    Review addReview(@RequestBody ReviewDTO reviewDTO);

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    void deleteReview(@PathVariable(name="id") Long id);

    @GetMapping("/reviewed/{userId}/{recipeId}")
    @ResponseStatus(HttpStatus.OK)
    boolean previouslyReviewed(@PathVariable(name="userId") Long userId,
                               @PathVariable(name="recipeId") Long recipeId);
}
