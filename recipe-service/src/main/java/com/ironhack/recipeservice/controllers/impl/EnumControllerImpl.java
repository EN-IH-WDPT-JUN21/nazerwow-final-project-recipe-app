package com.ironhack.recipeservice.controllers.impl;

import com.ironhack.recipeservice.controllers.EnumController;
import com.ironhack.recipeservice.enums.Cuisine;
import com.ironhack.recipeservice.enums.Diet;
import com.ironhack.recipeservice.enums.Measurement;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class EnumControllerImpl implements EnumController {

    @Override
    @GetMapping("/cuisines")
    @ResponseStatus(HttpStatus.OK)
    public Cuisine[] getAllCuisines(){
        return Cuisine.class.getEnumConstants();
    }

    @Override
    @GetMapping("/diets")
    @ResponseStatus(HttpStatus.OK)
    public Diet[] getAllDiets(){
        return Diet.class.getEnumConstants();
    }

    @Override
    @GetMapping("/measurements")
    @ResponseStatus(HttpStatus.OK)
    public Measurement[] getAllMeasurements(){
        return Measurement.class.getEnumConstants();
    }
}
