package com.ironhack.ratingservice.services.impl;

import com.ironhack.ratingservice.dao.Rating;
import com.ironhack.ratingservice.dto.RatingDTO;
import com.ironhack.ratingservice.dto.RecipeDTO;
import com.ironhack.ratingservice.repositories.RatingRepository;
import com.ironhack.ratingservice.services.RatingService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RatingServiceImplTest {

    @Autowired
    private RatingRepository ratingRepository;
    @Autowired
    private RatingService ratingService;

    Rating rating1;
    Rating rating2;
    Rating rating3;
    Rating rating4;
    Rating rating5;
    Rating rating6;
    Rating rating7;
    Rating rating8;
    Rating rating9;
    Rating rating10;
    Rating rating11;
    Rating rating12;
    Rating rating13;
    Rating rating14;

    @BeforeEach
    void setUp() {
        rating1 = new Rating(5, 1L, 1L);
        rating2 = new Rating(5, 1L, 2L);
        rating3 = new Rating(2, 2L, 1L);
        rating4 = new Rating(4, 2L, 3L);
        rating5 = new Rating(4, 12L, 2L);
        rating6 = new Rating(4, 3L, 1L);
        rating7 = new Rating(4, 4L, 1L);
        rating8 = new Rating(4, 5L, 1L);
        rating9 = new Rating(4, 6L, 3L);
        rating10 = new Rating(4, 7L, 3L);
        rating11 = new Rating(4, 8L, 3L);
        rating12 = new Rating(4, 9L, 4L);
        rating13 = new Rating(4, 10L, 4L);
        rating14 = new Rating(4, 11L, 4L);

        ratingRepository.saveAll(List.of(rating1, rating2, rating3, rating4, rating5, rating6, rating7, rating8, rating9, rating10, rating11, rating12, rating13, rating14));
    }

    @AfterEach
    void tearDown() {
        ratingRepository.deleteAll();
    }

    @Test
    void findByUserId() {
        List<RecipeDTO> recipeList = ratingService.findByUserId(1L);
        assertEquals(5, recipeList.size());
    }

    @Test
    void getAverageRatingForRecipe() {
        ratingRepository.deleteAll();
        rating3 = new Rating(2, 2L, 1L);
        rating4 = new Rating(4, 2L, 3L);
        ratingRepository.saveAll(List.of(rating3, rating4));
        var averageRating = ratingService.getAverageRatingForRecipe(2L);
        assertEquals((2+4)/2, averageRating);
    }

    @Test
    void getTop10Recipes() {
        List<RecipeDTO> recipeDTOList = ratingService.getTop10Recipes(10);
        assertEquals(10, recipeDTOList.size());
    }

    @Test
    void getTop10RecipesForUser() {
        List<RecipeDTO> recipeDTOList = ratingService.getTop10RecipesForUser(1L);
        assertEquals(5, recipeDTOList.size());
        assertEquals(1L, recipeDTOList.get(0).getId());
    }

    @Test
    void userPreviouslyRatedRecipe() {
        assertTrue(ratingService.userPreviouslyRatedRecipe(1L, 1L));
        assertFalse(ratingService.userPreviouslyRatedRecipe(11L, 1L));
    }

    @Test
    void rateRecipe_Valid_UpdatesExistingRating() {
        RatingDTO ratingDTO = new RatingDTO(3, 1L, 1L);
        var repoSizeBefore = ratingRepository.findAll().size();
        Rating rating = ratingService.rateRecipe(ratingDTO);
        var repoSizeAfter = ratingRepository.findAll().size();
        assertEquals(repoSizeBefore, repoSizeAfter);
        assertEquals(3, rating.getRating());
        assertEquals(3, ratingRepository.findById(rating1.getId()).get().getRating());
    }

    @Test
    void findByUserIdAndRecipeId_Valid() {
        Double rating = ratingService.findByUserIdAndRecipeId(new RatingDTO(0, 1L, 1L));
        assertEquals(5, rating);
    }

    @Test
    void findByUserIdAndRecipeId_Null() {
        var rating = ratingService.findByUserIdAndRecipeId(new RatingDTO(0, 1L, 65L));
        assertNull(rating);
    }
}