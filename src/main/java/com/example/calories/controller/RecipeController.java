package com.example.calories.controller;

import com.example.calories.Parser;
import com.example.calories.model.Client;
import com.example.calories.model.Recipe;
import com.example.calories.model.json.JSONRecipe;
import com.example.calories.model.json.RequestRecipes;
import com.example.calories.process.P_Client;
import com.example.calories.repository.ClientRepository;
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
        List<Recipe> recipeList = new ArrayList<>();
        List<Recipe> returnRecipeList = new ArrayList<>();
        Client client = clientRepository.findByClientId(requestRecipes.getClientId());
        List<JSONRecipe> jsonRecipeList = new ArrayList<>();
        Double clientCalories = P_Client.getCalories(client).getCalories();
        recipeList = recipeRepository.findAllRecipeByCalories(clientCalories/requestRecipes.getRecipeCount(), client.getMeat_eater());

        for (int i = 0; i < requestRecipes.getRecipeCount(); i++) {
            int randomNum = ThreadLocalRandom.current().nextInt(0, recipeList.size() - 1);
            returnRecipeList.add(recipeList.get(randomNum));
            recipeList.remove(randomNum);
        }

        return returnRecipeList;
    }
}
