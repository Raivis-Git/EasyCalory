package com.example.calories.model;

import javax.persistence.*;

@Entity
@Table(name = "recipe")
public class Recipe {
    @Id
    @GeneratedValue(generator = "recipe_generator")
    @SequenceGenerator(
            name = "recipe_generator",
            sequenceName = "recipe_sequence",
            initialValue = 1000
    )
    private Long recipeId;
    private Double caloriesPer100Grams;
    private Double portionWeight;
    private Double caloriesPerPortion;
    private String title;
    private String ingredients;
    private String intructions;

    public Double getPortionWeight() {
        return portionWeight;
    }

    public void setPortionWeight(Double portionWeight) {
        this.portionWeight = portionWeight;
    }

    public Double getCaloriesPer100Grams() {
        return caloriesPer100Grams;
    }

    public void setCaloriesPer100Grams(Double caloriesPer100Grams) {
        this.caloriesPer100Grams = caloriesPer100Grams;
    }

    public Double getCaloriesPerPortion() {
        return caloriesPerPortion;
    }

    public void setCaloriesPerPortion(Double caloriesPerPortion) {
        this.caloriesPerPortion = caloriesPerPortion;
    }

    public Long getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(Long recipeId) {
        this.recipeId = recipeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getIntructions() {
        return intructions;
    }

    public void setIntructions(String intructions) {
        this.intructions = intructions;
    }
}
