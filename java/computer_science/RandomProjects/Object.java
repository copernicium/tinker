public class Object
{
    private String name;
    private String description;
    boolean inInventory;
    
    public Object pickUp(){
        inInventory = true;
        return this;
    }
    
    public String inspect(){
        return description;
    }
    
    public void use(){
    }
    
    public Object(){
        name = "";
        description = "";
        inInventory = false;
    }
    Object(String name,String description){
        this.name = name;
        this.description = description;
        this.inInventory = inInventory;
    }
}
