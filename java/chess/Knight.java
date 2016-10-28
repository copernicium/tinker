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
	public Vector<ChessPosition> getL(ChessPiece[] chessPieces,int distanceLeft, int distanceUp){
		Vector<ChessPosition> lPositions = new Vector<>(0);
		{
			ChessPosition.Tester testPosition = new ChessPosition.Tester(this.position.getRow().get()-distanceLeft,this.position.getColumn().get()+distanceUp);
			if(ChessPosition.inBounds(testPosition) && !ChessBoard.isOccupied(new ChessPosition(testPosition),this.color,chessPieces)){
				lPositions.addElement(new ChessPosition(testPosition));
			}
			testPosition = new ChessPosition.Tester(testPosition.getRow(),ChessPosition.mirror(testPosition.getColumn()));
			if(ChessPosition.inBounds(testPosition) && !ChessBoard.isOccupied(new ChessPosition(testPosition),this.color,chessPieces)){
				lPositions.addElement(new ChessPosition(testPosition));
			}
			testPosition = new ChessPosition.Tester(ChessPosition.mirror(testPosition.getRow()),testPosition.getColumn());
			if(ChessPosition.inBounds(testPosition) && !ChessBoard.isOccupied(new ChessPosition(testPosition),this.color,chessPieces)){
				lPositions.addElement(new ChessPosition(testPosition));
			}
			testPosition = new ChessPosition.Tester(ChessPosition.mirror(testPosition.getRow()),ChessPosition.mirror(testPosition.getColumn()));
			if(ChessPosition.inBounds(testPosition) && !ChessBoard.isOccupied(new ChessPosition(testPosition),this.color,chessPieces)){
				lPositions.addElement(new ChessPosition(testPosition));
			}
		}
		return lPositions;
	}
    @Override
    public Vector<ChessPosition> getNewPositions(ChessPiece[] chessPieces){//TODO
		Vector<ChessPosition> possibleMoves = new Vector<>(0);
		possibleMoves.addAll(getL(chessPieces,1,2));
		possibleMoves.addAll(getL(chessPieces,2,1));
		System.out.println("Available: " + possibleMoves.toString());
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