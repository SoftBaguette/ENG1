package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;

/*
    GameObject is an abstract class which most of the game objects inherit from.
    It has all the core attributes and methods needed for any object in the game
*/

public class GameObject {
    int x;
    int y;
    int width;
    int height;
    int direction;
    int animation_state;
    int rotation;
    Rectangle hitbox;
    String img_name; 
    Texture img;
    public GameObject(int x, int y, int width, int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        direction = 0;
        animation_state = 0;
        rotation = 0;
        hitbox = new Rectangle(x,y, width, height);
        img_name = "";
        //img = new Texture(img_name);
    }

    public GameObject() {
        x = 0;
        y = 0;
        width = 0;
        height = 0;
        direction = 0;
        animation_state = 0;
        rotation = 0;
        hitbox = new Rectangle(x,y, width, height);
    }

    /*
        Checks if 2 object's hitbox's collide
        Parameter: another gameobject
        Returns: True if there is a collision. False if there isn't
    */
    public boolean check_hitbox(GameObject other){
        return hitbox.overlaps(other.hitbox);
        
    }

    // Draws the gameobject to the screen using batch
    // The width and height will scale the img to the objects width and height
    public void draw(Batch batch){
        batch.draw(img, x, y, width, height);
    }

}

