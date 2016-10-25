import java.util.Vector;
import java.util.Scanner; 
public class Room
{
    private String name;
    private String description;
    private Vector<Object> objects;
    private final static String[] actions = {"Look around"};
    
    public void printActions(){
        System.out.println("Available actions: ");
        for(int i = 0; i < actions.length; i++){
            System.out.println(i+1 + " " + actions[i]);
        }
    }    
    
    public String getName(){
        return name;
    }
    
    public void act(){
        Scanner reader = new Scanner(System.in);
        System.out.println("Enter an option: (1 - " + actions.length + ": ");
        int action = reader.nextInt();
        switch(action){
            default: break;
        }
    }
    
    public void lookAround(){
        System.out.println("You look around " + this.name + ".");
        System.out.println(this.description);
        System.out.println("There are some things scattered around: ");
        for(int i = 0; i < objects.size(); i++) {
            System.out.println(objects.elementAt(i));
        }
    }
    
    public Room(){
        name = "";
        description = "";
        objects = new Vector<Object>(0);
    }
    public Room(String name,String description,Vector<Object> objects){
        this.name = name;
        this.description = description;
        this.objects = objects;
    }
}
