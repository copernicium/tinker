package chess;

import java.util.Vector;

/**
 * Used to store conditions to limit recalculation time
 */
public class ConditionStorage{
	private ChessPieces last;

	private class PossibleMoveStorage{
		private Vector<ChessPosition> possibleMoves;
		public Vector<ChessPosition> getPossibleMoves(){
			return possibleMoves;
		}
		public void update(ChessPieces pieces,int INDEX){
			this.possibleMoves = pieces.getPieceAt(INDEX).limitMovesToLeavingCheck(pieces);
		}
		public PossibleMoveStorage(){
			possibleMoves = new Vector<>(0);
		}
	}

	PossibleMoveStorage[] possibleMoveStorage;

	public Vector<ChessPosition> getMovesAt(final int INDEX){
		return this.possibleMoveStorage[INDEX].getPossibleMoves();
	}

	/*public ChessPiece getPieceAt(final int INDEX){
		return ChessPiece.makePiece(this.possibleMoveStorage[INDEX].getPiece());
	}*/

	public void updateKings(){
		this.last.updateKings();
	}

	public void update(ChessPieces pieces,boolean force){
		MySystem.myAssert(pieces.length() == this.last.length() && pieces.length() == this.possibleMoveStorage.length,MySystem.getFileName(),MySystem.getLineNumber());
		if(this.changed(pieces) || force){
			this.last = ChessPieces.makePieces(pieces);
			for(int i = 0; i < this.possibleMoveStorage.length; i++){
				possibleMoveStorage[i].update(this.last,i);
			}
		}
	}

	public boolean changed(ChessPieces newPieces){
		return !this.last.equals(newPieces);
	}

	public ChessPieces getLast(){
		return new ChessPieces(this.last);
	}

	public ConditionStorage(ChessPieces pieces){
		this.last = ChessPieces.makePieces(pieces);
		this.possibleMoveStorage = new PossibleMoveStorage[pieces.length()];
		for(int i = 0; i < this.possibleMoveStorage.length; i++){
			this.possibleMoveStorage[i] = new PossibleMoveStorage();
		}
		this.update(pieces,true);
	}
}
