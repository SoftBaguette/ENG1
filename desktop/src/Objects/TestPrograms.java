package Objects;

public class TestPrograms {
    

    public static void main(String[] args) {
        // Initialise objects
        // Initialising the chef, ingredients, a chopping station, 
        // assembly station and recipe (in this case a salad)
        Chef chef1 = new Chef(5,5,5,5);
        Ingredients tomatoe_box = new Ingredients(new Item("Tomatoe", ""));
        Ingredients lettuce_box = new Ingredients(new Item("Lettuce", ""));
        Ingredients onion_box = new Ingredients(new Item("Onion", ""));
        Station chop1 = new Station(30, 30, 4, 4, "Chopping");
        Station assemblyStation = new AssemblyStation(50, 50, 4, 4, "Assembly");
        Item[] salad_ingredients = {new Item("Tomatoe", "Chopped"),new Item("Lettuce", "Chopped"),new Item("Onion", "Chopped")};
        Recipe salad = new Recipe("Salad", salad_ingredients);


        tomatoe_box.interact(chef1);
        lettuce_box.interact(chef1);
        onion_box.interact(chef1);

        chef1.move(30,30);
        // if interact key pressed:
        if (chef1.check_hitbox(chop1)){
            chop1.interact(chef1, salad);
        }
        chef1.move(50,50);
        if (chef1.check_hitbox(assemblyStation)){
            assemblyStation.interact(chef1, salad);
        }

        chef1.move(30,30);
        // if interact key pressed:
        if (chef1.check_hitbox(chop1)){
            chop1.interact(chef1, salad);
        }
        chef1.move(50,50);
        if (chef1.check_hitbox(assemblyStation)){
            assemblyStation.interact(chef1, salad);
        }

        chef1.move(30,30);
        // if interact key pressed:
        if (chef1.check_hitbox(chop1)){
            chop1.interact(chef1, salad);
        }
        chef1.move(50,50);
        if (chef1.check_hitbox(assemblyStation)){
            assemblyStation.interact(chef1, salad);
        }
        
    }
}
