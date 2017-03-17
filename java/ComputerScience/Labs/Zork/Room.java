package ComputerScience.Labs.Zork;

import java.util.ArrayList;

/**
 * Stores all of the information for a given room in the Zork text adventure game
 */
public class Room {
	private String name;
	private String desc;
	private Treasure treasure;
	private ArrayList<Room> exits;
	private ArrayList<Treasure> requirements;

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
		Room b = (Room)o;
		if(!this.name.equals(b.name)) return false;
		if(!this.desc.equals(b.desc)) return false;
		//if(!this.treasure.equals(b.treasure)) return false;
		if(!this.exits.equals(b.exits)) return false;
		//if(!this.requirements.equals(b.requirements)) return false;
		return true;
 	}

	/**
	 * Add exits to the Room
	 * @param exit - the Room to exit into
	 */
	public void addExit(Room exit){
		if(this.exits.indexOf(exit) == -1) this.exits.add(exit);
		if(exit.exits.indexOf(this) == -1) exit.exits.add(this);
	}

	/**
	 * Add treature to the Room
	 * @param treasure - the treasure
	 */
	public void addTreasure(Treasure treasure){
		this.treasure = treasure;
	}

	/**
	 * Get the description of the Room
	 * @return the Room description
	 */
	public String getDesc(){
		String desc = this.desc;
		if(this.treasure != null){
			desc += " The room also contains " + this.treasure.getName() + ".";
		}
		return desc;
	}

	/**
	 * Get the exits of the Room
	 * @return String of Rooms that this Room exits into
	 */
	public String getExits(){
		String s = "";
		for(int i = 0; i < this.exits.size(); i++){
			s += "the ";
			s += this.exits.get(i).getName();
			if(this.exits.size() > 2 && i != this.exits.size() - 1) s += ",";
			if(i == this.exits.size() - 2) s += " and";
			if(i != this.exits.size() -1) s += " ";
		}
		return s;
	}

	/**
	 * Formats the remaining requirements of a room to be easier to read
	 * @param inventory the player's inventory
	 * @return a String of the remaining requirements
	 */
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

	/**
	 * Adds a requirement that must be met before the treasure is available
	 * @param a the requirement
	 */
	public void addRequirement(Treasure a){
		this.requirements.add(a);
	}

	/**
	 * Returns an ArrayList of the remaining requirements of the room
	 * @param inventory the player's inventory
	 * @return the unmet requirements
	 */
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
	 * Checks to see if the player can enter the room
	 * @param inventory the player's inventory
	 * @return if they meet the requirements to enter the room
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
	 *  Get the treasure in the Room
	 * @return the Room's treasure
	 */
	public Treasure getTreasure(){
		return this.treasure;
	}

	/**
	 * Remove treasure from the Room
	 */
	public void removeTreasure(){
		this.treasure = null;
	}

	/**
	 * Get the exits of the Room--Alternate method
	 * @return a list of Rooms that this Room exits into
	 */
	public ArrayList<Room> showExits(){
		return this.exits;
	}

	/**
	 * Get the name of the Room
	 * @return the Room name
	 */
	public String getName(){
		return this.name;
	}

	/**
	 * Constructs a Room object
	 * @param name the name of the Room
	 * @param desc the Description of the Room
	 */
	public Room(String name, String desc){
		this.name = name;
		this.desc = desc;
		this.treasure = null;
		this.exits = new ArrayList<>();
		this.requirements = new ArrayList<>();
	}

	/**
	 * Construct a Room object
	 */
	public Room(){
		this("","");
	}
}
