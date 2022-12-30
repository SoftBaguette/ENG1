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
    
    

}
