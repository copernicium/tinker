package chess;
import java.util.Vector;
/**
 * A class to represent a basic chess piece
 */
public class ChessPiece
{
    public enum Type{UNASSIGNED,PAWN,ROOK,KNIGHT,BISHOP,QUEEN,KING};
    protected Type type;
    public enum Color{
		WHITE,BLACK;
		public static Color not(Color a){
			switch(a){
				case WHITE: 
					return BLACK;
				case BLACK:
					return WHITE;
				default: MySystem.nyi(MySystem.getFileName(),MySystem.getLineNumber());
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

	public void capture(){
		if(!alive) MySystem.nyi(MySystem.getFileName(),MySystem.getLineNumber());
		alive = false;
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
       return this.getNewPositions(chessPieces);
		// System.err.println("This is not a valid chess piece.");
        //MySystem.nyi(MySystem.getFileName(),MySystem.getLineNumber());
       // return new Vector<>(0);
    }
    
    public void move(ChessPosition position, ChessPiece[] chessPieces){
        this.move(position,chessPieces);
		//System.err.println("This is not a valid chess piece.");
        //MySystem.nyi(MySystem.getFileName(),MySystem.getLineNumber());
    }

    public boolean checkMove(ChessPosition position,ChessPiece[] chessPieces){
		for(ChessPosition chessPosition: getNewPositions(chessPieces)){
			if(position.equals(chessPosition)) return true;
		}
		return false;
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
		this.type = Type.UNASSIGNED;
    }

	public static ChessPiece makePiece(ChessPosition position, Color color, ChessPiece[] chessPieces){
		for(ChessPiece a: chessPieces){
			if(a.getPosition().equals(position) && a.getColor().equals(color)){
				switch(a.getType()){
					case KING:
						return new King(position,color);
					case KNIGHT:
						return new Knight(position,color);
					case QUEEN:
						return new Queen(position,color);
					case ROOK:
						return new Rook(position,color);
					case PAWN:
						return new Pawn(position,color);
					case BISHOP:
						return new Bishop(position,color);
					case UNASSIGNED:
						return new ChessPiece(position,color);
					default: MySystem.nyi(MySystem.getFileName(),MySystem.getLineNumber());
				}
			}
		}
		MySystem.nyi(MySystem.getFileName(),MySystem.getLineNumber());
		return new ChessPiece();
	}

    public ChessPiece(ChessPosition position,Color color){
        this.position = position;
        this.color = color;
		this.type = Type.UNASSIGNED;
        alive = true;
    }
}