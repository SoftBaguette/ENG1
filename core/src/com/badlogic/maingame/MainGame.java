package com.badlogic.maingame;

import Objects.*;

import java.io.IOException;

import org.json.simple.parser.ParseException;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class MainGame extends ApplicationAdapter {

    private OrthographicCamera camera;
    private SpriteBatch batch;
    private Chef chef1;
	private Chef chef2;
	private Chef chef3;
	private Chef[] chefs;
	private  Item onion;
	private  Item lettuce;
	private  Item tomato;
	private  Item[] salad_ingredients;
	private Recipe salad;
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
	private Station[] stations;

    @Override
    public void create(){
        Chef chef1 = new Chef(0, 0, 10, 10, 0);
        Chef chef2 = new Chef(100, 0, 10, 10, 0);
        Chef chef3 = new Chef(200, 0, 10, 10, 0);
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

     
        try {
            Ingredient_source tomato_box = new Ingredient_source(20, 0, 10, 10, null, 0,tomato);
            Ingredient_source lettuce_box = new Ingredient_source(50, 0, 10, 10, null, 0,lettuce);
            Ingredient_source onion_box = new Ingredient_source(80, 0, 10, 10, null, 0,onion);

            Ingredient_source burger_meat_box = new Ingredient_source(110, 0, 10, 10, null, 0,burger_meat);
            Ingredient_source burger_bun_box = new Ingredient_source(140, 0, 10, 10, null, 0, burger_bun);
        } catch (IOException | ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        

        Recipe[] recipes = {salad, burger};
        int current_recipe = 0;

        
        try {
            Station chopping_station = new Station(20,50,10,10, "Chopping", 2);
            Station toasting_station = new Station(50,50,10,10, "Toaster", 2);
            Station hob_station = new Station(80,50,10,10, "Hob", 2);
            Station bin = new Station(110,0,50,10, "Bin", 2);
            Station assembly_station = new Station(140,50,10,10, "Assembly", 2);
        } catch (IOException | ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        

        Station[] stations = {tomato_box, lettuce_box, onion_box, burger_meat_box, burger_bun_box, chopping_station, toasting_station, hob_station, bin, assembly_station};

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
    }
}
