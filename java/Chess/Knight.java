package Chess;

import java.util.TreeSet;
import MySystem.*;

/**
 * A Knight piece
 */
public class Knight extends ChessPiece
{
	private static final Type type = Type.KNIGHT;
	private static final char symbol = 'N';

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
	 * @return the letter representing this Chess piece
	 */
	public char getSymbol(){
		return (this.color == Color.WHITE) ? Character.toUpperCase(symbol) : Character.toLowerCase(symbol);
	}
	private ChessPosition.Tester[] getCorners(int xDisplacement, int yDisplacement){
		final int NUMBER_OF_CORNERS = 4;
		ChessPosition.Tester[] corners = new ChessPosition.Tester[NUMBER_OF_CORNERS];
		corners[0] = new ChessPosition.Tester(this.position.getRow().get() + xDisplacement, this.position.getColumn().get() + yDisplacement);
		corners[1] = new ChessPosition.Tester(this.position.getRow().get() - xDisplacement, this.position.getColumn().get() + yDisplacement);
		corners[2] = new ChessPosition.Tester(this.position.getRow().get() + xDisplacement, this.position.getColumn().get() - yDisplacement);
		corners[3] = new ChessPosition.Tester(this.position.getRow().get() - xDisplacement, this.position.getColumn().get() - yDisplacement);
		return corners;
	}
    @Override
    public void updatePossibleMoves(ChessPieces chessPieces){
		TreeSet<ChessPosition> possibleMoves = new TreeSet<>();
		for(ChessPosition.Tester testPosition: getCorners(-2,1)){
			if(testPosition.inBounds() && chessPieces.isUnoccupied(new ChessPosition(testPosition), this.color)){
				possibleMoves.add(new ChessPosition(testPosition));
			}
		}
		for(ChessPosition.Tester testPosition: getCorners(-1,2)){
			if(testPosition.inBounds() && chessPieces.isUnoccupied(new ChessPosition(testPosition), this.color)){
				possibleMoves.add(new ChessPosition(testPosition));
			}
		}
        this.possibleMoves = possibleMoves;
    }
    @Override
    public void move(ChessPosition newPosition){
       if(MySystem.contains(this.getPossibleMoves(),newPosition)){
			this.position = newPosition;
			return;
        }
		MySystem.error("Move failed: Not a valid move: trying to move from " + this.getPosition().toString() + " to " + newPosition.toString(), MySystem.getFileName(), MySystem.getLineNumber());
	}
	public Knight(Knight toCopy) {
		this.position = new ChessPosition(toCopy.position);
		this.color = toCopy.color;
		this.limitedMoves = toCopy.getLimitedMoves();
		this.possibleMoves = new TreeSet<>(toCopy.getPossibleMoves());
		this.limitedMoves = new TreeSet<>(toCopy.getLimitedMoves());
	}
    public Knight(){
        super();
    }
    public Knight(ChessPiece chessPiece){
		this(chessPiece.getPosition(),chessPiece.getColor(),chessPiece.getPossibleMoves(),chessPiece.getLimitedMoves());
	}
    public Knight(ChessPosition position, Color color,TreeSet<ChessPosition> possibleMoves,TreeSet<ChessPosition> limitedMoves){
        super(position,color,possibleMoves,limitedMoves);
    }
}