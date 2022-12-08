package Objects;

public class Bin extends Station{

    public Bin(){

    }

    public void Interact(Chef chef){
        if (chef.stack.isEmpty()){
            System.out.println("No FOOOD");
        }
        chef.stack.pop();
    }

}