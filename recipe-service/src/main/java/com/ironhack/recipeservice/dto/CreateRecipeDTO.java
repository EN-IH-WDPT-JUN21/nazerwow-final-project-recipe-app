package com.ironhack.recipeservice.dto;

import com.ironhack.recipeservice.dao.Ingredient;
import com.ironhack.recipeservice.enums.Cuisine;
import com.ironhack.recipeservice.enums.Diet;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CreateRecipeDTO {

    @NotNull
    private String name;
    @NotNull
    private List<Ingredient> ingredients;
    @NotNull
    private List<String> method;
    @NotNull
    private Long authorId;
    @NotNull
    @Enumerated(EnumType.STRING)
    private Cuisine cuisine;
    @NotNull
    @Enumerated(EnumType.STRING)
    private List<Diet> diets;



}