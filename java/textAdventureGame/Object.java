public class Object
{
    private String name;
    private String description;
    
    public Object pickUp(){
        return this;
    }
    
    public void use(){
    }
	Object(){
		name = "";
		description = "";
	}
	Object(String name,String description){
		this.name = name;
		this.description = description;
	}
}