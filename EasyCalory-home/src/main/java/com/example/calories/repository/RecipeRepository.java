package com.example.calories.repository;

import com.example.calories.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    @Query("SELECT u FROM #{#entityName} u WHERE u.caloriesPerPortion > (?1 - 25.0) AND u.caloriesPerPortion < (?1 + 25.0) AND u.hasMeat = ?2")
    List<Recipe> findAllRecipeByCalories(Double calories, Boolean hasMeat);
}

