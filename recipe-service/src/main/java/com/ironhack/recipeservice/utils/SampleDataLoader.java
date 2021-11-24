package com.ironhack.recipeservice.utils;

import com.ironhack.recipeservice.dao.Ingredient;
import com.ironhack.recipeservice.dao.Recipe;
import com.ironhack.recipeservice.enums.Cuisine;
import com.ironhack.recipeservice.enums.Diet;
import com.ironhack.recipeservice.enums.Measurement;
import com.ironhack.recipeservice.repositories.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SampleDataLoader implements CommandLineRunner {

    @Autowired
    RecipeRepository recipeRepository;

    @Override
    public void run(String... args) throws Exception {

        Ingredient ingredient1 = new Ingredient("Salt", 10, Measurement.G);
        Ingredient ingredient2 = new Ingredient("Pepper", 10, Measurement.G);
        Ingredient ingredient3 = new Ingredient("Chill Oil", 10, Measurement.ML);
        Ingredient ingredient4 = new Ingredient("Cloves of Garlic", 3, null);
        Ingredient ingredient5 = new Ingredient("Water", 100, Measurement.ML);
        Ingredient ingredient6 = new Ingredient("Chill Powder", 10, Measurement.G);
        Ingredient ingredient7 = new Ingredient("Chicken Stock", 10, Measurement.G);
        Ingredient ingredient8 = new Ingredient("Paprika", 10, Measurement.G);
        Ingredient ingredient9 = new Ingredient("Oregano", 10, Measurement.G);
        List<String> method = List.of("This is step 1 of how to make the recipe. This is step 1 of how to make the recipe.",
                "This is step 2 of how to make the recipe. This is step 2 of how to make the recipe.",
                "This is step 3 of how to make the recipe. This is step 3 of how to make the recipe. This is step 3 of how to make the recipe.");
        Recipe recipe1 = new Recipe(
                "Test Recipe1",
                List.of(ingredient1,ingredient2,ingredient3, ingredient4),
                method,
                5,
                10,
                1L,
                Cuisine.BRITISH,
                List.of(Diet.KETO, Diet.DAIRY_FREE, Diet.HALAL)
        );
        Recipe recipe2 = new Recipe(
                "Test Recipe2",
                List.of(ingredient1,ingredient2,ingredient3, ingredient5),
                method,
                5,
                25,
                1L,
                Cuisine.AMERICAN,
                List.of()
        );
        Recipe recipe3 = new Recipe(
                "Creamy mushroom Linguine with Shallots and White wine Creamy mushroom Linguine with Shallots and White wine Creamy mushroom Linguine with Shallots and White wine ",
                List.of(ingredient1,ingredient2,ingredient3, ingredient6, ingredient7, ingredient8, ingredient9),
                method,
                14,
                20,
                1L,
                Cuisine.AFRICAN,
                List.of(Diet.VEGETARIAN)
        );
        Recipe recipe4 = new Recipe(
                "Test Recipe1",
                List.of(ingredient1,ingredient2,ingredient3, ingredient4, ingredient5, ingredient6, ingredient7),
                method,
                10,
                45,
                1L,
                Cuisine.SPANISH,
                List.of(Diet.VEGAN)
        );
        Recipe recipe5 = new Recipe(
                "Test Recipe1",
                List.of(ingredient1,ingredient2,ingredient3, ingredient8, ingredient9, ingredient4),
                method,
                10,
                45,
                1L,
                Cuisine.KOREAN,
                List.of(Diet.GLUTEN_FREE)
        );
        Recipe recipe6 = new Recipe(
                "Test Recipe1",
                List.of(ingredient1,ingredient2,ingredient3, ingredient7, ingredient8),
                method,
                15,
                60,
                1L,
                Cuisine.CHINESE,
                List.of()
        );
        Recipe recipe7 = new Recipe(
                "Test Recipe1",
                List.of(ingredient1,ingredient2,ingredient3, ingredient4, ingredient5),
                method,
                2,
                30,
                1L,
                Cuisine.JAPANESE,
                List.of(Diet.GLUTEN_FREE)
        );

        recipeRepository.saveAll(List.of(recipe1, recipe2, recipe3, recipe4, recipe5, recipe6, recipe7));
    }
}
