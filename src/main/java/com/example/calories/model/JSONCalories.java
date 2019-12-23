package com.example.calories.model;

import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import java.io.Serializable;
@JsonPOJOBuilder
public class JSONCalories implements Serializable {

    public JSONCalories (Long client_Id) {
        this.client_Id = client_Id;
    }

    private Double calories = 0.0;
    private Long client_Id;

    public Long getClient_Id() {
        return client_Id;
    }

    public void setClient_Id(Long client_Id) {
        this.client_Id = client_Id;
    }

    public Double getCalories() {
        return calories;
    }

    public void setCalories(Double calories) {
        this.calories = calories;
    }
}
