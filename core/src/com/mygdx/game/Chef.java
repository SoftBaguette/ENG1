package com.mygdx.game.Objects;

public class Chef extends Person {
    
    Item[] items; 
    public Stack stack;

    public Chef(int x, int y, int width, int height, float speed) {
        super(x, y, width, height, speed);
        stack = new Stack(5);
    }

    public void move(int x, int y){
        this.x = x;
        this.y = y;
        this.hitbox.setPosition(x, y);
    }
    
    

}
