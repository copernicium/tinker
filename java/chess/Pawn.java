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
    public Vector<ChessPosition> getNewPositions(){
        switch(this.color){
			case WHITE:
            case BLACK:
            default: break;
        }
        
        //TODO: finish this
        return new Vector<ChessPosition>(0);
    }
    @Override
     public void move(ChessPosition position){
        for(ChessPosition a: getNewPositions()){
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