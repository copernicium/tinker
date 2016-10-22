import java.util.Vector;
/**
 * A pawn piece
 */
public class Pawn extends ChessPiece
{
    boolean firstMove;
    @Override
    public String toString(){
        return "P";
    }
    @Override
    public Vector<ChessPosition> getNewPositions(ChessPiece[] chessPieces){//TODO: add captures and maybe en passant and maybe promotion
		Vector<ChessPosition> possibleMoves = new Vector<ChessPosition>(0);
		int direction = (this.color == ChessPiece.Color.WHITE) ? 1 : -1;
		if(firstMove){
			final int INITIALTWOSQUAREADVANCE = 2;
			ChessPosition testPosition = new ChessPosition(this.position.getRow().get()+(INITIALTWOSQUAREADVANCE *direction),this.position.getColumn().get());
			if(isMoveAllowed(testPosition,chessPieces)) possibleMoves.addElement(testPosition);
		}
		{
			final int REGULARMOVEMENT = 1;
			ChessPosition testPosition = new ChessPosition(this.position.getRow().get()+(REGULARMOVEMENT*direction),this.position.getColumn().get());
			if(isMoveAllowed(testPosition,chessPieces)) possibleMoves.addElement(testPosition);					
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
        System.exit(1);
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