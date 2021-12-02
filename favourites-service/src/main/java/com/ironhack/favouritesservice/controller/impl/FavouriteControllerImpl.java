package com.ironhack.favouritesservice.controller.impl;

import com.ironhack.favouritesservice.dao.Favourite;
import com.ironhack.favouritesservice.dto.FavRecipeListDTO;
import com.ironhack.favouritesservice.dto.FavouriteDTO;
import com.ironhack.favouritesservice.dto.RecipeDTO;
import com.ironhack.favouritesservice.repositories.FavouriteRepository;
import com.ironhack.favouritesservice.services.impl.FavouritesServiceImpl;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/favourites")
public class FavouriteControllerImpl implements com.ironhack.favouritesservice.controller.FavouriteController {

    @Autowired
    private FavouritesServiceImpl favouritesService;

    @Override
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Favourite> findAll(){
        return favouritesService.findAll();
    }

    @Override
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Favourite findById(@PathVariable Long id){
        return favouritesService.findById(id);
    }

    @Override
    @GetMapping("/userid/{id}")
    @ResponseStatus(HttpStatus.OK)
    public FavRecipeListDTO getAllFavouriteRecipesByUserId(@PathVariable Long id){
        return favouritesService.getAllRecipesByUserId(id);
    }

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Favourite addToFavourites(@Valid @RequestBody FavouriteDTO favouriteDTO){
        return favouritesService.addToFavourites(favouriteDTO);
    }

    @Override
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeFromFavourites(@PathVariable Long id){
        favouritesService.removeFromFavourites(id);
    }

    @Override
    @GetMapping("/top10")
    public List<RecipeDTO> getTop10FavouritedRecipes() {
        return favouritesService.mostFavouritedRecipesLimitedBy(10);
    }
}
