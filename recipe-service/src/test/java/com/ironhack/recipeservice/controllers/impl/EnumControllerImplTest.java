package com.ironhack.recipeservice.controllers.impl;

import com.ironhack.recipeservice.enums.Cuisine;
import com.ironhack.recipeservice.enums.Diet;
import com.ironhack.recipeservice.enums.Measurement;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class EnumControllerImplTest {

    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;


    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getAllCuisines() throws Exception {
        MvcResult result = mockMvc.perform(get("/api/v1/cuisines"))
                .andExpect(status().isOk())
                .andReturn();
        assertTrue(result.getResponse().getContentAsString().contains(Cuisine.JAPANESE.toString()));
        assertTrue(result.getResponse().getContentAsString().contains(Cuisine.KOREAN.toString()));
        assertTrue(result.getResponse().getContentAsString().contains(Cuisine.FRENCH.toString()));
    }

    @Test
    void getAllDiets() throws Exception {
        MvcResult result = mockMvc.perform(get("/api/v1/diets"))
                .andExpect(status().isOk())
                .andReturn();
        assertTrue(result.getResponse().getContentAsString().contains(Diet.GLUTEN_FREE.toString()));
        assertTrue(result.getResponse().getContentAsString().contains(Diet.VEGETARIAN.toString()));
        assertTrue(result.getResponse().getContentAsString().contains(Diet.VEGAN.toString()));
    }

    @Test
    void getAllMeasurements() throws Exception {
        MvcResult result = mockMvc.perform(get("/api/v1/measurements"))
                .andExpect(status().isOk())
                .andReturn();
        assertTrue(result.getResponse().getContentAsString().contains(Measurement.KILOGRAM.toString()));
        assertTrue(result.getResponse().getContentAsString().contains(Measurement.CUP.toString()));
        assertTrue(result.getResponse().getContentAsString().contains(Measurement.MILLILITRE.toString()));
    }
}