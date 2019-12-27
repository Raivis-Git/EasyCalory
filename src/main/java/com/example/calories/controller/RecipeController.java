package com.example.calories.controller;

import com.example.calories.model.Recipe;
import com.example.calories.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
public class RecipeController {

    @Autowired
    RecipeRepository recipeRepository;

    @PostMapping("/recipes/addRecipe")
    public Recipe addRecipe(@Valid @RequestBody Recipe recipe) {
        return recipeRepository.save(recipe);
    }

    @PostMapping("/recipes/{calories}")
    public List<Recipe> returnRecipes(@PathVariable Double calories) {
        List<Recipe> recipeList = new ArrayList<>();


        return null;
    }

}
