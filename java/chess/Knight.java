package chess;
import java.util.Vector;

/**
 * A Knight piece
 */
public class Knight extends ChessPiece
{
	private static final Type type = Type.KNIGHT;

	/**
	 * Fetches the type of this piece
	 * @return the type of this piece
	 */
	@Override
	public Type getType(){
		return Knight.type;
	}

	/**
	 * The symbol to print given the type
	 * @return the letter representing this chess piece
	 */
	@Override
    public String print(){
        return "N";
    }
	private ChessPosition.Tester[] getCorners(int xDisplacement, int yDisplacement){
		final int NUMBEROFCORNERS = 4;
		ChessPosition.Tester[] corners = new ChessPosition.Tester[NUMBEROFCORNERS];
		corners[0] = new ChessPosition.Tester(this.position.getRow().get() + xDisplacement, this.position.getColumn().get() + yDisplacement);
		corners[1] = new ChessPosition.Tester(this.position.getRow().get() - xDisplacement, this.position.getColumn().get() + yDisplacement);
		corners[2] = new ChessPosition.Tester(this.position.getRow().get() + xDisplacement, this.position.getColumn().get() - yDisplacement);
		corners[3] = new ChessPosition.Tester(this.position.getRow().get() - xDisplacement, this.position.getColumn().get() - yDisplacement);
		return corners;
	}
    @Override
    public void updatePossibleMoves(ChessPieces chessPieces){
		Vector<ChessPosition> possibleMoves = new Vector<>(0);
		for(ChessPosition.Tester testPosition: getCorners(-2,1)){
			if(testPosition.inBounds() && !chessPieces.isOccupied(new ChessPosition(testPosition), this.color)){
				possibleMoves.addElement(new ChessPosition(testPosition));
			}
		}
		for(ChessPosition.Tester testPosition: getCorners(-1,2)){
			if(testPosition.inBounds() && !chessPieces.isOccupied(new ChessPosition(testPosition), this.color)){
				possibleMoves.addElement(new ChessPosition(testPosition));
			}
		}
        this.possibleMoves = possibleMoves;
    }
    @Override
    public void move(ChessPosition newPosition, ChessPieces chessPieces){
       if(MySystem.myContains(this.getPossibleMoves(),newPosition)){
			this.position = newPosition;
			return;
        }
		MySystem.error("Move failed: Not a valid move: trying to move from " + this.getPosition().toString() + " to " + newPosition.toString(),MySystem.getFileName(),MySystem.getLineNumber());
		MySystem.myAssert(false,MySystem.getFileName(),MySystem.getLineNumber());
	}
	public Knight(Knight toCopy) {
		this.position = new ChessPosition(toCopy.position);
		this.alive = toCopy.alive;
		this.color = toCopy.color;
		this.limitedMoves = toCopy.getLimitedMoves();
	}
    public Knight(){
        super();
    }
    public Knight(ChessPiece chessPiece){
		this(chessPiece.getPosition(),chessPiece.getColor(),chessPiece.getPossibleMoves(),chessPiece.getLimitedMoves());
	}
    public Knight(ChessPosition position, Color color,Vector<ChessPosition> possibleMoves,Vector<ChessPosition> limitedMoves){
        super(position,color,possibleMoves,limitedMoves);
    }
}