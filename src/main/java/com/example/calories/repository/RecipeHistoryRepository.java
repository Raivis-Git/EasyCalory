package com.example.calories.repository;

import com.example.calories.model.Client;
import com.example.calories.model.RecipeHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeHistoryRepository extends JpaRepository<RecipeHistory, Long> {
    @Query("SELECT u FROM #{#entityName} u WHERE u.clientId = ?1")
    List<RecipeHistory> findAllByClientId(Client clientId);
}
