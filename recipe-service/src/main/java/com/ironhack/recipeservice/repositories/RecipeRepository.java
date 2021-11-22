package com.ironhack.recipeservice.repositories;

import com.ironhack.recipeservice.dao.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {
}
