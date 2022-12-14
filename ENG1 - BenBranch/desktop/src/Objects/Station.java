package Objects;

import java.util.Arrays;

public class Station extends GameObject{
    
    String type;
    Item[] items_on_station;
    int pointer;
    boolean containsIngredients;
    Ingredients ingredients;

    public Station(int x, int y, int width, int height, String type, Ingredients ingredients,boolean containsIngredients) {
        super(x, y, width, height);
        this.type = type;
        items_on_station = new Item[10];
        pointer = 0;
        this.containsIngredients = containsIngredients;
        this.ingredients = ingredients;
    }

    public Station() {
        super();
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

        else if (type == "Assembly"){
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
                        System.out.println(item.name + " added to assemly station");
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
    }

}
