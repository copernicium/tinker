package TicTacToe;

import Util.*;

import java.util.Scanner;

/**
 * Runs a Tic Tac Toe game
 */
public class Game{
	public static void testMove(){
		Board board = new Board();
		board.print();
		System.out.println("-------------");
		board.set(new Board.Position(Board.Position._5));
		board.print();
		System.out.println("Expected to have an X in the center of the board.");
	}

	private static Board.Position getUserInput(){
		Scanner input = new Scanner(System.in);
		while(true){
			System.out.print("Please enter the position you wish to make your mark, \"help\" to display how the board is set up, or \"quit\" to exit: ");
			String str = input.next();
			if(Util.stringToLowerCase(str).equals("help")){
				Board.printLayout();
			}
			else if(Util.stringToLowerCase(str).equals("quit") || Util.stringToLowerCase(str).equals("exit")){
				System.out.println("Quitting game from user request");
				System.exit(0);
			}
			else if(Board.Position.canParsePosition(str)){
				return Board.Position.parsePosition(str);
			}
		}
		//can never reach here
	}

	public static void play(){
		Board board = new Board();
		while(board.getGameStatus() == Board.GameStatus.IN_PROGRESS){
			board.print();
			Board.Position position = getUserInput();
			board.set(position);
		}
	}

	/**
	 * The primary method that tests the Chess system and instantiates a new game
	 * @param args user arguments (no effect)
	 */
	public static void main(String[] args){
		System.out.println("Starting Game");
		//These are the tests:
		//testMove();

		//This is the actual game
		play();

		Util.println("\n\n\nEND OF GAME FILE\n\n\n", Util.getFileName(), Util.getLineNumber());
	}
}
