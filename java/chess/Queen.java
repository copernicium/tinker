package chess;
import java.util.Vector;

/**
 * A queen piece
 */
public class Queen extends ChessPiece
{
	private static final Type type = Type.QUEEN;

	/**
	 * Fetches the type of this piece
	 * @return the type of this piece
	 */
	@Override
	public Type getType(){
		return Queen.type;
	}

	/**
	 * The symbol to print given the type
	 * @return the letter representing this chess piece
	 */
  	@Override
    public String print(){
        return "Q";
    }
    @Override
    public void updatePossibleMoves(ChessPieces chessPieces){
		Vector<ChessPosition> possibleMoves = new Vector<>(0);
		{
			final int NUMBER_OF_DIRECTIONS = 4;
			{//Bishop movement
				int rowDirection = -1, columnDirection = -1;
				for(int j = 0; j < NUMBER_OF_DIRECTIONS ; j++){
					for(int i = 1; i <= ChessPosition.Row.DIMENSION; i++){
						ChessPosition.Tester testPosition = new ChessPosition.Tester(this.position.getRow().get() + (i * rowDirection), this.position.getColumn().get() + (i * columnDirection));
						if(testPosition.inBounds() && chessPieces.isUnoccupied(new ChessPosition(testPosition), this.color)){
							possibleMoves.addElement(new ChessPosition(testPosition));
							if(chessPieces.isOccupied(new ChessPosition(testPosition), Color.not(this.color)))
								break;//if it will capture a piece, stop it there
						} else break;
					}
					rowDirection *= -1;//switch row direction every time
					if(j == 1) columnDirection *= -1;//switch column direction for every two switches of the row direction so all diagonals are covered
				}
			}
			{//Rook movement
				int direction = -1, row = 1, column = 0;
				for(int j = 0; j < NUMBER_OF_DIRECTIONS; j++){
					for(int i = 1; i <= ChessPosition.Row.DIMENSION; i++){
						ChessPosition.Tester testPosition = new ChessPosition.Tester(this.position.getRow().get() + (i * direction * row), this.position.getColumn().get() + (i * direction * column));
						if(testPosition.inBounds() && chessPieces.isUnoccupied(new ChessPosition(testPosition), this.color)){
							possibleMoves.addElement(new ChessPosition(testPosition));
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
		}
        this.possibleMoves = possibleMoves;
    }
    @Override
    public void move(ChessPosition newPosition){
		if(MySystem.myContains(this.getPossibleMoves(),newPosition)){
			this.position = newPosition;
			return;
		}
		MySystem.error("Move failed: Not a valid move: trying to move from " + this.getPosition().toString() + " to " + newPosition.toString(),MySystem.getFileName(),MySystem.getLineNumber());
		MySystem.myAssert(false,MySystem.getFileName(),MySystem.getLineNumber());
	}
	public Queen(Queen toCopy) {
		this.position = new ChessPosition(toCopy.position);
		this.alive = toCopy.alive;
		this.color = toCopy.color;
		this.limitedMoves = toCopy.getLimitedMoves();
	}
    public Queen(){
        super();
    }
    public Queen(ChessPiece chessPiece){
		this(chessPiece.getPosition(),chessPiece.getColor(),chessPiece.getPossibleMoves(),chessPiece.getLimitedMoves());
	}
    public Queen(ChessPosition position, Color color,Vector<ChessPosition> possibleMoves,Vector<ChessPosition> limitedMoves){
        super(position,color,possibleMoves,limitedMoves);
    }
}