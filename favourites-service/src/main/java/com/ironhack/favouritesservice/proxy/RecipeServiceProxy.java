package com.ironhack.favouritesservice.proxy;

import com.ironhack.favouritesservice.dto.RecipeDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;

@FeignClient("recipe-service")
public interface RecipeServiceProxy {

    @GetMapping("api/v1/recipes/{id}")
    @ResponseStatus(HttpStatus.OK)
    RecipeDTO findById(@PathVariable(name = "id") Long id);

}
