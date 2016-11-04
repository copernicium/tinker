package chess;
import java.util.Vector;

/**
 * A Knight piece
 */
public class Knight extends ChessPiece
{
   	@Override
    public String toString(){
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
    public Vector<ChessPosition> getNewPositions(ChessPiece[] chessPieces){
		Vector<ChessPosition> possibleMoves = new Vector<>(0);
		for(ChessPosition.Tester testPosition: getCorners(-2,1)){
			if(ChessPosition.inBounds(testPosition) && !ChessBoard.isOccupied(new ChessPosition(testPosition), this.color, chessPieces)){
				possibleMoves.addElement(new ChessPosition(testPosition));
			}
		}
		for(ChessPosition.Tester testPosition: getCorners(-1,2)){
			if(ChessPosition.inBounds(testPosition) && !ChessBoard.isOccupied(new ChessPosition(testPosition), this.color, chessPieces)){
				possibleMoves.addElement(new ChessPosition(testPosition));
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
    public Knight(){
        super();
        type = Type.KNIGHT;
    }
    public Knight(ChessPiece chessPiece){
		this(chessPiece.getPosition(),chessPiece.getColor());
	}
    public Knight(ChessPosition position, Color color){
        super(position,color);
        this.type = Type.KNIGHT;
    }
}