package chess;
import java.util.Vector;

/**
 * A queen piece
 */
public class Queen extends ChessPiece
{
  	@Override
    public String print(){
        return "Q";
    }
    @Override
    public Vector<ChessPosition> getNewPositions(ChessPiece[] chessPieces){
		Vector<ChessPosition> possibleMoves = new Vector<>(0);
		{
			{//Bishop movement
				int rowDirection = -1, columnDirection = -1;
				for(int j = 0; j < 4; j++){
					for(int i = 1; i <= ChessPosition.Row.DIMENSION; i++){
						ChessPosition.Tester testPosition = new ChessPosition.Tester(this.position.getRow().get() + (i * rowDirection), this.position.getColumn().get() + (i * columnDirection));
						if(ChessPosition.inBounds(testPosition) && !ChessBoard.isOccupied(new ChessPosition(testPosition), this.color, chessPieces)){
							possibleMoves.addElement(new ChessPosition(testPosition));
							if(ChessBoard.isOccupied(new ChessPosition(testPosition), Color.not(this.color), chessPieces))
								break;//if it will capture a piece, stop it there
						} else break;
					}
					rowDirection *= -1;//switch row direction every time
					if(j == 1) columnDirection *= -1;//switch column direction for every two switches of the row direction so all diagonals are covered
				}
			}
			{//Rook movement
				int direction = -1, row = 1, column = 0;
				for(int j = 0; j < 4; j++){
					for(int i = 1; i <= ChessPosition.Row.DIMENSION; i++){
						ChessPosition.Tester testPosition = new ChessPosition.Tester(this.position.getRow().get() + (i * direction * row), this.position.getColumn().get() + (i * direction * column));
						if(ChessPosition.inBounds(testPosition) && !ChessBoard.isOccupied(new ChessPosition(testPosition), this.color, chessPieces)){
							possibleMoves.addElement(new ChessPosition(testPosition));
							if(ChessBoard.isOccupied(new ChessPosition(testPosition), Color.not(this.color), chessPieces)) break;//if it will capture a piece, stop it there
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
        return possibleMoves;
    }
    @Override
    public void move(ChessPosition newPosition, ChessPiece[] chessPieces){
        for(ChessPosition a: getNewPositions(chessPieces)){
            if(newPosition.equals(a)){
                this.position = newPosition;
                return;
            }
        }
        System.err.println("Move failed. Not a valid move.");
		MySystem.myAssert(false,MySystem.getFileName(),MySystem.getLineNumber());
	}
	public Queen(Queen toCopy) {
		this.position = new ChessPosition(toCopy.position);
		this.alive = toCopy.alive;
		this.color = toCopy.color;
		this.type = toCopy.type;
	}
    public Queen(){
        super();
        type = Type.QUEEN;
    }
    public Queen(ChessPiece chessPiece){
		this(chessPiece.getPosition(),chessPiece.getColor());
	}
    public Queen(ChessPosition position, Color color){
        super(position,color);
        this.type = Type.QUEEN;
    }
}