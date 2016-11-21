package chess;
import java.util.Vector;
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
				default: MySystem.nyi(MySystem.getFileName(),MySystem.getLineNumber());
			}
			return WHITE;
		}
	}
    protected Color color;
    protected ChessPosition position;
    protected boolean alive;
	private final static Type type = Type.UNASSIGNED;

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
		if(!alive) MySystem.nyi(MySystem.getFileName(),MySystem.getLineNumber());
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
		if(this.getType() != b.getType()) return false;
		if(this.getColor() != b.getColor()) return false;
		if(!this.getPosition().equals(b.getPosition())) return false;
		if(this.getAlive() != b.getAlive()) return false;
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
<<<<<<< HEAD
<<<<<<< HEAD
	 * Limits the possible moves to a those which do not result the king being in check
	 * @param CHESS_PIECES all of the pieces
	 * @returnvthe possible moves which do not result the king being in check
	 */
	public Vector<ChessPosition> limitMovesToLeavingCheck(final ChessPieces CHESS_PIECES){//TODO: make faster
=======
	 *
	 * @param CHESS_PIECE
	 * @param CHESS_PIECES
	 * @return
	 */
	public static Vector<ChessPosition> limitMovesToLeavingCheck(final ChessPiece CHESS_PIECE, final ChessPieces CHESS_PIECES){//TODO
>>>>>>> parent of 3c2205d... Get limiting moves to those which leave check to work
		Vector<ChessPosition> newMoves = new Vector<>(0);
		Vector<ChessPosition> allPossible = ChessPiece.makePiece(this).getNewPositions(ChessPieces.makePieces(CHESS_PIECES));
		for(ChessPosition testMove: allPossible){
			ChessPieces testPieces = ChessPieces.makePieces(CHESS_PIECES);
<<<<<<< HEAD
			ChessPiece testPiece = ChessPiece.makePiece(this);
=======
			ChessPiece testPiece = ChessPiece.makePiece(CHESS_PIECE);
			MySystem.println("Trying to move " + testPiece.toString() + " to " + testMove.toString(),MySystem.getFileName(),MySystem.getLineNumber());
>>>>>>> parent of 3c2205d... Get limiting moves to those which leave check to work
			int position = testPieces.getIndexOf(testPiece);
			testPiece.move(testMove,testPieces);
			testPieces.set(position,testPiece);
			testPieces.getKing(testPiece.getColor()).update(testPieces);
			if(!testPieces.getKing(testPiece.getColor()).getCheck()) newMoves.add(testMove);
		}
		return newMoves;
	}

	/**
	 * Calculates all the positions this piece can move to
	 * @param chessPieces an array of pieces representing a chess board
	 * @return a vector of chess positions that this piece can be moved to
	 */
    protected Vector<ChessPosition> getNewPositions(ChessPieces chessPieces){
      	MySystem.error("This is not a valid chess piece.",MySystem.getFileName(),MySystem.getLineNumber());
       	return new Vector<>(0);
    }

	/**
	 * Moves this chess pieces
	 * @param position the position to move this piece to
	 * @param chessPieces an array of pieces representing a chess board
	 */
	public void move(ChessPosition position, ChessPieces chessPieces){
       MySystem.error("This is not a valid chess piece.", MySystem.getFileName(),MySystem.getLineNumber());
    }

    private static boolean myContains(Vector<ChessPosition> all, ChessPosition a){
		for(ChessPosition b: all){
			if(a.equals(b)) return true;
		}
		return false;
	}

	/**
	 * Checks to see if this piece can move to a given position
	 * @param CHECK_MOVE the position to check to see if this piece can move to
	 * @param CHESS_PIECES an array of pieces representing a chess board
	 * @param last the last conditions
	 * @return true if this piece can move to that position
	 */
	/*public boolean checkMoveDeep(final ChessPosition CHECK_MOVE,final ChessPieces CHESS_PIECES,final ConditionStorage last){
		MySystem.myAssert((CHESS_PIECES.checkExists(this)),MySystem.getFileName(),MySystem.getLineNumber());
		last.update(CHESS_PIECES,false);
		return myContains(last.getMovesAt(last.getLast().getIndexOf(this)),CHECK_MOVE);
	}*/

	/**
	 * Checks to see if this piece can move to a given position
	 * @param CHECK_MOVE the position to check to see if this piece can move to
	 * @param CHESS_PIECES an array of pieces representing a chess board
	 * @return true if this piece can move to that position
	 */
	public boolean checkMoveDeep(final ChessPosition CHECK_MOVE,final ChessPieces CHESS_PIECES){
		MySystem.myAssert((CHESS_PIECES.checkExists(this)),MySystem.getFileName(),MySystem.getLineNumber());
		return myContains(this.limitMovesToLeavingCheck(CHESS_PIECES),CHECK_MOVE);
	}

	/**
	 * Checks to see if this piece can move to a given position
	 * @param CHECK_MOVE the position to check to see if this piece can move to
	 * @param CHESS_PIECES an array of pieces representing a chess board
	 * @return true if this piece can move to that position
	 */
    public boolean checkMove(final ChessPosition CHECK_MOVE,final ChessPieces CHESS_PIECES){
		MySystem.myAssert((CHESS_PIECES.checkExists(this)),MySystem.getFileName(),MySystem.getLineNumber());
<<<<<<< HEAD
		return myContains(this.getNewPositions(CHESS_PIECES),CHECK_MOVE);
=======
		for(ChessPosition possiblePosition: this.getNewPositions(CHESS_PIECES)){//TODO: limit?
			if(CHECK_MOVE.equals(possiblePosition)) return true;
		}
		return false;
>>>>>>> parent of 3c2205d... Get limiting moves to those which leave check to work
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
			default: MySystem.nyi(MySystem.getFileName(),MySystem.getLineNumber());
		}
		MySystem.nyi(MySystem.getFileName(),MySystem.getLineNumber());
		return new ChessPiece();
	}

	/**
	 * Used to create a new chess piece that matches a set of criteria
	 * @param position the position of the piece to use
	 * @param color the color of the piece to use
	 * @param type the type of piece to use
	 * @return a new instance of the chess piece
	 */
	public static ChessPiece makePiece(ChessPosition position, Color color,Type type){
		switch(type){
			case KING:
				return new King(position,color);
			case KNIGHT:
				return new Knight(position,color);
			case QUEEN:
				return new Queen(position,color);
			case ROOK:
				return new Rook(position,color);
			case PAWN:
				return new Pawn(position,color);
			case BISHOP:
				return new Bishop(position,color);
			//case UNASSIGNED:
			//	return new ChessPiece(position,color);
			default: MySystem.nyi(MySystem.getFileName(),MySystem.getLineNumber());
		}
		MySystem.nyi(MySystem.getFileName(),MySystem.getLineNumber());
		return new ChessPiece();
	}

	/**
	 * Used to copy an chess piece from an array that matches a set of criteria
	 * @param position the position of the piece to copy
	 * @param color the color of the piece to copy
	 * @param chessPieces the array of pieces to search through
	 * @return a new instance of the chess piece
	 */
	public static ChessPiece makePiece(ChessPosition position, Color color, ChessPieces chessPieces){
		for(ChessPiece a: chessPieces.toArray()){
			if(a.getPosition().equals(position) && a.getColor().equals(color)){
				switch(a.getType()){
					case KING:
						return new King(position,color);
					case KNIGHT:
						return new Knight(position,color);
					case QUEEN:
						return new Queen(position,color);
					case ROOK:
						return new Rook(position,color);
					case PAWN:
						return new Pawn(position,color);
					case BISHOP:
						return new Bishop(position,color);
					case UNASSIGNED:
						return new ChessPiece(position,color);
					default: MySystem.nyi(MySystem.getFileName(),MySystem.getLineNumber());
				}
			}
		}
		MySystem.nyi(MySystem.getFileName(),MySystem.getLineNumber());
		return new ChessPiece();
	}

	/**
	 * Creates a new instance of a chess piece
	 */
	public ChessPiece(){
		this(new ChessPosition(), Color.WHITE);
	}

	/**
	 * Used to copy a chess piece by value
	 * @param toCopy the chess piece to copy
	 */
	public ChessPiece(ChessPiece toCopy){
		this(new ChessPosition(toCopy.getPosition()),Color.WHITE);
	}

	/**
	 * Create a new instance of a chess piece given some values
	 * @param position the position of the piece
	 * @param color the color of the piece
	 */
    public ChessPiece(ChessPosition position,Color color){
        this.position = position;
        this.color = color;
        alive = true;
    }
}