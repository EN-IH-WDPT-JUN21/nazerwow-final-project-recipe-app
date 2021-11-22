package com.ironhack.recipeservice.dao;

import com.ironhack.recipeservice.enums.Cuisine;
import com.ironhack.recipeservice.enums.Diet;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ElementCollection()
    @LazyCollection(LazyCollectionOption.FALSE)
    @Column(name = "Ingredient")
    private List<Ingredient> ingredients;
    @ElementCollection()
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<String> method;
    private Long authorId;
    @Enumerated(EnumType.STRING)
    private Cuisine cuisine;
    @ElementCollection()
    @LazyCollection(LazyCollectionOption.FALSE)
    @Column(name = "Diet")
    @Enumerated(EnumType.STRING)
    private List<Diet> diets;

    private LocalDate createdDate = LocalDate.now();
    private LocalDate editedDate = LocalDate.now();

    public Recipe(String name, List<Ingredient> ingredients, List<String> method, Long authorId, Cuisine cuisine, List<Diet> diets) {
        this.name = name;
        this.ingredients = ingredients;
        this.method = method;
        this.authorId = authorId;
        this.cuisine = cuisine;
        this.diets = diets;
    }
}
