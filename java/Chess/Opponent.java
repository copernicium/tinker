package Chess;

import Util.Util;

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
		if(moveable_pieces.size() == 0){
			Util.error("Opponent could not find any possible moves. Game should have ended by now. Pieces: " + PIECES, Util.getFileName(), Util.getLineNumber());
			Util.println("Board would look like: ", Util.getFileName(), Util.getLineNumber());
			(new ChessBoard(PIECES)).print();
		}
		Random rand = new Random();
		int index = moveable_pieces.elementAt(rand.nextInt(moveable_pieces.size()));
		ChessPiece start = PIECES.getPieceAt(index);
		ChessPosition target = Util.getRandomTreeSetElement(PIECES.getPieceAt(index).getLimitedMoves());
		return new ChessPiece.Move(start,target);
	}

	public Opponent(ChessPiece.Color color){
		this.color = color;
	}
}
