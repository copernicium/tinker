package chess;

/**
 * Stores an array of pieces and useful methods associated with them
 */
public class ChessPieces{
	private ChessPiece[] pieces;

	/**
	 * Checks to see if a current position is occupied (unavailable) by a given color
	 * @param checkPosition the position that is checked for occupancy
	 * @param color the color of the occupying piece must be
	 * @return true if a given position is occupied by a piece of a given color
	 */
	public boolean isOccupied(ChessPosition checkPosition,ChessPiece.Color color){
		for(ChessPiece a: this.pieces){
			if(!a.getAlive() || a.getType() == ChessPiece.Type.UNASSIGNED) continue;
			if(color == a.getColor() && checkPosition.equals(a.getPosition()))return true;
		}
		return false;
	}

	@Override
	public String toString(){
		String string = "ChessPieces(";
		for(ChessPiece a: this.pieces){
			string += " " + a.toString();
		}
		string += ")";
		return string;
	}

	/**
	 * Checks if a given piece exists in an array of pieces
	 * @param TEST_PIECE the piece which is checked for existence
	 * @return true if the piece exists in the array
	 */
	public boolean checkExists(final ChessPiece TEST_PIECE){
		if(!TEST_PIECE.getAlive()) return false;
		for(ChessPiece a: this.pieces){
			if(!a.getAlive()) continue;
			if(TEST_PIECE.equalsByType(a)) return true;
		}
		return false;
	}

	/**
	 * Used to copy an array of pieces by value
	 * @param CHESS_PIECES the array to copy
	 * @return the new array
	 */
	public static ChessPieces makePieces(final ChessPieces CHESS_PIECES){
		ChessPieces newPieces = new ChessPieces(CHESS_PIECES.length());
		for(int i = 0; i < CHESS_PIECES.length(); i++){
			newPieces.set(i,ChessPiece.makePiece(CHESS_PIECES.getPieceAt(i)));
		}
		return newPieces;
	}

	boolean equals(ChessPieces b){
		if(b.toArray().length != this.toArray().length) return false;
		for(int i = 0; i<this.toArray().length; i++){
			if(!b.toArray()[i].equalsByType(this.toArray()[i]))return false;
		}
		return true;
	}
	/**
	 * Finds the index of a chess piece in an array, crashing if it cannot be found.
	 * @param FIND_ME the piece to find
	 * @return the index to the piece in the array
	 */
	public int getIndexOf(final ChessPiece FIND_ME){
		for(int i =0; i < this.pieces.length; i++){
			if(FIND_ME.equalsByType(this.pieces[i]))return i;
		}
		MySystem.myAssert(false,MySystem.getFileName(),MySystem.getLineNumber());
		return -1;
	}

	/**
	 * Finds the location of a piece that meets certain criteria in an array. It crashes if it cannot be found.
	 * @param type the type of piece
	 * @param color the color of the piece
	 * @return the index of the piece if it exits
	 */
	public int getIndexOf(ChessPiece.Type type, ChessPiece.Color color){
		for(int i = 0; i < this.pieces.length; i++){
			if(type == this.pieces[i].getType() && color == this.pieces[i].getColor()) return i;
		}
		MySystem.error("Piece not found in array",MySystem.getFileName(),MySystem.getLineNumber());
		return -1;
	}

	/**
	 * Sets a the piece at a given index to a given value
	 * @param INDEX the index of the piece to set
	 * @param CHESS_PIECE the piece to set it to
	 */
	public void set(final int INDEX,final ChessPiece CHESS_PIECE){
		this.pieces[INDEX] = CHESS_PIECE;
	}

	/**
	 * Captures the piece at a given index
	 * @param INDEX the index of the piece to capture
	 */
	public void captureAt(final int INDEX){
		this.pieces[INDEX].capture();
	}

	/**
	 * Returns the index of a piece in an array which occupies a given position. It crashes if it cannot be found.
	 * @param chessPosition the position to look for
	 * @return the index of the piece if it exits
	 */
	public int getIndexOf(ChessPosition chessPosition){
		for(int i = 0; i < this.pieces.length; i++){
			if(chessPosition.equals(this.pieces[i].getPosition())) return i;
		}
		MySystem.error("Piece not found in array",MySystem.getFileName(),MySystem.getLineNumber());
		return -1;
	}

	public ChessPiece getPieceAt(final int INDEX){
		return this.pieces[INDEX];
	}

	/**
	 * Fetches all of the pieces on the chess board
	 * @return the array of all the chess pieces
	 */
	public ChessPiece[] toArray(){
		return this.pieces;
	}

	/**
	 * Fetches the king of the appropriate color
	 * @param color the color of the king to get
	 * @return the king piece of the given color
	 */
	public King getKing(ChessPiece.Color color){
		return new King(this.getPieceAt(this.getIndexOf(ChessPiece.Type.KING, color)));
	}

	/**
	 * Finds the kings and then updates just their check statuses
	 * @return the updated array of pieces
	 */
	public void updateKingChecks(){
		King updatedWhiteKing = getKing(ChessPiece.Color.WHITE);
		King updatedBlackKing = getKing(ChessPiece.Color.BLACK);

		updatedWhiteKing.updateCheck(ChessPieces.makePieces(this));
		updatedBlackKing.updateCheck(ChessPieces.makePieces(this));

		this.set(this.getIndexOf(ChessPiece.Type.KING, ChessPiece.Color.WHITE), updatedWhiteKing);
		this.set(this.getIndexOf(ChessPiece.Type.KING, ChessPiece.Color.BLACK), updatedBlackKing);
	}

	/**
	 * Finds the kings and then updates their check and checkmate statuses
	 * @return the updated array of pieces
	 */
	public void updateKings(){
		King updatedWhiteKing = getKing(ChessPiece.Color.WHITE);
		King updatedBlackKing = getKing(ChessPiece.Color.BLACK);

		updatedWhiteKing.update(ChessPieces.makePieces(this));
		updatedBlackKing.update(ChessPieces.makePieces(this));

		this.set(this.getIndexOf(ChessPiece.Type.KING, ChessPiece.Color.WHITE), updatedWhiteKing);
		this.set(this.getIndexOf(ChessPiece.Type.KING, ChessPiece.Color.BLACK), updatedBlackKing);
	}

	/**
	 * Kills the piece occuppying a given chess positon
	 * @param chessPosition the position of the piece to be killed
	 */
	public void capture(ChessPosition chessPosition){
		this.pieces[this.getIndexOf(chessPosition)].capture();
	}

	/**
	 * Find the index of the first chess piece in an array that hasn't been assigned an identity
	 * @return the index of the next unassigned piece
	 */
	public int findNextUnassigned(){
		for(int i = 0; i < this.pieces.length; i++){
			if(this.pieces[i].getType() == ChessPiece.Type.UNASSIGNED) return i;
		}
		MySystem.myAssert(false,MySystem.getFileName(),MySystem.getLineNumber());
		return -1;
	}

	/**
	 * Checks to make sure that position being a assigned a piece is not already occupied any other piece
	 * @param PIECE the piece to be assigned into the array
	 * @return the array after the piece has been added to it
	 */
	public void setNextPiece(final ChessPiece PIECE){
		MySystem.myAssert(!this.isOccupied(PIECE.getPosition(),PIECE.getColor()),MySystem.getFileName(),MySystem.getLineNumber());
		MySystem.myAssert(!this.isOccupied(PIECE.getPosition(), ChessPiece.Color.not(PIECE.getColor())),MySystem.getFileName(),MySystem.getLineNumber());
		this.pieces[this.findNextUnassigned()] = ChessPiece.makePiece(PIECE.getPosition(), PIECE.getColor(), PIECE.getType());
	}

	/**
	 * Fetches the length of the pieces array
	 * @return the length of the pieces array
	 */
	public int length(){
		return this.pieces.length;
	}


	public ChessPieces(){
		this(0);
	}

	public ChessPieces(final int LENGTH){
		this.pieces = new ChessPiece[LENGTH];
		for(int i = 0; i< pieces.length; i++){
			this.pieces[i] = new ChessPiece();
		}
	}

	public ChessPieces(ChessPiece[] pieces){
		this.pieces = pieces;
	}

	public ChessPieces(ChessPieces pieces){
		this.pieces = (ChessPieces.makePieces(pieces)).toArray();
	}
}