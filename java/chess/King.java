package chess;

import java.util.Vector;

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
				enemyPiece.updatePossibleMoves(CHESS_PIECES);//TODO: this should not have to be here
				if(MySystem.myContains(enemyPiece.getPossibleMoves(),this.getPosition())){//if it can take this piece (the king)
					check = true;
					return;
				}
			}
		}
		check = false;
	}

	private boolean checkMyMoves(final ChessPieces CHESS_PIECES){
		MySystem.myAssert(this.getCheck(),MySystem.getFileName(),MySystem.getLineNumber());
		this.updatePossibleMoves(CHESS_PIECES);
		int index = CHESS_PIECES.getIndexOf(this);
		ChessPieces postMovePieces = ChessPieces.makePieces(CHESS_PIECES);
		for(ChessPosition possibleMove: this.getPossibleMoves()){
			King testMe = new King(CHESS_PIECES.getPieceAt(index));
			testMe.move(possibleMove);
			postMovePieces.set(index,testMe);
			testMe.updateCheck(postMovePieces);
			if(!testMe.getCheck()){//could the king itself move out of check
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
		for(int i = 0; i < CHESS_PIECES.length(); i++){//TODO: make foreach?
			ChessPiece defendingPiece = ChessPiece.makePiece(CHESS_PIECES.getPieceAt(i));
			if(defendingPiece.getColor() == this.getColor() && !defendingPiece.equalsByType(this)){
				for(ChessPosition possibleMove : defendingPiece.getPossibleMoves()){
					{
						ChessPiece testPiece = ChessPiece.makePiece(defendingPiece);
						if(!postMovePieces.checkExists(testPiece)) MySystem.error("Piece does not exist",MySystem.getFileName(),MySystem.getLineNumber());//TODO: comment-out after done tinkering with kings
						if(!testPiece.checkMove(possibleMove,postMovePieces)) MySystem.error("Error: trying to move piece to invalid location.",MySystem.getFileName(),MySystem.getLineNumber());//TODO: comment-out after done tinkering with kings
						if(postMovePieces.isOccupied(possibleMove, ChessPiece.Color.not(postMovePieces.getPieceAt(i).getColor()))) postMovePieces.capture(possibleMove);
						testPiece.move(possibleMove);
						postMovePieces.set(i,testPiece);
					}
					{
						this.updateCheck(postMovePieces);

						if(!this.check){//if a move moves it out of check, then it isn't in checkmate
							this.checkmate = false;
							return;
						}
					}
					postMovePieces.set(i,defendingPiece);
				}
			}
		}
		this.checkmate = this.checkMyMoves(CHESS_PIECES);
	}

	public void update(final ChessPieces ORIGINAL_PIECES){
		ChessPieces chessPieces = new ChessPieces(ORIGINAL_PIECES);
		int index = chessPieces.getIndexOf(this);
		updateCheck(chessPieces);
		chessPieces.set(index, this);
		updateCheckmate(chessPieces);
	}

	@Override
	public String toString(){
		return "ChessPiece( type:" + this.getType() + " color:" + this.color + " position:" + this.position + " check:" + this.check + " checkmate:" + this.checkmate + " )";
	}

	@Override
	public void capture(){
		MySystem.error("Error: Kings can never be captured",MySystem.getFileName(),MySystem.getLineNumber());
	}

	/**
	 * The symbol to print given the type
	 * @return the letter representing this chess piece
	 */
	@Override
	public String print(){
		return "K";
	}
	@Override
	public void updatePossibleMoves(ChessPieces chessPieces){
		Vector<ChessPosition> possibleMoves = new Vector<>(0);
		final int MOVEMENT_DISTANCE = 1;
		for(int y = -MOVEMENT_DISTANCE ; y <= MOVEMENT_DISTANCE ; y++){
			for(int x = -MOVEMENT_DISTANCE ; x <= MOVEMENT_DISTANCE ; x++){
				ChessPosition.Tester testPosition = new ChessPosition.Tester(this.position.getRow().get() + x, this.position.getColumn().get() + y);
				if(testPosition.inBounds() && chessPieces.isUnoccupied(new ChessPosition(testPosition), this.color)){
					possibleMoves.addElement(new ChessPosition(testPosition));
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
	public boolean equals(final ChessPiece b){
		if(!(b instanceof King)) return false;
		King testPiece = new King(b);
		if(this.getType() != testPiece.getType()) return false;
		if(this.getColor()!=testPiece.getColor()) return false;
		if(!this.getPosition().equals(testPiece.getPosition())) return false;
		if(this.getAlive() != testPiece.getAlive()) return false;
		if(this.getCheck() != testPiece.getCheck()) return  false;
		if(this.getCheckmate() != testPiece.getCheckmate()) return  false;
		if(!this.getPossibleMoves().equals(testPiece.getPossibleMoves())) return false;
		if(!this.getLimitedMoves().equals(testPiece.getLimitedMoves())) return false;
		return true;
	}

	@Override
	public void move(ChessPosition newPosition){
		if(MySystem.myContains(this.getPossibleMoves(),newPosition)){
			this.position = newPosition;
			return;
		}
		MySystem.error("Move failed: Not a valid move: trying to move from " + this.getPosition().toString() + " to " + newPosition.toString() + " out of " + this.getPossibleMoves().toString(),MySystem.getFileName(),MySystem.getLineNumber());
		MySystem.myAssert(false,MySystem.getFileName(),MySystem.getLineNumber());
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
	public King(ChessPosition position, Color color,Vector<ChessPosition> possibleMoves,Vector<ChessPosition> limitedMoves){
		super(position,color,possibleMoves,limitedMoves);
		check = false;
		checkmate = false;
	}
}