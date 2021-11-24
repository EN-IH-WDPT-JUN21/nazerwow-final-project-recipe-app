package com.ironhack.recipeservice.controllers.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.recipeservice.dao.Ingredient;
import com.ironhack.recipeservice.dao.Recipe;
import com.ironhack.recipeservice.dto.CreateRecipeDTO;
import com.ironhack.recipeservice.dto.RecipeDTO;
import com.ironhack.recipeservice.enums.Cuisine;
import com.ironhack.recipeservice.enums.Diet;
import com.ironhack.recipeservice.enums.Measurement;
import com.ironhack.recipeservice.repositories.RecipeRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class RecipeControllerImplTest {

    @Autowired
    private RecipeRepository recipeRepository;
    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();


    Recipe recipe1;
    Recipe recipe2;
    Ingredient ingredient1;
    Ingredient ingredient2;
    Ingredient ingredient3;
    List<String> method;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
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
        recipe2 = new Recipe(
                "Test Recipe2",
                List.of(ingredient1,ingredient2,ingredient3),
                method,
                10,
                20,
                1L,
                Cuisine.AFRICAN,
                List.of(Diet.VEGAN)
        );
        recipeRepository.saveAll(List.of(recipe1, recipe2));
    }

    @AfterEach
    void tearDown() {
        recipeRepository.deleteAll();
    }


    @Test
    void findAll_Valid() throws Exception {
        MvcResult result = mockMvc.perform(get("/api/v1/recipes"))
                .andExpect(status().isOk())
                .andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("Test Recipe1"));
        assertTrue(result.getResponse().getContentAsString().contains("Test Recipe2"));
        assertTrue(result.getResponse().getContentAsString().contains("Salt"));
        assertTrue(result.getResponse().getContentAsString().contains("Pepper"));
        assertTrue(result.getResponse().getContentAsString().contains("GLUTEN_FREE"));
        assertTrue(result.getResponse().getContentAsString().contains("BRITISH"));
    }

    @Test
    void findById() throws Exception {
        MvcResult result = mockMvc.perform(get("/api/v1/recipes/" + recipe1.getId()))
                .andExpect(status().isOk())
                .andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("Test Recipe1"));
        assertFalse(result.getResponse().getContentAsString().contains("Test Recipe2"));
        assertTrue(result.getResponse().getContentAsString().contains("GLUTEN_FREE"));
        assertFalse(result.getResponse().getContentAsString().contains("VEGAN"));
        assertTrue(result.getResponse().getContentAsString().contains("BRITISH"));
        assertFalse(result.getResponse().getContentAsString().contains("AMERICAN"));
    }

    @Test
    void deleteRecipe() throws Exception {
        var repoSizeBefore = recipeRepository.findAll().size();
        MvcResult result = mockMvc.perform(delete("/api/v1/recipes/" + recipe1.getId()))
                .andExpect(status().isNoContent())
                .andReturn();
        var repoSizeAfter = recipeRepository.findAll().size();
        assertEquals(repoSizeBefore - 1, repoSizeAfter);
    }

    @Test
    void addRecipe_Valid() throws Exception {
        var repoSizeBefore = recipeRepository.findAll().size();
        CreateRecipeDTO createRecipeDTO = new CreateRecipeDTO(
                "Test Recipe3",
                List.of(ingredient1),
                List.of("MethodTest1", "MethodTest2"),
                10,
                35,
                1L,
                Cuisine.KOREAN,
                List.of(Diet.VEGETARIAN, Diet.GLUTEN_FREE)
        );
        String body = objectMapper.writeValueAsString(createRecipeDTO);
        mockMvc.perform(post("/api/v1/recipes")
                        .content(body).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();
        var repoSizeAfter = recipeRepository.findAll().size();
        assertEquals(repoSizeBefore + 1, repoSizeAfter);
    }

    @Test
    void addRecipe_Invalid_BadRequestDueToNulls() throws Exception {
        var repoSizeBefore = recipeRepository.findAll().size();
        CreateRecipeDTO createRecipeDTO = new CreateRecipeDTO(
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null
        );
        String body = objectMapper.writeValueAsString(createRecipeDTO);
        mockMvc.perform(post("/api/v1/recipes")
                        .content(body).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andReturn();
        var repoSizeAfter = recipeRepository.findAll().size();
        assertEquals(repoSizeBefore, repoSizeAfter);
    }

    @Test
    void updateRecipe_Valid_CheckResponse() throws Exception {
        RecipeDTO recipeDTO = new RecipeDTO(
                "Test Recipe3",
                List.of(ingredient1),
                List.of("MethodTest1", "MethodTest2"),
                10,
                30,
                1L,
                Cuisine.KOREAN,
                List.of(Diet.VEGETARIAN, Diet.GLUTEN_FREE)
        );
        String body = objectMapper.writeValueAsString(recipeDTO);
        MvcResult result = mockMvc.perform(put("/api/v1/recipes/" + recipe1.getId())
                        .content(body).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted())
                .andReturn();
        Recipe updatedRecipe = recipeRepository.findById(recipe1.getId()).get();
        assertTrue(result.getResponse().getContentAsString().contains(recipeDTO.getName()));
        assertTrue(result.getResponse().getContentAsString().contains(ingredient1.getName()));
        assertTrue(result.getResponse().getContentAsString().contains(recipeDTO.getMethod().get(0)));
        assertTrue(result.getResponse().getContentAsString().contains(recipeDTO.getCuisine().toString()));
        assertTrue(result.getResponse().getContentAsString().contains(recipeDTO.getDiets().get(0).toString()));
    }

    @Test
    void updateRecipe_Valid_CheckRepoUpdated() throws Exception {
        RecipeDTO recipeDTO = new RecipeDTO(
                "Test Recipe3",
                List.of(ingredient1),
                List.of("MethodTest1", "MethodTest2"),
                1,
                23,
                1L,
                Cuisine.KOREAN,
                List.of(Diet.VEGETARIAN, Diet.GLUTEN_FREE)
        );
        String body = objectMapper.writeValueAsString(recipeDTO);
        MvcResult result = mockMvc.perform(put("/api/v1/recipes/" + recipe1.getId())
                        .content(body).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted())
                .andReturn();
        Recipe updatedRecipe = recipeRepository.findById(recipe1.getId()).get();
        assertEquals(recipe1.getId(), updatedRecipe.getId());
        assertEquals(recipeDTO.getName(), updatedRecipe.getName());
        assertEquals(recipeDTO.getCuisine(), updatedRecipe.getCuisine());
        assertEquals(recipeDTO.getDiets(), updatedRecipe.getDiets());
        assertEquals(recipeDTO.getIngredients().size(), updatedRecipe.getIngredients().size());
    }

}