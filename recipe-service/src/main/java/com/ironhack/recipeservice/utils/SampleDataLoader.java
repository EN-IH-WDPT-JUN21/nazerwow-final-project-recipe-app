package com.ironhack.recipeservice.utils;

import com.github.javafaker.Faker;
import com.ironhack.recipeservice.dao.Ingredient;
import com.ironhack.recipeservice.dao.Recipe;
import com.ironhack.recipeservice.enums.Cuisine;
import com.ironhack.recipeservice.enums.Diet;
import com.ironhack.recipeservice.enums.Measurement;
import com.ironhack.recipeservice.repositories.RecipeRepository;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class SampleDataLoader implements CommandLineRunner {

    @Autowired
    private RecipeRepository recipeRepository;
    @Autowired
    private Faker faker;


    @Override
    public void run(String... args) {

        Measurement[] measurementList = Measurement.class.getEnumConstants();
        Cuisine[] cuisines = Cuisine.class.getEnumConstants();
        Diet[] diets = Diet.class.getEnumConstants();

        List<String> sampleMethod = IntStream.rangeClosed(1, 5)
                .mapToObj(i -> faker.chuckNorris().fact()).collect(Collectors.toList());

        List<Ingredient> sampleIngredients = IntStream.rangeClosed(1, 100)
                .mapToObj(i -> new Ingredient(
                        faker.food().ingredient(),
                        faker.number().randomDigit(),
                        measurementList[faker.number().numberBetween(0, measurementList.length)]
                        )).collect(Collectors.toList());

        List<Recipe> sampleRecipes = IntStream.rangeClosed(1, 100)
                .mapToObj(i -> new Recipe(
                        faker.food().dish(),
                        randomIngredientList(sampleIngredients),
                        sampleMethod,
                        faker.number().numberBetween(0, 19),
                        faker.number().numberBetween(10, 60),
                        faker.number().numberBetween(1L, 20L),
                        cuisines[faker.number().numberBetween(0, cuisines.length)],
                        List.of(diets[faker.number().numberBetween(0, diets.length)],
                                diets[faker.number().numberBetween(0, diets.length)])))
                .collect(Collectors.toList());

        recipeRepository.saveAll(sampleRecipes);
    }

    private Ingredient randomIngredient(List<Ingredient> ingredientList) {
        return ingredientList.get(faker.number().numberBetween(0, 99));
    }

    private List<Ingredient> randomIngredientList(List<Ingredient> ingredientList){
        List<Ingredient> randomList = new ArrayList<>();
        for(int i = 0; i < faker.number().numberBetween(2, 12); i ++){
            randomList.add(randomIngredient(ingredientList));
        }
        return randomList;
    }
}
