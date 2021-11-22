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
        Ingredient ingredient2 = new Ingredient("Pepper", 10, Measurement.GRAM);
        Ingredient ingredient3 = new Ingredient("Chill Oil", 10, null);
        List<String> method = List.of("Step1", "Step2", "Step3");
        Recipe recipe1 = new Recipe(
                "Test Recipe1",
                List.of(ingredient1,ingredient2,ingredient3),
                method,
                1L,
                Cuisine.BRITISH,
                List.of(Diet.GLUTEN_FREE)
        );

        recipeRepository.save(recipe1);
    }
}
