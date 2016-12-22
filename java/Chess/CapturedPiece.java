package Chess;

import MySystem.MySystem;
import java.util.TreeSet;

/**
 * Represents a captured piece
 */
public class CapturedPiece extends ChessPiece{
	private Chess.ChessPiece.Type type;

	/**
	 * Kills the piece
	 */
	public void capture(){
		MySystem.error("Error: trying to capture an already captured piece", MySystem.getFileName(), MySystem.getLineNumber());
	}

	/**
	 * Assembles the information stored by the piece into a string
	 * @return the string of information
	 */
	@Override
	public String toString(){
		return "CapturedPiece(type:" + this.getType() + " color:" + this.color + " position:" + this.position + ")";
	}

	/**
	 * The symbol to print given the type
	 * @return the letter representing this Chess piece
	 */
	public char getSymbol(){
		return ChessPiece.makePiece(this.type,new ChessPiece(this)).getSymbol();
	}

	/**
	 * Fetches the color of the piece
	 * @return the color of the piece
	 */
	public Chess.ChessPiece.Color getColor(){
		return this.color;
	}

	/**
	 * Limits the possible moves to a those which do not result the king being in check
	 * @param CHESS_PIECES all of the pieces
	 */
	public void limitMovesToLeavingCheck(final ChessPieces CHESS_PIECES){
		MySystem.error("Trying to limit possible moves of a captured piece.",MySystem.getFileName(),MySystem.getLineNumber());
	}

	public TreeSet<ChessPosition> getLimitedMoves(){
		return this.limitedMoves;
	}

	/**
	 * Fetches all the positions this piece can move to
	 * @return a vector of Chess positions that this piece can be moved to
	 */
	public TreeSet<ChessPosition> getPossibleMoves(){
		//MySystem.error("Trying to get possible moves from a captured piece.",MySystem.getFileName(),MySystem.getLineNumber());//TODO: add back in?
		return new TreeSet<>();
	}

	/**
	 * Calculates all the positions this piece can move to
	 * @param chessPieces an array of pieces representing a Chess board
	 */
	@Override
	public void updatePossibleMoves(ChessPieces chessPieces){
		MySystem.error("Trying to update moves for a captured piece.", MySystem.getFileName(), MySystem.getLineNumber());
	}

	/**
	 * Moves this Chess pieces
	 * @param position the position to move this piece to
	 */
	@Override
	public void move(ChessPosition position,boolean useLimited){
		MySystem.error("Trying to move a captured chess piece.", MySystem.getFileName(), MySystem.getLineNumber());
	}

	/**
	 * Fetches the type of this piece
	 * @return the type of this piece
	 */
	public Chess.ChessPiece.Type getType(){
		return this.type;
	}

	/**
	 * Fetches the position of this Chess piece
	 * @return the position of this Chess piece
	 */
	public ChessPosition getPosition(){
		return new ChessPosition(this.position);//TODO
	}

	/**
	 * Creates a new instance of a Chess piece
	 */
	public CapturedPiece(){
		this(Type.UNASSIGNED,Chess.ChessPiece.Color.WHITE,new ChessPosition());
	}

	/**
	 * Used to copy a Chess piece by value
	 * @param toCopy the Chess piece to copy
	 */
	public CapturedPiece(Chess.ChessPiece toCopy){
		this.position = new ChessPosition(toCopy.getPosition());
		this.color = toCopy.getColor();
		this.possibleMoves = new TreeSet<>();
		this.limitedMoves = new TreeSet<>();
		this.type = toCopy.getType();
	}

	/**
	 * Create a new instance of a Chess piece given some values
	 * @param type the type of piece
	 * @param color the color of the piece
	 */
	public CapturedPiece(Type type, Chess.ChessPiece.Color color,ChessPosition position){
		this.position = new ChessPosition(position);//TODO
		this.color = color;
		this.type = type;
		this.possibleMoves = new TreeSet<>();
		this.limitedMoves = new TreeSet<>();
	}
}
