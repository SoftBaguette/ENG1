package Objects;

public class AssemblyStation extends Station{
    
    Item[] items_on_station;
    int pointer;

    public AssemblyStation(int x, int y, int width, int height, String type) {
        super(x, y, width, height, type);
        items_on_station = new Item[10];
        pointer = 0;
    }

    public AssemblyStation() {
        super();
        items_on_station = new Item[10];
        pointer = 0;
    }

    public void interact(Recipe recipe, Chef chef){
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

    //Test case
    public static void main(String[] args) {
        // Initialising the chef, ingredients, a chopping station, 
        // assembly station and recipe (in this case a salad)
        Chef chef1 = new Chef(5,5,5,5);
        Ingredients tomatoe_box = new Ingredients(new Item("Tomatoe", ""));
        Ingredients lettuce_box = new Ingredients(new Item("Lettuce", ""));
        Ingredients onion_box = new Ingredients(new Item("Onion", ""));
        ChoppingStation chop1 = new ChoppingStation();
        AssemblyStation assemblyStation = new AssemblyStation();
        Item[] salad_ingredients = {new Item("Tomatoe", "Chopped"),new Item("Lettuce", "Chopped"),new Item("Onion", "Chopped")};
        Recipe salad = new Recipe("Salad", salad_ingredients);
        // Get all the ingredients
        tomatoe_box.interact(chef1);
        lettuce_box.interact(chef1);
        onion_box.interact(chef1);
        // Chop the top item on the stack and then add to assembly station
        // Repeat till the stack is empty
        chop1.interact(chef1);
        assemblyStation.interact(salad, chef1);
        chop1.interact(chef1);
        assemblyStation.interact(salad, chef1);
        chop1.interact(chef1);
        // Assemble the salad
        assemblyStation.interact(salad, chef1);
        
        
        


    }
}

