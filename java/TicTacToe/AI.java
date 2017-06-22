package TicTacToe;

import TicTacToe.Game.Move;
import Util.Point;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 */
public class AI implements Player{
	public Move getRandomMove(Board board){
		ArrayList<Move> possibleMoves = new ArrayList<>();
		Mark[][] marks = board.getMarks();
		Mark mark = board.getNextMark();
		for(int y = 0; y < marks.length; y++){
			for(int x = 0; x < marks[0].length; x++){
				if(marks[y][x] == Mark.NULL){
					possibleMoves.add(new Move(new Point<Integer>(x, y), mark));
				}
			}
		}
		Random rand = new Random();
		return possibleMoves.get(rand.nextInt(possibleMoves.size()));
	}

	@Override
	public Move getMove(Board board){
		Move move = getRandomMove(board);
		System.out.print(move.getPosition() + "\n");
		return move;
	}
}
