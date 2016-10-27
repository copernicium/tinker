package computer_science.RandomProjects.TextAdventure;
import java.lang.*;
import java.util.Vector;
public class TextAdventure
{
    private Vector<Room> rooms;
    int currentRoom;
    public static TextAdventure buildGame(){
        Vector<Room> rooms = new Vector<Room>(0);
        {
            Vector<java.lang.Object> objects = new Vector<java.lang.Object>(0);
            objects.addElement(new java.lang.Object("Dagger","A rusty old dagger that is no fun to play with"));
            rooms.addElement(new Room("the dining hall","It is large ornate room with a fancy oak table in its center. It's not your style.",objects));
        }
        TextAdventure textAdventure= new TextAdventure(rooms);
        return textAdventure;
    }
    
    public void play(){
        if(rooms.size()==0){
            System.out.println("There are no rooms. Exiting");
            return;
        }
        
        System.out.println("You wake up in " + rooms.elementAt(0).getName() + ".");
        while(currentRoom < rooms.size()){
            rooms.elementAt(currentRoom).printActions();
            rooms.elementAt(currentRoom).act();
            break;
        }
    }
    public TextAdventure(){
        rooms = new Vector<Room>(0);
        currentRoom = 0;
    }
    public TextAdventure(Vector<Room> rooms){
        this.rooms = rooms;
        this.currentRoom = 0;
    }
}
