package com.ironhack.favouritesservice.controller;

import com.ironhack.favouritesservice.dao.Favourite;
import com.ironhack.favouritesservice.dto.FavRecipeListDTO;
import com.ironhack.favouritesservice.dto.FavouriteDTO;
import com.ironhack.favouritesservice.dto.RecipeDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

public interface FavouriteController {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    List<Favourite> findAll();

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    Favourite findById(@PathVariable Long id);

    @GetMapping("/userid/{id}")
    @ResponseStatus(HttpStatus.OK)
    List<RecipeDTO> getAllFavouriteRecipesByUserId(@PathVariable Long id);

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    Favourite addToFavourites(@RequestBody FavouriteDTO favouriteDTO);

    @DeleteMapping("/remove")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void removeFromFavourites(@RequestBody FavouriteDTO favouriteDTO);

    @GetMapping("/top10")
    @ResponseStatus(HttpStatus.OK)
    List<RecipeDTO> getTop10FavouritedRecipes();

    @PutMapping("/recipeisfavourited")
    @ResponseStatus(HttpStatus.CREATED)
    boolean isRecipeFavourited(@Valid @RequestBody FavouriteDTO favouriteDTO);
}
