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
    public Vector<ChessPosition> getNewPositions(ChessPiece[] chessPieces){//TODO
		Vector<ChessPosition> possibleMoves = new Vector<ChessPosition>(0);
        if(firstMove){
			ChessPosition testPosition = new ChessPosition(this.position.getRow().get()+2,this.position.getColumn().get());
			if(isMoveAllowed(testPosition,chessPieces)) possibleMoves.addElement(testPosition);
		}
		{
			ChessPosition testPosition = new ChessPosition(this.position.getRow().get()+1,this.position.getColumn().get());
			if(isMoveAllowed(testPosition,chessPieces)) possibleMoves.addElement(testPosition);					
		}
        return possibleMoves;
    }
    @Override
     public void move(ChessPosition position, ChessPiece[] chessPieces){
        for(ChessPosition a: getNewPositions(chessPieces)){
            if(position == a){
                this.position = a;
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