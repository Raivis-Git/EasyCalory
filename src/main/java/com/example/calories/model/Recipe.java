package com.example.calories.model;

import javax.persistence.*;

@Entity
@Table(name = "recipes")
public class Recipe {
    @Id
    @GeneratedValue(generator = "recipe_generator")
    @SequenceGenerator(
            name = "recipe_generator",
            sequenceName = "recipe_sequence",
            initialValue = 1000
    )
    private Long recipeId;
    private Double caloriesPerPortion;
    private Double caloriesPer100g;
    private Double weight;
    private String title;
    @Column(columnDefinition = "TEXT")
    private String ingredients;
    @Column(columnDefinition = "TEXT")
    private String intructions;
    private Boolean hasMeat;

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getCaloriesPer100g() {
        return caloriesPer100g;
    }

    public void setCaloriesPer100g(Double caloriesPer100g) {
        this.caloriesPer100g = caloriesPer100g;
    }

    public Boolean getHasMeat() {
        return hasMeat;
    }

    public void setHasMeat(Boolean hasMeat) {
        this.hasMeat = hasMeat;
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
