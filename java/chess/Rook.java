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
		{
			ChessPosition.Tester testPosition = new ChessPosition.Tester(this.position.getRow().get(),this.position.getColumn().get());
			for(int i = this.position.getRow().get(); i < (ChessPosition.Row._8 - this.position.getRow().get()); i++){
				testPosition = new ChessPosition.Tester(this.position.getRow().get()+i,this.position.getColumn().get());
				if(ChessPosition.inBounds(testPosition)){
					if(!ChessBoard.isOccupied(new ChessPosition(testPosition),this.color,chessPieces)) possibleMoves.addElement(new ChessPosition(testPosition));
					else break;
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
    Rook(){
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