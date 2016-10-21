/**
 * A representation of a chess board
 */
public class ChessBoard
{
	private static class NumbersOfPieces{
		public static final int PAWNS=8;
		public static final int ROOKS=2;
		public static final int KNIGHTS=2;
		public static final int BISHOPS=2;
		public static final int QUEENS=1;
		public static final int KINGS=1;
		public static final int TOTAL=16;
	}
	private ChessPiece[] whitePieces;
	private ChessPiece[] blackPieces;
	
	public void kill(ChessPiece chessPiece){
		//TODO This
	}
	
	private ChessPiece[] fillHalfOfBoard(ChessPiece.Color color){
		ChessPiece[] chessPieces = new ChessPiece[NumbersOfPieces.TOTAL];
		int piecesFilled = 0;
		for(int i =0; i < NumbersOfPieces.PAWNS; i++) chessPieces[i+piecesFilled] = new Pawn(new ChessPosition(),color);
		piecesFilled += NumbersOfPieces.PAWNS;
		for(int i =0; i < NumbersOfPieces.ROOKS; i++) chessPieces[i+piecesFilled] = new ChessPiece(new ChessPosition(),color);
		piecesFilled += NumbersOfPieces.ROOKS;
		for(int i =0; i < NumbersOfPieces.KNIGHTS; i++) chessPieces[i+piecesFilled] = new ChessPiece(new ChessPosition(),color);
		piecesFilled += NumbersOfPieces.KNIGHTS;
		for(int i =0; i < NumbersOfPieces.BISHOPS; i++) chessPieces[i+piecesFilled] = new ChessPiece(new ChessPosition(),color);
		piecesFilled += NumbersOfPieces.BISHOPS;
		for(int i =0; i < NumbersOfPieces.QUEENS; i++) chessPieces[i+piecesFilled] = new ChessPiece(new ChessPosition(),color);
		piecesFilled += NumbersOfPieces.QUEENS;
		for(int i =0; i < NumbersOfPieces.KINGS; i++) chessPieces[i+piecesFilled] = new ChessPiece(new ChessPosition(),color);
		return chessPieces;
	}
	
	public ChessBoard(){
		whitePieces = fillHalfOfBoard(ChessPiece.Color.WHITE);
		blackPieces = fillHalfOfBoard(ChessPiece.Color.BLACK);
	}
}