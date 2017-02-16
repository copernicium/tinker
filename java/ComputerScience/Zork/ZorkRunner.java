package ComputerScience.Zork;

import MySystem.MySystem;
import java.util.Scanner;

public class ZorkRunner {
	/**
	 * Represents any of the defined commands the player can run
	 */
	enum Command{
		HELP,QUIT,INVENTORY,MOVE,TAKE,ROOM_INFO,UNKNOWN;

		/**
		 * Reads a string and parses a command from it
		 * @param s the string to read
		 * @return the command it parsed
		 */
		public static Command toCommand(String s){
			s = MySystem.stringToLowerCase(s);
			switch(s){
				case "help":
					return HELP;
				case "quit":
					return QUIT;
				case "inventory":
					return INVENTORY;
				case "move":
					return MOVE;
				case "take":
					return TAKE;
				case "room info":
					return ROOM_INFO;
				default:
					return UNKNOWN;
			}
		}
	}

	public static void main(String[] args){
		//initialize objects
		Scanner in = new Scanner(System.in);
		Game game = new Game();

		//infinite loop for game
		while(true){
			System.out.print("Enter a command (type \"help\" for help): ");
			Command command = Command.toCommand(in.nextLine().trim());
			switch(command){
				case ROOM_INFO:
					{
						String message = "";
						message += "You are currently standing in " + game.getCurrentRoom().getName();
						message += "\n" + game.getCurrentRoom().getDesc();
						message += "\nThere are exits to " + game.getCurrentRoom().getExits() + ".";
						System.out.println(message);
						break;
					}
				case HELP:
					{
						String message = "Command help\n";
						message += "\t\"help\"         List all commands and their descriptions\n";
						message += "\t\"inventory\"    Display the player's inventory\n";
						message += "\t\"move\"         Prompt the player to enter a new room\n";
						message += "\t\"take\"         Remove the treasure from the current room and add it in the player's inventory\n";
						message += "\t\"quit\"         Exit the game\n";
						message += "\t\"room info\"    Display information about the room the player is in";

						System.out.println(message);
						break;
					}

				case INVENTORY:
					{
						System.out.println(game.getInventory());
					}
					break;
				case MOVE:
					{
						boolean done = false;
						while(!done) {
							final String STAY = "stay";

							System.out.print("The exits from  the" + game.getCurrentRoom().getName() + " are to " + game.getCurrentRoom().getExits() + ". Which room do you want to enter? (Type \"" + STAY + "\" to stay where you are): ");
							String query = MySystem.stringToLowerCase(in.nextLine().trim());

							if(query.equals(STAY)) break;

							for (Room r : game.getCurrentRoom().showExits()) {
								if (query.equals(MySystem.stringToLowerCase(r.getName()))) {
									game.enterRoom(game.getRoom(game.getRoomIndex(r)));
									done = true;
									break;
								}
							}
							if(!done){
								System.out.println("\"" + query + "\"" + " is not a valid room.");
							}
						}
					}
					break;
				case QUIT:
					{
						System.out.println("Goodbye");
						return;
					}
				case TAKE:
					{
						if(game.getCurrentRoom().getTreasure() == null){
							System.out.println("There aren't any treasures in " + game.getCurrentRoom().getName());
						} else{
							System.out.println("You took the " + game.getCurrentRoom().getTreasure().getName());
							game.pickUpTreasure(game.getCurrentRoom().getTreasure(), game.getCurrentRoom());
						}
						break;
					}
				case UNKNOWN:
					{
						System.out.println("Command not found. Type \"help\" for help");
						break;
					}
				default:
					MySystem.nyi(MySystem.getFileName(),MySystem.getLineNumber());
			}
			System.out.print("\n");
		}
	}
}
