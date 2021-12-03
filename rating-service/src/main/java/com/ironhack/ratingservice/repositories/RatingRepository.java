package com.ironhack.ratingservice.repositories;

import com.ironhack.ratingservice.dao.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {

    List<Rating> findByUserId(Long id);

    Optional<Rating> findByRecipeIdAndUserId(Long recipeId, Long userId);

    @Query(value = "SELECT AVG(r.rating) FROM rating r WHERE r.recipeId = :recipId")
    Optional<Double> getAverageRatingForRecipe(@Param("recipId") Long recipeId);

    @Query(value = "SELECT r FROM rating r GROUP BY r.recipeId ORDER BY AVG(r.rating) DESC LIMIT :limit")
    List<Rating> getTopRatedRecipesLimitBy(@Param("limit") int limit);

    @Query(value = "SELECT r FROM rating r WHERE r.userId = :userId GROUP BY r.recipeId ORDER BY AVG(r.rating) DESC LIMIT :limit")
    List<Rating> getTopRatedRecipesByUserIdLimitBy(@Param("userId") Long userId, @Param("limit") int limit);

}
