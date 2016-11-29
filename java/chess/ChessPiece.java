package chess;

import java.util.TreeSet;
import MySystem.*;

/**
 * A class to represent a basic chess piece
 */
public class ChessPiece
{
	/**
	 * Represents the types of chess pieces
	 */
	public enum Type{
		UNASSIGNED,PAWN,ROOK,KNIGHT,BISHOP,QUEEN,KING
	}

	/**
	 * Represents the color of a chess piece
	 */
	public enum Color{
		WHITE,BLACK;

		public static Color not(Color a){
			switch(a){
				case WHITE:
					return BLACK;
				case BLACK:
					return WHITE;
				default: MySystem.nyi(MySystem.getFileName(), MySystem.getLineNumber());
			}
			return WHITE;
		}
	}
	protected Color color;
	protected ChessPosition position;
	protected boolean alive;
	private final static Type type = Type.UNASSIGNED;
	protected TreeSet<ChessPosition> possibleMoves;
	protected TreeSet<ChessPosition> limitedMoves;

	/**
	 * Fetches the status of the current piece
	 * @return true if the piece has not been captured
	 */
	public boolean getAlive(){
		return alive;
	}

	/**
	 * Kills the piece
	 */
	public void capture(){
		if(!alive) MySystem.error("Error: trying to capture an already captured piece", MySystem.getFileName(), MySystem.getLineNumber());
		alive = false;
	}

	/**
	 * Assembles the information stored by the piece into a string
	 * @return the string of information
	 */
	@Override
	public String toString(){
		return "ChessPiece( type:" + this.getType() + " color:" + this.color + " position:" + this.position + " alive:" + this.alive + ")";
	}

	/**
	 * The symbol to print given the type
	 * @return the letter representing this chess piece
	 */
	public String print(){
		return "U";
	}

	/**
	 * Fetches the color of the piece
	 * @return the color of the piece
	 */
	public Color getColor(){
		return color;
	}

	/**
	 * Checks for equality by value with a given piece
	 * @param b the piece to be compared to
	 * @return true if the pieces are equal by value
	 */
	public boolean equals(ChessPiece b){
		if(b == null) return false;
		if(this.getType() != b.getType()) return false;
		if(this.getColor() != b.getColor()) return false;
		if(!this.getPosition().equals(b.getPosition())) return false;
		if(this.getAlive() != b.getAlive()) return false;
		if(!MySystem.treeSetEquals(this.getPossibleMoves(),b.getPossibleMoves())) return false;
		if(!MySystem.treeSetEquals(this.getLimitedMoves(),b.getLimitedMoves())) return false;
		return true;
	}

	/**
	 * Uses more sophisticated logic to test for equality given the type, as pieces have different instance variables
	 * @param a the piece to compare to
	 * @return true if the two pieces are equal
	 */
	public boolean equalsByType(ChessPiece a){
		switch(this.getType()){
			case PAWN:{
				Pawn b = new Pawn(a);
				return b.equals(this);
			}
			case KING:{
				King b = new King(a);
				return b.equals(this);
			}
			default:
				return a.equals(this);
		}
	}

	/**
	 * Limits the possible moves to a those which do not result the king being in check
	 * @param CHESS_PIECES all of the pieces
	 */
	public void limitMovesToLeavingCheck(final ChessPieces CHESS_PIECES){
		TreeSet<ChessPosition> newMoves = new TreeSet<>();
		if(!this.getAlive()){
			this.limitedMoves = newMoves;
			return;
		}
		ChessPieces testPieces = ChessPieces.makePieces(CHESS_PIECES);
		for(ChessPosition testMove: this.getPossibleMoves()){
			ChessPiece testPiece = ChessPiece.makePiece(this);
			int index = testPieces.getIndexOf(testPiece);
			testPiece.move(testMove);
			testPieces.set(index,testPiece);
			testPieces.updatePossibleMoves(Color.not(this.getColor()));
			testPieces.updateKingCheck(testPiece.getColor());
			if(!testPieces.getKing(this.getColor()).getCheck()) newMoves.add(testMove);
			testPieces.set(index,this);
		}
		this.limitedMoves = newMoves;
	}

	public TreeSet<ChessPosition> getLimitedMoves(){
		return this.limitedMoves;
	}

	/**
	 * Fetches all the positions this piece can move to
	 * @return a vector of chess positions that this piece can be moved to
	 */
	public TreeSet<ChessPosition> getPossibleMoves(){
		//MySystem.error("This is not a valid chess piece.",MySystem.getFileName(),MySystem.getLineNumber());
		return this.possibleMoves;
	}

	/**
	 * Calculates all the positions this piece can move to
	 * @param chessPieces an array of pieces representing a chess board
	*/
	public void updatePossibleMoves(ChessPieces chessPieces){
		MySystem.error("This is not a valid chess piece.", MySystem.getFileName(), MySystem.getLineNumber());
	}

	/**
	 * Moves this chess pieces
	 * @param position the position to move this piece to
	 */
	public void move(ChessPosition position){
		MySystem.error("This is not a valid chess piece.", MySystem.getFileName(), MySystem.getLineNumber());
	}

	/**
	 * Checks to see if this piece can move to a given position
	 * @param CHECK_MOVE the position to check to see if this piece can move to
	 * @param CHESS_PIECES an array of pieces representing a chess board
	 * @return true if this piece can move to that position
	 */
	public boolean checkMoveDeep(final ChessPosition CHECK_MOVE,final ChessPieces CHESS_PIECES){
		MySystem.myAssert((CHESS_PIECES.checkExists(this)), MySystem.getFileName(), MySystem.getLineNumber());
		if(!MySystem.contains(this.getLimitedMoves(),CHECK_MOVE)) return false;
		return true;
	}

	/**
	 * Checks to see if this piece can move to a given position
	 * @param CHECK_MOVE the position to check to see if this piece can move to1
	 * @param CHESS_PIECES an array of pieces representing a chess board
	 * @return true if this piece can move to that position
	 */
	public boolean checkMove(final ChessPosition CHECK_MOVE,final ChessPieces CHESS_PIECES){
		MySystem.myAssert((CHESS_PIECES.checkExists(this)), MySystem.getFileName(), MySystem.getLineNumber());
		return MySystem.contains(this.getPossibleMoves(),CHECK_MOVE);
	}

	/**
	 * Fetches the type of this piece
	 * @return the type of this piece
	 */
	public Type getType(){
		return ChessPiece.type;
	}

	/**
	 * Fetches the position of this chess piece
	 * @return the position of this chess piece
	 */
	public ChessPosition getPosition(){
		return new ChessPosition(position);
	}

	/**
	 * Used to copy chess pieces by value
	 * @param chessPiece the piece to copy
	 * @return a new instance with the same values
	 */
	public static ChessPiece makePiece(Type type,ChessPiece chessPiece){
		switch(type){
			case KING:
				return new King(chessPiece);
			case KNIGHT:
				return new Knight(chessPiece);
			case QUEEN:
				return new Queen(chessPiece);
			case ROOK:
				return new Rook(chessPiece);
			case PAWN:
				return new Pawn(chessPiece);
			case BISHOP:
				return new Bishop(chessPiece);
			case UNASSIGNED:
				return new ChessPiece(chessPiece);
			default: MySystem.nyi(MySystem.getFileName(), MySystem.getLineNumber());
		}
		MySystem.nyi(MySystem.getFileName(), MySystem.getLineNumber());
		return new ChessPiece();
	}

	/**
	 * Used to copy chess pieces by value
	 * @param chessPiece the piece to copy
	 * @return a new instance with the same values
	 */
	public static ChessPiece makePiece(ChessPiece chessPiece){
		switch(chessPiece.getType()){
			case KING:
				return new King(chessPiece);
			case KNIGHT:
				return new Knight(chessPiece);
			case QUEEN:
				return new Queen(chessPiece);
			case ROOK:
				return new Rook(chessPiece);
			case PAWN:
				return new Pawn(chessPiece);
			case BISHOP:
				return new Bishop(chessPiece);
			case UNASSIGNED:
				return new ChessPiece(chessPiece);
			default: MySystem.nyi(MySystem.getFileName(), MySystem.getLineNumber());
		}
		MySystem.nyi(MySystem.getFileName(), MySystem.getLineNumber());
		return new ChessPiece();
	}

	/*/**
	 * Used to copy an chess piece from an array that matches a set of criteria
	 * @param position the position of the piece to copy
	 * @param chessPieces the array of pieces to search through
	 * @return a new instance of the chess piece
	 */
	/*public static ChessPiece makePiece(ChessPosition position, ChessPieces chessPieces){
		for(ChessPiece a: chessPieces.toArray()){
			if(a.getPosition().equals(position)) return a;
		}
		MySystem.nyi(MySystem.getFileName(),MySystem.getLineNumber());
		return new ChessPiece();
	}*/

	/**
	 * Creates a new instance of a chess piece
	 */
	public ChessPiece(){
		this(new ChessPosition(), Color.WHITE,new TreeSet<>(),new TreeSet<>());
	}

	/**
	 * Used to copy a chess piece by value
	 * @param toCopy the chess piece to copy
	 */
	public ChessPiece(ChessPiece toCopy){
		this(new ChessPosition(toCopy.getPosition()),Color.WHITE,toCopy.getPossibleMoves(),toCopy.getLimitedMoves());
	}

	/**
	 * Create a new instance of a chess piece given some values
	 * @param position the position of the piece
	 * @param color the color of the piece
	 */
	public ChessPiece(ChessPosition position,Color color,TreeSet<ChessPosition> possibleMoves,TreeSet<ChessPosition> limitedMoves){
		this.position = position;
		this.color = color;
		this.alive = true;
		this.possibleMoves = possibleMoves;
		this.limitedMoves = limitedMoves;
	}
}