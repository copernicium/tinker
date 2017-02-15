package ComputerScience.Zork;

import java.util.ArrayList;

/**
 * Stores all of the information for a given room in the Zork text adventure game
 */
public class Room {
	private String name;
	private String desc;
	private Treasure treasure;
	private ArrayList<Room> exits;

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
		String s = "The ";
		for(int i = 0; i < this.exits.size(); i++){
			if(i != 0) s += " the ";
			s += this.exits.get(i).getName();
			if(this.exits.size() > 2 && i != this.exits.size() - 1) s += ",";
			if(i == this.exits.size() - 2) s += " and";
		}
		return s;
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
	}
}
