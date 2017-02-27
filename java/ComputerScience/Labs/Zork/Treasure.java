package ComputerScience.Labs.Zork;

import java.util.ArrayList;

/**
 * Stores the treasure for a room
 */
public class Treasure {
	String name;
	Room room;
	ArrayList<Treasure> requirements;

	/**
	 * Returns an ArrayList of the remaining requirements of the treasure
	 * @param inventory the player's inventory
	 * @return the unmet requirements
	 */
	public ArrayList<Treasure> getRemainingRequirements(ArrayList<Treasure> inventory){
		ArrayList<Treasure> remaining = new ArrayList<>();
		for(Treasure a: this.requirements){
			boolean has = false;
			for(Treasure b: inventory){
				if(a.equals(b)){
					has = true;
					break;
				}
			}
			if(!has) remaining.add(a);
		}
		return remaining;
	}

	/**
	 * Formats the remaining requirements of a treasure to be easier to read
	 * @param inventory the player's inventory
	 * @return a String of the remaining requirements
	 */
	public String printRequirements(ArrayList<Treasure> inventory){
		ArrayList<Treasure> remainingRequirements = this.getRemainingRequirements(inventory);
		String s = "";
		for(int i = 0; i < remainingRequirements.size(); i++){
			s += "the ";
			s += remainingRequirements.get(i).getName();
			if(remainingRequirements.size() > 2 && i != remainingRequirements.size() - 1) s += ",";
			if(i == remainingRequirements.size() - 2) s += " and";
			if(i != remainingRequirements.size() - 1) s += " ";
		}
		return s;
	}

	/**
	 * Adds a requirement that must be met before the treasure is available
	 * @param a the requirement
	 */
	public void addRequirement(Treasure a){
		this.requirements.add(a);
	}

	/**
	 * Gets the requirements to take this treasure
	 * @return the requirements
	 */
	public ArrayList<Treasure> getRequirements(){
		return this.requirements;
	}

	/**
	 * Checks to see if the player can take the treasure
	 * @param inventory the player's inventory
	 * @return trrue if they meet the requirements
	 */
	public boolean meetsRequirements(ArrayList<Treasure> inventory){
		for(Treasure a: this.requirements){
			boolean has = false;
			for(Treasure b: inventory){
				if(a.equals(b)){
					has = true;
					break;
				}
			}
			if(!has) return false;
		}
		return true;
	}

	/**
	 * Converts the object to a readable String
	 * @return the String representing the object
	 */
	@Override
	public String toString(){
		return this.name;
	}

	/**
	 * The equality operator by value for this type
	 * @param o the object to compare with
	 * @return true if they are equal by value
	 */
	@Override
	public boolean equals(Object o){
		if(this == o) return true;
		if(o == null || o.getClass() != this.getClass()) return false;
		Treasure b = (Treasure)o;
		if(!this.name.equals(b.name)) return false;
		//if(!this.room.equals(b.room)) return false;
		return true;
 	}

	/**
	 * Returns the name of the treasure
	 * @return the treasure's name
	 */
	public String getName(){
		return this.name;
	}

	/**
	 * Construct a Treasure object
	 * @param name the name of the treasure
	 * @param room the room that the treasure is in
	 */
	public Treasure(String name, Room room){
		this.name = name;
		this.room = room;
		this.requirements = new ArrayList<>();
	}

	public Treasure(){
		this("",null);
	}
}
