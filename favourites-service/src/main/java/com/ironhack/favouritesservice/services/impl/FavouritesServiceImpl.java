package com.ironhack.favouritesservice.services.impl;

import com.ironhack.favouritesservice.dao.Favourite;
import com.ironhack.favouritesservice.dto.FavRecipeListDTO;
import com.ironhack.favouritesservice.dto.FavouriteDTO;
import com.ironhack.favouritesservice.dto.RecipeDTO;
import com.ironhack.favouritesservice.proxy.RecipeServiceProxy;
import com.ironhack.favouritesservice.repositories.FavouriteRepository;
import com.ironhack.favouritesservice.services.FavouritesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FavouritesServiceImpl implements FavouritesService {

    @Autowired
    private FavouriteRepository favouriteRepository;
    @Autowired
    private RecipeServiceProxy recipeService;

    @Override
    public List<Favourite> findAll(){
        return favouriteRepository.findAll();
    }

    @Override
    public Favourite findById(Long id){
        Optional<Favourite> favourite = favouriteRepository.findById(id);
        if(favourite.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Favourite Exists with Id:" + id);
        return favourite.get();
    }

    @Override
    public List<Favourite> findByUserId(Long id){
        return favouriteRepository.findByUserId(id);
    }

    @Override
    public FavRecipeListDTO getAllRecipesByUserId(Long id){
        return recipeListFromFavouritesList(findByUserId(id));
    }

    @Override
    public Favourite addToFavourites(FavouriteDTO favouriteDTO){
        return favouriteRepository.save(createFavouriteFromDTO(favouriteDTO));
    }

    @Override
    public void removeFromFavourites(Long id){
        favouriteRepository.delete(findById(id));
    }

    private Favourite createFavouriteFromDTO(FavouriteDTO favouriteDTO) {
        return new Favourite(favouriteDTO.getUserId(), favouriteDTO.getRecipeId());
    }


    private FavRecipeListDTO recipeListFromFavouritesList(List<Favourite> favouritesList) {
        List<RecipeDTO> favRecipeList = new ArrayList<>();
        for (Favourite favourite : favouritesList) {
            favRecipeList.add(recipeService.findById(favourite.getRecipeId()));
        }
        return new FavRecipeListDTO(favRecipeList);
    }
}
