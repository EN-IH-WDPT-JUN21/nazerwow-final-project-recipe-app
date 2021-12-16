package com.ironhack.reviewservice.repositories;

import com.ironhack.reviewservice.dao.Review;
import com.ironhack.reviewservice.dto.ReviewResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findByRecipeId(Long recipeId);
    List<Review> findByUserId(Long userId);


    @Query(value = "SELECT " +
            " review.id, review.title, review.content, review.created_date, review.edited_date, " +
            "user.email, user.name, user.id AS userId , rating.rating , rating.id AS ratingId " +
            "FROM review " +
            "LEFT JOIN user ON review.user_id = user.id " +
            "LEFT JOIN rating ON review.rating_id = rating.id " +
            "WHERE review.recipe_id = :recipeId", nativeQuery = true)
    List<ReviewResponse> getReviewResponseByRecipeId(@Param("recipeId") Long recipeId);

    @Query(value = "SELECT " +
            "review.id, review.title, review.content, review.created_date, review.edited_date, " +
            "user.name, user.email, user.id AS userId , rating.rating , rating.id AS ratingId " +
            "FROM review " +
            "LEFT JOIN user ON review.user_id = user.id " +
            "LEFT JOIN rating ON review.rating_id = rating.id " +
            "WHERE review.user_id = :userId", nativeQuery = true)
    List<ReviewResponse> getReviewResponseByUserId(@Param("userId") Long userId);

    @Query(value = "SELECT r FROM Review r WHERE r.userId = :userId AND r.recipeId = :recipeId")
    Optional<Review> findByUserIdAndRecipeId(@Param("userId")Long userId, @Param("recipeId") Long recipeId);

}
