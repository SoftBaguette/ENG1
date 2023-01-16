package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

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

    public void move(){
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) x -= 200 * Gdx.graphics.getDeltaTime();
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) x += 200 * Gdx.graphics.getDeltaTime();
        if(Gdx.input.isKeyPressed(Input.Keys.UP)) y += 200 * Gdx.graphics.getDeltaTime();
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) y -= 200 * Gdx.graphics.getDeltaTime();
        hitbox.setPosition(x,y);
    }

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

    public void move2(int x, int y){
        this.x = x;
        this.y = y;
        this.hitbox.setPosition(x, y);
    }

    public void move_left(){
        x -= speed *Gdx.graphics.getDeltaTime();
    }
    public void move_right(){
        x += speed * Gdx.graphics.getDeltaTime();
    }

    public void move_up(){
        y -= speed  * Gdx.graphics.getDeltaTime();
    }
    public void move_down(){
        y += speed  * Gdx.graphics.getDeltaTime();
    }
    public void print_pos(){
        System.out.println(x);
        System.out.println(y);
    }
    

}
