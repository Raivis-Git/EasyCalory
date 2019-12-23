package com.example.calories.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "recipes")
public class Recipe {
    private String id;
    private List<Ingredients> ingredients;
    private List<Instructions> instructions;
    private List<NutritionPerIngredient> nutr_per_ingredient;
    private String url;
    private String title;
    private List<Quantity> quantity;

}
class Quantity {
    private String text;
}
class NutritionPerIngredient {
    private String fat;
    private String nrg;
    private String pro;
}
class Ingredients {
    private String text;
}
class Instructions {
    private String text;
}

