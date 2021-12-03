package com.ironhack.ratingservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RatingDTO {

    private Long id;

    private double rating;
    private Long recipeId;
    private Long userId;

    public RatingDTO(double rating, Long recipeId, Long userId) {
        this.rating = rating;
        this.recipeId = recipeId;
        this.userId = userId;
    }
}