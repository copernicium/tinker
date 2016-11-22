package chess;

import java.util.Vector;

/**
 * A rook piece
 */
public class Rook extends ChessPiece
{
	private static final Type type = Type.ROOK;

	/**
	 * Fetches the type of this piece
	 * @return the type of this piece
	 */
	@Override
	public Type getType(){
		return Rook.type;
	}

	/**
	 * The symbol to print given the type
	 * @return the letter representing this chess piece
	 */
	@Override
    public String print(){
        return "R";
    }

    @Override
    public Vector<ChessPosition> getNewPositions(ChessPieces chessPieces){
		Vector<ChessPosition> possibleMoves = new Vector<>(0);
		{
			int direction = -1, row = 1, column = 0;
			for(int j = 0; j < 4; j++){
				for(int i = 1; i <= ChessPosition.Row.DIMENSION; i++){
					ChessPosition.Tester testPosition = new ChessPosition.Tester(this.position.getRow().get() + (i * direction * row), this.position.getColumn().get() + (i * direction * column));
					if(testPosition.inBounds() && !chessPieces.isOccupied(new ChessPosition(testPosition), this.color)){
						possibleMoves.addElement(new ChessPosition(testPosition));
						if(chessPieces.isOccupied(new ChessPosition(testPosition), Color.not(this.color))) break;//if it will capture a piece, stop it there
					} else break;
				}
				direction *= -1;//switch direction each time to cover both directions of rows and columns
				if(j==1){//switch which axes movement is restricted to
					row = 0;
					column = 1;
				}
			}
		}
        return possibleMoves;
    }
    @Override
    public void move(ChessPosition newPosition, ChessPieces chessPieces){
        for(ChessPosition a: getNewPositions(chessPieces)){
            if(newPosition.equals(a)){
                this.position = newPosition;
                return;
            }
        }
		MySystem.error("Move failed: Not a valid move: trying to move from " + this.getPosition().toString() + " to " + newPosition.toString(),MySystem.getFileName(),MySystem.getLineNumber());
		MySystem.myAssert(false,MySystem.getFileName(),MySystem.getLineNumber());
	}

	public Rook(Rook toCopy) {
		this.position = new ChessPosition(toCopy.position);
		this.alive = toCopy.alive;
		this.color = toCopy.color;
	}

    public Rook(){
        super();
    }
    public Rook(ChessPiece chessPiece){
		this(chessPiece.getPosition(),chessPiece.getColor());
	}
    public Rook(ChessPosition position, Color color){
        super(position,color);
    }
}