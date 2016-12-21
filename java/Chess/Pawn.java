package Chess;

import java.util.TreeSet;
import MySystem.*;
/**
 * A pawn piece
 */
public class Pawn extends ChessPiece
{
    private boolean firstMove;//pawns can move differently on their first move

	private static final Type type = Type.PAWN;

	/**
	 * Fetches the type of this piece
	 * @return the type of this piece
	 */
	@Override
	public Type getType(){
		return Pawn.type;
	}

	@Override
	public String toString(){
		return "ChessPiece(type:" + this.getType() + " color:" + this.color + " position:" + this.position + " firstMove:" + this.firstMove + " possibleMoves:" + this.possibleMoves + " limitedMoves:" + this.limitedMoves + ")";
	}

	/**
	 * The symbol to print given the type
	 * @return the letter representing this Chess piece
	 */
	@Override
    public String print(){
        return "P";
    }
    @Override
    public void updatePossibleMoves(ChessPieces chessPieces){//TODO: en passant and maybe promotion
		TreeSet<ChessPosition> possibleMoves = new TreeSet<>();
		int direction = (this.getColor() == ChessPiece.Color.WHITE) ? 1 : -1;
		ChessPosition.Tester testPosition = new ChessPosition.Tester();
		final int ADVANCE_DISTANCE = 1;
		{//if it can capture a piece
			final int CAPTURE_DISTANCE = 1;
			{
				testPosition.set(this.position.getRow().get() + CAPTURE_DISTANCE, this.position.getColumn().get() + (ADVANCE_DISTANCE * direction));
				if(testPosition.inBounds() && chessPieces.isOccupied(new ChessPosition(testPosition), Color.not(this.color))){
					possibleMoves.add(new ChessPosition(testPosition));
				}
			}
			{//if it can capture a piece
				final int DIAGONAL_DISTANCE = -1;
				testPosition.set(this.position.getRow().get() - CAPTURE_DISTANCE, this.position.getColumn().get() + (DIAGONAL_DISTANCE * direction));
				if(testPosition.inBounds() && chessPieces.isOccupied(new ChessPosition(testPosition), Color.not(this.color))){
					possibleMoves.add(new ChessPosition(testPosition));
				}
			}
		}
		{
			testPosition.set(this.position.getRow().get()+(ADVANCE_DISTANCE*direction),this.position.getColumn().get());
			if(testPosition.inBounds() && chessPieces.isUnoccupied(new ChessPosition(testPosition),this.color)){
				possibleMoves.add(new ChessPosition(testPosition));
			}
		}
		if(this.getFirstMove()){
			final int INITIAL_TWO_SQUARE_ADVANCE = 2, INBETWEEN_POSITION_DIST = 1;
			testPosition.set(this.position.getRow().get()+(INITIAL_TWO_SQUARE_ADVANCE * direction),this.position.getColumn().get());
			ChessPosition.Tester inBetweenPosition = new ChessPosition.Tester(this.position.getRow().get() + INBETWEEN_POSITION_DIST  * direction,this.position.getColumn().get());
			if(testPosition.inBounds() && chessPieces.isUnoccupied(new ChessPosition(testPosition),this.color) && inBetweenPosition.inBounds() && chessPieces.isUnoccupied(new ChessPosition(testPosition))){
				possibleMoves.add(new ChessPosition(testPosition));
			}
		}
		this.possibleMoves = possibleMoves;
	}

    public boolean getFirstMove(){
		return this.firstMove;
	}

	/**
	 * Checks for equality by value with a given piece
	 * @param o the piece to be compared to
	 * @return true if the pieces are equal by value
	 */
    @Override
	public boolean equals(Object o){
		if(!(o instanceof Pawn)) return false;
		Pawn b = (Pawn)o;
		if(this.getType() != b.getType()) return false;
		if(this.getColor()!= b.getColor()) return false;
		if(!this.getPosition().equals(b.getPosition())) return false;
		if(this.getAlive() != b.getAlive()) return false;
		if(this.getFirstMove() != b.getFirstMove()) return  false;
		if(!MySystem.treeSetEquals(this.getPossibleMoves(),b.getPossibleMoves())) return false;
		if(!MySystem.treeSetEquals(this.getLimitedMoves(),b.getLimitedMoves())) return false;
		return true;
	}

    @Override
    public void move(ChessPosition newPosition){
		if(MySystem.contains(this.getPossibleMoves(),newPosition)){
			this.position = newPosition;
			this.firstMove = false;
			return;
		}
		MySystem.error("Move failed: Not a valid move: trying to move from " + this.getPosition().toString() + " to " + newPosition.toString(), MySystem.getFileName(), MySystem.getLineNumber());
	}

	public Pawn(Pawn toCopy) {
		this.position = new ChessPosition(toCopy.position);
		this.color = toCopy.color;
		this.firstMove = toCopy.firstMove;
		this.possibleMoves = new TreeSet<>(toCopy.getPossibleMoves());
		this.limitedMoves = new TreeSet<>(toCopy.getLimitedMoves());
	}

    public Pawn(){
        super();
        firstMove = true;
    }
	public Pawn(ChessPiece chessPiece){
		this(chessPiece.getPosition(),chessPiece.getColor(),chessPiece.getPossibleMoves(),chessPiece.getLimitedMoves());
		if(chessPiece instanceof Pawn){
			Pawn toCopy = (Pawn)chessPiece;
			this.position = new ChessPosition(toCopy.position);
			this.color = toCopy.color;
			this.firstMove = toCopy.getFirstMove();
			this.possibleMoves = new TreeSet<>(toCopy.getPossibleMoves());
			this.limitedMoves = new TreeSet<>(toCopy.getLimitedMoves());
		}
	}
    public Pawn(ChessPosition position,Color color,TreeSet<ChessPosition> possibleMoves,TreeSet<ChessPosition> limitedMoves){
        super(position,color,possibleMoves,limitedMoves);
		this.firstMove = (position.getRow().equals(ChessBoard.PiecePlacement.Row.PAWN )|| position.getRow().equals(ChessBoard.mirror(ChessBoard.PiecePlacement.Row.PAWN)));
    }
}
