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
        Vector<ChessPosition> possibleMoves = new Vector<ChessPosition>(0);
        switch(this.color){
            case WHITE:
                if(firstMove) possibleMoves.addElement(new ChessPosition(this.position.getRow().get()+2,this.position.getColumn().get()));
                possibleMoves.addElement(new ChessPosition(this.position.getRow().get()+1,this.position.getColumn().get()));
                break;
            case BLACK:
                if(firstMove) possibleMoves.addElement(new ChessPosition(this.position.getRow().get()-2,this.position.getColumn().get()));
                possibleMoves.addElement(new ChessPosition(this.position.getRow().get()-1,this.position.getColumn().get()));
                break;
            default: break;
        }
        
        //TODO: finish this
        return possibleMoves;
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