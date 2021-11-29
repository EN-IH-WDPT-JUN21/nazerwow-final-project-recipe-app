package com.ironhack.recipeservice.services.impl;

import com.ironhack.recipeservice.dao.Ingredient;
import com.ironhack.recipeservice.dao.Recipe;
import com.ironhack.recipeservice.dto.CreateRecipeDTO;
import com.ironhack.recipeservice.dto.RecipeDTO;
import com.ironhack.recipeservice.enums.Cuisine;
import com.ironhack.recipeservice.enums.Diet;
import com.ironhack.recipeservice.enums.Measurement;
import com.ironhack.recipeservice.repositories.RecipeRepository;
import com.ironhack.recipeservice.services.RecipeService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RecipeServiceImplTest {

    @Autowired
    private RecipeRepository recipeRepository;
    @Autowired
    private RecipeService recipeService;

    Recipe recipe1;
    Ingredient ingredient1;
    Ingredient ingredient2;
    Ingredient ingredient3;
    List<String> method;

    @BeforeEach
    void setUp() {
        ingredient1 = new Ingredient("Salt", 10, Measurement.G);
        ingredient2 = new Ingredient("Pepper", 10, Measurement.GRAM);
        ingredient3 = new Ingredient("Chill Oil", 10, null);
        method = List.of("Step1", "Step2", "Step3");
        recipe1 = new Recipe(
                "Test Recipe1",
                List.of(ingredient1,ingredient2,ingredient3),
                method,
                10,
                20,
                1L,
                Cuisine.BRITISH,
                List.of(Diet.GLUTEN_FREE)
        );
        recipeRepository.save(recipe1);
    }

    @AfterEach
    void tearDown() {
        recipeRepository.deleteAll();
    }

    @Test
    void findAll_Valid() {
        var recipeList = recipeService.findAll();
        assertEquals(1, recipeList.size());
    }

    @Test
    void findById_Valid() {
        var recipe = recipeService.findById(recipe1.getId());
        assertEquals(recipe1.getName(), recipe.getName());
    }

    @Test
    void findById_Valid_Properties() {
        var recipe = recipeService.findById(recipe1.getId());
        assertEquals(recipe1.getName(), recipe.getName());
        assertEquals(ingredient1.getName(), recipe.getIngredients().get(0).getName());
        assertEquals(ingredient1.getMeasurement(), recipe.getIngredients().get(0).getMeasurement());
        assertEquals(ingredient1.getQuantity(), recipe.getIngredients().get(0).getQuantity());
        assertEquals(3, recipe.getIngredients().size());
        assertEquals(Diet.GLUTEN_FREE, recipe.getDiets().get(0));
        assertEquals("Step1", recipe.getMethod().get(0));
        assertEquals(Cuisine.BRITISH, recipe.getCuisine());
    }


    @Test
    void findById_ThrowsException() {
        assertThrows(ResponseStatusException.class, () -> recipeService.findById(recipe1.getId() - 60L));
    }

    @Test
    void deleteRecipe() {
        var recipeListBefore = recipeService.findAll().size();
        recipeService.deleteRecipe(recipe1.getId());
        var recipeListAfter = recipeService.findAll().size();
        assertEquals(recipeListBefore - 1, recipeListAfter);
    }

    @Test
    void deleteRecipe_ThrowsException() {
        assertThrows(ResponseStatusException.class, () ->  recipeService.deleteRecipe(recipe1.getId() - 60L));
    }

    @Test
    void deleteRecipe_ThrowsException2() {
        recipeService.deleteRecipe(recipe1.getId());
        assertThrows(ResponseStatusException.class, () ->  recipeService.deleteRecipe(recipe1.getId()));
    }

    @Test
    void addRecipe_Valid_DatabaseIncrease() {
        CreateRecipeDTO createRecipeDTO = new CreateRecipeDTO(
                "Test Recipe2",
                List.of(ingredient2, ingredient3),
                method,
                10,
                20,
                2L,
                Cuisine.CARIBBEAN,
                List.of(Diet.VEGETARIAN, Diet.GLUTEN_FREE)
        );
        var recipeListBefore = recipeService.findAll().size();
        Recipe recipe = recipeService.addRecipe(createRecipeDTO);
        var recipeListAfter = recipeService.findAll().size();
        assertEquals(recipeListBefore + 1, recipeListAfter);
    }

    @Test
    void addRecipe_Valid_CheckValues() {
        CreateRecipeDTO createRecipeDTO = new CreateRecipeDTO(
                "Test Recipe2",
                List.of(ingredient2, ingredient3),
                method,
                10,
                20,
                2L,
                Cuisine.CARIBBEAN,
                List.of(Diet.VEGETARIAN, Diet.GLUTEN_FREE)
        );
        Recipe recipe = recipeService.addRecipe(createRecipeDTO);
        assertEquals(createRecipeDTO.getName(), recipe.getName());
        assertEquals(createRecipeDTO.getCuisine(), recipe.getCuisine());
        assertEquals(createRecipeDTO.getDiets(), recipe.getDiets());
        assertEquals(createRecipeDTO.getIngredients(), recipe.getIngredients());
        assertEquals(LocalDate.now().getDayOfMonth(), recipe.getCreatedDate().getDayOfMonth());
        assertEquals(LocalDate.now().getDayOfMonth(), recipe.getEditedDate().getDayOfMonth());
    }

    @Test
    void updateRecipe_Valid_NoDataBaseIncrease() {
        RecipeDTO recipeDTO = new RecipeDTO(
                "Test Recipe2",
                List.of(ingredient2, ingredient3),
                method,
                10,
                20,
                2L,
                Cuisine.CARIBBEAN,
                List.of(Diet.VEGETARIAN, Diet.GLUTEN_FREE)
        );
        var recipeListBefore = recipeService.findAll().size();
        Recipe recipe = recipeService.updateRecipe(recipe1.getId(), recipeDTO);
        var recipeListAfter = recipeService.findAll().size();
        assertEquals(recipeListBefore, recipeListAfter);
    }

    @Test
    void updateRecipe_Valid_TestUpdated() {
        recipe1.setCreatedDate(LocalDate.now().minusYears(50L));
        recipeRepository.save(recipe1);
        System.out.println(recipe1.getCreatedDate());
        RecipeDTO recipeDTO = new RecipeDTO(
                "Test Recipe2",
                List.of(ingredient2, ingredient3),
                method,
                10,
                20,
                2L,
                Cuisine.CARIBBEAN,
                List.of(Diet.VEGETARIAN, Diet.GLUTEN_FREE)
        );
        recipeService.updateRecipe(recipe1.getId(), recipeDTO);
        Recipe updatedRecipe = recipeService.findById(recipe1.getId());
        assertEquals(recipe1.getId(), updatedRecipe.getId());
        assertEquals(recipeDTO.getName(), updatedRecipe.getName());
        assertEquals(recipeDTO.getCuisine(), updatedRecipe.getCuisine());
        assertEquals(recipeDTO.getDiets(), updatedRecipe.getDiets());
        assertEquals(recipeDTO.getIngredients().size(), updatedRecipe.getIngredients().size());
        assertNotEquals(updatedRecipe.getCreatedDate().getYear(), updatedRecipe.getEditedDate().getYear());
    }

    @Test
    void updateRecipe_Valid_TestNullFilter() {
        recipe1.setCreatedDate(LocalDate.now().minusYears(50L));
        recipeRepository.save(recipe1);
        Recipe originalRecipe = recipeService.findById(recipe1.getId());
        RecipeDTO recipeDTO = new RecipeDTO(
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null
        );
        recipeService.updateRecipe(recipe1.getId(), recipeDTO);
        Recipe updatedRecipe = recipeService.findById(recipe1.getId());
        assertEquals(originalRecipe.getId(), updatedRecipe.getId());
        assertEquals(originalRecipe.getName(), updatedRecipe.getName());
        assertEquals(originalRecipe.getCuisine(), updatedRecipe.getCuisine());
        assertEquals(originalRecipe.getDiets().get(0), updatedRecipe.getDiets().get(0));
        assertEquals(originalRecipe.getIngredients().size(), updatedRecipe.getIngredients().size());
        assertNotEquals(updatedRecipe.getCreatedDate().getYear(), updatedRecipe.getEditedDate().getYear());
    }

    @Test
    void updateRecipe_ThrowsException() {
        RecipeDTO recipeDTO = new RecipeDTO(
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null
        );
        assertThrows(ResponseStatusException.class, () ->  recipeService.updateRecipe(recipe1.getId() - 60L,
                recipeDTO));
    }

    @Test
    void findByUserId_valid() {
        var recipeFound = recipeService.findByUserId(recipe1.getAuthorId());
        assertEquals(recipe1.getName(), recipeFound.get(0).getName());
    }

    @Test
    void findByUserId_Invalid() {
        assertThrows(ResponseStatusException.class, () -> recipeService.findByUserId(recipe1.getAuthorId() - 65L));
    }
}