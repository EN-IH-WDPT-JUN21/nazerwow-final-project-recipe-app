package com.ironhack.favouritesservice.repositories;
import com.ironhack.favouritesservice.dao.Favourite;
import com.ironhack.favouritesservice.dto.FavouriteRecipeCountDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FavouriteRepository extends JpaRepository<Favourite, Long> {

    List<Favourite> findByUserId(Long userId);

    @Query(value = "SELECT COUNT(recipe_id) AS favourited_count, recipe_id FROM favourite GROUP BY recipe_id ORDER BY favourited_count DESC LIMIT :limit", nativeQuery = true)
    List<long[]> getMostFavouritedRecipesLimitedUpto(@Param("limit") int limit);

    @Query(value = "SELECT f FROM Favourite f WHERE f.userId = :userId AND f.recipeId = :recipeId")
    Optional<Favourite> findByUserIdAndRecipeId(@Param("userId")Long userId,@Param("recipeId") Long recipeId);

}
