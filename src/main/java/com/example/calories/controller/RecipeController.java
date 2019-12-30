package com.example.calories.controller;

import com.example.calories.Parser;
import com.example.calories.model.Client;
import com.example.calories.model.Recipe;
import com.example.calories.model.RecipeHistory;
import com.example.calories.model.json.JSONRecipe;
import com.example.calories.model.json.RequestRecipes;
import com.example.calories.process.P_Client;
import com.example.calories.repository.ClientRepository;
import com.example.calories.repository.RecipeHistoryRepository;
import com.example.calories.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@RestController
public class RecipeController {

    @Autowired
    RecipeRepository recipeRepository;
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    RecipeHistoryRepository recipeHistoryRepository;

    @PostMapping("/recipes/addRecipe")
    public Recipe addRecipe(@Valid @RequestBody Recipe recipe) throws FileNotFoundException, UnsupportedEncodingException {
        return recipeRepository.save(recipe);
    }

    @GetMapping("/recipes/parser")
    public Boolean parseRecipes() {
        try {
            Parser parser = new Parser();
            List<Recipe> recipeList = parser.parseFile();
            for (Recipe recipe : recipeList) {
                recipeRepository.save(recipe);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @PostMapping(value = "/recipes", consumes = "application/json", produces = "application/json")
    public List<Recipe> returnRecipes(@RequestBody RequestRecipes requestRecipes) throws Exception {

        Long clientId = requestRecipes.getClientId();
        Client client = clientRepository.findByClientId(clientId);

        List<Recipe> recipeList = new ArrayList<>();
        List<Recipe> returnRecipeList = new ArrayList<>();
        List<RecipeHistory> oldRecipeHistoryList = recipeHistoryRepository.findAllByClientId(client);
        List<RecipeHistory> newRecipeHistoryList = new ArrayList<>();

        Double clientRecipeCalories = P_Client.getCalories(client).getCalories() / requestRecipes.getRecipeCount();

        if (client.getMeat_eater())
            recipeList = recipeRepository.findAllRecipeByCalories(clientRecipeCalories);
        else
            recipeList = recipeRepository.findAllRecipeByCaloriesWithoutMeat(clientRecipeCalories);

        for (int i = 0; i < requestRecipes.getRecipeCount(); i++) {

            RecipeHistory recipeHistory = new RecipeHistory();

            int randomNum = ThreadLocalRandom.current().nextInt(0, recipeList.size() - 1);
            Recipe recipe = recipeList.get(randomNum);

            returnRecipeList.add(recipe);
            recipeHistory.setClientId(client);
            recipeHistory.setRecipeId(recipe);
            newRecipeHistoryList.add(recipeHistory);
            recipeList.remove(randomNum);
        }
        recipeHistoryRepository.saveAll(newRecipeHistoryList);
        if (oldRecipeHistoryList != null || oldRecipeHistoryList.size() != 0)
            recipeHistoryRepository.deleteAll(oldRecipeHistoryList);

        return returnRecipeList;
    }
}
