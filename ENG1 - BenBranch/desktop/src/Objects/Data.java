package Objects;

public class IngredientData {
    main(){
        Station ChoppingStation = new Station(0, 0 , 10, 10, 'type', Null, false);
        Ingredients Carrot = new Ingredients((["chop"]); // Ingredients are instantiated here with the
        Station CarrotStation = new Station(10, 10, 10, 10, 'type', Carrot, true); // This station contains the ingredient Carrot
        // Need to implement a method in class Station where it checks if containsIngredients is true, if it is, the ingredient stored in the station is added to the Chef's stack
        Map<string, Station[]> PreparationStations = new HashMap<string, Station[]>();
        PreparationStations.put("chop",ChoppingStation); // Hope you can see what this is: maps all possible preparationSteps that an Ingredient might have onto an instantiation of a station in which it can be performed.



    }
}

