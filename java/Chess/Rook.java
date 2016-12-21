package Chess;

import java.util.TreeSet;
import MySystem.*;

/**
 * A rook piece
 */
public class Rook extends ChessPiece
{
	private static final Type type = Type.ROOK;

	/**
	 * Fetches the type of this piece
	 * @return the type of this piece
	 */
	@Override
	public Type getType(){
		return Rook.type;
	}

	/**
	 * The symbol to print given the type
	 * @return the letter representing this Chess piece
	 */
	@Override
    public String print(){
        return "R";
    }

    @Override
    public void updatePossibleMoves(ChessPieces chessPieces){
		TreeSet<ChessPosition> possibleMoves = new TreeSet<>();
		ChessPosition.Tester testPosition = new ChessPosition.Tester();
		{
			int direction = -1, row = 1, column = 0;
			final int NUMBER_OF_DIRECTIONS = 4;
			for(int j = 0; j < NUMBER_OF_DIRECTIONS ; j++){
				for(int i = 1; i <= ChessPosition.Row.DIMENSION; i++){
					testPosition.set(this.position.getRow().get() + (i * direction * row), this.position.getColumn().get() + (i * direction * column));
					if(testPosition.inBounds() && chessPieces.isUnoccupied(new ChessPosition(testPosition), this.color)){
						possibleMoves.add(new ChessPosition(testPosition));
						if(chessPieces.isOccupied(new ChessPosition(testPosition), Color.not(this.color))) break;//if it will capture a piece, stop it there
					} else break;
				}
				direction *= -1;//switch direction each time to cover both directions of rows and columns
				if(j==1){//switch which axes movement is restricted to
					row = 0;
					column = 1;
				}
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

	public Rook(Rook toCopy) {
		this.position = new ChessPosition(toCopy.position);
		this.color = toCopy.color;
		this.possibleMoves = new TreeSet<>(toCopy.getPossibleMoves());
		this.limitedMoves = new TreeSet<>(toCopy.getLimitedMoves());
	}

    public Rook(){
        super();
    }
    public Rook(ChessPiece chessPiece){
		this(chessPiece.getPosition(),chessPiece.getColor(),chessPiece.getPossibleMoves(),chessPiece.getLimitedMoves());
	}
    public Rook(ChessPosition position, Color color,TreeSet<ChessPosition> possibleMoves,TreeSet<ChessPosition> limitedMoves){
        super(position,color,possibleMoves,limitedMoves);
    }
}