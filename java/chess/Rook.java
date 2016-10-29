package chess;

import java.util.Vector;

/**
 * A rook piece
 */
public class Rook extends ChessPiece
{
   	@Override
    public String toString(){
        return "R";
    }

    @Override
    public Vector<ChessPosition> getNewPositions(ChessPiece[] chessPieces){
		Vector<ChessPosition> possibleMoves = new Vector<>(0);
		{
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
    public Rook(){
        super();
        type = Type.ROOK;
    }
    public Rook(ChessPiece chessPiece){
		this(chessPiece.getPosition(),chessPiece.getColor());
	}
    public Rook(ChessPosition position, Color color){
        super(position,color);
        this.type = Type.ROOK;
    }
}