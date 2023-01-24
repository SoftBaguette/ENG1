package com.mygdx.game;

/*
    Each recipe has a name and then a list of ingredients it needs to make it
    This is used for the assembly station to know what items are needed to assemble the food item
*/

public class Recipe {
    String recipe_name;
    Item[] ingredients;

    public Recipe(String recipe_name, Item[] ingredients){
        this.recipe_name = recipe_name;
        this.ingredients = ingredients;
    }


}
