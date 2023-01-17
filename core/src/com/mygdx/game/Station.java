package com.mygdx.game;

import java.io.FileReader;
import java.util.Arrays;

import java.io.FileNotFoundException;
import java.io.IOException;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Station extends GameObject{
    
    String type;
    Item[] items_on_station;
    int pointer;
    String[] interactable_items;
    Item updated_item;
    long start_time_interaction;
    static long interaction_duration;
    boolean interacting;
    // [[type, action]]
    String[][] station_actions;
    String action;
    boolean assembled;
    ProgressBar progressBar;
    float interaction_duraction;
    
    public Station(int x, int y, int width, int height, String type) throws FileNotFoundException, IOException, ParseException {
        super(x, y, width, height);
        this.type = type;
        items_on_station = new Item[10];
        pointer = 0;
        progressBar = new ProgressBar(x+7, y+height+10, 50,10);
        Object obj = new JSONParser().parse(new FileReader("assets/Stations.json"));
        JSONArray jsonArray = (JSONArray) obj;
        int length = jsonArray.size();
        for (int i = 0; i < length; i++) {
            JSONObject jsonObject = (JSONObject) jsonArray.get(i);
            String json_type = (String)jsonObject.get("type");
            if (json_type.equals(type)){
                action = (String) jsonObject.get("action");
                JSONArray interactable_items_json = (JSONArray) jsonObject.get("interactable_items");
                interactable_items = new String[interactable_items_json.size()];
                System.out.println(interactable_items_json.size());
                for (int j = 0; j < interactable_items_json.size(); j++) {
                    System.out.println((String) interactable_items_json.get(j));
                    interactable_items[j] = (String) interactable_items_json.get(j);
                }
                interaction_duraction = (Long) jsonObject.get("interaction_duration");
                img_name = (String) jsonObject.get("img_name");
                //TODO uncomment this when moving to intelliJ
                img = new Texture(img_name);
                System.out.println(img_name);
                //img = new Texture("badlogic.jpg");
            }
        }
        interacting = false;
        station_actions = new String[][] {{"Chopping", "Chopped"},{"Toaster", "Toasted"}, {"Hob", "Flip Me"}};
        assembled = false;
    }

    public Station() {
        super();
    }

    public void update(){
        if (interacting == true){
            long interaction_duration=3000;
            float percent = (float) (System.currentTimeMillis() - start_time_interaction+1)/interaction_duration;
            progressBar.setProgress((int) (percent*100));
            //System.out.println(progressBar.progress);
            if (System.currentTimeMillis() - start_time_interaction > interaction_duration){
                items_on_station[0] = updated_item;
                interacting = false;
                progressBar.setProgress(0);
                System.out.println("Finished");
            }
        }
    }

    //Draw the station and also draw all the items on each station
    public void draw(Batch batch){
        batch.draw(img,x,y,width,height);
        if (interacting == true){
            progressBar.draw(batch, 0);
        }
        if (type == "Assembly"){
            int x_offset = 0;
            for(Item station_item: items_on_station){
                if (station_item != null){
                    batch.draw(new Texture("Plate.png"), x + x_offset, y, 32, 32);
                    batch.draw(new Texture(station_item.name + station_item.status + ".png"),x + x_offset, y , 32,32);
                    x_offset +=50;
                }

            }
        }else{
            if (items_on_station[0] != null){
                batch.draw(new Texture("Plate.png"), x, y, 32, 32);
                batch.draw(new Texture(items_on_station[0].name + items_on_station[0].status + ".png"),x , y , 32,32);
            }
        }
    }

    public void interact(Chef chef, Recipe recipe){
        // Place down item from chef stack to station
        if (type == "Assembly"){

            if (assembled == true){
                if (chef.stack.isFull()){
                    System.out.println("Stack is full");
                    return;
                }
                chef.stack.push(items_on_station[0]);
                items_on_station[0] = null;
                assembled = false;
            }
            else{
                // Cannot assemble anything if the chef isn't
                // carrying anything
                if (chef.stack.isEmpty()){
                    System.out.println("Stack empty");
                    return;
                }

                // If the top of chef's stack is valid for the current recipe
                // add the item to the assembly station
                for (Item item : recipe.ingredients) {
                    if (chef.stack.isEmpty() != true){
                        if (chef.stack.peak().name == item.name && chef.stack.peak().status == item.status){
                            items_on_station[++pointer] = chef.stack.pop();
                            System.out.println("\n" + item.name + " added to assemly station");
                        }
                    }
                }

                // Check if all the items on the assembly station
                // are right for the recipe
                int items_ready = 0;
                for (Item item : items_on_station) {
                    for (Item item2 : recipe.ingredients) {
                        if (item != null && item2 != null){
                            if (item.name == item2.name && item.status == item2.status){
                            items_ready += 1;
                        }
                        }
                        
                    }
                }
                if (items_ready == recipe.ingredients.length){
                    
                    Item assembled_item = new Item(recipe.recipe_name, "");
                    System.out.println("Successfully assembled: " +assembled_item.name);
                    items_on_station = new Item[10];
                    items_on_station[0] = assembled_item;
                    assembled = true;
                }
            }
            
        }
        // The bin is to get rid of the top item from the Chef's stack
        else if (type == "Bin"){
            // Can only pop if there is something in the stack
            if (chef.stack.isEmpty()){
                System.out.println("No FOOOD");
                return;
            }
            chef.stack.pop();
        }
    
        else{
            // Place item on station
            if (items_on_station[0] == null){
                if (chef.stack.isEmpty() == false){
                    for (String item: interactable_items){
                        System.out.println(item);
                    }

                    if(Arrays.asList(interactable_items).contains(chef.stack.peak().name)){
                        items_on_station[0] = chef.stack.pop();
                    }
                    
                }
            }
            // If there is an item on the station, we need to interact with it (e.g . Chopping)
            // A timer will also start here and the 
            else if (items_on_station[0].status == "" && interacting == false){
                for (int i = 0; i < station_actions.length; i++) {
                    if (type == station_actions[i][0]){
                        updated_item = new Item(items_on_station[0].name, station_actions[i][1]);
                        updated_item.status = station_actions[i][1];
                        start_time_interaction = System.currentTimeMillis();
                        interacting = true;
                    }
                }

            }
            // Picking up item when it is complete
            else if (items_on_station[0].status != "" && type != "Hob"){
                if (interacting == false){
                    if (chef.stack.isFull()){
                        System.out.println("Stack is full");
                        return;
                    }
                    chef.stack.push(items_on_station[0]);
                    updated_item = null;
                    items_on_station[0] = null;
                }
            }
            //TODO: CHANGE THIS BECAUSE IT IS NOT GOOD
            // Picking up item when it is complete
            else if (items_on_station[0].status == "Cooked" && type == "Hob"){
                if (interacting == false){
                    if (chef.stack.isFull()){
                        System.out.println("Stack is full");
                        return;
                    }
                    chef.stack.push(items_on_station[0]);
                    updated_item = null;
                    items_on_station[0] = null;
                }
            }

            else if (items_on_station[0].status == "Flip Me" && type == "Hob"){
                items_on_station[0].status = "Cooked";
                start_time_interaction = System.currentTimeMillis();
                interacting = true;
            }
        }

        chef.stack.printStack();
        return;
    }

}
