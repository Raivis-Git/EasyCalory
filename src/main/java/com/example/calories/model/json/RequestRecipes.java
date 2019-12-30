package com.example.calories.model.json;

public class RequestRecipes {
    private Long clientId;
    private Integer recipeCount;
    private Boolean hasMeat;

    public Boolean getHasMeat() {
        return hasMeat;
    }

    public void setHasMeat(Boolean hasMeat) {
        this.hasMeat = hasMeat;
    }

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
