package Objects;

import java.util.Arrays;

public class Station extends GameObject{
    
    String type;
    Item[] items_on_station;
    int pointer;
    String[] interactable_items;
    Item updated_item;
    long start_time_interaction;
    float interaction_duration;
    boolean interacting;

    public Station(int x, int y, int width, int height, String type) {
        super(x, y, width, height);
        this.type = type;
        items_on_station = new Item[10];
        pointer = 0;
        interaction_duration = 4;
        interacting = false;
        if (type == "Chopping"){
            interactable_items = new String[] {"Tomato", "Onion", "Lettuce"};
        }
        else if (type == "Toaster"){
            interactable_items = new String[] {"Burger Bun"};
        }
        else if (type == "Hob"){
            interactable_items = new String[] {"Burger Meat"};
        }
        else{
            interactable_items = new String[] {};
        }
    }

    public Station() {
        super();
    }

    public void update(){
        if (interacting == true){
            if (System.currentTimeMillis() - start_time_interaction > interaction_duration){
                items_on_station[0] = updated_item;
                interacting = false;
                System.out.println("Finished");
            }
        }
    }

    public void interact(Chef chef, Recipe recipe){
        // Place down item from chef stack to station
        if (type == "Assembly"){
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
                System.out.println("Item made");
                
                Item assembled = new Item(recipe.recipe_name, "");
                System.out.println(assembled.name);
                items_on_station = new Item[10];
                items_on_station[0] = assembled;
            }
        }
        else if (type == "Bin"){
            // Can only pop if there is something in the stack
            if (chef.stack.isEmpty()){
                System.out.println("No FOOOD");
                return;
            }
            chef.stack.pop();
        }
        else{
            if (items_on_station[0] == null){
                if (chef.stack.isEmpty() == false){
                    if(Arrays.asList(interactable_items).contains(chef.stack.peak().name)){
                        items_on_station[0] = chef.stack.pop();
                    }
                    
                }
            }
            else if (items_on_station[0].status == ""){
                if (type == "Chopping"){
                    updated_item = items_on_station[0];
                    updated_item.status = "Chopped";
                    start_time_interaction = System.currentTimeMillis();
                    interacting = true;
                }
                else if (type == "Toaster"){
                    updated_item = items_on_station[0];
                    updated_item.status = "Toasted";
                    start_time_interaction = System.currentTimeMillis();
                    interacting = true;
                }
                // This function will be called when the player is
                // in its hitbox and presses specified key
                else if (type == "Hob"){
                    updated_item = items_on_station[0];
                    updated_item.status = "Flip Me";
                    start_time_interaction = System.currentTimeMillis();
                    interacting = true;
                }

            }
            else if (items_on_station[0].status != "" && type != "hob"){
                if (interacting == false){
                    chef.stack.push(items_on_station[0]);
                    updated_item = null;
                    items_on_station[0] = null;
                }
            }
        }

        
        /*

        else if (type == "Hob"){
            // Empty station
            if (pointer == 0){
                Item top_stack_item = chef.stack.peak();
                if (top_stack_item.name == "Burger Meat"){
                    if (top_stack_item.status == ""){
                        items_on_station[pointer] = top_stack_item;
                        pointer++;
                        // Wait x amount of time
                        items_on_station[0].status = "Flip Me";

                    }
                }
            }
            else if (pointer > 0){
                if (items_on_station[0].status == "Flip Me"){
                    items_on_station[0].status = "Flipped";
                    // Wait x amount of time
                    items_on_station[0].status = "Cooked";
                    chef.stack.pop();
                    chef.stack.push(items_on_station[0]);
                    pointer --;
                }
            }
            }*/
        
        chef.stack.printStack();
        return;
    }

}
