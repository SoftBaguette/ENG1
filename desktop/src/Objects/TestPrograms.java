package Objects;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

//import static org.junit.Assert.assertEquals;

//import org.junit.Test;
 

// @Test
public class TestPrograms {
    public TestPrograms(){

    }

    public void large_scale_test(){
        Chef chef1 = new Chef(0, 0, 10, 10);
        Chef chef2 = new Chef(100, 0, 10, 10);
        Chef chef3 = new Chef(200, 0, 10, 10); 
        Chef[] chefs = {chef1, chef2, chef3};

        // Salad Items
        Item onion = new Item("Onion", "");
        Item tomato = new Item("Tomato", "");
        Item lettuce = new Item("Lettuce", "");
        Item[] salad_ingredients = {new Item("Tomato", "Chopped"),new Item("Lettuce", "Chopped"),new Item("Onion", "Chopped")};
        Recipe salad = new Recipe("Salad", salad_ingredients);

        // Burger Items
        Item burger_meat = new Item("Burger Meat", "");
        Item burger_bun = new Item("Burger Bun", "");
        Item[] burger_ingredients = {new Item("Burger Meat", "Cooked"),new Item("Burger Bun", "Toasted"),};
        Recipe burger = new Recipe("Burger", burger_ingredients);
        
        Ingredient_source tomato_box = new Ingredient_source(20, 0, 10, 10, null, 0,tomato);
        Ingredient_source lettuce_box = new Ingredient_source(50, 0, 10, 10, null, 0,lettuce);
        Ingredient_source onion_box = new Ingredient_source(80, 0, 10, 10, null, 0,onion);

        Ingredient_source burger_meat_box = new Ingredient_source(110, 0, 10, 10, null, 0,burger_meat);
        Ingredient_source burger_bun_box = new Ingredient_source(140, 0, 10, 10, null, 0, burger_bun);

        Recipe[] recipes = {salad, burger};
        int current_recipe = 0;

        Station chopping_station = new Station(20,50,10,10, "Chopping", 2);
        Station toasting_station = new Station(50,50,10,10, "Toaster", 2);
        Station hob_station = new Station(80,50,10,10, "Hob", 2);
        Station bin = new Station(110,0,50,10, "Bin", 2);
        Station assembly_station = new Station(140,50,10,10, "Assembly", 2);

        Station[] stations = {tomato_box, lettuce_box, onion_box, burger_meat_box, burger_bun_box, chopping_station, toasting_station, hob_station, bin, assembly_station};

        chef1.stack.push(new Item("Onion", "Chopped"));
        chef1.stack.push(new Item("Lettuce", "Chopped"));
        chef1.stack.push(new Item("Tomato", "Chopped"));
        boolean cooked = false;
        while (cooked == false){
            Scanner myObj = new Scanner(System.in);
            System.out.println("Chef x");
            int new_x = Integer.parseInt(myObj.nextLine()) ;
            System.out.println("Chef y");
            int new_y = Integer.parseInt(myObj.nextLine());

            chef1.move(new_x, new_y);

            for (Station station : stations) {
                station.update();
                if (chef1.check_hitbox(station)){
                    System.out.println("Interact");
                    if (Integer.parseInt(myObj.nextLine()) == 1){
                        station.interact(chef1, recipes[current_recipe]);
                        System.out.println("Interacted");
                        if (station.assembled == true){
                            current_recipe += 1;
                            System.out.println("Increased" +current_recipe);
                        }
                        //chef1.stack.printStack();
                    }
                }
            }

        }

    }

    /* 
    // @Test
    public void testSalad() {
        // Initialise objects
        // Initialising the chef, ingredients, a chopping station, 
        // assembly station and recipe (in this case a salad)
        Chef chef1 = new Chef(5,5,5,5);
        Ingredients tomatoe_box = new Ingredients(new Item("Tomato", ""));
        Ingredients lettuce_box = new Ingredients(new Item("Lettuce", ""));
        Ingredients onion_box = new Ingredients(new Item("Onion", ""));
        Station chop1 = new Station(30, 30, 4, 4, "Chopping");
        Station assemblyStation = new AssemblyStation(50, 50, 4, 4, "Assembly");
        Item[] salad_ingredients = {new Item("Tomato", "Chopped"),new Item("Lettuce", "Chopped"),new Item("Onion", "Chopped")};
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

    public void testSalad2() {
        // Initialise objects
        // Initialising the chef, ingredients, a chopping station, 
        // assembly station and recipe (in this case a salad)
        Chef chef1 = new Chef(5,5,5,5);
        Ingredients tomatoe_box = new Ingredients(new Item("Tomato", ""));
        Ingredients lettuce_box = new Ingredients(new Item("Lettuce", ""));
        Ingredients onion_box = new Ingredients(new Item("Onion", ""));
        Station chop1 = new Station(30, 30, 4, 4, "Chopping");
        Station assemblyStation = new AssemblyStation(50, 50, 4, 4, "Assembly");
        Item[] salad_ingredients = {new Item("Tomato", "Chopped"),new Item("Lettuce", "Chopped"),new Item("Onion", "Chopped")};
        Recipe salad = new Recipe("Salad", salad_ingredients);


        tomatoe_box.interact(chef1);
        lettuce_box.interact(chef1);
        onion_box.interact(chef1);
        
        chop1.interact(chef1, salad);
        chop1.interact(chef1, salad);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            
        }
        System.out.println(chop1.start_time_interaction);
        chop1.update();
        chop1.interact(chef1, salad);
        
        assemblyStation.interact(chef1, salad);
        
    }

    public void testBurger(){
        Chef chef1 = new Chef(5,5,5,5);
        Ingredients burger_box = new Ingredients(new Item("Burger Meat", ""));
        Ingredients bun_box = new Ingredients(new Item("Burger Bun", ""));
        Station toaster = new Station(100, 100, 10, 10, "Toaster");
        Station hob = new Station(30, 30, 10, 10, "Hob");
        Station assemblyStation = new AssemblyStation(50, 50, 4, 4, "Assembly");
        Item[] burger_ingredients = {new Item("Burger Meat", "Cooked"),new Item("Burger Bun", "Toasted"),};
        Recipe burger = new Recipe("Burger", burger_ingredients);
        
        burger_box.interact(chef1);
        bun_box.interact(chef1);
        chef1.stack.printStack();
        chef1.move(100,100);
        // if interact key pressed:
        if (chef1.check_hitbox(toaster)){
            toaster.interact(chef1, burger);
        }
        chef1.stack.printStack();
        chef1.move(50,50);
        if (chef1.check_hitbox(assemblyStation)){
            assemblyStation.interact(chef1, burger);
        }

        chef1.move(30,30);
        // if interact key pressed:
        if (chef1.check_hitbox(hob)){
            hob.interact(chef1, burger);
        }
        if (chef1.check_hitbox(hob)){
            hob.interact(chef1,burger);
        }
        chef1.stack.printStack();
        chef1.move(50,50);
        if (chef1.check_hitbox(assemblyStation)){
            assemblyStation.interact(chef1, burger);
        }
    }

    public void whileLoopTest(){
        Chef chef1 = new Chef(5,5,5,5);

        Ingredients burger_box = new Ingredients(new Item("Burger Meat", ""));
        Ingredients bun_box = new Ingredients(new Item("Burger Bun", ""));

        Station chop1 = new Station(80, 80, 10, 10, "Chopping");
        Station toaster = new Station(100, 100, 10, 10, "Toaster");
        Station hob = new Station(30, 30, 10, 10, "Hob");
        Station assemblyStation = new AssemblyStation(50, 50, 4, 4, "Assembly");
        Station[] stations = {chop1, toaster, hob, assemblyStation};

        Item[] burger_ingredients = {new Item("Burger Meat", "Cooked"),new Item("Burger Bun", "Toasted"),};
        Recipe burger = new Recipe("Burger", burger_ingredients);

        burger_box.interact(chef1);
        bun_box.interact(chef1);

        boolean cooked = false;
        while (cooked == false){
            Scanner myObj = new Scanner(System.in);
            System.out.println("Chef x");
            int new_x = Integer.parseInt(myObj.nextLine()) ;
            System.out.println("Chef y");
            int new_y = Integer.parseInt(myObj.nextLine());
            
            chef1.move(new_x, new_y);
            for (Station station : stations) {
                if (chef1.check_hitbox(station)){
                    System.out.println("Interact");
                    if (Integer.parseInt(myObj.nextLine()) == 1){
                        station.interact(chef1, burger);
                        System.out.println("Interacted");
                        //chef1.stack.printStack();
                    }
                    //if (Gdx.input.isKeyPressed(Keys.E)){
                        
                    //}
                }
            }
            
        }
        
    }

    public void whileLoopTest2(){
        Chef chef1 = new Chef(100,100,5,5);

        Ingredients burger_box = new Ingredients(new Item("Burger Meat", ""));
        Ingredients bun_box = new Ingredients(new Item("Burger Bun", ""));

        Station chop1 = new Station(80, 80, 10, 10, "Chopping");
        Station toaster = new Station(100, 100, 10, 10, "Toaster");
        Station hob = new Station(30, 30, 10, 10, "Hob");
        Station assemblyStation = new AssemblyStation(50, 50, 4, 4, "Assembly");
        Station[] stations = {chop1, toaster, hob, assemblyStation};

        Item[] burger_ingredients = {new Item("Burger Meat", "Cooked"),new Item("Burger Bun", "Toasted"),};
        Recipe burger = new Recipe("Burger", burger_ingredients);

        burger_box.interact(chef1);
        bun_box.interact(chef1);

        boolean cooked = false;
        
        while (cooked == false){

        }

    }*/
    public static void main(String[] args) {
        TestPrograms test = new TestPrograms();
        //test.testSalad2();
        test.large_scale_test();
    }
}
