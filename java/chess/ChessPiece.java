package chess;
import java.util.Vector;
/**
 * A class to represent a basic chess piece
 */
public class ChessPiece
{
    public enum Type{PAWN,ROOK,KNIGHT,BISHOP,QUEEN,KING};
    protected Type type;
    public enum Color{
		WHITE,BLACK;
		public static Color not(Color a){
			switch(a){
				case WHITE: 
					return BLACK;
				case BLACK:
					return WHITE;
				default: MySystem.myAssert(false,MySystem.getFileName(),MySystem.getLineNumber());
			}
			return WHITE;
		}
	};
    protected Color color;
    protected ChessPosition position;
    protected boolean alive;
    
	public boolean getAlive(){
		return alive;
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
	
    protected Vector<ChessPosition> getNewPositions(ChessPiece[] chessPieces){
        System.err.println("This is not a valid chess piece.");
        MySystem.myAssert(false,MySystem.getFileName(),MySystem.getLineNumber());
        return new Vector<>(0);
    }
    
    public void move(ChessPosition position, ChessPiece[] chessPieces){
        System.err.println("This is not a valid chess piece.");
        MySystem.myAssert(false,MySystem.getFileName(),MySystem.getLineNumber());
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