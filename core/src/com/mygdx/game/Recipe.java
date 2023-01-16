package com.mygdx.game.Objects;

public class Recipe {
    String recipe_name;
    Item[] ingredients;

    public Recipe(String recipe_name, Item[] ingredients){
        this.recipe_name = recipe_name;
        this.ingredients = ingredients;
    }


}
