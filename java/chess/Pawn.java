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
    public Vector<ChessPosition> getNewPositions(ChessPiece[] chessPieces){//TODO: add diagonals
		Vector<ChessPosition> possibleMoves = new Vector<ChessPosition>(0);
		switch(this.color){
			case WHITE:
				{
					if(firstMove){
						ChessPosition testPosition = new ChessPosition(this.position.getRow().get()+2,this.position.getColumn().get());
						if(isMoveAllowed(testPosition,chessPieces)) possibleMoves.addElement(testPosition);
					}
					{
						ChessPosition testPosition = new ChessPosition(this.position.getRow().get()+1,this.position.getColumn().get());
						if(isMoveAllowed(testPosition,chessPieces)) possibleMoves.addElement(testPosition);					
					}
					System.out.println(possibleMoves.toString());
				}
				break;
			case BLACK: 
				{
					if(firstMove){
						ChessPosition testPosition = new ChessPosition(this.position.getRow().get()-2,this.position.getColumn().get());
						if(isMoveAllowed(testPosition,chessPieces)) possibleMoves.addElement(testPosition);
					}
					{
						ChessPosition testPosition = new ChessPosition(this.position.getRow().get()-1,this.position.getColumn().get());
						if(isMoveAllowed(testPosition,chessPieces)) possibleMoves.addElement(testPosition);					
					}
				}
				break;
			default: MyAssert.myAssert(false);
		}
        return possibleMoves;
    }
    @Override
    public void move(ChessPosition newPosition, ChessPiece[] chessPieces){
		System.out.print(this.getPosition().toString() + " Possible Moves:");
		for(ChessPosition a: getNewPositions(chessPieces)){
			System.out.print(a.toString() + ",");
		}
		System.out.print("\n");
        for(ChessPosition a: getNewPositions(chessPieces)){
			System.out.println(newPosition.toString() + " vs " + a.toString());
            if(newPosition.equals(a)){
                this.position = newPosition;
                this.firstMove = false;
				System.out.println("SUCCESSFUL:" + this.getPosition().toString());
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