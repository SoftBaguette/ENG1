package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;


/*
    Each chef is a GameObject which the user can control.
    The stack is what the stations will interact with to pop items or push items
    
*/

public class Chef extends Person {
    Item[] items; 
    public Stack stack;
    Texture plate_img;

    public Chef(int x, int y, int width, int height, float speed) {
        super(x, y, width, height, speed);
        stack = new Stack(5);
        img = new Texture("ChefImage.PNG");
        plate_img = new Texture("Plate.png");
    }

    // Allow the user to move the chef using the arrow keys and WASD keys
    public void move(){
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A)){
             x -= 200 * Gdx.graphics.getDeltaTime();
             // Border of 9px on the left (9 because of design choices, can be 0)
             if (x < 9) {x = 9;}
        }
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D)){
             x += 200 * Gdx.graphics.getDeltaTime();
             // Border of 759px on the right (width - chef_width - 9)
             if (x > 759) {x = 759;}
        }
        if(Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.W)){
            y += 200 * Gdx.graphics.getDeltaTime();
            // Border of 439px on the top (height - chef_height - 9)
            if (y > 439) {y = 439;}
        }
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN) || Gdx.input.isKeyPressed(Input.Keys.S)){
            y -= 200 * Gdx.graphics.getDeltaTime();
            // Border of 9 px on the bottom (430 for above serving station)
             if (y < 9) {y = 9;}
        }
        // The hitbox needs to be updated everytime the chef moves
        hitbox.setPosition(x,y);
    }

    /*
        Draw the chef and each item + plate the chef is carrying
        Has the batch as a parameter to draw to the screen
        The filenames of need to be specific as it makes a texture based on the items the chef is holding and
        finds a file for that item. E.g. Chopped onion has the img name "OnionChopped.png" since the name of the
        item will be "Onion" and the status is "Chopped"
    */
    public void draw(Batch batch){
        int x_offset = 0;
        int y_offset = 0;
        batch.draw(img, x,y,width,height);
        for (int i = 0; i <= stack.top; i++) {
            batch.draw(plate_img, x + x_offset, y + y_offset, 16,16);
            batch.draw(new Texture(stack.arr[i].name + stack.arr[i].status + ".png"),x + x_offset, y + y_offset, 16,16);
            x_offset += 10;
            y_offset+=5;
        }
    }

}
