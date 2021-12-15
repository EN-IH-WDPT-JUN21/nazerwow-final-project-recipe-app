package com.ironhack.reviewservice.repositories;

import com.ironhack.reviewservice.dao.Review;
import com.ironhack.reviewservice.dto.ReviewResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findByRecipeId(Long recipeId);
    List<Review> findByUserId(Long userId);


    @Query(value = "SELECT " +
            " review.title, review.content, user.name, rating.rating " +
            "FROM review " +
            "LEFT JOIN user ON review.user_id = user.id " +
            "LEFT JOIN rating ON review.rating_id = rating.id " +
            "WHERE review.recipe_id = :recipeId", nativeQuery = true)
    List<ReviewResponse> getReviewResponseByRecipeId(@Param("recipeId") Long recipeId);

}
