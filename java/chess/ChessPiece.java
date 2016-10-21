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
    
	public String toString(){
		return "U";
	}
	
    public Vector<ChessPosition> getNewPositions(){
        //TODO: finish this
        return new Vector<ChessPosition>(0);
    }
    
    public void move(ChessPosition position){
        for(ChessPosition a: getNewPositions()){
            if(position == a){
                this.position = a;
                return;
            }
        }
        System.err.println("Move failed. Not a valid move.");
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
    }
    public ChessPiece(ChessPosition position,Color color){
        this.position = position;
        this.color = color;
    }
}