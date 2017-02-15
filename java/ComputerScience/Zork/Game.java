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

			this.rooms.add(gym);
			this.rooms.add(pit);
			this.rooms.add(cafe);
			this.rooms.add(cafeteria);
			this.rooms.add(shed);
			this.rooms.add(room);

			Treasure trophy = new Treasure("Trophy",gym);
			Treasure driveTeam = new Treasure("Drive Team",pit);
			Treasure lunch = new Treasure("Lunch",cafeteria);
			Treasure software = new Treasure("Software",cafe);
			Treasure safetyGlasses = new Treasure("Safety Glasses",room);
			Treasure robotParts = new Treasure("Robot Parts",shed);

			treasures.add(trophy);
			treasures.add(driveTeam);
			treasures.add(lunch);
			treasures.add(software);
			treasures.add(safetyGlasses);
			treasures.add(robotParts);

			gym.addTreasure(trophy);
			pit.addTreasure(driveTeam);
			cafeteria.addTreasure(lunch);
			cafe.addTreasure(software);
			room.addTreasure(safetyGlasses);
			shed.addTreasure(robotParts);
		}
		{
			/*Room livingRoom = new Room("Living room", "It's comfy cozy with bananas.");
			Room kitchen = new Room("Kitchen", "There's food, so there's that.");
			Room bathroom = new Room("Bathroom", "*flush*");
			Room porch = new Room("Porch", "It has a nice view of the street.");

			livingRoom.addExit(kitchen);
			livingRoom.addExit(bathroom);
			livingRoom.addExit(porch);
			kitchen.addExit(bathroom);

			rooms.add(livingRoom);
			rooms.add(kitchen);
			rooms.add(bathroom);
			rooms.add(porch);

			Treasure kitten = new Treasure("Kitten", livingRoom);
			Treasure knife = new Treasure("Knife", kitchen);
			treasures.add(kitten);
			treasures.add(knife);
			livingRoom.addTreasure(kitten);
			kitchen.addTreasure(knife);*/
		}
		for(Room r: this.rooms){
			System.out.println(r.getName() + " " + r.getDesc() + " " + r.getExits() +  " "+  r.getTreasure().getName());
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
	 * Returns a string with the contents of the player's inventory
	 * @return the player's inventory in string form
	 */
	public String getInventory(){
		if(this.inventory.size() == 0) return "Your inventory is empty";
		String items = "";
		for(Treasure item: this.inventory){
			items += item.getName();
			if(this.inventory.size() > 2 && this.inventory.indexOf(item) < this.inventory.size() - 1){
				items += ",";
			}
			if(this.inventory.indexOf(item) == this.inventory.size() - 2) items += " and";
		}
		return items;
	}

	/**
	 * Enters a room
	 * @param room the room to enter
	 * @return the message announcing the contents of the room
	 */
	public String enterRoom(Room room){
		String message = "";
		message += "You are standing in the " + room.getName();
		message += "\n" + room.getDesc();
		message += "\nThere are exits to: " + room.getExits();
		return message;
	}

	/**
	 * Pick up treasure and add to inventory
	 * @param treasure the treasure to pick up
	 * @param room the current room
	 */
	public void pickUpTreasure(Treasure treasure, Room room){
		inventory.add(treasure);
		room.removeTreasure();
	}
}
