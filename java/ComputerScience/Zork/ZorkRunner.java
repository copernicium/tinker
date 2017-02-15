package ComputerScience.Zork;

import MySystem.MySystem;

import java.util.Scanner;

public class ZorkRunner {
	public static void main(String[] args){
		//initialize objects
		Scanner in = new Scanner(System.in);
		Game game = new Game();

		Room currentRoom  = game.getRoom(2);//start in the cafe
		//infinite loop for game
		while(true){
			//display room info
			System.out.println(game.enterRoom(currentRoom));
			//prompt
			System.out.print("Enter a command (type \"help\" for help): ");
			String command = MySystem.stringToLowerCase(in.nextLine());
			//get size of exits array list
			//loop through array list and check if user typed its name
			//enter room if they did
			for(Room nextRoom: currentRoom.showExits()){
				if(command.equals(MySystem.stringToLowerCase(nextRoom.getName()))){
					//set the new room and refresh the loop
					currentRoom = game.getRoom(game.getRoomIndex(nextRoom));
					//clear command, break out of loop
					command = null;
					break;
				}
			}
			if(command != null){
				//check if user typed room treasure
				//add to inventory and remove from room if they did
				if(currentRoom.getTreasure() != null && command.equals(MySystem.stringToLowerCase(currentRoom.getTreasure().getName()))){
					System.out.println("You took the "+currentRoom.getTreasure().getName());
					game.pickUpTreasure(currentRoom .getTreasure(), currentRoom);
					//display inventory if user typed inventory
				}
				else if(command.equals("inventory")){
					System.out.println(game.getInventory());
					//display help if user typed help
				}
				else if(command.equals("help")){
					//build a help menu
					String message = "To move to another room, type the name of that room.";
					message+="\nTo pick up an object, type the name of that object.";
					message+="\nTo view your inventory, type 'inventory'";
					//output the message
					System.out.println(message);
					//message if input not understood
				}
				else if(command.equals("quit")){
					System.out.println("Goodbye");
					return;
				}
				else{
					System.out.println("Command not found. Type \"help\" for help");
				}
			}
		}
	}
}
