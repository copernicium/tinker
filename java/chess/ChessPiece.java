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
	protected Type type;

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
	}//TODO; move all of them here (switch type)

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
	 * Calculates all the positions this piece can move to
	 * @param chessPieces an array of pieces representing a chess board
	 * @return a vector of chess positions that this piece can be moved to
	 */
    protected Vector<ChessPosition> getNewPositions(ChessPiece[] chessPieces){
      	MySystem.error("This is not a valid chess piece.",MySystem.getFileName(),MySystem.getLineNumber());
       	return new Vector<>(0);
    }

	/**
	 * Moves this chess pieces
	 * @param position the position to move this piece to
	 * @param chessPieces an array of pieces representing a chess board
	 */
	public void move(ChessPosition position, ChessPiece[] chessPieces){
       MySystem.error("This is not a valid chess piece.", MySystem.getFileName(),MySystem.getLineNumber());
    }

	/**
	 * Checks to see if this piece can move to a given position
	 * @param CHECK_MOVE the position to check to see if this piece can move to
	 * @param CHESS_PIECES an array of pieces representing a chess board
	 * @return true if this piece can move to that position
	 */
    public boolean checkMove(final ChessPosition CHECK_MOVE,final ChessPiece[] CHESS_PIECES){
		MySystem.myAssert((ChessBoard.checkExists(this,CHESS_PIECES)),MySystem.getFileName(),MySystem.getLineNumber());
		for(ChessPosition possiblePosition: this.getNewPositions(CHESS_PIECES)){
			if(CHECK_MOVE.equals(possiblePosition)) return true;
		}
		return false;
	}

	/**
	 * Fetches the type of this piece
	 * @return the type of this piece
	 */
	public Type getType(){
        return type;
    }

	/**
	 * Fetches the position of this chess piece
	 * @return the position of this chess piece
	 */
	public ChessPosition getPosition(){
        return position;
    }

    public static ChessPiece[] makePieces(final ChessPiece[] CHESS_PIECES){
		ChessPiece[] newPieces = new ChessPiece[CHESS_PIECES.length];
		for(int i = 0; i < CHESS_PIECES.length; i++){
			newPieces[i] = ChessPiece.makePiece(CHESS_PIECES[i]);
		}
		return newPieces;
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
	 * Used to copy an chess piece from an array that matches a set of criteria
	 * @param position the position of the piece to copy
	 * @param color the color of the piece to copy
	 * @param chessPieces the array of pieces to search through
	 * @return a new instance of the chess piece
	 */
	public static ChessPiece makePiece(ChessPosition position, Color color, ChessPiece[] chessPieces){
		for(ChessPiece a: chessPieces){
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
		this.type = Type.UNASSIGNED;
        alive = true;
    }
}