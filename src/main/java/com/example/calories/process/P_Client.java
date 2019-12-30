package com.example.calories.process;

import com.example.calories.constants.CalorieCounterConstants;
import com.example.calories.model.Client;
import com.example.calories.model.json.JSONCalories;

public class P_Client {

    public static JSONCalories getCalories(Client client) throws Exception {
//        Men BMR = 66.4730 + (13.7516 x weight in kg) + (5.0033 x height in cm) – (6.7550 x age in years)
//        Women BMR = 655.0955 + (9.5634 x weight in kg) + (1.8496 x height in cm) – (4.6756 x age in years)
        Long clientId = client.getId();
        JSONCalories jsonCalories = new JSONCalories(clientId);
        Double calories;
        String sex;
        sex = client.getSex();
        if (sex.equalsIgnoreCase(Client.MALE)) {
            calories = CalorieCounterConstants.MALE_INITIAL_CALORIE_CONSTANT + ( CalorieCounterConstants.MALE_WEIGHT_CONSTANT * client.getWeight())
                    + (CalorieCounterConstants.MALE_HEIGHT_CONSTANT * client.getHeight()) - (CalorieCounterConstants.MALE_AGE_CONSTANT * client.getAge());
        } else if (sex.equalsIgnoreCase(Client.FEMALE)) {
            calories = CalorieCounterConstants.FEMALE_INITIAL_CALORIE_CONSTANT + ( CalorieCounterConstants.FEMALE_WEIGHT_CONSTANT * client.getWeight())
                    + (CalorieCounterConstants.FEMALE_HEIGHT_CONSTANT * client.getHeight()) - (CalorieCounterConstants.FEMALE_AGE_CONSTANT * client.getAge());
        } else
            throw new Exception("Client with id " + clientId + " is not male or female");
        jsonCalories.setCalories(calories);
        return jsonCalories;
    }
}
