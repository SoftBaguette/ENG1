package com.mygdx.game.Objects;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.simple.parser.ParseException;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Ingredient_source extends Station{
    Item ingredient;

    public Ingredient_source(int x, int y, int width, int height, String type, float interaction_duraction,Stage stage ,Item ingredient) throws FileNotFoundException, IOException, ParseException{
        super(x, y, width, height, type, interaction_duration, stage);
        this.ingredient = ingredient;
    }

    // Pushes the item into the Chef's stack if there is 
    // space
    public void interact(Chef chef, Recipe recipe){
        if (chef.stack.isFull()){
            return;
        }
        chef.stack.push(ingredient);
        
    }
    @Override
    public void update(){

    }
    @Override
    public void draw(Batch batch){
        batch.draw(img, x,y);
    }

}
