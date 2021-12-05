package com.ironhack.ratingservice.dto;

import com.ironhack.ratingservice.enums.Cuisine;
import com.ironhack.ratingservice.enums.Diet;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RecipeDTO {


    private Long id;
    private String name;
    private List<Ingredient> ingredients;
    private List<String> method;
    private Integer prepTime;
    private Integer cookingTime;
    private Long authorId;
    @Enumerated(EnumType.STRING)
    private Cuisine cuisine;
    @Enumerated(EnumType.STRING)
    private List<Diet> diets;
    private String imageUrl;

    public RecipeDTO(String name, List<Ingredient> ingredients, List<String> method, Integer prepTime, Integer cookingTime, Long authorId, Cuisine cuisine, List<Diet> diets, String imageUrl) {
        this.name = name;
        this.ingredients = ingredients;
        this.method = method;
        this.prepTime = prepTime;
        this.cookingTime = cookingTime;
        this.authorId = authorId;
        this.cuisine = cuisine;
        this.diets = diets;
        this.imageUrl = imageUrl;
    }
}

