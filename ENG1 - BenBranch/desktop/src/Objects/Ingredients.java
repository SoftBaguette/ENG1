package Objects;



public class Ingredients extends GameObject{
    Item ingredient;
    string[] preparationSteps;

    public Ingredients(string[] preparationSteps) {
        this.preparationSteps = preparationSteps;}

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
