package Objects;

import java.util.Arrays;

public class Station extends GameObject{
    
    static String type;

    public Station(String type){
        this.type = type;
    }

    public void interact(Chef chef, Recipe recipe){
        // This function will be called when the player is
        // in its hitbox and presses specified key

        if (type == "Chopping"){
            // List of all the choppable items (TBD)
            String[] choppable_items = {"Tomatoe", "Onion", "Lettuce"};

            
            Item top_stack_item = chef.stack.peak();
            // Check if top of chef's stack can be chopped
            if (top_stack_item.status == ""){
                if (Arrays.asList(choppable_items).contains(top_stack_item.name)){
                    // Change the status of the item, pop the stack
                    // and push the item with the updated status to the stack
                    top_stack_item.status = "Chopped";
                    chef.stack.pop();
                    chef.stack.push(top_stack_item);
                }
            }
        }

        // Pops item on chefs stack
        else if (type == "Bin"){
            // Can only pop if there is something in the stack
            if (chef.stack.isEmpty()){
                System.out.println("No FOOOD");
                return;
            }
            chef.stack.pop();
        }
    }

}
