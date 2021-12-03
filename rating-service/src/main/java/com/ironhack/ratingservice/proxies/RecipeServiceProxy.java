package com.ironhack.ratingservice.proxies;


import com.ironhack.ratingservice.dto.RecipeDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;

public interface RecipeServiceProxy {

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    RecipeDTO findById(@PathVariable(name = "id") Long id);
}
