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

    public void draw(Batch batch){
        batch.draw(img, x, y ,width, height);
        if (status == "Happy" || status == "Sad"){
            batch.draw(new Texture(status+".png"), x+15, y+15, 32,32);
        }
        else{
            batch.draw(new Texture("ThinkingBubble.png"), x+15, y+15);
            batch.draw(new Texture(desired_food.recipe_name + ".png"),x+15, y + 15, 32,32);
        }


    }
    
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

    public static void main(String[] args) {
        Item[] meal_options = {new Item("Salad", ""), new Item("Burger", "")};
        //Customer customer1 = new Customer(0, 0, 0, 0, 0, meal_options);
        //.out.println(customer1.served(new Item("Salad", "")));
    
    }


}
