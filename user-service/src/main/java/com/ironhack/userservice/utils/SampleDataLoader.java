package com.ironhack.userservice.utils;

import com.github.javafaker.Faker;
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
                        faker.internet().emailAddress(),
                        List.of(Role.USER),
                        faker.country().name(),
                        faker.dragonBall().character(),
                        faker.dragonBall().character()
                )).collect(Collectors.toList());

        userRepository.saveAll(users);

        userRepository.save(new User("Nathan", "nazerwow", "gills11@gmail.com", List.of(Role.USER), "Uk", "It's me", "URL" ));
    }

}