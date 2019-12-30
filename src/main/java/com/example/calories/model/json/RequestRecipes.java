package com.example.calories.model.json;

public class RequestRecipes {
    private Long clientId;
    private Integer recipeCount;

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Integer getRecipeCount() {
        return recipeCount;
    }

    public void setRecipeCount(Integer recipeCount) {
        this.recipeCount = recipeCount;
    }
}
