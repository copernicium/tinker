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
		MySystem.myAssert(this.getCheck(), MySystem.getFileName(), MySystem.getLineNumber());
		this.updatePossibleMoves(CHESS_PIECES);
		ChessPieces postMovePieces = ChessPieces.makePieces(CHESS_PIECES);
		int index = postMovePieces.getIndexOf(this.getPosition());
		for(ChessPosition possibleMove: this.getLimitedMoves()){
			postMovePieces.move(index,possibleMove);
			postMovePieces.updateAllPossibleMoves(Color.not(this.getColor()));//only update the opposite color because it only needs to look to see if it's moved out of check which is determined by the other color's ability to take the king
			postMovePieces.updateKingCheck(this.getColor());
			if(!postMovePieces.getKing(this.getColor()).getCheck()){//could the king itself move out of check
				return false;
			}
			postMovePieces.set(index,this);//undo the failed move
		}
		return true;
	}

	private void updateCheckmate(final ChessPieces CHESS_PIECES){
		if(!this.check){
			this.checkmate = false;
			return;//if it's not in check, then it doesn't need to check if it's in checkmate
		}
		ChessPieces postMovePieces = ChessPieces.makePieces(CHESS_PIECES);
		for(int i = 0; i < postMovePieces.length(); i++){//TODO: make foreach?
			ChessPiece defendingPiece = ChessPiece.makePiece(postMovePieces.getPieceAt(i));
			if(defendingPiece.getColor() != this.getColor() || defendingPiece.equalsByType(this)) continue;
			for(ChessPosition possibleMove : defendingPiece.getLimitedMoves()){
				{
					//if(!postMovePieces.checkExists(testPiece)) MySystem.error("Piece does not exist",MySystem.getFileName(),MySystem.getLineNumber());//useful when tinkering with kings
					//if(!testPiece.checkMove(possibleMove,postMovePieces)) MySystem.error("Error: trying to move piece to invalid location.",MySystem.getFileName(),MySystem.getLineNumber());//useful when tinkering with kings
					if(postMovePieces.isOccupied(possibleMove, ChessPiece.Color.not(postMovePieces.getPieceAt(i).getColor()))) postMovePieces.capture(possibleMove);
					postMovePieces.move(i,possibleMove);
				}
				{
					postMovePieces.updateAllPossibleMoves(Color.not(this.getColor()));
					this.updateCheck(postMovePieces);

					if(!this.check){//if a move moves it out of check, then it isn't in checkmate
						this.checkmate = false;
						return;
					}
				}
				postMovePieces.set(i,defendingPiece);//undo the move
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
		return "ChessPiece( type:" + this.getType() + " color:" + this.color + " position:" + this.position + " check:" + this.check + " checkmate:" + this.checkmate + " )";
	}

	@Override
	public void capture(){
		MySystem.error("Error: Kings can never be captured", MySystem.getFileName(), MySystem.getLineNumber());
	}

	/**
	 * The symbol to print given the type
	 * @return the letter representing this Chess piece
	 */
	@Override
	public String print(){
		return "K";
	}
	@Override
	public void updatePossibleMoves(ChessPieces chessPieces){
		TreeSet<ChessPosition> possibleMoves = new TreeSet<>();
		if(!this.getAlive()){
			this.possibleMoves = possibleMoves;
			return;
		}
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
	 * @param b the piece to be compared to
	 * @return true if the pieces are equal by value
	 */
	@Override
	public boolean equals(final Object b){
		if(!(b instanceof King)) return false;
		King testPiece = new King((ChessPiece)b);
		if(this.getType() != testPiece.getType()) return false;
		if(this.getColor()!=testPiece.getColor()) return false;
		if(!this.getPosition().equals(testPiece.getPosition())) return false;
		if(this.getAlive() != testPiece.getAlive()) return false;
		if(this.getCheck() != testPiece.getCheck()) return  false;
		if(this.getCheckmate() != testPiece.getCheckmate()) return  false;
		if(!MySystem.treeSetEquals(this.getPossibleMoves(),testPiece.getPossibleMoves())) return false;
		if(!MySystem.treeSetEquals(this.getLimitedMoves(),testPiece.getLimitedMoves())) return false;
		return true;
	}

	@Override
	public void move(ChessPosition newPosition){
		if(MySystem.contains(this.getPossibleMoves(),newPosition)){//TODO: make out of limited moves instead of p
			this.position = newPosition;
			return;
		}
		MySystem.error("Move failed: Not a valid move: trying to move from " + this.getPosition().toString() + " to " + newPosition.toString() + " out of " + this.getPossibleMoves().toString(), MySystem.getFileName(), MySystem.getLineNumber());
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
		this.alive = toCopy.alive;
		this.color = toCopy.color;
		this.check = toCopy.check;
		this.checkmate = toCopy.checkmate;
	}

	public King(ChessPiece chessPiece){
		this(chessPiece.getPosition(),chessPiece.getColor(),chessPiece.getPossibleMoves(),chessPiece.getLimitedMoves());
		if(chessPiece instanceof King){
			King toCopy = (King)chessPiece;
			this.position = new ChessPosition(toCopy.position);//TODO: redundant?
			this.alive = toCopy.alive;
			this.color = toCopy.color;
			this.check = toCopy.check;
			this.checkmate = toCopy.checkmate;
			this.possibleMoves = toCopy.getPossibleMoves();
			this.limitedMoves = toCopy.getLimitedMoves();
		}
	}
	public King(ChessPosition position, Color color,TreeSet<ChessPosition> possibleMoves,TreeSet<ChessPosition> limitedMoves){
		super(position,color,possibleMoves,limitedMoves);
		check = false;
		checkmate = false;
	}
}
