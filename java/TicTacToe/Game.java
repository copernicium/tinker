package TicTacToe;

import java.util.Scanner;

/**
 *
 */
public class Game {
	private Board board;

	private void runTurn(){
		System.out.println(this.board.toPrintable());
		System.out.print("Insert a position to add " + this.board.getNextMark().toString() + ": ");
		Scanner in = new Scanner(System.in);
		this.board.set(in.nextInt());
	}

	public void play(){
		while(this.board.getStatus() == Board.Status.UNFINISHED){
			runTurn();
		}
		System.out.println(this.board.toPrintable());
		System.out.println("Game finished with: " + this.board.getStatus());
	}

	private static void testRotation(){
		Board board = new Board();
		board.set(0,0);
		board.set(2,2);
		board.set(2,0);
		board.set(0,2);
		System.out.println(board.toPrintable());
		board.rotate90();
		System.out.println("Rotated");
		System.out.println(board.toPrintable());
	}

	public Game(){
		this.board = new Board();
	}

	public static void main(String[] args){
		Game game = new Game();
		game.play();
		//testRotation();
	}
}
