package Chess;

import java.util.TreeSet;
import MySystem.*;

/**
 * A king piece
 */
public class King extends ChessPiece
{
	private boolean check;
	private boolean checkmate;

	private static final Type type = Type.KING;
	private static final char symbol = 'K';

	/**
	 * Fetches the type of this piece
	 * @return the type of this piece
	 */
	@Override
	public Type getType(){
		return King.type;
	}

	public void updateCheck(final ChessPieces CHESS_PIECES){
		for(ChessPiece enemyPiece: CHESS_PIECES.toArray()){
			if(enemyPiece.getColor() == Color.not(this.color)){//if it's an enemy piece
				if(MySystem.contains(enemyPiece.getPossibleMoves(),this.getPosition())){//if it can take this piece (the king)
					check = true;
					return;
				}
			}
		}
		check = false;
	}

	private boolean checkMyMoves(final ChessPieces CHESS_PIECES){
		final boolean USE_LIMITED = true;
		if(!this.getCheck()){
			MySystem.error("Testing for checkmate when this king is not even in check", MySystem.getFileName(), MySystem.getLineNumber());
		}
		this.updatePossibleMoves(CHESS_PIECES);
		ChessPieces postMovePieces = ChessPieces.makePieces(CHESS_PIECES);
		int index = postMovePieces.getIndexOf(this.getPosition());
		for(ChessPosition possibleMove: this.getLimitedMoves()){
			postMovePieces.moveAndCapture(index,possibleMove,USE_LIMITED);
			postMovePieces.updateAllPossibleMoves(Color.not(this.getColor()));//only update the opposite color because it only needs to look to see if it's moved out of check which is determined by the other color's ability to take the king
			postMovePieces.updateKingCheck(this.getColor());
			if(!postMovePieces.getKing(this.getColor()).getCheck()){//could the king itself move out of check
				return false;
			}
			postMovePieces.unMove();
		}
		return true;
	}

	private void updateCheckmate(final ChessPieces CHESS_PIECES){
		if(!this.check){
			this.checkmate = false;
			return;//if it's not in check, then it doesn't need to check if it's in checkmate
		}
		ChessPieces postMovePieces = ChessPieces.makePieces(CHESS_PIECES);
		final boolean USE_LIMITED = true;
		for(int i = 0; i < postMovePieces.length(); i++){
			ChessPiece defendingPiece = ChessPiece.makePiece(postMovePieces.getPieceAt(i));
			if(defendingPiece.getColor() != this.getColor() || defendingPiece.equals(this)) continue;
			for(ChessPosition possibleMove : defendingPiece.getLimitedMoves()){
				postMovePieces.moveAndCapture(i,possibleMove,USE_LIMITED);

				postMovePieces.updateAllPossibleMoves(Color.not(this.getColor()));
				this.updateCheck(postMovePieces);

				if(!this.check){//if a move moves it out of check, then it isn't in checkmate
					this.checkmate = false;
					return;
				}

				postMovePieces.unMove();
			}

		}
		this.checkmate = this.checkMyMoves(CHESS_PIECES);
	}

	public void update(final ChessPieces ORIGINAL_PIECES){
		this.updateCheck(ORIGINAL_PIECES);
		this.updateCheckmate(ORIGINAL_PIECES);
	}

	@Override
	public String toString(){
		return "ChessPiece( type:" + this.getType() + " color:" + this.color + " position:" + this.position + " check:" + this.check + " checkmate:" + this.checkmate + " possibleMoves:" + this.possibleMoves + " limitedMoves:" + this.limitedMoves + " )";
	}

	/**
	 * The symbol to print given the type
	 * @return the letter representing this Chess piece
	 */
	public char getSymbol(){
		return (this.color == Color.WHITE) ? Character.toUpperCase(symbol) : Character.toLowerCase(symbol);
	}

	@Override
	public void updatePossibleMoves(ChessPieces chessPieces){
		TreeSet<ChessPosition> possibleMoves = new TreeSet<>();
		final int MOVEMENT_DISTANCE = 1;
		ChessPosition.Tester testPosition = new ChessPosition.Tester();
		for(int y = -MOVEMENT_DISTANCE ; y <= MOVEMENT_DISTANCE ; y++){
			for(int x = -MOVEMENT_DISTANCE ; x <= MOVEMENT_DISTANCE ; x++){
				testPosition.set(this.position.getRow().get() + x, this.position.getColumn().get() + y);
				if(testPosition.inBounds() && chessPieces.isUnoccupied(new ChessPosition(testPosition), this.color)){
					possibleMoves.add(new ChessPosition(testPosition));
				}
			}
		}
		this.possibleMoves = possibleMoves;
	}

	/**
	 * Checks for equality by value with a given piece
	 * @param o the piece to be compared to
	 * @return true if the pieces are equal by value
	 */
	@Override
	public boolean equals(final Object o){
		if(!(o instanceof King)) return false;
		King b = (King)o;
		if(this.getType() != b.getType()) return false;
		if(this.getColor()!= b.getColor()) return false;
		if(!this.getPosition().equals(b.getPosition())) return false;
		if(this.getAlive() != b.getAlive()) return false;
		if(this.getCheck() != b.getCheck()) return  false;
		if(this.getCheckmate() != b.getCheckmate()) return  false;
		if(!MySystem.treeSetEquals(this.getPossibleMoves(),b.getPossibleMoves())) return false;
		if(!MySystem.treeSetEquals(this.getLimitedMoves(),b.getLimitedMoves())) return false;
		return true;
	}

	@Override
	public void move(ChessPosition newPosition,boolean useLimited){
		TreeSet<ChessPosition> setForChecking = useLimited ? this.getLimitedMoves(): this.getPossibleMoves();
		if(MySystem.contains(setForChecking,newPosition)){
			this.position = newPosition;
			return;
		}
		MySystem.error("Move failed: Not a valid move: trying to move from " + this.getPosition().toString() + " to " + newPosition.toString(), MySystem.getFileName(), MySystem.getLineNumber());
	}

	public boolean getCheck(){
		return check;
	}
	public boolean getCheckmate(){
		return checkmate;
	}
	public King(){
		super();
		check = false;
		checkmate = false;
	}
	public King(King toCopy) {
		this.position = new ChessPosition(toCopy.position);
		this.color = toCopy.color;
		this.check = toCopy.check;
		this.checkmate = toCopy.checkmate;
		this.possibleMoves = new TreeSet<>(toCopy.getPossibleMoves());
		this.limitedMoves = new TreeSet<>(toCopy.getLimitedMoves());
	}

	public King(ChessPiece chessPiece){
		super(chessPiece);
		if(chessPiece instanceof King){
			King toCopy = (King)chessPiece;
			this.position = new ChessPosition(toCopy.position);//TODO: redundant because in super()?
			this.color = toCopy.color;
			this.check = toCopy.check;
			this.checkmate = toCopy.checkmate;
			this.possibleMoves = new TreeSet<>(toCopy.getPossibleMoves());
			this.limitedMoves = new TreeSet<>(toCopy.getLimitedMoves());
		}
	}
	public King(ChessPosition position, Color color){
		super(position,color);
		check = false;
		checkmate = false;
	}
}
