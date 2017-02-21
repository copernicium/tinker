package ComputerScience.Zork;

import java.util.ArrayList;

/**
 * Stores the treasure for a room
 */
public class Treasure {
	String name;
	Room room;
	ArrayList<Treasure> requirements;

	public String printRequirements(ArrayList<Treasure> inventory){
		int remainingSize = this.getRemainingRequirements(inventory).size();
		String s = "";
		for(int i = 0; i < remainingSize; i++){
			s += "the ";
			s += this.requirements.get(i).getName();
			if(remainingSize > 2 && i != remainingSize - 1) s += ",";
			if(i == remainingSize - 2) s += " and";
			if(i != remainingSize - 1) s += " ";
		}
		return s;
	}

	public void addRequirement(Treasure a){
		this.requirements.add(a);
	}

	public ArrayList<Treasure> getRemainingRequirements(ArrayList<Treasure> inventory){
		ArrayList<Treasure> remaining = new ArrayList<>();
		for(Treasure a: this.requirements){
			for(Treasure b: inventory){
				if(a.equals(b)){
					remaining.add(a);
					break;
				}
			}
			remaining.add(a);
		}
		return remaining;
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

	@Override
	public String toString(){
		return this.name;
	}

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
