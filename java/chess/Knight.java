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
    @Override
    public Vector<ChessPosition> getNewPositions(ChessPiece[] chessPieces){//TODO
		Vector<ChessPosition> possibleMoves = new Vector<>(0);
		{
			MySystem.nyi(MySystem.getFileName(),MySystem.getLineNumber());
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