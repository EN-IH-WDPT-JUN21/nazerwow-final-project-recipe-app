package com.ironhack.recipeservice.dto;

import com.ironhack.recipeservice.dao.Ingredient;
import com.ironhack.recipeservice.enums.Cuisine;
import com.ironhack.recipeservice.enums.Diet;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
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
    private Long authorId;
    @Enumerated(EnumType.STRING)
    private Cuisine cuisine;
    @Enumerated(EnumType.STRING)
    private List<Diet> diets;

    public RecipeDTO(String name, List<Ingredient> ingredients, List<String> method, Long authorId, Cuisine cuisine, List<Diet> diets) {
        this.name = name;
        this.ingredients = ingredients;
        this.method = method;
        this.authorId = authorId;
        this.cuisine = cuisine;
        this.diets = diets;
    }
}

