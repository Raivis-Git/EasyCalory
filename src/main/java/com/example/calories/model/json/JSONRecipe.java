package com.example.calories.model.json;

import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

@JsonPOJOBuilder
public class JSONRecipe {
    private String id;
    private List<Ingredients> ingredients;
    private List<Instructions> instructions;
    private List<NutritionPerIngredient> nutr_per_ingredient;
    private String url;
    private String title;
    private List<Quantity> quantity;
    private List<Unit> unit;

    public List<Unit> getUnit() {
        return unit;
    }

    public Set<String> getTextUnit() {
        List<Unit> unitList = getUnit();
        Set<String> unitStringList = new TreeSet<>();
        for (Unit unit : unitList) {
            unitStringList.add(unit.getText());
        }
        return unitStringList;
    }

    public List<Double> getListDoubleUnit() {
        List<Unit> unitList = getUnit();
        List<Double> unitDoubleList = new ArrayList<>();
        String text;
        for (Unit unit : unitList) {
            text = unit.getText();
            Double result;
            if (text.equalsIgnoreCase("ounce")) {
              result = 0.028;
            } else if (text.trim().equalsIgnoreCase("cup")) {
                result = 0.25;
            } else if (text.trim().equalsIgnoreCase("teaspoon")) {
                result = 0.0049;
            } else if (text.trim().equalsIgnoreCase("tablespoon")) {
                result = 0.014;
            } else if (text.trim().equalsIgnoreCase("pound")) {
                result = 0.453;
            } else if (text.trim().equalsIgnoreCase("dash")) {
                result = 0.0004;
            } else if (text.trim().equalsIgnoreCase("drop")) {
                result = 0.00005;
            } else if (text.trim().equalsIgnoreCase("pint")) {
                result = 0.403;
            } else if (text.trim().equalsIgnoreCase("g")) {
                result = 0.001;
            } else if (text.trim().equalsIgnoreCase("bushel")) {
                result = 27.2;
            } else if (text.trim().equalsIgnoreCase("fl. oz")) {
                result = 0.029;
            } else if (text.trim().equalsIgnoreCase("gallon")) {
                result = 3.78;
            } else if (text.trim().equalsIgnoreCase("glass")) {
                result = 0.2;
            } else if (text.trim().equalsIgnoreCase("kg")) {
                result = 1.0;
            } else if (text.trim().equalsIgnoreCase("liter")) {
                result = 1.0;
            } else if (text.trim().equalsIgnoreCase("ml")) {
                result = 0.01;
            } else if (text.trim().equalsIgnoreCase("pinch")) {
                result = 0.0004;
            } else if (text.trim().equalsIgnoreCase("quart")) {
                result = 0.946;
            } else if (text.trim().equalsIgnoreCase("scoop")) {
                result = 0.045;
            } else if (text.trim().equalsIgnoreCase("shot")) {
                result = 0.029;
            }  else {
                result = 0.0;
            }

            unitDoubleList.add(result);
        }

        return unitDoubleList;
    }

    public void setUnit(List<Unit> unit) {
        this.unit = unit;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Ingredients> getIngredients() {
        return ingredients;
    }

    public String getTextIngredients() {
        List<Ingredients> ingredientsList = getIngredients();
        String text = "";
        for (Ingredients ingredients : ingredientsList) {
            text += ingredients.getText().trim() + ";\n";
        }
        return text;
    }

    public void setIngredients(List<Ingredients> ingredients) {
        this.ingredients = ingredients;
    }

    public List<Instructions> getInstructions() {
        return instructions;
    }

    public String getTextInstructions() {
        List<Instructions> instructionList = getInstructions();
        String text = "";
        for (Instructions instructions : instructionList) {
            text += instructions.getText() + "\n";
        }
        return text;
    }

    public void setInstructions(List<Instructions> instructions) {
        this.instructions = instructions;
    }

    public List<NutritionPerIngredient> getNutr_per_ingredient() {
        return nutr_per_ingredient;
    }

    public List<Double> getNutritionList() {
        List<NutritionPerIngredient> nutritionPerIngredients = getNutr_per_ingredient();
        List<Double> nutritionList = new ArrayList<>();

        for (NutritionPerIngredient nutr : nutritionPerIngredients) {
            Double result = nutr.getFat()*9 + nutr.getPro()*4 + nutr.getSug()*4.2;
            nutritionList.add(result);
        }

        return nutritionList;
    }

    public void setNutr_per_ingredient(List<NutritionPerIngredient> nutr_per_ingredient) {
        this.nutr_per_ingredient = nutr_per_ingredient;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Quantity> getQuantity() {
        return quantity;
    }

    public List<Double> getListDoubleQuantity() {
        List<Quantity> quantities = getQuantity();
        List<Double> quantitiesDouble = new ArrayList<>();
        for (Quantity quant : quantities) {
            Double result;
            if (quant.getText().contains("+")) {
                String[] str = quant.getText().trim().split("\\+");
                result = Double.parseDouble(str[0]) + Double.parseDouble(str[1]);
            } else if (quant.getText().trim().contains(" ")) {
                result = Double.parseDouble(quant.getText().trim().substring(0,quant.getText().trim().indexOf(" ")));
            } else if (quant.getText().contains("/")) {
                String[] str = quant.getText().trim().split("\\/");
                result = Double.parseDouble(str[0]) / Double.parseDouble(str[1]);
            } else if (quant.getText().contains("*")) {
                String[] str = quant.getText().trim().split("\\*");
                result = Double.parseDouble(str[0]) * Double.parseDouble(str[1]);
            } else if (quant.getText().contains("-")) {
                String[] str = quant.getText().trim().split("\\-");
                result = Double.parseDouble(str[0]) - Double.parseDouble(str[1]);
            } else {
                result = Double.parseDouble(quant.getText().trim());
            }
            quantitiesDouble.add(result);
        }
        return quantitiesDouble;
    }

    public void setQuantity(List<Quantity> quantity) {
        this.quantity = quantity;
    }
}
class Quantity {
    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
class NutritionPerIngredient {
    private Double fat;
    private Double sug;
    private Double pro;

    public Double getFat() {
        return fat;
    }

    public void setFat(Double fat) {
        this.fat = fat;
    }

    public Double getSug() {
        return sug;
    }

    public void setSug(Double sug) {
        this.sug = sug;
    }

    public Double getPro() {
        return pro;
    }

    public void setPro(Double pro) {
        this.pro = pro;
    }
}
class Ingredients {
    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
class Instructions {
    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
class Unit {
    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}

