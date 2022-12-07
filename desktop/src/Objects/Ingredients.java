package Objects;

public class Ingredients {
    Item ingredient;

    public Ingredients(Item ingredient){
        this.ingredient = ingredient;
    }

    // Pushes the item into the Chef's stack if there is 
    // space
    public boolean interact(Chef chef){
        if (chef.stack.isFull()){
            return false;
        }
        chef.stack.push(ingredient);
        return true;
    }

    
}
