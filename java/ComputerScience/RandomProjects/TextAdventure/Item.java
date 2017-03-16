package ComputerScience.RandomProjects.TextAdventure;
public class Item
{
    private String name;
    private String description;
    boolean inInventory;
    
    public Item pickUp(){
        inInventory = true;
        return this;
    }
    
    public String inspect(){
        return description;
    }
    
    public void use(){
    }
    
    public Item(){
        name = "";
        description = "";
        inInventory = false;
    }
    Item(String name, String description){
        this.name = name;
        this.description = description;
        this.inInventory = inInventory;
    }
}
