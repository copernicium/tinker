package chess;

import java.util.Vector;

/**
 * A king piece
 */
public class King extends ChessPiece
{
   	@Override
    public String toString(){
        return "K";
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
    public King(){
        super();
        type = Type.KING;
    }
    public King(ChessPiece chessPiece){
		this(chessPiece.getPosition(),chessPiece.getColor());
	}
    public King(ChessPosition position, Color color){
        super(position,color);
        this.type = Type.KING;
    }
}