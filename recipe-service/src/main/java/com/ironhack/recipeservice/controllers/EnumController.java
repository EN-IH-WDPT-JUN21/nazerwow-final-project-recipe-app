package com.ironhack.recipeservice.controllers;

import com.ironhack.recipeservice.enums.Cuisine;
import com.ironhack.recipeservice.enums.Diet;
import com.ironhack.recipeservice.enums.Measurement;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

public interface EnumController {

    @GetMapping("/cuisines")
    @ResponseStatus(HttpStatus.OK)
    Cuisine[] getAllCuisines();

    @GetMapping("/diets")
    @ResponseStatus(HttpStatus.OK)
    Diet[] getAllDiets();

    @GetMapping("/measurements")
    @ResponseStatus(HttpStatus.OK)
    Measurement[] getAllMeasurements();
}
