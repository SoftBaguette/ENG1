package com.mygdx.game;

import java.io.FileNotFoundException;
import java.io.IOException;
import com.badlogic.gdx.graphics.Texture;
import org.json.simple.parser.ParseException;
import com.badlogic.gdx.graphics.g2d.Batch;

/*
    The ingredient source is very similar to stations except it has one job which is to add one item to the chef's stack
*/

public class Ingredient_source extends Station{
    Item ingredient;

    public Ingredient_source(int x, int y, int width, int height, String type,Item ingredient) throws FileNotFoundException, IOException, ParseException{
        super(x, y, width, height, type);
        progressBar = null;
        this.ingredient = ingredient;
        String img_name = ingredient.name.substring(0, 1).toUpperCase() + ingredient.name.substring(1)+"Box.png";
        System.out.println(img_name);
        img = new Texture(ingredient.name + "Box.png");
    }

    // Pushes the item into the Chef's stack if there is space
    public void interact(Chef chef, Recipe recipe){
        if (chef.stack.isFull()){
            return;
        }
        chef.stack.push(ingredient);
        
    }
    @Override
    public void update(){

    }
    
    // Draw the ingredient source to the screen using batch
    @Override
    public void draw(Batch batch){
        batch.draw(img, x,y);
    }

}
