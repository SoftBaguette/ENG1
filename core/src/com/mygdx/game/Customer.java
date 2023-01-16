package com.mygdx.game.Objects;

public class Customer extends Person{

    String status;
    
    Item desired_food;
    public Customer(int x, int y, int width, int height, float speed, Item[] meal_options) {
        super(x, y, width, height, speed);
        status = "started";
        desired_food = meal_options[(int)(Math.random() * meal_options.length)];
    }
    
    public void move(){
        if (status=="started"){
            x += speed;
            // In range of serving station. TODO
            if (x> 400 && x<450){
                status = "waiting";
            }
        }
        else if(status == "served"){
            x += speed;
            // Remove customer when leaves the screen
            if (x > 800){
                status = "destroy";
            }
        }
        
    }

    public boolean served(Item item){
        status = "served";
        if (item.name == desired_food.name && item.status == desired_food.status){
            
            return true;
        }else{
            return false;
        }
    }

    public static void main(String[] args) {
        Item[] meal_options = {new Item("Salad", ""), new Item("Burger", "")};
        Customer customer1 = new Customer(0, 0, 0, 0, 0, meal_options);
        System.out.println(customer1.served(new Item("Salad", "")));
    
    }


}
