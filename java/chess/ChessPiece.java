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
    protected boolean alive;
    
	public boolean getAlive(){
		return alive;
	}
	
    protected boolean inBounds(ChessPosition checkPosition){
        if(checkPosition.getColumn().get() > ChessPosition.Column.DIMENSION && checkPosition.getColumn().get() >= 0) return false;
        if(checkPosition.getRow().get() > ChessPosition.Row.DIMENSION  && checkPosition.getRow().get() >=0) return false;
        return true;
    }
    
    public String toString(){
        return "U";
    }
    
	public Color getColor(){
        return color;
    }
	
	public boolean equals(ChessPiece b){
		if(this.getType() != b.getType()) return false;
		if(this.getColor()!=b.getColor()) return false;
		if(!this.getPosition().equals(b.getPosition())) return false;
		if(this.getAlive() != b.getAlive()) return false;
		return true;
	}
	
	protected boolean isMoveAllowed(ChessPosition testPosition,ChessPiece[] chessPieces){
		return !ChessBoard.isOccupied(testPosition,this.color,chessPieces);
	}
	
    protected Vector<ChessPosition> getNewPositions(ChessPiece[] chessPieces){
        System.err.println("This is not a valid chess piece.");
        System.exit(1);
        return new Vector<ChessPosition>(0);
    }
    
    public void move(ChessPosition position, ChessPiece[] chessPieces){
        System.err.println("This is not a valid chess piece.");
        System.exit(1);
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