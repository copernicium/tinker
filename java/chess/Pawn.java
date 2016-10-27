package chess;
import java.util.Vector;
/**
 * A pawn piece
 */
public class Pawn extends ChessPiece
{
    boolean firstMove;//pawns can move differently on their first move
	
    @Override
    public String toString(){
        return "P";
    }
    @Override
    public Vector<ChessPosition> getNewPositions(ChessPiece[] chessPieces){//TODO: en passant and maybe promotion
		Vector<ChessPosition> possibleMoves = new Vector<ChessPosition>(0);
		int direction = (this.color == ChessPiece.Color.WHITE) ? 1 : -1;
		
		if(firstMove){
			final int INITIALTWOSQUAREADVANCE = 2;
			ChessPosition.Tester testPosition = new ChessPosition.Tester(this.position.getRow().get()+(INITIALTWOSQUAREADVANCE *direction),this.position.getColumn().get()); 
			if(ChessPosition.inBounds(testPosition)){
				if(!ChessBoard.isOccupied(new ChessPosition(testPosition),this.color,chessPieces)) possibleMoves.addElement(new ChessPosition(testPosition));
			}
		}
		{
			final int REGULARMOVEMENT = 1;
			ChessPosition.Tester testPosition = new ChessPosition.Tester(this.position.getRow().get()+(REGULARMOVEMENT*direction),this.position.getColumn().get());
			if(ChessPosition.inBounds(testPosition)){
				if(!ChessBoard.isOccupied(new ChessPosition(testPosition),this.color,chessPieces)) possibleMoves.addElement(new ChessPosition(testPosition));
			}
		}
		{
			final int DIAGONALDIST = 1;
			ChessPosition.Tester testPosition = new ChessPosition.Tester(this.position.getRow().get()+(DIAGONALDIST*direction),this.position.getColumn().get()+DIAGONALDIST);
			if(ChessPosition.inBounds(testPosition)){
				if(ChessBoard.isOccupied(new ChessPosition(testPosition),Color.not(this.color),chessPieces)) possibleMoves.addElement(new ChessPosition(testPosition));
			}
		}
		{
			final int DIAGONALDIST = 1;
			ChessPosition.Tester testPosition = new ChessPosition.Tester(this.position.getRow().get()+(DIAGONALDIST*direction),this.position.getColumn().get()-DIAGONALDIST);
			if(ChessPosition.inBounds(testPosition)){
				if(ChessBoard.isOccupied(new ChessPosition(testPosition),Color.not(this.color),chessPieces)) possibleMoves.addElement(new ChessPosition(testPosition));
			}
		}
        return possibleMoves;
    }
    @Override
    public void move(ChessPosition newPosition, ChessPiece[] chessPieces){
        for(ChessPosition a: getNewPositions(chessPieces)){
            if(newPosition.equals(a)){
                this.position = newPosition;
                this.firstMove = false;
                return;
            }
        }
        System.err.println("Move failed. Not a valid move.");
		MySystem.myAssert(false,MySystem.getFileName(),MySystem.getLineNumber());
	}
    Pawn(){
        super();
        type = Type.PAWN;
        firstMove = true;
    }
    public Pawn(ChessPosition position,Color color){
        super(position,color);
        this.type = Type.PAWN;
        this.firstMove = true;
    }
}