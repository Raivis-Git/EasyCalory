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
    private NutrValuesPer100g nutr_values_per100g;
    private List<Double> weight_per_ingr;

    public List<Double> getWeight_per_ingr() {
        return weight_per_ingr;
    }

    public void setWeight_per_ingr(List<Double> weight_per_ingr) {
        this.weight_per_ingr = weight_per_ingr;
    }

    public NutrValuesPer100g getNutr_values_per100g() {
        return nutr_values_per100g;
    }

    public void setNutr_values_per100g(NutrValuesPer100g nutr_values_per100g) {
        this.nutr_values_per100g = nutr_values_per100g;
    }

    public Double getEnergyToCal100g() {
        NutrValuesPer100g nutr = getNutr_values_per100g();
        return nutr.getEnergy() / 4.18;
    }

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

    public Set<String> getSetIngredient() {
        List<Ingredients> ingredientsList = getIngredients();
        Set<String> ingredientsSet = new TreeSet<>();
        for (Ingredients ingredients : ingredientsList) {
            try {
                String text = ingredients.getText().trim();
                if (text.contains(","))
                ingredientsSet.add(text.substring(0, text.indexOf(",")));
                else
                    ingredientsSet.add(text);
            } catch (Exception e) {
                System.out.println(ingredients.getText());
            }
        }
        return ingredientsSet;
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

    public List<Double> getNutritionEnergyToKCal() {
        List<Double> nutrList = new ArrayList<>();
        List<NutritionPerIngredient> nutritionPerIngredients = getNutr_per_ingredient();
        for (NutritionPerIngredient nutr : nutritionPerIngredients) {
            nutrList.add(nutr.getNrg()/4.18);
        }
        return nutrList;
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
                try {
                Double result = 0.0;
                if (quant.getText().contains("+")) {
                    String[] str = quant.getText().trim().split("\\+");
                    result = Double.parseDouble(str[0]) + Double.parseDouble(str[1]);
                } else if (quant.getText().contains("to")) {
                    String[] str = quant.getText().trim().split("to");
                    for (int i = 0; i<str.length; i++) {
                        if (str[i].trim().contains(" ")) {
                            result += Double.parseDouble(str[i].trim().substring(0, str[i].trim().indexOf(" ")));
                        } else if (str[i].contains("/")) {
                            String[] secondStr = str[i].split("\\/");
                            result += Double.parseDouble(secondStr[0].trim()) / Double.parseDouble(secondStr[1].trim());
                        } else {
                            result += Double.parseDouble(str[i]);
                        }
                    }
                    result = result / 2;
                } else if (quant.getText().contains("-")) {
                    String[] str = quant.getText().trim().split("\\-");
                    for (int i = 0; i < str.length; i++) {
                        if (str[i].trim().contains(" ")) {
                            result += Double.parseDouble(str[i].trim().substring(0, str[i].trim().indexOf(" ")));
                        } else if (str[i].contains("/")) {
                            String[] secondStr = str[i].split("\\/");
                            result += Double.parseDouble(secondStr[0].trim()) / Double.parseDouble(secondStr[1].trim());
                        } else {
                            result += Double.parseDouble(str[i]);
                        }
                    }
                    result = result / 2;
                } else if (quant.getText().trim().contains(" ")) {
                    if (quant.getText().trim().substring(0,quant.getText().trim().indexOf(" ")).contains("/")) {
                        String temp = quant.getText().trim().substring(0, quant.getText().indexOf(" "));
                        if (temp.contains("/")) {
                            String[] arrTemp = temp.split("\\/");
                            result = Double.parseDouble(arrTemp[0]) / Double.parseDouble(arrTemp[1]);
                        }
                    } else
                        result = Double.parseDouble(quant.getText().trim().substring(0,quant.getText().trim().indexOf(" ")));
                } else if (quant.getText().contains("/")) {
                    String[] str = quant.getText().trim().split("\\/");
                    result = Double.parseDouble(str[0]) / Double.parseDouble(str[1]);
                } else if (quant.getText().trim().contains(" ")) {
                    result = Double.parseDouble(quant.getText().trim().substring(0, quant.getText().trim().indexOf(" ")));
                } else if (quant.getText().contains("*")) {
                    String[] str = quant.getText().trim().split("\\*");
                    result = Double.parseDouble(str[0]) * Double.parseDouble(str[1]);
                } else {
                    result = Double.parseDouble(quant.getText().trim());
                }
                quantitiesDouble.add(result);
            } catch (Exception e) {
                System.out.println(quant.getText());
            }
            }
        return quantitiesDouble;
    }

    public static void main(String[] args) {
        List<String> stringList = new ArrayList<>();
        stringList.add("1/4 1/2");
        stringList.add("1/4 to 1/2");
        stringList.add("3");
        List<Double> quantitiesDouble = new ArrayList<>();
        for (String quant : stringList) {
            Double result = 0.0;
            if (quant.contains("+")) {
                String[] str = quant.trim().split("\\+");
                result = Double.parseDouble(str[0]) + Double.parseDouble(str[1]);
            } else if (quant.contains("to")) {
                String[] str = quant.trim().split("to");
                for (int i = 0; i<str.length; i++) {
                    if (str[i].trim().contains(" ")) {
                        result += Double.parseDouble(str[i].trim().substring(0, str[i].trim().indexOf(" ")));
                    } else if (str[i].contains("/")) {
                        String[] secondStr = str[i].split("\\/");
                        result += Double.parseDouble(secondStr[0].trim()) / Double.parseDouble(secondStr[1].trim());
                    } else {
                        result += Double.parseDouble(str[i]);
                    }
                }
                result = result / 2;
            } else if (quant.contains("-")) {
                String[] str = quant.trim().split("\\-");
                for (int i = 0; i<str.length; i++) {
                    if (str[i].trim().contains(" ")) {
                        result += Double.parseDouble(str[i].trim().substring(0, str[i].trim().indexOf(" ")));
                    } else if (str[i].contains("/")) {
                        String[] secondStr = str[i].split("\\/");
                        result += Double.parseDouble(secondStr[0].trim()) / Double.parseDouble(secondStr[1].trim());
                    } else {
                        result += Double.parseDouble(str[i]);
                    }
                }
                result = result / 2;
            } else if (quant.trim().contains(" ")) {
                if (quant.trim().substring(0,quant.trim().indexOf(" ")).contains("/")) {
                    String temp = quant.trim().substring(0, quant.indexOf(" "));
                        if (temp.contains("/")) {
                            String[] arrTemp = temp.split("\\/");
                            result = Double.parseDouble(arrTemp[0]) / Double.parseDouble(arrTemp[1]);
                        }
                } else
                    result = Double.parseDouble(quant.trim().substring(0,quant.trim().indexOf(" ")));
            } else if (quant.contains("/")) {
                String[] str = quant.trim().split("\\/");
                result = Double.parseDouble(str[0]) / Double.parseDouble(str[1]);
            } else if (quant.contains("*")) {
                String[] str = quant.trim().split("\\*");
                result = Double.parseDouble(str[0]) * Double.parseDouble(str[1]);
            } else {
                result = Double.parseDouble(quant.trim());
            }
            quantitiesDouble.add(result);
        }
        System.out.println(quantitiesDouble.toString());
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
    private Double nrg;

    public Double getNrg() {
        return nrg;
    }

    public void setNrg(Double nrg) {
        this.nrg = nrg;
    }

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
class NutrValuesPer100g {
    private Double energy;

    public Double getEnergy() {
        return energy;
    }

    public void setEnergy(Double energy) {
        this.energy = energy;
    }
}

