package chess;

import java.util.Vector;

/**
 * A king piece
 */
public class King extends ChessPiece
{
	private boolean check;
	private boolean checkMate;
   	@Override
    public String toString(){
        return "K";
    }
	@Override
    public Vector<ChessPosition> getNewPositions(ChessPiece[] chessPieces){
		Vector<ChessPosition> possibleMoves = new Vector<>(0);
		for(int y = -1; y <= 1; y++){
			for(int x = -1; x <= 1; x++){
				ChessPosition.Tester testPosition = new ChessPosition.Tester(this.position.getRow().get() + x, this.position.getColumn().get() + y);
				if(ChessPosition.inBounds(testPosition) && !ChessBoard.isOccupied(new ChessPosition(testPosition), this.color, chessPieces)){
					possibleMoves.addElement(new ChessPosition(testPosition));
				}
			}
		}
        return possibleMoves;
    }
    @Override
    public void move(ChessPosition newPosition, ChessPiece[] chessPieces){
        for(ChessPosition a: getNewPositions(chessPieces)){
            if(newPosition.equals(a)){
                this.position = newPosition;
                return;
            }
        }
        System.err.println("Move failed. Not a valid move.");
		MySystem.myAssert(false,MySystem.getFileName(),MySystem.getLineNumber());
	}
	public boolean getCheck(){
		return check;
	}
	public boolean getCheckMate(){
		return checkMate;
	}
    public King(){
        super();
        type = Type.KING;
		check = false;
		checkMate = false;
    }
    public King(ChessPiece chessPiece){
		this(chessPiece.getPosition(),chessPiece.getColor());
	}
    public King(ChessPosition position, Color color){
        super(position,color);
        this.type = Type.KING;
		check = false;
		checkMate = false;
    }
}