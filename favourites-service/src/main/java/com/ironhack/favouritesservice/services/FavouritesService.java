package com.ironhack.favouritesservice.services;

import com.ironhack.favouritesservice.dao.Favourite;
import com.ironhack.favouritesservice.dto.FavRecipeListDTO;
import com.ironhack.favouritesservice.dto.FavouriteDTO;
import com.ironhack.favouritesservice.dto.RecipeDTO;

import java.util.List;

public interface FavouritesService {

    List<Favourite> findAll();

    Favourite findById(Long id);

    List<Favourite> findByUserId(Long id);

    List<RecipeDTO> getAllRecipesByUserId(Long id);

    Favourite addToFavourites(FavouriteDTO favouriteDTO);

    void removeFromFavourites(FavouriteDTO favouriteDTO);

    List<RecipeDTO> mostFavouritedRecipesLimitedBy(int i);

    boolean isRecipeFavourited(FavouriteDTO favouriteDTO);
}
