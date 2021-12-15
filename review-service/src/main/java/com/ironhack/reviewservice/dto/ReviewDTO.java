package com.ironhack.reviewservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ReviewDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;
    private String content;
    private Long recipeId;
    private Long userId;
    private Long ratingId;

    public ReviewDTO(String title, String content, Long recipeId, Long userId, Long ratingId) {
        this.title = title;
        this.content = content;
        this.recipeId = recipeId;
        this.userId = userId;
        this.ratingId = ratingId;
    }
}