package com.mygdx.game;

public class Item {
    
    // Name of item
    // Ingredients: 
    // Tomatoe, Onion, Lettuce, Cheese, Pizza Base, Buns, Burger, Jacket Potatoe
    // Finished Items:
    // Burger
    // Pizza
    // Salad
    // Jacket Potatoe
    String name;

    // Status of item
    // Raw = ""
    // Chopped
    // Puree 
    // Cooked
    // Need to flip
    // Toasted
    // Assembled
    String status;
    
    // Item images are name + status + ".png"
    
    // Initialise object
    public Item(String name,String status){
        this.status = status;
        this.name = name;
    }

}
