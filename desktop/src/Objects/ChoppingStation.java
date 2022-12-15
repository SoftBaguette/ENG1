package Objects;

import java.util.Arrays;

public class ChoppingStation{
    
    public ChoppingStation (){

    }

    // This function will be called when the player is
    // in its hitbox and presses specified key
    public void interact(Chef chef){
        // List of all the choppable items (TBD)
        String[] choppable_items = {"Tomatoe", "Onion", "Lettuce"};

        
        Item item = chef.stack.peak();
        // Check if top of chef's stack can be chopped
        if (item.status == ""){
            if (Arrays.asList(choppable_items).contains(item.name)){
                // Change the status of the item, pop the stack
                // and push the item with the updated status to the stack
                item.status = "Chopped";
                chef.stack.pop();
                chef.stack.push(item);
            }
        }

        

    }

    
}
