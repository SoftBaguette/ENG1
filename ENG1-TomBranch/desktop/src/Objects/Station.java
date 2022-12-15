package Objects;

import java.util.Arrays;

public class Station extends GameObject{
    Item[] items_on_station;
    int pointer;
    Item updated_item;
    long start_time_interaction;
    static float interaction_duration;
    boolean interacting;
    // [[type, action]]
    String[][] station_actions;
    boolean assembled;
    boolean continuesWhenLeft;
    String type;
    public Station(int x, int y, int width, int height, float interaction_duration, boolean continuesWhenLeft, String type) { // Only use this constructor for special stations
        super(x, y, width, height);
        this.type = type;
        this.continuesWhenLeft = continuesWhenLeft;
        items_on_station = new Item[10];
        pointer = 0;
        this.interaction_duration = interaction_duration;
        interacting = false;
        assembled = false;
        }
    public Station(int x, int y, int width, int height, float interaction_duration, boolean continuesWhenLeft) { // Use this constructor for stations that don't need specific extra functionality
        super(x, y, width, height);
        this.continueswhenLeft = continuesWhenLeft;
        items_on_station = new Item[10];
        pointer = 0;
        this.interaction_duration = interaction_duration;
        interacting = false;
        assembled = false;
        Type = Null;
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
        if (type == "Assembly"){ // Functionality for assembly station
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
                            System.out.println("\n" + item.name + " added to assembly station");
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
                System.out.println("No FOOD");
                return;
            }
            chef.stack.pop();
        }
        else{
            // Place item on station
            if (items_on_station[0] == null){
                if (chef.stack.isEmpty() == false){
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
                        updated_item = items_on_station[0];
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
                    items_on_station[0] = null;}}}}}

