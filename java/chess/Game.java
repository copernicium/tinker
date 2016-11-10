package chess;
import java.util.Scanner;
/**
 * Runs a chess game
 */
public class Game{
	private static ChessPosition getInput(){
		Scanner scanner = new Scanner(System.in);
		String input = scanner.next();
		if(input.contains("quit") || input.contains("exit")){
			System.out.println("Quitting from user input.");
			System.exit(0);
		}
		while(!ChessPosition.testConversion(input)){
			System.out.println("Please input a valid position: ");
			input = scanner.next();
		}
		return  ChessPosition.toChessPosition(input);
	}
	private static void testInput(){
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
	}

	private static void checkChecks(final ChessBoard CHESS_BOARD){
		King whiteKing = new King(CHESS_BOARD.getPieces()[ChessBoard.find(ChessPiece.Type.KING, ChessPiece.Color.WHITE,CHESS_BOARD.getPieces())]);
		King blackKing = new King(CHESS_BOARD.getPieces()[ChessBoard.find(ChessPiece.Type.KING, ChessPiece.Color.BLACK,CHESS_BOARD.getPieces())]);

		whiteKing.update(CHESS_BOARD.getPieces());
		blackKing.update(CHESS_BOARD.getPieces());

		if(whiteKing.getCheckMate()) System.out.println("Game over. " + ChessPiece.Color.not(whiteKing.getColor()).toString() + " wins.");
		else if(blackKing.getCheckMate()) System.out.println("Game over. " + ChessPiece.Color.not(blackKing.getColor()).toString() + " wins.");
		//System.out.println("White( check:" + whiteKing.getCheck() + " checkmate:" + whiteKing.getCheckMate() + ") Black( check:" + blackKing.getCheck() + " checkmate:" + blackKing.getCheckMate() + ")");
	}

	private static void testCheckmate(){
		ChessBoard chessBoard = new ChessBoard();
		chessBoard.print();
		Game.checkChecks(chessBoard);
		{
			ChessPiece test = ChessPiece.makePiece(new ChessPosition(ChessPosition.Row._2, ChessPosition.Column.F), ChessPiece.Color.WHITE, chessBoard.getPieces());
			ChessPosition testMove = new ChessPosition(ChessPosition.Row._3, ChessPosition.Column.F);
			testMove(chessBoard,test,testMove);
		}
		Game.checkChecks(chessBoard);
		{
			ChessPiece test = ChessPiece.makePiece(new ChessPosition(ChessPosition.Row._7, ChessPosition.Column.E), ChessPiece.Color.BLACK, chessBoard.getPieces());
			ChessPosition testMove = new ChessPosition(ChessPosition.Row._5, ChessPosition.Column.E);
			testMove(chessBoard,test,testMove);
		}
		Game.checkChecks(chessBoard);
		{
			ChessPiece test = ChessPiece.makePiece(new ChessPosition(ChessPosition.Row._2, ChessPosition.Column.G), ChessPiece.Color.WHITE, chessBoard.getPieces());
			ChessPosition testMove = new ChessPosition(ChessPosition.Row._4, ChessPosition.Column.G);
			testMove(chessBoard,test,testMove);
		}
		Game.checkChecks(chessBoard);
		{
			ChessPiece test = ChessPiece.makePiece(new ChessPosition(ChessPosition.Row._8, ChessPosition.Column.D), ChessPiece.Color.BLACK, chessBoard.getPieces());
			ChessPosition testMove = new ChessPosition(ChessPosition.Row._4, ChessPosition.Column.H);
			testMove(chessBoard,test,testMove);
		}
		Game.checkChecks(chessBoard);
	}

	private static void play(){//TODO: take in user inputs and stuff
		ChessBoard chessBoard = new ChessBoard();
		while(!chessBoard.getGameOver()){
			chessBoard.print();
			System.out.println("It is " + chessBoard.getPlayerTurn() + "'s turn.");
			System.out.println("Available pieces to move are: " + chessBoard.getMoveablePositionsByPlayer().toString());
			System.out.print("Input a piece to move (the piece's location): ");
			ChessPiece chessPiece = ChessPiece.makePiece(Game.getInput(),chessBoard.getPlayerTurn(),chessBoard.getPieces());
			{//TODO: make user input valid things
				boolean checkIfPieceIsValid = false;
				for(ChessPiece a : chessBoard.getMoveablePiecesByPlayer()){
					if(chessPiece.equals(a))checkIfPieceIsValid = true;
				}
				//MySystem.myAssert(checkIfPieceIsValid,MySystem.getFileName(),MySystem.getLineNumber());
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

	public static void testCopy(){
		{
			Pawn a = new Pawn(new ChessPosition(ChessPosition.Row._4, ChessPosition.Column.D), ChessPiece.Color.WHITE);

			ChessPiece b = new Pawn(a);
			a.move(new ChessPosition(ChessPosition.Row._5, ChessPosition.Column.D), new ChessPiece[0]);
			System.out.println("a(" + a.toString() + ") b(" + b.toString() + ")");
			System.out.println("==:" + (a == b) + " .equals():" + b.equalsByType(a));
		}
		System.out.print("\n");
		{
			ChessPiece[] a = new ChessBoard().getPieces();
			ChessPiece[] b = ChessPiece.makePieces(a);
			System.out.println((a[0]==b[0]) + " " + (a[0].equals(b[0])));
			a[0].move(new ChessPosition(ChessPosition.Row._4, ChessPosition.Column.A),a);
			System.out.println((a[0]==b[0]) + " " + (a[0].equals(b[0])));
		}
	}

	public static void testSingleMove(){
		ChessBoard chessBoard = new ChessBoard();
		{
			ChessPiece test = ChessPiece.makePiece(new ChessPosition(ChessPosition.Row._2, ChessPosition.Column.A), ChessPiece.Color.WHITE, chessBoard.getPieces());
			ChessPosition testMove = new ChessPosition(ChessPosition.Row._4, ChessPosition.Column.A);
			testMove(chessBoard,test,testMove);
		}
	}

	public static void main(String[] args){
		//testMovement();
		//testInput();
		//testCheckmate();
		//testSingleMove();
		//testCopy();
		play();
		System.out.println("END OF GAME");//used to move the nyi to the bottom of the print outs
		MySystem.nyi(MySystem.getFileName(),MySystem.getLineNumber());
	}
}