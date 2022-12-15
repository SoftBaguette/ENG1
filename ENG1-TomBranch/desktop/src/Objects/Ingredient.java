package Objects;

public class Ingredient extends Item {
    Item ingredient; // Do we need this?
    int stepPointer; // Use this to determine where in the preparation process an ingredient is
    String[] preparationSteps; // Strings are used because the data file can map strings onto individual stations
    public Ingredient(String[] preparationSteps) {
        this.preparationSteps = preparationSteps;
        stepPointer = 0;
    }
}
