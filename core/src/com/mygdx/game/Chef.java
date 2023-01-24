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
        img = new Texture("ChefImage.png");
        plate_img = new Texture("Plate.png");
    }

    // Allow the user to move the chef using the arrow keys and WASD keys
    //TODO have boundaries
    public void move(){
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A)){
             x -= 200 * Gdx.graphics.getDeltaTime();
        }
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D)){
             x += 200 * Gdx.graphics.getDeltaTime();
        }
        if(Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.W)){
             y += 200 * Gdx.graphics.getDeltaTime();
        }
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN) || Gdx.input.isKeyPressed(Input.Keys.S)){
             y -= 200 * Gdx.graphics.getDeltaTime();
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
