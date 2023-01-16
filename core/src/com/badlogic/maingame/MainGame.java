package com.badlogic.maingame;

//import Objects.*;

import java.io.IOException;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.mygdx.game.*;
import org.json.simple.parser.ParseException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;

public class MainGame extends ApplicationAdapter {

    private OrthographicCamera camera;
    private SpriteBatch batch;
    private Chef chef1;
    private Chef chef2;
    private Chef chef3;
    private Chef[] chefs;
    private Item onion;
    private  Item lettuce;
    private  Item tomato;
    private  Item[] salad_ingredients;
    private Recipe salad;
    private Recipe burger;
    private Recipe[] recipes;
    private Ingredient_source tomato_box;
    private Ingredient_source lettuce_box;
    private Ingredient_source onion_box;
    private Ingredient_source burger_meat_box;
    private Ingredient_source burger_bun_box;
    private Station chopping_station;
    private Station toasting_station;
    private Station hob_station;
    private Station bin;
    private Station assembly_station;
    public Station[] stations;

    private Stage stage;

    private Customer customer1;
    private Customer[] customers;
    private int current_customer;
    private int reputation;

    @Override
    public void create(){
        reputation = 3;
        stage = new Stage();


        chef1 = new Chef(0, 0, 32, 32, 200);
        chef2 = new Chef(1000, 0, 10, 10, 0);
        chef3 = new Chef(2000, 0, 10, 10, 0);
        chefs = new Chef[] {chef1, chef2, chef3};



        // Salad Items
        onion = new Item("Onion", "");
        tomato = new Item("Tomato", "");
        lettuce = new Item("Lettuce", "");
        salad_ingredients = new Item[]{new Item("Tomato", "Chopped"),new Item("Lettuce", "Chopped"),new Item("Onion", "Chopped")};
        salad = new Recipe("Salad", salad_ingredients);

        // Burger Items
        Item burger_meat = new Item("Burger Meat", "");
        Item burger_bun = new Item("Burger Bun", "");
        Item[] burger_ingredients = {new Item("Burger Meat", "Cooked"),new Item("Burger Bun", "Toasted"),};
        burger = new Recipe("Burger", burger_ingredients);


        try {
            tomato_box = new Ingredient_source(20, 400, 64, 64, null,tomato);
            lettuce_box = new Ingredient_source(90, 400, 10, 10, null, lettuce);
            onion_box = new Ingredient_source(160, 400, 10, 10, null, onion);

            burger_meat_box = new Ingredient_source(210, 400, 10, 10, null, burger_meat);
            burger_bun_box = new Ingredient_source(440, 400, 10, 10, null,  burger_bun);
        } catch (IOException | ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        recipes = new Recipe[]{salad, burger};
        int current_recipe = 0;
        customer1 = new Customer(50,50,32,32,100,recipes);
        customers = new Customer[5];
        customers[0] = customer1;
        current_customer = 0;
        try {
            chopping_station = new Station(100,300,64,64, "Chopping");
            hob_station = new Station(180,300,64,64, "Hob");
            toasting_station = new Station(250,300,64,64, "Toaster");
            /*
            Station bin = new Station(110,0,50,10, "Bin", 2, stage);*/
            assembly_station = new Station(200,200,128,65, "Assembly");
        } catch (IOException | ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        //Station[] stations = {tomato_box, lettuce_box, onion_box, burger_meat_box, burger_bun_box, chopping_station, toasting_station, hob_station, bin, assembly_station};
        //Station[] stations = {tomato_box, lettuce_box, onion_box, burger_meat_box, burger_bun_box, chopping_station};
        stations = new Station[] {chopping_station, tomato_box, lettuce_box, onion_box,burger_bun_box, burger_meat_box, assembly_station, toasting_station, hob_station};

        camera = new OrthographicCamera();
        camera.setToOrtho(false,800, 480);
        batch = new SpriteBatch();

    }

    @Override
    public void render(){
        ScreenUtils.clear(0,0,0.2f,1);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        BitmapFont font = new BitmapFont();
        font.draw(batch, "Hello world", 10, 10);


        //Drawing everything
        for (Station station : stations) {
            station.draw(batch);
            station.update();
            for (Chef chef : chefs) {
                if (chef.check_hitbox(station)){
                    if (Gdx.input.isKeyJustPressed(Input.Keys.E)){
                        station.interact(chef, customers[current_customer].desired_food);
                        chef.stack.printStack();
                    }
                }
            }
        }
        for (Chef chef: chefs){
            chef.draw(batch);
        }

        for (Customer customer:customers){
            if (customer!= null){
                customer.move();
                customer.draw(batch);
                if (chef1.check_hitbox(customer)){
                    if (Gdx.input.isKeyJustPressed(Input.Keys.E)){
                        if (chef1.stack.top != -1){
                            reputation += customer.served(chef1);
                            current_customer += 1;
                            if (current_customer == customers.length){
                                current_customer = 0;
                            }
                            customers[current_customer] = new Customer(50,50,32,32,50,recipes);

                        }
                        }
                }
            }
        }



        chef1.move();



        batch.end();
    }

    @Override
    public void dispose(){
        batch.dispose();
    }
}
