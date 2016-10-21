import java.util.Vector;
/**
 * A pawn piece
 */
public class Pawn extends ChessPiece
{
    boolean firstMove;
    @Override
    public Vector<ChessPosition> getNewPositions(){
        switch(this.color){
            
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
        firstMove = true;
    }
    public Pawn(ChessPosition position,Color color,boolean firstMove){
        super(position,color);
        this.firstMove = firstMove;
    }
}