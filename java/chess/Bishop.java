package chess;
import java.util.Vector;

/**
 * A bishop piece
 */
public class Bishop extends ChessPiece
{
	@Override
    public String print(){
        return "B";
    }
    @Override
    public Vector<ChessPosition> getNewPositions(ChessPiece[] chessPieces){
		Vector<ChessPosition> possibleMoves = new Vector<>(0);
		{
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
	public Bishop(Bishop toCopy) {
		this.position = new ChessPosition(toCopy.position);
		this.alive = toCopy.alive;
		this.color = toCopy.color;
		this.type = toCopy.type;
	}

    public Bishop(){
        super();
        type = Type.BISHOP;
    }
    public Bishop(ChessPiece chessPiece){
		this(chessPiece.getPosition(),chessPiece.getColor());
	}
    public Bishop(ChessPosition position, Color color){
        super(position,color);
        this.type = Type.BISHOP;
    }
}