package TicTacToe;

import java.util.Scanner;
import TicTacToe.Game.Move;

/**
 *
 */
public class User implements Player{
	public Move getMove(Board board){
		Scanner in = new Scanner(System.in);
		return new Game.Move(Board.intToPoint(in.nextInt()),board.getNextMark());
	}
}
