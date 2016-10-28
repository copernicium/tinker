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
    public Vector<ChessPosition> getNewPositions(ChessPiece[] chessPieces){//TODO
		Vector<ChessPosition> possibleMoves = new Vector<>(0);
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
    Rook(){
        super();
        type = Type.ROOK;
    }
    public Rook(ChessPosition position, Color color){
        super(position,color);
        this.type = Type.ROOK;
    }
}