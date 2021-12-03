package com.ironhack.ratingservice.dao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double rating;
    private Long recipeId;
    private Long userId;

    public Rating(double rating, Long recipeId, Long userId) {
        this.rating = rating;
        this.recipeId = recipeId;
        this.userId = userId;
    }
}
