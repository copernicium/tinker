package chess;

/**
 * Used to store a set of conditions to save time by limiting re-calculation
 */
public class PiecesStorage{
	private ChessPieces last;

	public String toString(){
		return "ConditionStorage(" + this.getLast().toString() + ")";
	}

	public boolean equals(PiecesStorage b){
		return this.getLast().equals(b.getLast());
	}

	public ChessPieces getLast(){
		return ChessPieces.makePieces(last);
	}

	public boolean changed(ChessPieces newPieces){
		return !last.equals(newPieces);
	}

	public void updatePieces(ChessPieces newPieces){
		if(this.changed(newPieces)) last = ChessPieces.makePieces(newPieces);
	}

	public PiecesStorage(){
		this(new ChessPieces());
	}
	public PiecesStorage(ChessPieces last){
		this.last = ChessPieces.makePieces(last);
	}
	public PiecesStorage(PiecesStorage toCopy){
		this(toCopy.getLast());
	}
}