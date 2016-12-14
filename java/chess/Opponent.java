package chess;

import MySystem.MySystem;

import java.util.Random;
import java.util.Vector;

/**
 * A chess ai
 */
public class Opponent {
	public static class Move{
		private ChessPosition start;
		private ChessPosition target;

		public ChessPosition getStart(){
			return this.start;
		}

		public ChessPosition getTarget(){
			return this.target;
		}

		public Move(){
			this.start = new ChessPosition();
			this.target = new ChessPosition();
		}

		public Move(ChessPosition start,ChessPosition target){
			this.start = start;
			this.target = target;
		}
	}

	private ChessPiece.Color color;

	public ChessPiece.Color getColor(){
		return this.color;
	}

	public Move getMove(final ChessPieces PIECES){
		Vector<Integer> moveable_pieces = new Vector<>(0);
		for(int i = 0; i<PIECES.length(); i++){
			ChessPiece a = PIECES.toArray()[i];
			if(a.getColor().equals(this.getColor()) && a.getLimitedMoves().size() > 0 && a.getAlive()) moveable_pieces.addElement(i);
		}
		Random rand = new Random();
		int index = rand.nextInt(moveable_pieces.size());
		ChessPosition start = PIECES.getPieceAt(index).getPosition();
		ChessPosition target = MySystem.getRandomTreeSetElement(PIECES.getPieceAt(index).getLimitedMoves());
		return new Move(start,target);
	}

	public Opponent(ChessPiece.Color color){
		this.color = color;
	}
}
