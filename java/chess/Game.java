/**
 * Runs a chess game
 */
public class Game{
	public static void main(String[] args){
	    ChessBoard chessBoard = new ChessBoard();
		chessBoard.print();
		ChessPiece test = new Pawn(new ChessPosition(ChessPosition.Row._7,ChessPosition.Column.A),ChessPiece.Color.BLACK);
		ChessPosition testMove = new ChessPosition(ChessPosition.Row._5,ChessPosition.Column.A);
		chessBoard.move(test,testMove);
		chessBoard.print();
		System.out.println("NYI");
	}
}