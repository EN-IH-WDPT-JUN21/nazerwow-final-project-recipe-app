package com.ironhack.ratingservice.repositories;

import com.ironhack.ratingservice.dao.Rating;
import com.ironhack.ratingservice.dto.TopRecipeDTO;
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

    @Query(value = "SELECT AVG(r.rating) FROM Rating r WHERE r.recipeId = :recipId")
    Optional<Double> getAverageRatingForRecipe(@Param("recipId") Long recipeId);

    @Query(value = "SELECT recipe_id FROM rating GROUP BY recipe_id ORDER BY AVG(rating) DESC LIMIT :limit", nativeQuery = true)
    List<Long[]> getTopRatedRecipesLimitBy(@Param("limit") int limit);

    @Query(value = "SELECT recipe_id FROM rating WHERE user_id = :userId GROUP BY recipe_id ORDER BY AVG(rating) DESC LIMIT :limit", nativeQuery = true)
    List<Long[]> getTopRatedRecipesByUserIdLimitBy(@Param("userId") Long userId, @Param("limit") int limit);

}
