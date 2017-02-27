package ComputerScience.Labs.Zork;

import java.util.ArrayList;

/**
 * Runs the Zork Game
 */
public class Game {
	private ArrayList<Treasure> inventory;
	private ArrayList<Room> rooms;
	private ArrayList<Treasure> treasures;
	private Room currentRoom;

	private static final int STARTING_ROOM = 2, ENDING_ROOM = 0;

	/**
	 * Constructs the Game object
	 * Create the rooms and add to the list
	 * Create exits and add to the list
	 */
	public Game(){
		this.inventory = new ArrayList<>();
		this.treasures = new ArrayList<>();
		this.rooms = new ArrayList<>();
		{
			Room gym = new Room("Gym", "A large, brightly lit room with semi-obnoxious music playing. There are many robots competing against each other on a walled-off field in the center. The stands are packed.");
			Room pit = new Room("Pit", "The room is full of tents, each with a different team, and there are many people walking about unhelpfully shouting \"ROBOT.\"");
			Room cafe = new Room("Cafe", "There are many students crouched over or sitting at a few tables covered in laptops, wires, aluminum shavings, and miscellaneous other tech.");
			Room cafeteria = new Room("Cafeteria", "It is full of circular tables, and there is a long table in the corner stacked with food for team members and volunteers.");
			Room shed = new Room("Shed", "A small room with shelves lining every wall storing everything from batteries to wagos to gear boxes.");
			Room room = new Room("Room", "A cramped room with way too many tables for its size. Everything appears to be covered in aluminum shavings or sawdust and not a single horizontal surface has been spared from the rising tide of prototypes, tools, and machinery.");

			gym.addExit(pit);
			gym.addExit(cafeteria);
			pit.addExit(gym);
			cafeteria.addExit(gym);
			cafeteria.addExit(shed);
			cafeteria.addExit(cafe);
			cafe.addExit(room);
			cafe.addExit(cafeteria);
			room.addExit(shed);
			room.addExit(cafe);
			shed.addExit(cafeteria);
			shed.addExit(cafe);

			Treasure trophy = new Treasure("Trophy",gym);
			Treasure driveTeam = new Treasure("Drive Team",pit);
			Treasure lunch = new Treasure("Lunch",cafeteria);
			Treasure software = new Treasure("Software",cafe);
			Treasure safetyGlasses = new Treasure("Safety Glasses",room);
			Treasure robotParts = new Treasure("Robot Parts",shed);

			driveTeam.addRequirement(lunch);
			software.addRequirement(robotParts);
			trophy.addRequirement(robotParts);
			trophy.addRequirement(driveTeam);
			trophy.addRequirement(software);

			gym.addTreasure(trophy);
			pit.addTreasure(driveTeam);
			cafeteria.addTreasure(lunch);
			cafe.addTreasure(software);
			room.addTreasure(safetyGlasses);
			shed.addTreasure(robotParts);

			pit.addRequirement(safetyGlasses);
			shed.addRequirement(safetyGlasses);

			this.rooms.add(gym);
			this.rooms.add(pit);
			this.rooms.add(cafe);
			this.rooms.add(cafeteria);
			this.rooms.add(shed);
			this.rooms.add(room);

			this.treasures.add(trophy);
			this.treasures.add(driveTeam);
			this.treasures.add(lunch);
			this.treasures.add(software);
			this.treasures.add(safetyGlasses);
			this.treasures.add(robotParts);

			this.currentRoom = this.rooms.get(STARTING_ROOM);
		}
	}

	/**
	 * Returns the room ata given index
	 * @param index the index of the room
	 * @return the room
	 */
	public Room getRoom(int index){
		return this.rooms.get(index);
	}

	/**
	 * Returns the index of a given room
	 * @param room the Room
	 * @return the index of the room
	 */
	public int getRoomIndex(Room room){
		return this.rooms.indexOf(room);
	}

	/**
	 * Fetches the player's inventory
	 * @return the player's inventory
	 */
	public ArrayList<Treasure> getInventory(){
		return this.inventory;
	}

	/**
	 * Returns a string with the contents of the player's inventory
	 * @return the player's inventory in string form
	 */
	public String printInventory(){
		if(this.inventory.size() == 0) return "Your inventory is empty";
		String items = "You have ";
		for(Treasure item: this.inventory){
			items += item.getName();
			if(this.inventory.size() > 2 && this.inventory.indexOf(item) < this.inventory.size() - 1){
				items += ",";
			}
			if(this.inventory.indexOf(item) == this.inventory.size() - 2) items += " and";
			if(this.inventory.indexOf(item) != this.inventory.size() -1) items += " ";
		}
		items += ".";
		return items;
	}

	/**
	 * Checks to see if the player has met the requirements to win
	 * @return true if the player has beaten the game
	 */
	public boolean getVictory(){
		for(Room r: this.rooms){
			if(r.getTreasure() != null) return false;
		}
		return currentRoom.equals(this.rooms.get(ENDING_ROOM));
	}

	/**
	 * Returns the current room
	 * @return the current room
	 */
	public Room getCurrentRoom(){
		return this.currentRoom;
	}

	/**
	 * Enters a room
	 * @param room the room to enter
	 */
	public void enterRoom(Room room){
		if(!room.meetsRequirements(this.inventory)){
			System.out.println("You cannot enter the " + room.getName() + ". You do not have " + room.printRequirements(this.inventory).toString() + ".");
			return;
		}
		String message = "";
		message += "You entered the " + room.getName() + ".";
		message += "\n" + room.getDesc();
		message += "\nThere are exits to " + room.getExits() + ".";
		System.out.println(message);
		this.currentRoom = room;
	}

	/**
	 * Pick up treasure and add to inventory
	 * @param treasure the treasure to pick up
	 * @param room the current room
	 */
	public void pickUpTreasure(Treasure treasure, Room room,ArrayList<Treasure> inventory){
		if(this.getCurrentRoom().getTreasure() == null){
			System.out.println("There aren't any treasures in " + this.getCurrentRoom().getName());
			return;
		}
		if(!treasure.meetsRequirements(inventory)){
			System.out.println("You do not meet the requirements to take the " + treasure.getName() + ". You still need " + treasure.printRequirements(inventory) + ".");
			return;
		}
		System.out.println("You took the " + this.getCurrentRoom().getTreasure().getName() + ".");
		inventory.add(treasure);
		room.removeTreasure();
	}
}
