package Chess;

import MySystem.MySystem;

import java.util.Random;
import java.util.Vector;

/**
 * A Chess ai
 */
public class Opponent {
	private ChessPiece.Color color;

	public ChessPiece.Color getColor(){
		return this.color;
	}

	public ChessPiece.Move getRandomMove(final ChessPieces PIECES){
		Vector<Integer> moveable_pieces = new Vector<>(0);
		for(int i = 0; i < PIECES.length(); i++){
			ChessPiece a = PIECES.toArray()[i];
			if(a.getColor().equals(this.getColor()) && a.getLimitedMoves().size() > 0 && a.getAlive()) moveable_pieces.addElement(i);
		}
		Random rand = new Random();
		int index = moveable_pieces.elementAt(rand.nextInt(moveable_pieces.size()));
		ChessPiece start = PIECES.getPieceAt(index);
		ChessPosition target = MySystem.getRandomTreeSetElement(PIECES.getPieceAt(index).getLimitedMoves());
		return new ChessPiece.Move(start,target);
	}

	public Opponent(ChessPiece.Color color){
		this.color = color;
	}
}
