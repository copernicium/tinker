package ComputerScience.Zork;

/**
 * Stores the treasure for a room
 */
public class Treasure {
	String name;
	Room room;

	/**
	 * Returns the name of the treasure
	 * @return the treasure's name
	 */
	public String getName(){
		return this.name;
	}

	/**
	 * Contruct a Treasure object
	 * @param name the name of the treasure
	 * @param room the room that the treasure is in
	 */
	public Treasure(String name, Room room){
		this.name = name;
		this.room = room;
	}
}
