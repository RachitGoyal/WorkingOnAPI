package com.example.nimish.workingonapi;

/**
 * Created by Nimish on 7/24/2015.
 */
public class RecipePojo {

        String recipeName;
        String cookingMethod;
        String recipeImageURL;

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public String getCookingMethod() {
        return cookingMethod;
    }

    public void setCookingMethod(String cookingMethod) {
        this.cookingMethod = cookingMethod;
    }

    public String getRecipeImageURL() {
        return recipeImageURL;
    }

    public void setRecipeImageURL(String recipeImageURL) {
        this.recipeImageURL = recipeImageURL;
    }
}
