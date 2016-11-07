package chess;
import java.util.Vector;
/**
 * A class to represent a basic chess piece
 */
public class ChessPiece
{
    public enum Type{
		UNASSIGNED,PAWN,ROOK,KNIGHT,BISHOP,QUEEN,KING
    }
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
	}
    protected Color color;
    protected ChessPosition position;
    protected boolean alive;
	protected Type type;
    
	public boolean getAlive(){
		return alive;
	}

	public void capture(){
		if(!alive) MySystem.nyi(MySystem.getFileName(),MySystem.getLineNumber());
		alive = false;
	}

	@Override
    public String toString(){
        return "ChessPiece( type:" + this.getType() + " color:" + this.color + " position:" + this.position + " alive:" + this.alive + ")";
    }

	public String print(){
		return "U";
	}

	public Color getColor(){
        return color;
    }
	
	public boolean equals(ChessPiece b){
		if(this.getType() != b.getType()) return false;
		if(this.getColor() != b.getColor()) return false;
		if(!this.getPosition().equals(b.getPosition())) return false;
		if(this.getAlive() != b.getAlive()) return false;
		return true;
	}

	public boolean equalsByType(ChessPiece a){
		switch(this.getType()){
			case PAWN:{
				Pawn b = new Pawn(a);
				return b.equals(this);
			}
			case KING:{
				King b = new King(a);
				return b.equals(this);
			}
			default:
				return a.equals(this);
		}
	}

    protected Vector<ChessPosition> getNewPositions(ChessPiece[] chessPieces){
      	MySystem.error("This is not a valid chess piece.",MySystem.getFileName(),MySystem.getLineNumber());
       	return new Vector<>(0);
    }
    
    public void move(ChessPosition position, ChessPiece[] chessPieces){
       MySystem.error("This is not a valid chess piece.", MySystem.getFileName(),MySystem.getLineNumber());
    }

    public boolean checkMove(final ChessPosition CHECK_MOVE,final ChessPiece[] CHESS_PIECES){
		MySystem.myAssert((ChessBoard.checkExists(this,CHESS_PIECES)),MySystem.getFileName(),MySystem.getLineNumber());
		for(ChessPosition possiblePosition: this.getNewPositions(CHESS_PIECES)){
			if(CHECK_MOVE.equals(possiblePosition)) return true;
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

    public static ChessPiece[] makePieces(final ChessPiece[] CHESS_PIECES){
		ChessPiece[] newPieces = new ChessPiece[CHESS_PIECES.length];
		for(int i = 0; i < CHESS_PIECES.length; i++){
			newPieces[i] = ChessPiece.makePiece(CHESS_PIECES[i]);
		}
		return newPieces;
	}

    public static ChessPiece makePiece(ChessPiece chessPiece){
		switch(chessPiece.getType()){
			case KING:
				return new King(chessPiece);
			case KNIGHT:
				return new Knight(chessPiece);
			case QUEEN:
				return new Queen(chessPiece);
			case ROOK:
				return new Rook(chessPiece);
			case PAWN:
				return new Pawn(chessPiece);
			case BISHOP:
				return new Bishop(chessPiece);
			case UNASSIGNED:
				return new ChessPiece(chessPiece);
			default: MySystem.nyi(MySystem.getFileName(),MySystem.getLineNumber());
		}
		MySystem.nyi(MySystem.getFileName(),MySystem.getLineNumber());
		return new ChessPiece();
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

	public ChessPiece(ChessPiece toCopy){
		this.position = new ChessPosition(toCopy.getPosition());
		this.color = toCopy.color;
		this.type = toCopy.type;
		this.alive = toCopy.alive;
	}

    public ChessPiece(ChessPosition position,Color color){
        this.position = position;
        this.color = color;
		this.type = Type.UNASSIGNED;
        alive = true;
    }
}