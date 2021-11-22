package com.ironhack.recipeservice.dao;

import com.ironhack.recipeservice.enums.Measurement;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.Embeddable;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Ingredient {

    private Long id;

    private String name;
    private double quantity;
    private Measurement measurement;

    public Ingredient(String name, double quantity, Measurement measurement) {
        this.name = name;
        this.quantity = quantity;
        this.measurement = measurement;
    }

}
