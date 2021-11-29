package com.ironhack.userservice;

import com.github.javafaker.Faker;
import com.ironhack.recipeservice.dao.Ingredient;
import com.ironhack.recipeservice.enums.Cuisine;
import com.ironhack.recipeservice.enums.Diet;
import com.ironhack.recipeservice.enums.Measurement;
import com.ironhack.userservice.dao.User;
import com.ironhack.userservice.enums.Role;
import com.ironhack.userservice.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class SampleDataLoader implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Faker faker;


    @Override
    public void run(String... args) {

        List<User> users = IntStream.rangeClosed(1, 20)
                .mapToObj(i -> new User(
                        faker.name().name(),
                        faker.superhero().name(),
                        faker.internet().password(3, 7),
                        List.of(Role.USER),
                        faker.internet().emailAddress(),
                        faker.country().name(),
                        faker.dragonBall().character(),
                        faker.dragonBall().character()
                )).collect(Collectors.toList());

        userRepository.saveAll(users);
    }

}
