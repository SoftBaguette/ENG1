package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

public class Customer extends Person{

    String status;
    
    public Recipe desired_food;
    public Customer(int x, int y, int width, int height, float speed, Recipe[] meal_options) {
        super(x, y, width, height, speed);
        status = "started";
        desired_food = meal_options[(int)(Math.random() * meal_options.length)];
        img = new Texture("Customer.png");
    }

    // Draw the customer using batch
    // The customer will show what item it wants.
    // When the customer has been served, it will show if it is happy or sad (using a simple emoji)
    public void draw(Batch batch){
        batch.draw(img, x, y ,width, height);
        //Draw how the customer is feeling after being served
        if (status == "Happy" || status == "Sad"){
            batch.draw(new Texture(status+".png"), x+30, y+30, 32,32);
        }
        // Draw what food item the customer wants
        else{
            batch.draw(new Texture("ThinkingBubble.png"), x+30, y+30);
            batch.draw(new Texture(desired_food.recipe_name + ".png"),x+30, y + 30, 32,32);
        }


    }
    
    //Move the customer towards the serving station. When it is served move it off the screen
    public void move(){
        hitbox.setPosition(x,y);
        if (status=="started"){
            x += 100* Gdx.graphics.getDeltaTime();
            // In range of serving station. TODO
            if (x> 400 && x<450){
                status = "waiting";
            }
        }
        else if(status == "Happy" || status == "Sad"){
            x += 100 * Gdx.graphics.getDeltaTime();
            // Remove customer when leaves the screen
            if (x > 800){
                status = "destroy";
            }
        }
        
    }

    // This function is for serving the customers.
    // It takes in a chef as a paremeter and uses it's stack
    // Returns an integer value that affects the reputation
    // TODO: For assessment 2 change what happens when the customer is served
    public int served(Chef chef){
        if (chef.stack.peak().name == desired_food.recipe_name){
            System.out.println("Served");
            chef.stack.pop();
            status = "Happy";
            return 0;
        }else{
            chef.stack.pop();
            status = "Sad";
            return -1;
        }
    }

}
