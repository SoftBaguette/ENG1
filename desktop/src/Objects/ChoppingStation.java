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

    // Example Scenario
    public static void main(String[] args) {
        Chef chef1 = new Chef(5,5,1);
        chef1.stack.push(new Item("Onion", ""));

        Ingredients tomatoe_box = new Ingredients(new Item("Tomatoe", ""));
        tomatoe_box.interact(chef1);
        chef1.stack.printStack();

        ChoppingStation chop1 = new ChoppingStation();
        chop1.interact(chef1);
        chef1.stack.printStack();

    }
}
