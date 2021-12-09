package com.ironhack.recipeservice.services.impl;

import com.ironhack.recipeservice.dao.Recipe;
import com.ironhack.recipeservice.dto.CreateRecipeDTO;
import com.ironhack.recipeservice.dto.RecipeDTO;
import com.ironhack.recipeservice.repositories.RecipeRepository;
import com.ironhack.recipeservice.services.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class RecipeServiceImpl implements RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;

    public List<Recipe> findAll() {
        return recipeRepository.findAll();
    }

    public Recipe findById(Long id) {
        Optional<Recipe> recipe = recipeRepository.findById(id);
        if (recipe.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Recipe found with ID: " + id);
        return recipe.get();
    }

    public void deleteRecipe(Long id) {
        Recipe recipe = findById(id);
        recipeRepository.delete(recipe);
    }

    public Recipe addRecipe(CreateRecipeDTO createRecipeDTO){
        return recipeRepository.save(convertNewRecipeDTOToRecipe(createRecipeDTO));
    }

    public Recipe updateRecipe(RecipeDTO recipeDTO){
        Recipe recipe = findById(recipeDTO.getId());
        return recipeRepository.save(updateRecipeWithRecipeDTO(recipeDTO, recipe));
    }

    @Override
    public List<Recipe> findByUserId(Long id) {
        List<Recipe> recipeList = recipeRepository.findByAuthorId(id);
        if(recipeList.size() == 0) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This user has no favourite recipes saved");
        return recipeList;
    }

    private Recipe updateRecipeWithRecipeDTO(RecipeDTO recipeDTO, Recipe recipe) {
        if (recipeDTO.getName() != null) {
            recipe.setName(recipeDTO.getName());
        }
        if (recipeDTO.getIngredients() != null) {
            recipe.setIngredients(recipeDTO.getIngredients());
        }
        if (recipeDTO.getMethod() != null) {
            recipe.setMethod(recipeDTO.getMethod());
        }
        if (recipeDTO.getAuthorId() != null) {
            recipe.setAuthorId(recipeDTO.getAuthorId());
        }
        if (recipeDTO.getCuisine() != null) {
            recipe.setCuisine(recipeDTO.getCuisine());
        }
        if (recipeDTO.getDiets() != null) {
            recipe.setDiets(recipeDTO.getDiets());
        }
        recipe.setEditedDate(LocalDate.now());
        return recipe;
    }

    private Recipe convertNewRecipeDTOToRecipe(CreateRecipeDTO createRecipeDTO) {
        return new Recipe(
                createRecipeDTO.getName(),
                createRecipeDTO.getIngredients(),
                createRecipeDTO.getMethod(),
                createRecipeDTO.getPrepTime(),
                createRecipeDTO.getCookingTime(),
                createRecipeDTO.getAuthorId(),
                createRecipeDTO.getCuisine(),
                createRecipeDTO.getDiets(),
                createRecipeDTO.getImageUrl()
        );
    }


}
