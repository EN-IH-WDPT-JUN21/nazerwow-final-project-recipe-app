package com.ironhack.favouritesservice.services;

import com.ironhack.favouritesservice.dao.Favourite;
import com.ironhack.favouritesservice.dto.FavRecipeListDTO;
import com.ironhack.favouritesservice.dto.FavouriteDTO;

import java.util.List;

public interface FavouritesService {

    List<Favourite> findAll();

    Favourite findById(Long id);

    List<Favourite> findByUserId(Long id);

    FavRecipeListDTO getAllRecipesByUserId(Long id);

    Favourite addToFavourites(FavouriteDTO favouriteDTO);

    void removeFromFavourites(Long id);
}
