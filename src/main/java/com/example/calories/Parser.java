package com.example.calories;

import com.example.calories.model.Recipe;
import com.example.calories.model.json.JSONRecipe;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Parser {
// http://im2recipe.csail.mit.edu/
    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
        final String Url = "C:\\Users\\raivu\\Desktop\\recipes_with_nutritional_info.json";
        Gson gson = new Gson();
        Type type = new TypeToken<List<JSONRecipe>>() {}.getType();
//        List<JSONRecipe> recipe = gson.fromJson(getMyJsonString() ,type);
        final File jsonFile = new File(Url);
        final InputStream inputStream = new DataInputStream(new FileInputStream(jsonFile));
        Reader reader = new InputStreamReader(inputStream, "UTF-8");
        List<JSONRecipe> result  = new Gson().fromJson(reader, type);
        List<Double> caloryList = new ArrayList<>();
        for (JSONRecipe jsonRecipe : result) {
            Recipe recipe = new Recipe();
            recipe.setTitle(jsonRecipe.getTitle());
            recipe.setIntructions(jsonRecipe.getTextInstructions());
            recipe.setIngredients(jsonRecipe.getTextIngredients());


            List<Double> quantityList = jsonRecipe.getListDoubleQuantity();
            List<Double> unitList = jsonRecipe.getListDoubleUnit();
            List<Double> nutrList = jsonRecipe.getNutritionList();
                Double calories = 0.0;
            for (int i=0; i<jsonRecipe.getQuantity().size(); i++) {
                calories += quantityList.get(i) * unitList.get(i) * nutrList.get(i);
            }
            caloryList.add(calories);
        }

        System.out.println();


    }
    public static String getMyJsonString(){
        return "[{\n" +
                "        \"fsa_lights_per100g\":{\n" +
                "            \"fat\":\"green\",\n" +
                "            \"salt\":\"green\",\n" +
                "            \"saturates\":\"green\",\n" +
                "            \"sugars\":\"orange\"\n" +
                "        },\n" +
                "        \"id\":\"000095fc1d\",\n" +
                "        \"ingredients\":[\n" +
                "            {\n" +
                "                \"text\":\"yogurt, greek, plain, nonfat\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"text\":\"strawberries, raw\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"text\":\"cereals ready-to-eat, granola, homemade\"\n" +
                "            }\n" +
                "        ],\n" +
                "        \"instructions\":[\n" +
                "            {\n" +
                "                \"text\":\"Layer all ingredients in a serving dish.\"\n" +
                "            }\n" +
                "        ],\n" +
                "        \"nutr_per_ingredient\":[\n" +
                "            {\n" +
                "                \"fat\":0.8845044000000001,\n" +
                "                \"nrg\":133.80964,\n" +
                "                \"pro\":23.110512399999998,\n" +
                "                \"sat\":0.26535132,\n" +
                "                \"sod\":81.64656,\n" +
                "                \"sug\":7.348190400000001\n" +
                "            },\n" +
                "            {\n" +
                "                \"fat\":0.46,\n" +
                "                \"nrg\":49.0,\n" +
                "                \"pro\":1.02,\n" +
                "                \"sat\":0.023,\n" +
                "                \"sod\":2.0,\n" +
                "                \"sug\":7.43\n" +
                "            },\n" +
                "            {\n" +
                "                \"fat\":7.415,\n" +
                "                \"nrg\":149.25,\n" +
                "                \"pro\":4.17,\n" +
                "                \"sat\":1.207,\n" +
                "                \"sod\":8.0,\n" +
                "                \"sug\":6.04\n" +
                "            }\n" +
                "        ],\n" +
                "        \"nutr_values_per100g\":{\n" +
                "            \"energy\":81.129461318947662,\n" +
                "            \"fat\":2.1401392635158909,\n" +
                "            \"protein\":6.9144365935655356,\n" +
                "            \"salt\":0.055978167389859668,\n" +
                "            \"saturates\":0.36534716195613937,\n" +
                "            \"sugars\":5.0863410343614399\n" +
                "        },\n" +
                "        \"partition\":\"train\",\n" +
                "        \"quantity\":[\n" +
                "            {\n" +
                "                \"text\":\"8\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"text\":\"1\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"text\":\"1/4\"\n" +
                "            }\n" +
                "        ],\n" +
                "        \"title\":\"Yogurt Parfaits\",\n" +
                "        \"unit\":[\n" +
                "            {\n" +
                "                \"text\":\"ounce\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"text\":\"cup\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"text\":\"cup\"\n" +
                "            }\n" +
                "        ],\n" +
                "        \"url\":\"http://tastykitchen.com/recipes/breakfastbrunch/yogurt-parfaits/\",\n" +
                "        \"weight_per_ingr\":[\n" +
                "            226.796,\n" +
                "            152.0,\n" +
                "            30.5\n" +
                "        ]\n" +
                "    },\n" +
                "    {\n" +
                "        \"fsa_lights_per100g\":{\n" +
                "            \"fat\":\"red\",\n" +
                "            \"salt\":\"orange\",\n" +
                "            \"saturates\":\"orange\",\n" +
                "            \"sugars\":\"orange\"\n" +
                "        },\n" +
                "        \"id\":\"00051d5b9d\",\n" +
                "        \"ingredients\":[\n" +
                "            {\n" +
                "                \"text\":\"sugars, granulated\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"text\":\"oil, corn, peanut, and olive\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"text\":\"egg substitute, powder\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"text\":\"orange juice, raw\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"text\":\"orange juice, raw\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"text\":\"leavening agents, baking powder, double-acting, sodium aluminum sulfate\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"text\":\"wheat flour, white, all-purpose, unenriched\"\n" +
                "            }\n" +
                "        ],\n" +
                "        \"instructions\":[\n" +
                "            {\n" +
                "                \"text\":\"Cream sugar and butter together till smooth.\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"text\":\"Add in egg beaters, orange rind, orange juice, and mix well.\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"text\":\"Mix together low sodium baking powder and flour.\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"text\":\"Add in to creamed mix and mix well.\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"text\":\"Roll dough into 1 inch balls and place on ungreased cookie sheet.\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"text\":\"Rub small amount of salt free butter on bottom of glass.\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"text\":\"Dip glass in granulated sugar.\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"text\":\"Flatten cookie dough ball slightly using flat end of glass.\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"text\":\"Bake at 300 degrees for 10-12 min.\"\n" +
                "            }\n" +
                "        ],\n" +
                "        \"nutr_per_ingredient\":[\n" +
                "            {\n" +
                "                \"fat\":0.0,\n" +
                "                \"nrg\":384.0,\n" +
                "                \"pro\":0.0,\n" +
                "                \"sat\":0.0,\n" +
                "                \"sod\":0.0,\n" +
                "                \"sug\":100.56000000000002\n" +
                "            },\n" +
                "            {\n" +
                "                \"fat\":168.0,\n" +
                "                \"nrg\":1488.0,\n" +
                "                \"pro\":0.0,\n" +
                "                \"sat\":24.132,\n" +
                "                \"sod\":0.0,\n" +
                "                \"sug\":0.0\n" +
                "            },\n" +
                "            {\n" +
                "                \"fat\":2.7625,\n" +
                "                \"nrg\":94.35000000000001,\n" +
                "                \"pro\":11.793750000000001,\n" +
                "                \"sat\":0.800275,\n" +
                "                \"sod\":170.0,\n" +
                "                \"sug\":4.6325\n" +
                "            },\n" +
                "            {\n" +
                "                \"fat\":0.010416666666666666,\n" +
                "                \"nrg\":2.333333333333333,\n" +
                "                \"pro\":0.03625,\n" +
                "                \"sat\":0.0012499999999999998,\n" +
                "                \"sod\":0.041666666666666664,\n" +
                "                \"sug\":0.4339583333333333\n" +
                "            },\n" +
                "            {\n" +
                "                \"fat\":0.0026041666666666665,\n" +
                "                \"nrg\":0.5833333333333333,\n" +
                "                \"pro\":0.0090625,\n" +
                "                \"sat\":0.00031249999999999995,\n" +
                "                \"sod\":0.010416666666666666,\n" +
                "                \"sug\":0.10848958333333332\n" +
                "            },\n" +
                "            {\n" +
                "                \"fat\":0.0,\n" +
                "                \"nrg\":6.0,\n" +
                "                \"pro\":0.0,\n" +
                "                \"sat\":0.0,\n" +
                "                \"sod\":1464.0,\n" +
                "                \"sug\":0.0\n" +
                "            },\n" +
                "            {\n" +
                "                \"fat\":4.305,\n" +
                "                \"nrg\":1592.5,\n" +
                "                \"pro\":45.185,\n" +
                "                \"sat\":0.679,\n" +
                "                \"sod\":7.0,\n" +
                "                \"sug\":1.1900000000000002\n" +
                "            }\n" +
                "        ],\n" +
                "        \"nutr_values_per100g\":{\n" +
                "            \"energy\":477.09640393594606,\n" +
                "            \"fat\":23.412485931109796,\n" +
                "            \"protein\":7.6254917146773344,\n" +
                "            \"salt\":0.54862055228055318,\n" +
                "            \"saturates\":3.4250537682338384,\n" +
                "            \"sugars\":14.298442949953758\n" +
                "        },\n" +
                "        \"partition\":\"val\",\n" +
                "        \"quantity\":[\n" +
                "            {\n" +
                "                \"text\":\"1/2\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"text\":\"3/4\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"text\":\"1/4\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"text\":\"1\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"text\":\"1/4\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"text\":\"1\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"text\":\"3 1/2\"\n" +
                "            }\n" +
                "        ],\n" +
                "        \"title\":\"Salt Free, Low Cholesterol Sugar Cookies Recipe\",\n" +
                "        \"unit\":[\n" +
                "            {\n" +
                "                \"text\":\"cup\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"text\":\"cup\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"text\":\"cup\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"text\":\"teaspoon\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"text\":\"teaspoon\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"text\":\"tablespoon\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"text\":\"cup\"\n" +
                "            }\n" +
                "        ],\n" +
                "        \"url\":\"http://cookeatshare.com/recipes/salt-free-low-cholesterol-sugar-cookies-6256\",\n" +
                "        \"weight_per_ingr\":[\n" +
                "            100.80000000000001,\n" +
                "            168.0,\n" +
                "            21.25,\n" +
                "            5.166666666666666,\n" +
                "            1.2916666666666665,\n" +
                "            13.799999999999999,\n" +
                "            437.5\n" +
                "        ]\n" +
                "    }]";
    }
}
