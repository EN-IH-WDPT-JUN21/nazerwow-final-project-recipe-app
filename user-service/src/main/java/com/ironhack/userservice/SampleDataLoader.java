package com.ironhack.userservice;

import com.ironhack.recipeservice.dao.Ingredient;
import com.ironhack.recipeservice.dao.Recipe;
import com.ironhack.recipeservice.enums.Cuisine;
import com.ironhack.recipeservice.enums.Diet;
import com.ironhack.recipeservice.enums.Measurement;
import com.ironhack.recipeservice.repositories.RecipeRepository;
import com.ironhack.userservice.dao.User;
import com.ironhack.userservice.enums.Role;
import com.ironhack.userservice.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SampleDataLoader implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {

        User user = new User(
                "TestUser1",
                "username1",
                "password1",
                List.of(Role.USER),
                "test@email.1",
                "testLocation1",
                "testBio1",
                "testUrl1"
        );

        userRepository.save(user);
    }
}
