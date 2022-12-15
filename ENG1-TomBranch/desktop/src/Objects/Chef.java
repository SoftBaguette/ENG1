package Objects;

public class Chef extends Person {
    
    Item[] items; 
    Stack stack;

    public Chef(int x, int y, int width, int height) {
        super(x, y, width, height);
        stack = new Stack(5);
    }

    public void move(int x, int y){
        this.x = x;
        this.y = y;
        this.hitbox.setPosition(x, y);
    }
    public void interact(Station station) {
        if (data.preparationStations[stack.peak().status[stepPointer]] == station) {
            // Allow the interaction in this case, since the chef is at the correct station for his current ingredient
        } else if (station instanceof Ingredient_source) { // If it's an ingredient source, code needs to be implemented to take ingredients
        }
    }}
