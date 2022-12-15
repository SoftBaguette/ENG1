package Objects;
import java.util.HashMap;
import java.util.Map;

public class Data {

    public static void main(String[] args) { // Initialises all of the stations and ingredients
    Station ChoppingStation = new Station(0, 0, 10, 10, 20, false);
    Station Oven = new Station(10, 10, 10, 10, 20, true);
    Ingredient Tomato = new Ingredient(new String[]{"chop", "fry"}); // Ingredients are instantiated here with the steps to prepare them
    Station TomatoStation = new Ingredient_source(10, 10, 10, 10, 1, Tomato); // This station contains the ingredient Carrot,
    Map<String, Station> PreparationStations = new HashMap<String, Station>();
    PreparationStations.put("chop",ChoppingStation);
    PreparationStations.put("bake", Oven);// Hope you can see what this is: maps all possible preparationSteps that an Ingredient might have onto an instantiation of a station in which it can be performed.

    }}



