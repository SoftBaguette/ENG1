package Objects;

public class Bin{

    public Bin(){

    }

    // Pops item on chefs stack
    public void interact(Chef chef){
        // Can only pop if there is something in the stack
        if (chef.stack.isEmpty()){
            System.out.println("No FOOOD");
        }
        chef.stack.pop();
    }

}