package chess;

import java.util.Vector;

/**
 * Used to store conditions to limit recalculation time
 */
public class ConditionStorage{
	private PiecesStorage pieces;

	private class PossibleMoveStorage{
		private Vector<ChessPosition> possibleMoves;
		private ChessPiece piece;
		public Vector<ChessPosition> getPossibleMoves(){
			return possibleMoves;
		}
		public ChessPiece getPiece(){
			return ChessPiece.makePiece(piece);
		}
		public void update(ChessPiece piece, ChessPieces pieces){
			this.piece = piece;
			this.possibleMoves = this.piece.limitMovesToLeavingCheck(pieces);
			if(this.piece instanceof King){
				King k = new King(this.piece);
				k.update(pieces);
				this.piece = k;
			}
		}
		public PossibleMoveStorage(){
			possibleMoves = new Vector<>(0);
			piece = new ChessPiece();
		}
	}

	PossibleMoveStorage[] possibleMoveStorage;

	public Vector<ChessPosition> getMovesAt(final int INDEX){
		return this.possibleMoveStorage[INDEX].getPossibleMoves();
	}

	public ChessPiece getPieceAt(final int INDEX){
		return ChessPiece.makePiece(this.possibleMoveStorage[INDEX].getPiece());
	}

	public void update(ChessPieces pieces,boolean force){
		MySystem.myAssert(pieces.length() == this.pieces.getLast().length() && pieces.length() == this.possibleMoveStorage.length,MySystem.getFileName(),MySystem.getLineNumber());
		if(this.pieces.changed(pieces) || force){
			this.pieces.updatePieces(pieces);
			for(int i = 0; i < this.possibleMoveStorage.length; i++){
				possibleMoveStorage[i].update(pieces.getPieceAt(i),this.pieces.getLast());
			}
		}
	}

	public boolean changed(ChessPieces pieces){
		return this.pieces.changed(pieces);
	}

	public ChessPieces getPieces(){
		return new ChessPieces(this.pieces.getLast());
	}

	public ConditionStorage(ChessPieces pieces){
		this.pieces = new PiecesStorage(pieces);
		this.possibleMoveStorage = new PossibleMoveStorage[pieces.length()];
		for(int i = 0; i < this.possibleMoveStorage.length; i++){
			this.possibleMoveStorage[i] = new PossibleMoveStorage();
		}
		this.update(pieces,true);
	}
}
