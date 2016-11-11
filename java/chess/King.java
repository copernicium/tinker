package chess;

import java.util.Vector;

/**
 * A king piece
 */
public class King extends ChessPiece
{
	private boolean check;
	private boolean checkMate;

	private static final Type type = Type.KING;

	/**
	 * Fetches the type of this piece
	 * @return the type of this piece
	 */
	@Override
	public Type getType(){
		return King.type;
	}

	private void updateCheck(final ChessPiece[] CHESS_PIECES){
		for(ChessPiece enemyPiece: CHESS_PIECES){
			if(enemyPiece.getColor() == Color.not(this.color)){//if it's an enemy piece
				for(ChessPosition possiblePosition : enemyPiece.getNewPositions(CHESS_PIECES)){
					if(possiblePosition.equals(this.position)){//if it can take this piece (the king)
						check = true;
						return;
					}
				}
			}
		}
		check = false;
	}

	private boolean checkMyMoves(final ChessPiece[] CHESS_PIECES){
		/*ChessPiece[] temp_pieces;
		{
			ChessBoard temp = new ChessBoard(ORIGINAL_PIECES);
			temp.checkIfGameOver();
			temp_pieces = temp.getPieces();
		}
		final ChessPiece[] CHESS_PIECES = ChessPiece.makePieces(temp_pieces);*/
		King original = new King(this);
		for(final ChessPosition POSSIBLE_MOVE: this.getNewPositions(CHESS_PIECES)){
		//	final ChessPiece[] TEST_PIECES = ChessPiece.makePieces(CHESS_PIECES);
			//System.out.println("CALLED FROM " + MySystem.getFileName() + ":" + MySystem.getLineNumber());
			ChessPiece[] postMovePieces = ChessBoard.testMove(this,POSSIBLE_MOVE,CHESS_PIECES);
			this.move(POSSIBLE_MOVE,CHESS_PIECES);
			this.updateCheck(postMovePieces);
			boolean leftCheck = false;
			leftCheck = !this.check;
			{//reset king to start
				this.position = new ChessPosition(original.position);
				this.alive = original.alive;
				this.color = original.color;
				this.check = original.check;
				this.checkMate = original.checkMate;
			}
			if(leftCheck){
				return false;
			}
		}
		return true;
	}

	private void updateCheckMate(final ChessPiece[] CHESS_PIECES){
		this.updateCheck(CHESS_PIECES);
		if(!this.check){
			this.checkMate = false;
			return;//if it's not in check, then it doesn't need to check if it's in checkmate
		}
		for(int i = 0; i < CHESS_PIECES.length; i++){
			final ChessPiece DEFENDING_PIECE = ChessPiece.makePiece(CHESS_PIECES[i]);
			if(DEFENDING_PIECE.getColor() == this.color && !DEFENDING_PIECE.equalsByType(this)){
				for(final ChessPosition POSSIBLE_MOVE : DEFENDING_PIECE.getNewPositions(CHESS_PIECES)){
					//System.out.println("CALLED FROM " + MySystem.getFileName() + ":" + MySystem.getLineNumber());
					ChessPiece[] postMovePieces = ChessBoard.testMove(DEFENDING_PIECE,POSSIBLE_MOVE,CHESS_PIECES);
					{
						this.updateCheck(postMovePieces);

						if(!this.check){//if a move moves it out of check, then it isn't in checkmate
							this.checkMate = false;
							return;
						}
					}
				}
			}
		}
		this.checkMate = this.checkMyMoves(CHESS_PIECES);
	}

	public void update(ChessPiece[] chessPieces){
		updateCheck(chessPieces);
		updateCheckMate(chessPieces);
	}

	@Override
	public String toString(){
		return "ChessPiece( type:" + this.getType() + " color:" + this.color + " position:" + this.position + " check:" + this.check + " checkmate:" + this.checkMate + " )";
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

	/**
	 * Checks for equality by value with a given piece
	 * @param b the piece to be compared to
	 * @return true if the pieces are equal by value
	 */
	@Override
	public boolean equals(final ChessPiece b){
		if(!(b instanceof King)) return false;
		King testPiece = (King)b;
		if(this.getType() != testPiece.getType()) return false;
		if(this.getColor()!=testPiece.getColor()) return false;
		if(!this.getPosition().equals(testPiece.getPosition())) return false;
		if(this.getAlive() != testPiece.getAlive()) return false;
		if(this.getCheck() != testPiece.getCheck()) return  false;
		if(this.getCheckMate() != testPiece.getCheckMate()) return  false;
		return true;
	}

    @Override
    public void move(ChessPosition newPosition, ChessPiece[] chessPieces){
        for(ChessPosition a: getNewPositions(chessPieces)){
            if(newPosition.equals(a)){
                this.position = newPosition;
                return;
            }
        }
		MySystem.error("Move failed: Not a valid move: trying to move from " + this.getPosition().toString() + " to " + newPosition.toString(),MySystem.getFileName(),MySystem.getLineNumber());
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
		check = false;
		checkMate = false;
    }
	public King(King toCopy) {
		this.position = new ChessPosition(toCopy.position);
		this.alive = toCopy.alive;
		this.color = toCopy.color;
		this.check = toCopy.check;
		this.checkMate = toCopy.checkMate;
	}

    public King(ChessPiece chessPiece){
		this(chessPiece.getPosition(),chessPiece.getColor());
	}
    public King(ChessPosition position, Color color){
        super(position,color);
		check = false;
		checkMate = false;
    }
}