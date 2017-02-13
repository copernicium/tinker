package ComputerScience.Zork;

import java.util.ArrayList;

/**
 * Runs the Zork Game
 */
public class Game {
	private ArrayList<Treasure> inventory;
	private ArrayList<Room> rooms;
	private ArrayList<Treasure> treasures;

	/**
	 * Constructs the Game object
	 * Create the rooms and add to the list
	 * Create exits and add to the list
	 */
	public Game(){
		this.inventory = new ArrayList<>();
		this.treasures = new ArrayList<>();
		this.rooms = new ArrayList<>();
		/*Room gym = new Room("Gym", "1");
		Room pit = new Room("Pit", "2");
		Room cafe = new Room("Cafe", "3");
		Room cafeteria = new Room("Cafeteria", "4");
		Room theShed = new Room("The Shed", "5");
		Room theRoom = new Room("The Room", "6");
		this.rooms.add(gym);
		this.rooms.add(pit);
		this.rooms.add(cafe);
		this.rooms.add(cafeteria);
		this.rooms.add(theShed);
		this.rooms.add(theRoom);*/
		Room livingRoom = new Room("Living Room","");
		Room kitchen = new Room("Kitchen","");
		Room bathroom = new Room("Bathroom","");
		Room porch = new Room("Porch","");
		Treasure kitten = new Treasure("Kitten",livingRoom);
		Treasure knife = new Treasure("Knife",kitchen);
		this.treasures.add(kitten);
		this.treasures.add(knife);

	}

	public static void main(String[] args){

	}
}
