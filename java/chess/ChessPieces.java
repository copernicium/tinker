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
	public static ChessPiece[] makePieces(final ChessPiece[] CHESS_PIECES){
		ChessPiece[] newPieces = new ChessPiece[CHESS_PIECES.length];
		for(int i = 0; i < CHESS_PIECES.length; i++){
			newPieces[i] = ChessPiece.makePiece(CHESS_PIECES[i]);
		}
		return newPieces;
	}

	/**
	 * Finds the location of a piece that meets certain criteria in an array. It crashes if it cannot be found.
	 * @param type the type of piece
	 * @param color the color of the piece
	 * @return the index of the piece if it exits
	 */
	public int find(ChessPiece.Type type, ChessPiece.Color color){
		for(int i = 0; i < this.pieces.length; i++){
			if(type == this.pieces[i].getType() && color == this.pieces[i].getColor()) return i;
		}
		MySystem.error("Piece not found in array",MySystem.getFileName(),MySystem.getLineNumber());
		return -1;
	}

	/**
	 * Returns the index of a piece in an array which occupies a given position. It crashes if it cannot be found.
	 * @param chessPosition the position to look for
	 * @return the index of the piece if it exits
	 */
	public int find(ChessPosition chessPosition){
		for(int i = 0; i < this.pieces.length; i++){
			if(chessPosition.equals(this.pieces[i].getPosition())) return i;
		}
		MySystem.error("Piece not found in array",MySystem.getFileName(),MySystem.getLineNumber());
		return -1;
	}

	/**
	 * Fetches all of the pieces on the chess board
	 * @return the array of all the chess pieces
	 */
	public ChessPiece[] getPieces(){
		return this.pieces;
	}


	/**
	 * Finds the index of a chess piece in an array, crashing if it cannot be found.
	 * @param FIND_ME the piece to find
	 * @return the index to the piece in the array
	 */
	public int find(final ChessPiece FIND_ME){
		for(int i =0; i < this.pieces.length; i++){
			if(FIND_ME.equalsByType(this.pieces[i]))return i;
		}
		MySystem.myAssert(false,MySystem.getFileName(),MySystem.getLineNumber());
		return -1;
	}

	/**
	 * Takes in an array of pieces, finds the kings, and then updates their check and checkmate statuses
	 * @return the updated array of pieces
	 */
	private void updateKings(){
		ChessPiece[] pieces = ChessPiece.makePieces(this.pieces);
		King whiteKing = new King(pieces[ChessBoard.find(ChessPiece.Type.KING, ChessPiece.Color.WHITE,pieces)]);
		King blackKing = new King(pieces[ChessBoard.find(ChessPiece.Type.KING, ChessPiece.Color.BLACK,pieces)]);

		whiteKing.update(pieces);
		blackKing.update(pieces);
	}

	/**
	 * Kills the piece occuppying a given chess positon
	 * @param chessPosition the position of the piece to be killed
	 */
	public void capture(ChessPosition chessPosition){
		this.pieces[ChessBoard.find(chessPosition,this.pieces)].capture();
	}

	/**
	 * Find the index of the first chess piece in an array that hasn't been assigned an identity
	 * @return the index of the next unassigned piece
	 */
	private int findNextUnassigned(){
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
	private void setNextPiece(final ChessPiece PIECE){
		MySystem.myAssert(!this.isOccupied(PIECE.getPosition(),PIECE.getColor()),MySystem.getFileName(),MySystem.getLineNumber());
		MySystem.myAssert(!this.isOccupied(PIECE.getPosition(), ChessPiece.Color.not(PIECE.getColor())),MySystem.getFileName(),MySystem.getLineNumber());
		this.pieces[this.findNextUnassigned()] = ChessPiece.makePiece(PIECE.getPosition(), PIECE.getColor(), PIECE.getType());
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
		this.pieces = ChessPieces.makePieces(pieces.getPieces());
	}
}