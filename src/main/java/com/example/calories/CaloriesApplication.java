package com.example.calories;

import com.example.calories.model.Client;
import com.example.calories.model.RecipeHistory;
import com.example.calories.repository.ClientRepository;
import com.example.calories.repository.RecipeHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@EnableJpaAuditing
@SpringBootApplication
public class CaloriesApplication{

	@Autowired
	RecipeHistoryRepository recipeHistoryRepository;
	@Autowired
	ClientRepository clientRepository;

	public static void main(String[] args) {
		SpringApplication.run(CaloriesApplication.class, args);
	}

//	public void run(String... args) {
//		RecipeHistory recipeHistory = new RecipeHistory();
//		recipeHistory.setClientRecipeHistoryId(clientRepository.findByClientId((long)1001));
//		recipeHistoryRepository.save(recipeHistory);
//	}
}
