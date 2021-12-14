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

        List<Ingredient> sampleIngredients = IntStream.rangeClosed(1, 20)
                .mapToObj(i -> new Ingredient(
                        faker.food().ingredient(),
                        faker.number().randomDigit(),
                        measurementList[faker.number().numberBetween(0, measurementList.length)]
                        )).collect(Collectors.toList());

        List<Recipe> sampleRecipes = IntStream.rangeClosed(1, 20)
                .mapToObj(i -> new Recipe(
                        faker.food().dish(),
                        randomIngredientList(sampleIngredients),
                        sampleMethod,
                        faker.number().numberBetween(0, 19),
                        faker.number().numberBetween(10, 60),
                        faker.number().numberBetween(1L, 20L),
                        cuisines[faker.number().numberBetween(0, cuisines.length)],
                        List.of(diets[faker.number().numberBetween(0, diets.length)],
                                diets[faker.number().numberBetween(0, diets.length)]),
                        randomFoodImage()))
                .collect(Collectors.toList());

        recipeRepository.saveAll(realSampleRecipes());
        recipeRepository.saveAll(sampleRecipes);
    }

    private Ingredient randomIngredient(List<Ingredient> ingredientList) {
        return ingredientList.get(faker.number().numberBetween(0, 20));
    }

    private List<Ingredient> randomIngredientList(List<Ingredient> ingredientList){
        List<Ingredient> randomList = new ArrayList<>();
        for(int i = 0; i < faker.number().numberBetween(2, 12); i ++){
            randomList.add(randomIngredient(ingredientList));
        }
        return randomList;
    }

    private String randomFoodImage() {
        List<String> imageList = List.of(
                "../../../../../assets/images/pizza.jpg",
                "../../../../../assets/images/sausage-chips.jpg",
                "../../../../../assets/images/pasta-beans.jpg",
                "../../../../../assets/images/bbq-ribs.jpg",
                "../../../../../assets/images/cheeseburger.jpg",
                "../../../../../assets/images/bruschette.png",
                "../../../../../assets/images/kebab.jpg",
                "../../../../../assets/images/fish-chips.jpg",
                "../../../../../assets/images/katsu-curry.jpg",
                "../../../../../assets/images/seafood-paella.png"
        );
        return imageList.get(faker.number().numberBetween(0, 9));
    }

    private List<Recipe> realSampleRecipes() {
        return List.of(
                new Recipe(
                        "Chilli Chicken Thighs with Rice and Cucumber Yoghurt",
                        List.of(new Ingredient(
                                "Chicken Thighs (Skin On)",
                                1,
                                Measurement.KILOGRAM),
                                new Ingredient(
                                        "East End Extra Hot Chilli Powder",
                                        1.5,
                                        Measurement.TABLESPOON),
                                new Ingredient(
                                        "Fish Sauce",
                                        2,
                                        Measurement.TABLESPOON),
                                new Ingredient(
                                        "Greek Yoghurt",
                                        1,
                                        Measurement.PINT
                                ),new Ingredient(
                                        "Rice",
                                        200,
                                        Measurement.G
                                ),
                                new Ingredient(
                                        "Cucumber",
                                        0.5)),
                        List.of("Pre-heat the oven to 170c",
                                "Slice across each chicken thigh 2 - 3 times",
                                "In a bowl mix the chilli powder and fish sauce into a paste, add the chicken thighs and" +
                                        "mix to ensure they are full covered ",
                                "Cook chicken thighs in the oven for 40 - 45 minutes",
                                "Meanwhile put the yoghurt into a bowl, and grate the cucumber into the yoghurt",
                                "With around 20 minutes to go cook the rice",
                                "Once everything is cooked, serve the Chicken Thighs, Rice and Cucumber Yoghurt on a plate"),
                        5,
                        45,
                        21L,
                        Cuisine.BRITISH,
                        List.of(Diet.GLUTEN_FREE),
                        "../../../../../assets/images/chilli-chicken.jpg"
                        ),
                new Recipe(
                        "Beninese Beef Stew",
                        List.of(new Ingredient(
                                        "Diced Beef",
                                        500,
                                        Measurement.G),
                                new Ingredient(
                                        "Garlic Cloves",
                                        2),
                                new Ingredient(
                                        "Brown Onion",
                                        1),
                                new Ingredient(
                                        "Curry Powder",
                                        1,
                                        Measurement.TABLESPOON
                                ),new Ingredient(
                                        "Peanut Butter",
                                        1,
                                        Measurement.TABLESPOON
                                ),new Ingredient(
                                        "Cayenne Pepper",
                                        1,
                                        Measurement.TEASPOON
                                ),new Ingredient(
                                        "Coconut Milk",
                                        400,
                                        Measurement.ML
                                ),new Ingredient(
                                        "Water",
                                        750,
                                        Measurement.ML
                                )),
                        List.of("Heat some oil in a large saucepan on medium-high, once hot brown the beef for 5 minutes",
                                "Meanwile dice the Onion and Garlic Cloves",
                                "Once browned, remove the beef from the pan, reduce the heat to medium and fry the diced" +
                                        "onion and garlic for 5 minutes",
                                "Add the beef back to the pan, along with all the other ingredients",
                                "Bring to the boil, once boiling reduce the temperature down to low and cook for 2 hours",
                                "Serve with the side of your choice (We love rice with ours)"),
                        5,
                        120,
                        21L,
                        Cuisine.AFRICAN,
                        List.of(Diet.GLUTEN_FREE),
                        "../../../../../assets/images/beef-stew.jpg"
                ),
                new Recipe(
                        "Jerk Lamb Chops",
                        List.of(new Ingredient(
                                        "Lamb Chops",
                                        4),
                                new Ingredient(
                                        "Jerk Sauce",
                                        1,
                                        Measurement.TABLESPOON),
                                new Ingredient(
                                        "Garlic Clove",
                                        1),
                                new Ingredient(
                                        "Thyme",
                                        1,
                                        Measurement.TEASPOON
                                ),new Ingredient(
                                        "Cooking Oil",
                                        3,
                                        Measurement.TABLESPOON
                                ),new Ingredient(
                                        "Salt",
                                        .5,
                                        Measurement.TEASPOON
                                ),new Ingredient(
                                        "Pepper",
                                        .5,
                                        Measurement.TEASPOON
                                )),
                        List.of("Add all ingredients to the bowl and marinate for 1 hour prior to cooking",
                                "When ready to cook, heat some oil in a frying pan on a high heat, pre-heat oven to " +
                                        "400f",
                                "Sear Lamb chops in the pan for 2 to 3 minutes each side",
                                "Put the Lamb chops in the oven for 5 - 15 minutes (5 for rare, 15 for medium to well"),
                        60,
                        20,
                        21L,
                        Cuisine.CARIBBEAN,
                        List.of(Diet.GLUTEN_FREE),
                        "../../../../../assets/images/jerk-lamb.jpeg"
                )

        );
    }
}
