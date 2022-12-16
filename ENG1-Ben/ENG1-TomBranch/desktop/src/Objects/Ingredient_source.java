package Objects;

// Haven't looked at this yet, should use the new Ingredient class to refer to specific ingredients though, since that class will store the properties of each ingredient

public class Ingredient_source extends Station{
    Ingredient ingredient;

    public Ingredient_source(int x, int y, int width, int height, float interaction_duration ,Ingredient ingredient){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.interaction_duration = interaction_duration;
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

    public static void main(String[] args) { // Do we need a main in this class?
    }
}
