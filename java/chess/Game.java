package chess;
import java.util.Scanner;
/**
 * Runs a chess game
 */
public class Game{
	private static ChessPosition getInput(){
		Scanner scanner = new Scanner(System.in);
		String input = scanner.next();
		return  ChessPosition.toChessPosition(input);
	}
	/*private static void testInput(){
		System.out.print("Test input: ");
		ChessPosition chessPosition = getInput();
		System.out.println("Scanned in: " + chessPosition);
	}
	private static ChessBoard testMove(ChessBoard chessBoard,ChessPiece test, ChessPosition testMove){
		chessBoard.move(test, testMove);
		System.out.println("---------------------");
		chessBoard.print();
		return chessBoard;
	}
	private static void testMovement(){
		ChessBoard chessBoard = new ChessBoard();
		chessBoard.print();
		{
			ChessPiece test = new Pawn(new ChessPosition(ChessPosition.Row._2, ChessPosition.Column.A), ChessPiece.Color.WHITE);
			ChessPosition testMove = new ChessPosition(ChessPosition.Row._4, ChessPosition.Column.A);
			testMove(chessBoard,test,testMove);//chessBoard.move(test, testMove);
		}
		{
			ChessPiece test = new Pawn(new ChessPosition(ChessPosition.Row._7, ChessPosition.Column.H), ChessPiece.Color.BLACK);
			ChessPosition testMove = new ChessPosition(ChessPosition.Row._6, ChessPosition.Column.H);
			testMove(chessBoard,test,testMove);//chessBoard.move(test, testMove);
		}
		{
			ChessPiece test = new Rook(new ChessPosition(ChessPosition.Row._1,ChessPosition.Column.A),ChessPiece.Color.WHITE);
			ChessPosition testMove = new ChessPosition(ChessPosition.Row._3,ChessPosition.Column.A);
			testMove(chessBoard,test,testMove);//chessBoard.move(test, testMove);
		}
		{
			ChessPiece test = new Pawn(new ChessPosition(ChessPosition.Row._7, ChessPosition.Column.G), ChessPiece.Color.BLACK);
			ChessPosition testMove = new ChessPosition(ChessPosition.Row._6, ChessPosition.Column.G);
			testMove(chessBoard,test,testMove);//chessBoard.move(test, testMove);
		}
		{
			ChessPiece test = new Pawn(new ChessPosition(ChessPosition.Row._2, ChessPosition.Column.D), ChessPiece.Color.WHITE);
			ChessPosition testMove = new ChessPosition(ChessPosition.Row._3, ChessPosition.Column.D);
			testMove(chessBoard,test,testMove);//chessBoard.move(test, testMove);
		}
		{
			ChessPiece test = new Pawn(new ChessPosition(ChessPosition.Row._7, ChessPosition.Column.F), ChessPiece.Color.BLACK);
			ChessPosition testMove = new ChessPosition(ChessPosition.Row._6, ChessPosition.Column.F);
			testMove(chessBoard,test,testMove);//chessBoard.move(test, testMove);
		}
		{
			ChessPiece test = new Rook(new ChessPosition(ChessPosition.Row._3,ChessPosition.Column.A),ChessPiece.Color.WHITE);
			ChessPosition testMove = new ChessPosition(ChessPosition.Row._3,ChessPosition.Column.C);
			testMove(chessBoard,test,testMove);//chessBoard.move(test, testMove);
		}
		{
			ChessPiece test = new Knight(new ChessPosition(ChessPosition.Row._8,ChessPosition.Column.B),ChessPiece.Color.BLACK);
			ChessPosition testMove = new ChessPosition(ChessPosition.Row._6,ChessPosition.Column.C);
			testMove(chessBoard,test,testMove);//chessBoard.move(test, testMove);
		}
		{
			ChessPiece test = new Rook(new ChessPosition(ChessPosition.Row._3, ChessPosition.Column.C), ChessPiece.Color.WHITE);
			ChessPosition testMove = new ChessPosition(ChessPosition.Row._6, ChessPosition.Column.C);
			testMove(chessBoard,test,testMove);//chessBoard.move(test, testMove);
		}
	}*/

	private static void play(){//TODO: take in user inputs and stuff
		ChessBoard chessBoard = new ChessBoard();
		final int ALLOWED_TURNS = 2;//Limit length of game for testing
		while(!chessBoard.checkIfGameOver()){
			chessBoard.print();
			System.out.println("It is " + chessBoard.getPlayerTurn() + "'s turn.");
			System.out.println("Available pieces to move are: " + chessBoard.getMoveablePositionsByPlayer().toString());
			System.out.print("Input a piece to move (the piece's location): ");
			ChessPiece chessPiece = ChessPiece.makePiece(Game.getInput(),chessBoard.getPlayerTurn(),chessBoard.getPieces());
			{
				boolean checkIfPieceIsValid = false;
				for(ChessPiece a : chessBoard.getMoveablePiecesByPlayer()){
					if(chessPiece.equals(a))checkIfPieceIsValid = true;
				}
				MySystem.myAssert(checkIfPieceIsValid,MySystem.getFileName(),MySystem.getLineNumber());
			}
			System.out.print("Input the location to move that piece to (available positions are " + chessPiece.getNewPositions(chessBoard.getPieces())+ ": ");
			ChessPosition chessPosition = Game.getInput();
			{
				boolean checkIfMoveIsValid = false;
				for(ChessPosition a : chessPiece.getNewPositions(chessBoard.getPieces())){
					if(chessPosition.equals(a))checkIfMoveIsValid = true;
				}
				MySystem.myAssert(checkIfMoveIsValid,MySystem.getFileName(),MySystem.getLineNumber());
			}
			chessBoard.move(chessPiece,chessPosition);
		}
		System.out.println("Game over");
	}

	public static void main(String[] args){
		//testMovement();
		//testInput();
		play();
		System.out.println("END OF GAME");//used to move the nyi to the bottom of the print outs
		MySystem.nyi(MySystem.getFileName(),MySystem.getLineNumber());
	}
}