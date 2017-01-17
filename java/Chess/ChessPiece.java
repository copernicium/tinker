package Chess;

import java.util.TreeSet;
import MySystem.MySystem;

/**
 * A class to represent a basic Chess piece
 */
public class ChessPiece{
	public static class Move{
		private ChessPiece start;
		private ChessPosition target;

		public ChessPiece getStart(){
			return this.start;
		}

		public ChessPosition getTarget(){
			return this.target;
		}

		public boolean equals(Object o){
			if(o == null || o.getClass() != this.getClass()) return false;
			Move b = (Move)o;
			if(!b.getStart().equals(this.getStart())) return false;
			if(!b.getTarget().equals(this.getTarget())) return false;
			return true;
	 	}

		public Move(){
			this.start = new ChessPiece();
			this.target = new ChessPosition();
		}

		public Move(ChessPiece start,ChessPosition target){
			this.start = start;
			this.target = target;
		}
		public Move(Move toCopy){
			this.start = ChessPiece.makePiece(toCopy.getStart());
			this.target = new ChessPosition(toCopy.getTarget());
		}
	}
	/**
	 * Represents the types of Chess pieces
	 */
	public enum Type{
		UNASSIGNED,PAWN,ROOK,KNIGHT,BISHOP,QUEEN,KING
	}

	/**
	 * Represents the color of a Chess piece
	 */
	public enum Color{
		WHITE,BLACK;

		public static Color not(Color a){
			switch(a){
				case WHITE:
					return BLACK;
				case BLACK:
					return WHITE;
				default: MySystem.error("Color is neither black nor white",MySystem.getFileName(), MySystem.getLineNumber());
			}
			return WHITE;
		}
	}
	protected Color color;
	protected ChessPosition position;
	private final static Type type = Type.UNASSIGNED;
	private final static char symbol = 'U';
	protected TreeSet<ChessPosition> possibleMoves;
	protected TreeSet<ChessPosition> limitedMoves;

	/**
	 * Fetches the status of the current piece
	 * @return true if the piece has not been captured
	 */
	public boolean getAlive(){
		return !(this instanceof CapturedPiece);
	}

	/**
	 * Assembles the information stored by the piece into a string
	 * @return the string of information
	 */
	@Override
	public String toString(){
		return "ChessPiece( type:" + this.getType() + " color:" + this.color + " position:" + this.position + " possibleMoves:" + this.possibleMoves + " limitedMoves:" + this.limitedMoves + ")";
	}

	/**
	 * The symbol to print given the type
	 * @return the letter representing this Chess piece
	 */
	public char getSymbol(){
		return (this.color == Color.WHITE) ? Character.toUpperCase(symbol) : Character.toLowerCase(symbol);
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
	 * @param o the piece to be compared to
	 * @return true if the pieces are equal by value
	 */
	public boolean equals(Object o){
		if(o == null || o.getClass() != this.getClass()) return false;
		ChessPiece b = (ChessPiece)o;
		if(this.getType() != b.getType()) return false;
		if(this.getColor() != b.getColor()) return false;
		if(!this.getPosition().equals(b.getPosition())) return false;
		if(!MySystem.treeSetEquals(this.getPossibleMoves(),b.getPossibleMoves())) return false;
		if(!MySystem.treeSetEquals(this.getLimitedMoves(),b.getLimitedMoves())) return false;
		return true;
	}

	/**
	 * Limits the possible moves to a those which do not result the king being in check
	 * @param CHESS_PIECES all of the pieces
	 */
	public void limitMovesToAvoidCheck(final ChessPieces CHESS_PIECES){
		ChessPiece original = makePiece(this);
		final boolean USE_LIMITED = false;
		TreeSet<ChessPosition> newMoves = new TreeSet<>();
		final int index = CHESS_PIECES.getIndexOf(this.getPosition());
		final ChessPosition enemyKingPosition = CHESS_PIECES.getKing(Color.not(this.getColor())).getPosition();
		ChessPieces testPieces = ChessPieces.makePieces(CHESS_PIECES);
		for(ChessPosition testMove: this.getPossibleMoves()){
			if(testMove.equals(enemyKingPosition)){//if moving this piece would capture the other king, then we don't need to worry about if that would leave this king in check
				newMoves.add(testMove);//TODO: add back in? Maybe not.
				continue;
			}
			testPieces.moveAndCapture(index,testMove,USE_LIMITED);
			testPieces.updateAllPossibleMoves(Color.not(this.getColor()));
			testPieces.updateKingCheck(this.getColor());
			if(!testPieces.getKing(this.getColor()).getCheck()) newMoves.add(testMove);//if moving this piece would not put the king in check, allow it
			testPieces.unMove();
		}
		if(!this.equals(original)){
			MySystem.error("Method changed the object. Should not have. Original: " + original.toString() + " this: " + this.toString(), MySystem.getFileName(), MySystem.getLineNumber());
		}
		this.limitedMoves = newMoves;
	}

	public TreeSet<ChessPosition> getLimitedMoves(){
		return this.limitedMoves;
	}

	/**
	 * Fetches all the positions this piece can move to
	 * @return a vector of Chess positions that this piece can be moved to
	 */
	public TreeSet<ChessPosition> getPossibleMoves(){
		//MySystem.error("This is not a valid Chess piece.",MySystem.getFileName(),MySystem.getLineNumber());
		return this.possibleMoves;
	}

	/**
	 * Calculates all the positions this piece can move to
	 * @param chessPieces an array of pieces representing a Chess board
	*/
	public void updatePossibleMoves(ChessPieces chessPieces){
		MySystem.error("This is not a valid Chess piece.", MySystem.getFileName(), MySystem.getLineNumber());
	}

	/**
	 * Moves this Chess pieces
	 * @param position the position to move this piece to
	 * @param useLimited check if the position is in the limited moves, not just the possibles
	 */
	public void move(ChessPosition position,boolean useLimited){
		MySystem.error("This is not a valid Chess piece.", MySystem.getFileName(), MySystem.getLineNumber());
	}

	/**
	 * Checks to see if this piece can move to a given position
	 * @param CHECK_MOVE the position to check to see if this piece can move to
	 * @param CHESS_PIECES an array of pieces representing a Chess board
	 * @return true if this piece can move to that position
	 */
	public boolean checkMoveDeep(final ChessPosition CHECK_MOVE,final ChessPieces CHESS_PIECES){
		if(!CHESS_PIECES.containsLiving(this)){
			MySystem.error("Move cannot be tested because piece does not exist in array", MySystem.getFileName(), MySystem.getLineNumber());
		}
		return MySystem.contains(this.getLimitedMoves(),CHECK_MOVE);
	}

	/**
	 * Checks to see if this piece can move to a given position
	 * @param CHECK_MOVE the position to check to see if this piece can move to1
	 * @param CHESS_PIECES an array of pieces representing a Chess board
	 * @return true if this piece can move to that position
	 */
	public boolean checkMove(final ChessPosition CHECK_MOVE,final ChessPieces CHESS_PIECES){
		if(!CHESS_PIECES.containsLiving(this)){
			MySystem.error("Move cannot be tested because piece does not exist in piece array", MySystem.getFileName(), MySystem.getLineNumber());
		}
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
	 * Fetches the position of this Chess piece
	 * @return the position of this Chess piece
	 */
	public ChessPosition getPosition(){
		return new ChessPosition(position);
	}

	/**
	 * Used to copy Chess pieces by value
	 * @param chessPiece the piece to copy
	 * @return a new instance with the same values
	 */
	public static ChessPiece makePiece(Type type,ChessPiece chessPiece){//TODO: find out if these are actually needed
		if(!chessPiece.getAlive()) return new CapturedPiece(chessPiece);
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
		return new ChessPiece();//will never reach this line
	}

	/**
	 * Used to copy Chess pieces by value
	 * @param chessPiece the piece to copy
	 * @return a new instance with the same values
	 */
	public static ChessPiece makePiece(ChessPiece chessPiece){
		if(!chessPiece.getAlive()) return new CapturedPiece(chessPiece);
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
		return new ChessPiece();//will never reach this line
	}

	/**
	 * Creates a new instance of a Chess piece
	 */
	public ChessPiece(){
		this(new ChessPosition(), Color.WHITE);
	}

	/**
	 * Used to copy a Chess piece by value
	 * @param toCopy the Chess piece to copy
	 */
	public ChessPiece(ChessPiece toCopy){
		this.position = new ChessPosition(toCopy.getPosition());
		this.color = toCopy.getColor();
		this.possibleMoves = new TreeSet<>(toCopy.getPossibleMoves());
		this.limitedMoves = new TreeSet<>(toCopy.getLimitedMoves());
	}

	/**
	 * Create a new instance of a Chess piece given some values
	 * @param position the position of the piece
	 * @param color the color of the piece
	 */
	public ChessPiece(ChessPosition position,Color color){
		this.position = position;
		this.color = color;
		this.possibleMoves = new TreeSet<>();
		this.limitedMoves = new TreeSet<>();
	}
}
