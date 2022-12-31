package Objects;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.simple.parser.ParseException;

public class Ingredient_source extends Station{
    Item ingredient;

    public Ingredient_source(int x, int y, int width, int height, String type, float interaction_duraction ,Item ingredient) throws FileNotFoundException, IOException, ParseException{
        super(x, y, width, height, type, interaction_duration);
        this.ingredient = ingredient;
    }

    // Pushes the item into the Chef's stack if there is 
    // space
    public void interact(Chef chef, Recipe recipe){
        if (chef.stack.isFull()){
            return;
        }
        chef.stack.push(ingredient);
        
    }

    public static void main(String[] args) throws FileNotFoundException, IOException, ParseException {
        Chef chef1  = new Chef(0, 0, 0, 0,0);
        Item onion = new Item("Onion", "");
        Ingredient_source onion_box = new Ingredient_source(0, 0, 0, 0, null, 0, onion);
        Station[] stations = {onion_box};
        for (Station station : stations) {
            station.interact(chef1, null);
        }
        chef1.stack.printStack();
    }
}
