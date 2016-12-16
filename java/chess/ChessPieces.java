package Chess;

import MySystem.*;

/**
 * Stores an array of pieces and useful methods associated with them
 */
public class ChessPieces{
	private ChessPiece[] pieces;

	public void limitMoves(ChessPiece.Color color){
		for(ChessPiece chessPiece : this.pieces){
			if(chessPiece.getColor() == color) chessPiece.limitMovesToLeavingCheck(this);
		}
	}

	public void updatePossibleMoves(ChessPiece.Color color){
		for(ChessPiece chessPiece: this.pieces){
			if(chessPiece.getColor() == color) chessPiece.updatePossibleMoves(this);
		}
	}

	public void updateAllMoves(ChessPiece.Color color){
		for(ChessPiece chessPiece: this.pieces){
			if(chessPiece.getColor() == color){
				chessPiece.updatePossibleMoves(this);
				chessPiece.limitMovesToLeavingCheck(this);
			}
		}
	}

	public void updateAllMoves(){
		for(ChessPiece chessPiece: this.pieces){
			chessPiece.updatePossibleMoves(this);
			chessPiece.limitMovesToLeavingCheck(this);
		}
	}

	public boolean isUnoccupied(ChessPosition checkPosition,ChessPiece.Color color){
		for(ChessPiece a: this.pieces){
			if(a.getAlive() && a.getType() != ChessPiece.Type.UNASSIGNED && color == a.getColor() && checkPosition.equals(a.getPosition())) return false;
		}
		return true;
	}

	public boolean isUnoccupied(ChessPosition checkPosition){
		for(ChessPiece a: this.pieces){
			if(a.getAlive() && a.getType() != ChessPiece.Type.UNASSIGNED && checkPosition.equals(a.getPosition())) return false;
		}
		return true;
	}

	public boolean isOccupied(ChessPosition checkPosition){
		return !this.isUnoccupied(checkPosition);
	}


	public boolean isOccupied(ChessPosition checkPosition,ChessPiece.Color color){
		return !this.isUnoccupied(checkPosition,color);
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
			if(a.getAlive() && TEST_PIECE.equalsByType(a)) return true;
		}
		return false;
	}

	/**
	 * Used to copy an array of pieces containbed by ChessPieces by value
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

	public boolean equals(Object o){
		if(o.getClass() != this.getClass()) return false;
		ChessPieces b = (ChessPieces)o; 
		if(b.toArray().length != this.toArray().length) return false;
		for(int i = 0; i<this.toArray().length; i++){
			if(!b.toArray()[i].equalsByType(this.toArray()[i])){
				MySystem.println(i + " is not equal " + this.toArray()[i] + b.toArray()[i],MySystem.getFileName(),MySystem.getLineNumber());
				return false;
			}
		}
		return true;
	}
	/**
	 * Finds the index of a Chess piece in an array, crashing if it cannot be found.
	 * @param FIND_ME the piece to find
	 * @return the index to the piece in the array
	 */
	public int getIndexOf(final ChessPiece FIND_ME){
		for(int i =0; i < this.pieces.length; i++){
			if(FIND_ME.equalsByType(this.getPieceAt(i))) return i;
		}
		MySystem.error("Error: Unable to get index of " + FIND_ME.toString() + " in " + this.toString(), MySystem.getFileName(), MySystem.getLineNumber());
		return -1;
	}

	/**
	 * Finds the location of a piece that meets certain criteria in an array. It crashes if it cannot be found.
	 * @param color the color of the piece
	 * @return the index of the piece if it exits
	 */
	private int getIndexOfKing(ChessPiece.Color color){
		for(int i = 0; i < this.pieces.length; i++){
			if(this.pieces[i].getType() == ChessPiece.Type.KING && color == this.pieces[i].getColor()) return i;
		}
		MySystem.error("King of color " + color.toString() + " not found in array", MySystem.getFileName(), MySystem.getLineNumber());
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
	 * Returns the index of a piece in an array which occupies a given position. It crashes if it cannot be found.
	 * @param chessPosition the position to look for
	 * @return the index of the piece if it exits
	 */
	public int getIndexOf(ChessPosition chessPosition){
		for(int i = 0; i < this.pieces.length; i++){
			if(chessPosition.equals(this.pieces[i].getPosition())) return i;
		}
		MySystem.error("Piece not found in array", MySystem.getFileName(), MySystem.getLineNumber());
		return -1;
	}

	public ChessPiece getPieceAt(final int INDEX){
		return this.pieces[INDEX];
	}

	public ChessPiece getPieceAt(final ChessPosition POSITION){
		return this.getPieceAt(this.getIndexOf(POSITION));
	}

	public ChessPiece getPieceAt(final ChessPiece PIECE){
		return this.getPieceAt(this.getIndexOf(PIECE));
	}

	/**
	 * Fetches all of the pieces on the Chess board
	 * @return the array of all the Chess pieces
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
		return new King(this.getPieceAt(this.getIndexOfKing(color)));
	}

	/**
	 * Finds a king and then updates just its check status
	 */
	public void updateKingCheck(ChessPiece.Color color){
		King updatedKing = getKing(color);

		updatedKing.updateCheck(this);

		this.set(this.getIndexOfKing(color), updatedKing);
	}

	/**
	 * Finds the kings and then updates their check and checkmate statuses
	 */
	public void updateKings(){
		King updatedWhiteKing = getKing(ChessPiece.Color.WHITE);
		King updatedBlackKing = getKing(ChessPiece.Color.BLACK);

		updatedWhiteKing.update(this);
		updatedBlackKing.update(this);

		this.set(this.getIndexOfKing(ChessPiece.Color.WHITE), updatedWhiteKing);
		this.set(this.getIndexOfKing(ChessPiece.Color.BLACK), updatedBlackKing);
	}

	/**
	 * Kills the piece occupying a given Chess position
	 * @param chessPosition the position of the piece to be killed
	 */
	public void capture(ChessPosition chessPosition){
		this.pieces[this.getIndexOf(chessPosition)].capture();
	}

	/**
	 * Find the index of the first Chess piece in an array that hasn't been assigned an identity
	 * @return the index of the next unassigned piece
	 */
	private int findNextUnassigned(){
		for(int i = 0; i < this.pieces.length; i++){
			if(this.pieces[i].getType() == ChessPiece.Type.UNASSIGNED) return i;
		}
		MySystem.error("Error: Unable to find index of the next unassigned piece, array full", MySystem.getFileName(), MySystem.getLineNumber());
		return -1;
	}

	/**
	 * Checks to make sure that position being a assigned a piece is not already occupied any other piece
	 * @param PIECE the piece to be assigned into the array
	 */
	public void setNextPiece(final ChessPiece PIECE){
		MySystem.myAssert(this.isUnoccupied(PIECE.getPosition()), MySystem.getFileName(), MySystem.getLineNumber());
		this.pieces[this.findNextUnassigned()] = ChessPiece.makePiece(PIECE);
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

	public ChessPieces(ChessPieces toCopy){
		this.pieces = ChessPieces.makePieces(toCopy).toArray();
	}
}
