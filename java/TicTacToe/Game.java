package TicTacToe;

import MySystem.*;

/**
 * Runs a Tic Tac Toe game
 */
public class Game{
	public static void testMove(){
		Board board = new Board();
		//TODO
	}

	private static void play(){
		//TODO
	}

	/**
	 * The primary method that tests the Chess system and instantiates a new game
	 * @param args user arguments (no effect)
	 */
	public static void main(String[] args){
		System.out.println("Starting Game");
		//These are the tests:
		testMove();

		//This is the actual game
		//play();

		MySystem.println("\n\n\nEND OF GAME FILE\n\n\n", MySystem.getFileName(), MySystem.getLineNumber());
	}
}
