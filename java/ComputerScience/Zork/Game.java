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
		//creates rooms
		Room livingRoom = new Room("Living room","comfy cozy with bananas");
		Room kitchen = new Room("Kitchen","there's food, so there's that");
		Room bathroom = new Room("Bathroom", "***flush***");
		Room porch = new Room("Porch","has a nice view of the street");

		rooms.add(livingRoom );
		rooms.add(kitchen);
		rooms.add(bathroom);
		rooms.add(porch);
		livingRoom.addExit(kitchen);
		livingRoom.addExit(bathroom);
		livingRoom.addExit(porch);
		kitchen.addExit(bathroom);

		Treasure kitten = new Treasure("Kitten",livingRoom );
		Treasure knife = new Treasure("Knife",kitchen);
		treasures.add(kitten);
		treasures.add(knife);
		livingRoom.addTreasure(kitten);
		kitchen.addTreasure(knife);

	}

	public static void main(String[] args){

	}
}
