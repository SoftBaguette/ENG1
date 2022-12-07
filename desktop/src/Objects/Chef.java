package Objects;

public class Chef extends Person {
    
    Item[] items; 
    Stack stack;
    public Chef (int x, int y, int direction){
        this.x = x;
        this.y = y;
        this.height = 10;
        this.width = 10;
        this.direction = direction;
        this.animation_state = 1;
        this.rotation = 0;
        stack = new Stack(5);
    }

}
