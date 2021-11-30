package com.ironhack.favouritesservice.dto;

import com.ironhack.favouritesservice.enums.Measurement;
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

    private String name;
    private double quantity;
    private Measurement measurement;

}
