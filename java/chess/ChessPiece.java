import java.util.Vector;
/**
 * A class to represent a basic chess piece
 */
public class ChessPiece
{
    public enum Type{PAWN,ROOK,KNIGHT,BISHOP,QUEEN,KING};
    protected Type type;
    public enum Color{WHITE,BLACK};
    protected Color color;
    protected ChessPosition position;
    boolean alive;
    
    protected boolean inBounds(ChessPosition checkPosition){
        if(checkPosition.getColumn().get() > ChessPosition.Column.DIMENSION && checkPosition.getColumn().get() >= 0) return false;
        if(checkPosition.getRow().get() > ChessPosition.Row.DIMENSION  && checkPosition.getRow().get() >=0) return false;
        return true;
    }
    
    public String toString(){
        return "U";
    }
    
    protected Vector<ChessPosition> getNewPositions(){
        System.err.println("This is not a valid chess piece.");
        System.exit(1);
        //TODO: finish this
        return new Vector<ChessPosition>(0);
    }
    
    public void move(ChessPosition position){
        /*for(ChessPosition a: getNewPositions()){
            if(position == a){
                this.position = a;
                return;
            }
        }*/
        System.err.println("This is not a valid chess piece.");
        System.exit(1);
    }
    
    public Color getColor(){
        return color;
    }
    
    public Type getType(){
        return type;
    }
    
    public ChessPosition getPosition(){
        return position;
    }
    
    public ChessPiece(){
        position = new ChessPosition();
        color = Color.WHITE;
        alive = true;
    }
    public ChessPiece(ChessPosition position,Color color){
        this.position = position;
        this.color = color;
        alive = true;
    }
}