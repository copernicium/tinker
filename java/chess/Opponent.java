package chess;

/**
 * A chess ai
 */
public class Opponent {
	public static class Move{
		private ChessPosition start;
		private ChessPosition target;

		public Move(){
			this.start = new ChessPosition();
			this.target = new ChessPosition();
		}
	}

	private ChessPiece.Color color;

	public ChessPiece.Color getColor(){
		return this.color;
	}

	public Move getMove(ChessPieces pieces){
		return new Move();
	}

	public Opponent(ChessPiece.Color color){
		this.color = color;
	}
}
