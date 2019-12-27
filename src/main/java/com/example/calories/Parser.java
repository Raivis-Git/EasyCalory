package com.example.calories;

import com.example.calories.dao.RecipeDAO;
import com.example.calories.model.Recipe;
import com.example.calories.model.json.JSONRecipe;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
@ComponentScan
@EntityScan("model")
@EnableJpaRepositories("dao")
public class Parser {
    // http://im2recipe.csail.mit.edu/
    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {

        RecipeDAO recipeDAO = new RecipeDAO();

        final String Url = "\\\\nas.l24.local\\users\\raivis.treikals\\Desktop\\recipes_with_nutritional_info.json";
        Gson gson = new Gson();
        Type type = new TypeToken<List<JSONRecipe>>() {
        }.getType();
//        List<JSONRecipe> recipe = gson.fromJson(getMyJsonString() ,type);
        final File jsonFile = new File(Url);
        final InputStream inputStream = new DataInputStream(new FileInputStream(jsonFile));
        Reader reader = new InputStreamReader(inputStream, "UTF-8");
        List<JSONRecipe> result = new Gson().fromJson(reader, type);
        List<Double> caloryList = new ArrayList<>();
        Set<String> ingredientList = new TreeSet<>();
        for (JSONRecipe jsonRecipe : result) {
            Recipe recipe = new Recipe();
            recipe.setTitle(jsonRecipe.getTitle());
            recipe.setIntructions(jsonRecipe.getTextInstructions());
            recipe.setIngredients(jsonRecipe.getTextIngredients());
            recipe.setCaloriesPer100g(jsonRecipe.getEnergyToCal100g());

            if (jsonRecipe.getTextIngredients().contains("meat") ||
                    jsonRecipe.getTextIngredients().contains("animal") ||
                    jsonRecipe.getTextIngredients().contains("bacon") ||
                    jsonRecipe.getTextIngredients().contains("beef") ||
                    jsonRecipe.getTextIngredients().contains("turkey") ||
                    jsonRecipe.getTextIngredients().contains("mcdonald") ||
                    jsonRecipe.getTextIngredients().contains("pork") ||
                    jsonRecipe.getTextIngredients().contains("chicken") ||
                    jsonRecipe.getTextIngredients().contains("roland")) {

                recipe.setHasMeat(true);
            } else {
                recipe.setHasMeat(false);
            }

            ingredientList.addAll(jsonRecipe.getSetIngredient());

            List<Double> quantityList = jsonRecipe.getListDoubleQuantity();
            List<Double> unitList = jsonRecipe.getListDoubleUnit();
//            List<Double> nutrList = jsonRecipe.getNutritionList();
            List<Double> nutrList = jsonRecipe.getNutritionEnergyToKCal();
            List<Double> weightPerIngr = jsonRecipe.getWeight_per_ingr();

            Double weight = 0.0;
            for (int i = 0; i < jsonRecipe.getQuantity().size(); i++) {
//                calories += quantityList.get(i) * unitList.get(i) * nutrList.get(i);
//                weight += nutrList.get(i);
                weight += weightPerIngr.get(i) * unitList.get(i) * quantityList.get(i);
            }
            caloryList.add(weight * jsonRecipe.getEnergyToCal100g() / 100);
            recipe.setCaloriesPerPortion(weight * jsonRecipe.getEnergyToCal100g() / 100);
            recipe.setWeight(weight);
//            recipeDAO.saveRecipe(recipe);
            System.out.println();
        }
        Integer counter = 0;
        for (Double dou : caloryList) {
            if (dou > 1200 / 5 && dou < 3500 / 5)
                counter++;
        }

        System.out.println();


    }

    public static List<Recipe> parseFile() throws FileNotFoundException, UnsupportedEncodingException {

//        DecimalFormat df4 = new DecimalFormat(".##");

        final String Url = "\\\\nas.l24.local\\users\\raivis.treikals\\Desktop\\recipes_with_nutritional_info.json";
        List<Recipe> recipeList = new ArrayList<>();
        Gson gson = new Gson();
        Type type = new TypeToken<List<JSONRecipe>>() {
        }.getType();
//        List<JSONRecipe> recipe = gson.fromJson(getMyJsonString() ,type);
        final File jsonFile = new File(Url);
        final InputStream inputStream = new DataInputStream(new FileInputStream(jsonFile));
        Reader reader = new InputStreamReader(inputStream, "UTF-8");
        List<JSONRecipe> result = new Gson().fromJson(reader, type);
        for (JSONRecipe jsonRecipe : result) {
            Recipe recipe = new Recipe();
            recipe.setTitle(jsonRecipe.getTitle());
            recipe.setIntructions(jsonRecipe.getTextInstructions());
            recipe.setIngredients(jsonRecipe.getTextIngredients());
//            recipe.setCaloriesPer100g(jsonRecipe.getNutrPer100g());

            if (hasMeat(jsonRecipe.getTextIngredients()) || hasMeat(jsonRecipe.getTitle())) {
                recipe.setHasMeat(true);
            } else {
                recipe.setHasMeat(false);
            }
            List<Double> quantityList = jsonRecipe.getListDoubleQuantity();
            List<Double> unitList = jsonRecipe.getListDoubleUnit();
            List<Double> nutrList = jsonRecipe.getNutritionEnergyToKCal();
            List<Double> weightPerIngr = jsonRecipe.getWeight_per_ingr();
            Double weight = 0.0;
            for (int i = 0; i < jsonRecipe.getQuantity().size(); i++) {
//                calories += quantityList.get(i) * unitList.get(i) * nutrList.get(i);
//                weight += nutrList.get(i);
                weight += weightPerIngr.get(i) * unitList.get(i) * quantityList.get(i);
            }
            Double calories = (double) Math.round(weight * jsonRecipe.getEnergyToCal100g() / 100);
            if (calories < 1200 && calories > 99) {
                recipe.setCaloriesPerPortion((double) Math.round(weight * jsonRecipe.getEnergyToCal100g()) / 100);
                recipe.setCaloriesPer100g((double) Math.round(jsonRecipe.getEnergyToCal100g() * 100) / 100);
                recipe.setWeight((double) Math.round(weight * 100) / 100);
                recipeList.add(recipe);
            }
        }
        return recipeList;
    }

    public static Boolean hasMeat(String givenString) {

        if (givenString.contains("meat") ||
                givenString.contains("animal") ||
                givenString.contains("bacon") ||
                givenString.contains("beef") ||
                givenString.contains("turkey") ||
                givenString.contains("mcdonald") ||
                givenString.contains("pork") ||
                givenString.contains("chicken") ||
                givenString.contains("roland") ||
                givenString.contains("salmon") ||
                givenString.contains("fish") ||
                givenString.contains("shrimp") ||
                givenString.contains(" clam ") ||
                givenString.contains(" clams ") ||
                givenString.contains("oyster") ||
                givenString.contains("mussel") ||
                givenString.contains("squid") ||
                givenString.contains(" rib ") ||
                givenString.contains(" ribs ")) {

            return true;
        }
        return false;
    }
}
