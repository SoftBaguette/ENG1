package Objects;


import com.badlogic.gdx.math.Rectangle;

public class GameObject {
    int x;
    int y;
    int width;
    int height;
    int direction;
    int animation_state;
    int rotation;
    Rectangle hitbox;

    public GameObject(int x, int y, int width, int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        direction = 0;
        animation_state = 0;
        rotation = 0;
        hitbox = new Rectangle(x,y, width, height);
    }

    public GameObject() {
        x = 0;
        y = 0;
        width = 0;
        height = 0;
        direction = 0;
        animation_state = 0;
        rotation = 0;
        hitbox = new Rectangle(x,y, width, height);
    }

    public boolean check_hitbox(GameObject other){
        return hitbox.overlaps(other.hitbox);
        
    }


   

}

