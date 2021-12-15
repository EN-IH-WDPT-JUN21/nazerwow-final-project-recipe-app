package com.ironhack.reviewservice.service.impl;

import com.ironhack.reviewservice.dao.Review;
import com.ironhack.reviewservice.dto.ReviewResponse;
import com.ironhack.reviewservice.repositories.ReviewRepository;
import com.ironhack.reviewservice.service.ReviewService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ReviewServiceImplTest {

    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private ReviewService reviewService;

    private Review review1;
    private Review review2;
    private Review review3;
    private Review review4;
    private Review review5;


    @BeforeEach
    void setUp() {
        review1 = new Review("Title 1", "Title content 1", 1L, 1L, 1L);
        review2 = new Review("Title 2", "Title content 2", 1L, 2L, 2L);
        review3 = new Review("Title 3", "Title content 3", 2L, 3L, 3L);
        review4 = new Review("Title 4", "Title content 4", 3L, 4L, 4L);
        review5 = new Review("Title 5", "Title content 5", 4L, 4L, 5L);

        reviewRepository.saveAll(List.of(review1, review2, review3, review4, review5));
    }

    @AfterEach
    void tearDown() {
        reviewRepository.deleteAll();
    }

    @Test
    void getByRecipeId() {
        List<ReviewResponse> reviewReturned = reviewService.getByRecipeId(review1.getRecipeId());
        assertEquals(2, reviewReturned.size());
        assertEquals(review1.getContent(), reviewReturned.get(0).getContent());
    }

    @Test
    void getByUserId() {
        List<ReviewResponse> reviewsReturned = reviewService.getByUserId(review4.getUserId());
        assertEquals(2, reviewsReturned.size());
        assertEquals(review4.getContent(), reviewsReturned.get(0).getContent());
    }

    @Test
    void getByid() {
        Review reviewReturned = reviewService.getById(review1.getId());
        assertEquals(review1.getContent(), reviewReturned.getContent());
    }

    @Test
    void getByid_Throws() {
        assertThrows(ResponseStatusException.class, () -> reviewService.getById(review1.getId() + 65L));
    }
}