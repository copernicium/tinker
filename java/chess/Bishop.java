package chess;
import java.util.Vector;

/**
 * A bishop piece
 */
public class Bishop extends ChessPiece
{
   	@Override
    public String toString(){
        return "B";
    }
    @Override
    public Vector<ChessPosition> getNewPositions(ChessPiece[] chessPieces){//TODO
		Vector<ChessPosition> possibleMoves = new Vector<>(0);
		{
			MySystem.nyi(MySystem.getFileName(),MySystem.getLineNumber());
		}
		System.out.println("At:" + this.position.toString() + " Possible:" + possibleMoves.toString());
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