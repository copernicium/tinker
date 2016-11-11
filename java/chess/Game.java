package chess;
import java.util.Scanner;
/**
 * Runs a chess game
 */
public class Game{
	/**
	 * Prompts the user to enter in a chess position
	 * @return the chess position entered by the user
	 */
	private static ChessPosition getInput(){
		Scanner scanner = new Scanner(System.in);
		String input = scanner.next();
		while(!ChessPosition.testConversion(input)){
			if(input.contains("quit") || input.contains("exit")){
				System.out.println("Quitting from user input.");
				System.exit(0);
			}
			System.out.print("Please input a valid position or \"exit\" to exit: ");
			input = scanner.next();
		}
		return  ChessPosition.toChessPosition(input);
	}

	/**
	 * Tests that user input and string to ChessPosition conversion is working
	 */
	private static void testInput(){
		System.out.print("Test input: ");
		ChessPosition chessPosition = getInput();
		System.out.println("Scanned in: " + chessPosition);
	}

	/**
	 * Updates and prints a chess board given some test values
	 * @param chessBoard the board to update
	 * @param test the piece to move
	 * @param testMove the position to move the piece to
	 * @return the updated chess board
	 */
	private static ChessBoard testMove(ChessBoard chessBoard,ChessPiece test, ChessPosition testMove){
		chessBoard.move(test, testMove);
		System.out.println("---------------------");
		chessBoard.print();
		return chessBoard;
	}

	/**
	 * Tests a series of pre-programmed moves that should work
	 */
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

	/**
	 * Tests to see if the game has ended due to checkmate and prints the appropriate information if it has
	 * @param CHESS_BOARD the board to check for checkmate
	 */
	private static void checkChecks(final ChessBoard CHESS_BOARD){
		King whiteKing = new King(CHESS_BOARD.getPieces()[ChessBoard.find(ChessPiece.Type.KING, ChessPiece.Color.WHITE,CHESS_BOARD.getPieces())]);
		King blackKing = new King(CHESS_BOARD.getPieces()[ChessBoard.find(ChessPiece.Type.KING, ChessPiece.Color.BLACK,CHESS_BOARD.getPieces())]);

		whiteKing.update(CHESS_BOARD.getPieces());
		blackKing.update(CHESS_BOARD.getPieces());

		if(whiteKing.getCheckmate()) System.out.println("Game over. " + ChessPiece.Color.not(whiteKing.getColor()).toString() + " wins.");
		else if(blackKing.getCheckmate()) System.out.println("Game over. " + ChessPiece.Color.not(blackKing.getColor()).toString() + " wins.");
		//System.out.println("White( check:" + whiteKing.getCheck() + " checkmate:" + whiteKing.getCheckMate() + ") Black( check:" + blackKing.getCheck() + " checkmate:" + blackKing.getCheckMate() + ")");
	}

	/**
	 * Tests checkmate logic given a series of moves that result in checkmate
	 */
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
		Game.checkChecks(chessBoard);//white loses
	}

	/**
	 * Prompts users to input the necessary information and manages their chess game
	 */
	private static void play(){//TODO: finish
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

	/**
	 * Tests copy-by-value methods
	 */
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

	/**
	 * Tests a single move that is valid
	 */
	public static void testSingleMove(){
		ChessBoard chessBoard = new ChessBoard();
		{
			ChessPiece test = ChessPiece.makePiece(new ChessPosition(ChessPosition.Row._2, ChessPosition.Column.A), ChessPiece.Color.WHITE, chessBoard.getPieces());
			ChessPosition testMove = new ChessPosition(ChessPosition.Row._4, ChessPosition.Column.A);
			testMove(chessBoard,test,testMove);
		}
	}

	/**
	 * The primary method that tests the chess system and instantiates a new game
	 * @param args user arguments (no effect)
	 */
	public static void main(String[] args){
		//tests
		//testMovement();
		//testInput();
		testCheckmate();
		//testSingleMove();
		//testCopy();

		//the actual game
		//play();
		MySystem.println("\n\n\nGAME OVER\n\n\n",MySystem.getFileName(),MySystem.getLineNumber());
	}
}