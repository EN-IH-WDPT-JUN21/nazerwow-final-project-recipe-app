package com.ironhack.favouritesservice.controller.impl;

import com.ironhack.favouritesservice.dao.Favourite;
import com.ironhack.favouritesservice.dto.FavouriteDTO;
import com.ironhack.favouritesservice.dto.RecipeDTO;
import com.ironhack.favouritesservice.services.impl.FavouritesServiceImpl;
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
    public List<RecipeDTO> getAllFavouriteRecipesByUserId(@PathVariable Long id){
        return favouritesService.getAllRecipesByUserId(id);
    }

    @Override
    @PutMapping("/recipeisfavourited")
    @ResponseStatus(HttpStatus.CREATED)
    public boolean isRecipeFavourited(@Valid @RequestBody FavouriteDTO favouriteDTO){
        return favouritesService.isRecipeFavourited(favouriteDTO);
    }

    @Override
    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public Favourite addToFavourites(@Valid @RequestBody FavouriteDTO favouriteDTO){
        return favouritesService.addToFavourites(favouriteDTO);
    }

    @Override
    @PutMapping("/remove")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeFromFavourites(@RequestBody FavouriteDTO favouriteDTO){
        favouritesService.removeFromFavourites(favouriteDTO);
    }

    @Override
    @GetMapping("/top10")
    public List<RecipeDTO> getTop10FavouritedRecipes() {
        return favouritesService.mostFavouritedRecipesLimitedBy(10);
    }
}
