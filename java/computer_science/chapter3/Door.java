package computer_science.chapter3;
/**
 * A class to simulate a door
 * 
 * @author Logan Traffas
 * @version 10/3/16
 * assignment: Lab 3.1 Door class
 */
public class Door
{
    private String name;
    private String state;
    
    public void open(){
        state = "open";
    }
    
    public void close(){
        state = "closed";
    }
    
    public String getName(){
        return name;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public String getState(){
        return state;
    }
    
    public void setState(String state){
        this.state = state;
    }
    
    public Door(){
        name = "Defualt";
        state = "closed";
    }
    
    public Door(String name,String state){
        this.name = name;
        this.state = state;
    }
}
