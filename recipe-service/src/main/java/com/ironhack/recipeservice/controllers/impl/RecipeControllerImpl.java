package com.ironhack.recipeservice.controllers.impl;

import com.ironhack.recipeservice.controllers.RecipeController;
import com.ironhack.recipeservice.dao.Recipe;
import com.ironhack.recipeservice.dto.CreateRecipeDTO;
import com.ironhack.recipeservice.dto.RecipeDTO;
import com.ironhack.recipeservice.services.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/recipes")
public class RecipeControllerImpl implements RecipeController {

    @Autowired
    private RecipeService recipeService;

    @Override
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Recipe> findAll(){
        return recipeService.findAll();
    }

    @Override
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Recipe findById(@PathVariable(name = "id") Long id){
        return recipeService.findById(id);
    }

    @Override
    @GetMapping("/user/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<Recipe> findByUserId(@PathVariable(name = "id") Long id){
        return recipeService.findByUserId(id);
    }

    @Override
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRecipe(@PathVariable(name = "id") Long id){
        recipeService.deleteRecipe(id);
    }

    @Override
    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public Recipe addRecipe(@Valid @RequestBody CreateRecipeDTO createRecipeDTO){
        return recipeService.addRecipe(createRecipeDTO);
    }

    @Override
    @PutMapping("/edit")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Recipe updateRecipe(@RequestBody @Valid RecipeDTO recipeDTO){
        return recipeService.updateRecipe(recipeDTO);
    }


}
