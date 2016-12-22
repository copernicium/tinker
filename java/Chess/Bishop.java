package Chess;
import java.util.TreeSet;
import MySystem.*;

/**
 * A bishop piece
 */
public class Bishop extends ChessPiece
{
	private static final Type type = Type.BISHOP;
	private static final char symbol = 'B';

	/**
	 * Fetches the type of this piece
	 * @return the type of this piece
	 */
	@Override
	public Type getType(){
		return Bishop.type;
	}

	/**
	 * The symbol to print given the type
	 * @return the letter representing this Chess piece
	 */
	public char getSymbol(){
		return (this.color == Color.WHITE) ? Character.toUpperCase(symbol) : Character.toLowerCase(symbol);
	}

    @Override
    public void updatePossibleMoves(ChessPieces chessPieces){
		TreeSet<ChessPosition> possibleMoves = new TreeSet<>();
		ChessPosition.Tester testPosition = new ChessPosition.Tester();
		{
			int rowDirection = -1, columnDirection = -1;
			final int NUMBER_OF_DIAGONALS = 4;
			for(int j = 0; j < NUMBER_OF_DIAGONALS ; j++){
				for(int i = 1; i <= ChessPosition.Row.DIMENSION; i++){
					testPosition.set(this.position.getRow().get() + (i * rowDirection), this.position.getColumn().get() + (i * columnDirection));
					if(testPosition.inBounds() && chessPieces.isUnoccupied(new ChessPosition(testPosition), this.color)){
						possibleMoves.add(new ChessPosition(testPosition));
						if(chessPieces.isOccupied(new ChessPosition(testPosition), Color.not(this.color))) break;//if it will capture a piece, stop it there
					} else break;
				}
				rowDirection *= -1;//switch row direction every time
				if(j == 1) columnDirection *= -1;//switch column direction for every two switches of the row direction so all diagonals are covered
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
	public Bishop(Bishop toCopy) {
		this.position = new ChessPosition(toCopy.position);
		this.color = toCopy.color;
		this.possibleMoves = new TreeSet<>(toCopy.getPossibleMoves());
		this.limitedMoves = new TreeSet<>(toCopy.getLimitedMoves());
	}

    public Bishop(){
        super();
    }
    public Bishop(ChessPiece chessPiece){
		super(chessPiece.getPosition(),chessPiece.getColor(),chessPiece.getPossibleMoves(),chessPiece.getLimitedMoves());
	}
    public Bishop(ChessPosition position, Color color,TreeSet<ChessPosition> possibleMoves,TreeSet<ChessPosition> limitedMoves){
        super(position,color,possibleMoves,limitedMoves);
    }
}