package chess;
import java.util.Scanner;
import java.util.Vector;

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
		Game.handelStatus(chessBoard);
		return chessBoard;
	}

	/**
	 * Tests a series of pre-programmed moves that should work
	 */
	private static void testMovement(){
		ChessBoard chessBoard = new ChessBoard();
		chessBoard.print();
		{
			ChessPiece test = ChessPiece.makePiece(new ChessPosition(ChessPosition.Row._2, ChessPosition.Column.A),chessBoard.getPieces());
			ChessPosition testMove = new ChessPosition(ChessPosition.Row._4, ChessPosition.Column.A);
			testMove(chessBoard,test,testMove);//chessBoard.move(test, testMove);
		}
		{
			ChessPiece test = ChessPiece.makePiece(new ChessPosition(ChessPosition.Row._7, ChessPosition.Column.H),chessBoard.getPieces());
			ChessPosition testMove = new ChessPosition(ChessPosition.Row._6, ChessPosition.Column.H);
			testMove(chessBoard,test,testMove);//chessBoard.move(test, testMove);
		}
		{
			ChessPiece test = ChessPiece.makePiece(new ChessPosition(ChessPosition.Row._1,ChessPosition.Column.A),chessBoard.getPieces());
			ChessPosition testMove = new ChessPosition(ChessPosition.Row._3,ChessPosition.Column.A);
			testMove(chessBoard,test,testMove);//chessBoard.move(test, testMove);
		}
		{
			ChessPiece test = ChessPiece.makePiece(new ChessPosition(ChessPosition.Row._7, ChessPosition.Column.G),chessBoard.getPieces());
			ChessPosition testMove = new ChessPosition(ChessPosition.Row._6, ChessPosition.Column.G);
			testMove(chessBoard,test,testMove);//chessBoard.move(test, testMove);
		}
		{
			ChessPiece test = ChessPiece.makePiece(new ChessPosition(ChessPosition.Row._2, ChessPosition.Column.D), chessBoard.getPieces());
			ChessPosition testMove = new ChessPosition(ChessPosition.Row._3, ChessPosition.Column.D);
			testMove(chessBoard,test,testMove);//chessBoard.move(test, testMove);
		}
		{
			ChessPiece test = ChessPiece.makePiece(new ChessPosition(ChessPosition.Row._7, ChessPosition.Column.F),chessBoard.getPieces());
			ChessPosition testMove = new ChessPosition(ChessPosition.Row._6, ChessPosition.Column.F);
			testMove(chessBoard,test,testMove);//chessBoard.move(test, testMove);
		}
		{
			ChessPiece test = ChessPiece.makePiece(new ChessPosition(ChessPosition.Row._3,ChessPosition.Column.A),chessBoard.getPieces());
			ChessPosition testMove = new ChessPosition(ChessPosition.Row._3,ChessPosition.Column.C);
			testMove(chessBoard,test,testMove);//chessBoard.move(test, testMove);
		}
		{
			ChessPiece test = ChessPiece.makePiece(new ChessPosition(ChessPosition.Row._8,ChessPosition.Column.B),chessBoard.getPieces());
			ChessPosition testMove = new ChessPosition(ChessPosition.Row._6,ChessPosition.Column.C);
			testMove(chessBoard,test,testMove);//chessBoard.move(test, testMove);
		}
		{
			ChessPiece test = ChessPiece.makePiece(new ChessPosition(ChessPosition.Row._3, ChessPosition.Column.C), chessBoard.getPieces());
			ChessPosition testMove = new ChessPosition(ChessPosition.Row._6, ChessPosition.Column.C);
			testMove(chessBoard,test,testMove);//chessBoard.move(test, testMove);
		}
	}

	/**
	 * Checks the game's status to see if it should be continued
	 * @param board the board to check
	 * @return true if the game should be continued
	 */
	private static boolean continueGame(ChessBoard board){
		return board.getStatus() == ChessBoard.Status.IN_PROGRESS;
	}

	private static void handelStatus(ChessBoard board){
		switch(board.getStatus()){
			case BLACK_WIN:
				System.out.println("Game over. Black wins.");
				break;
			case WHITE_WIN:
				System.out.println("Game over. White wins.");
				break;
			case IN_PROGRESS:
				break;
			default:
				MySystem.nyi(MySystem.getFileName(),MySystem.getLineNumber());
		}
		if(!Game.continueGame(board)){
			System.out.println("Thanks for playing.");
			System.exit(1);
		}
	}

	/**
	 * Tests checkmate logic given a series of moves that result in checkmate
	 */
	private static void testCheckmate(){
		ChessBoard chessBoard = new ChessBoard();
		chessBoard.print();
		{
			ChessPiece test = ChessPiece.makePiece(new ChessPosition(ChessPosition.Row._2, ChessPosition.Column.F), chessBoard.getPieces());
			ChessPosition testMove = new ChessPosition(ChessPosition.Row._3, ChessPosition.Column.F);
			testMove(chessBoard,test,testMove);
		}
		{
			ChessPiece test = ChessPiece.makePiece(new ChessPosition(ChessPosition.Row._7, ChessPosition.Column.E), chessBoard.getPieces());
			ChessPosition testMove = new ChessPosition(ChessPosition.Row._5, ChessPosition.Column.E);
			testMove(chessBoard,test,testMove);
		}
		{
			ChessPiece test = ChessPiece.makePiece(new ChessPosition(ChessPosition.Row._2, ChessPosition.Column.G), chessBoard.getPieces());
			ChessPosition testMove = new ChessPosition(ChessPosition.Row._4, ChessPosition.Column.G);
			testMove(chessBoard,test,testMove);
		}
		{
			ChessPiece test = ChessPiece.makePiece(new ChessPosition(ChessPosition.Row._8, ChessPosition.Column.D), chessBoard.getPieces());
			ChessPosition testMove = new ChessPosition(ChessPosition.Row._4, ChessPosition.Column.H);
			testMove(chessBoard,test,testMove);
		}
	}

	/**
	 * Tests check logic given a series of moves that result in check
	 */
	private static void testCheck(){
		ChessBoard chessBoard = new ChessBoard();
		chessBoard.print();
		{
			ChessPiece test = ChessPiece.makePiece(new ChessPosition(ChessPosition.Row._2, ChessPosition.Column.E), chessBoard.getPieces());
			ChessPosition testMove = new ChessPosition(ChessPosition.Row._4, ChessPosition.Column.E);
			testMove(chessBoard,test,testMove);
		}
		{
			ChessPiece test = ChessPiece.makePiece(new ChessPosition(ChessPosition.Row._7, ChessPosition.Column.D), chessBoard.getPieces());
			ChessPosition testMove = new ChessPosition(ChessPosition.Row._5, ChessPosition.Column.D);
			testMove(chessBoard,test,testMove);
		}
		{
			ChessPiece test = ChessPiece.makePiece(new ChessPosition(ChessPosition.Row._1, ChessPosition.Column.E), chessBoard.getPieces());
			ChessPosition testMove = new ChessPosition(ChessPosition.Row._2, ChessPosition.Column.E);
			testMove(chessBoard,test,testMove);
		}
		{
			ChessPiece test = ChessPiece.makePiece(new ChessPosition(ChessPosition.Row._8, ChessPosition.Column.D), chessBoard.getPieces());
			ChessPosition testMove = new ChessPosition(ChessPosition.Row._6, ChessPosition.Column.D);
			testMove(chessBoard,test,testMove);
		}
		{
			ChessPiece test = ChessPiece.makePiece(new ChessPosition(ChessPosition.Row._2, ChessPosition.Column.E), chessBoard.getPieces());
			ChessPosition testMove = new ChessPosition(ChessPosition.Row._3, ChessPosition.Column.F);
			testMove(chessBoard,test,testMove);
		}
		{
			ChessPiece test = ChessPiece.makePiece(new ChessPosition(ChessPosition.Row._6, ChessPosition.Column.D), chessBoard.getPieces());
			ChessPosition testMove = new ChessPosition(ChessPosition.Row._4, ChessPosition.Column.F);
			testMove(chessBoard,test,testMove);
		}
		System.out.print("Available moves: " + chessBoard.getMoveablePositionsByPlayer().toString() + " out of these pieces " + chessBoard.getMoveablePiecesByPlayer().toString() + " to these locations ");
		try {
			System.out.print(chessBoard.getMoveablePiecesByPlayer().elementAt(0).getLimitedMoves().toString());
		} catch(ArrayIndexOutOfBoundsException e){
			System.out.print("[]");
		}
		System.out.print("\n");
	}


	/**
	 * Prompts users to input the necessary information and manages their chess game
	 */
	private static void play(){//TODO: finish
		ChessBoard chessBoard = new ChessBoard();
		while(Game.continueGame(chessBoard)){
			chessBoard.print();
			System.out.println("It is " + chessBoard.getPlayerTurn() + "'s turn.");
			System.out.println("Available pieces to move are: " + chessBoard.getMoveablePositionsByPlayer().toString());
			ChessPiece inputPiece = new ChessPiece();
			{
				boolean checkIfPieceIsValid = false;
				while(!checkIfPieceIsValid){
					System.out.print("Input a piece to move (the piece's location): ");
					inputPiece = ChessPiece.makePiece(Game.getInput(),chessBoard.getPieces());
					for(ChessPiece a : chessBoard.getMoveablePiecesByPlayer()){
						if(inputPiece.equals(a))checkIfPieceIsValid = true;
					}
				}
				inputPiece = chessBoard.getPieces().getPieceAt(chessBoard.getPieces().getIndexOf(inputPiece));
			}
			ChessPosition moveTarget = new ChessPosition();
			{
				boolean checkIfMoveIsValid = false;
				while(!checkIfMoveIsValid){
					System.out.print("Input the location to move that piece to (available positions are " +  inputPiece.getLimitedMoves().toString() + ": ");
					moveTarget = Game.getInput();
					for(ChessPosition a : inputPiece.getLimitedMoves()){
						if(moveTarget.equals(a)) checkIfMoveIsValid = true;
					}
				}
				MySystem.myAssert(checkIfMoveIsValid,MySystem.getFileName(),MySystem.getLineNumber());
			}
			chessBoard.move(inputPiece,moveTarget);
		}
		System.out.println("Game over");
	}

	/**
	 * Tests copy-by-value methods
	 */
	public static void testCopy(){
		{
			Pawn a = new Pawn(new ChessPosition(ChessPosition.Row._4, ChessPosition.Column.D), ChessPiece.Color.WHITE, new Vector<>(0),new Vector<>(0));

			ChessPiece b = new Pawn(a);
			a.move(new ChessPosition(ChessPosition.Row._5, ChessPosition.Column.D), new ChessPieces(0));
			System.out.println("a(" + a.toString() + ") b(" + b.toString() + ")");
			System.out.println("==:" + (a == b) + " .equals():" + b.equalsByType(a));
		}
		System.out.print("\n");
		{
			ChessPieces a = new ChessBoard().getPieces();
			ChessPieces b = ChessPieces.makePieces(a);
			System.out.println((a.getPieceAt(0)==b.getPieceAt(0)) + " " + (a.getPieceAt(0).equals(b.getPieceAt(0))));
			a.getPieceAt(0).move(new ChessPosition(ChessPosition.Row._4, ChessPosition.Column.A),a);
			System.out.println((a.getPieceAt(0)==b.getPieceAt(0)) + " " + (a.getPieceAt(0).equals(b.getPieceAt(0))));
		}
	}

	/**
	 * Tests a single move that is valid
	 */
	public static void testSingleMove(){
		ChessBoard chessBoard = new ChessBoard();
		{
			ChessPiece test = ChessPiece.makePiece(new ChessPosition(ChessPosition.Row._2, ChessPosition.Column.A), chessBoard.getPieces());
			ChessPosition testMove = new ChessPosition(ChessPosition.Row._4, ChessPosition.Column.A);
			testMove(chessBoard,test,testMove);
		}
	}

	/**
	 * Tests movements that lead to a pawn capture
	 */
	public static void testCapture(){
		ChessBoard chessBoard = new ChessBoard();
		{
			ChessPiece test = ChessPiece.makePiece(new ChessPosition(ChessPosition.Row._2, ChessPosition.Column.D), chessBoard.getPieces());
			ChessPosition testMove = new ChessPosition(ChessPosition.Row._4, ChessPosition.Column.D);
			testMove(chessBoard,test,testMove);
		}
		{
			ChessPiece test = ChessPiece.makePiece(new ChessPosition(ChessPosition.Row._7, ChessPosition.Column.E), chessBoard.getPieces());
			ChessPosition testMove = new ChessPosition(ChessPosition.Row._5, ChessPosition.Column.E);
			testMove(chessBoard,test,testMove);
		}
		{
			ChessPiece test = ChessPiece.makePiece(new ChessPosition(ChessPosition.Row._4, ChessPosition.Column.D), chessBoard.getPieces());
			ChessPosition testMove = new ChessPosition(ChessPosition.Row._5, ChessPosition.Column.E);
			testMove(chessBoard,test,testMove);
		}
	}

	/**
	 * The primary method that tests the chess system and instantiates a new game
	 * @param args user arguments (no effect)
	 */
	public static void main(String[] args){
		//These are the tests:
		//testMovement();
		//testInput();
		//testCopy();
		//testSingleMove();
		//testCapture();
		//testCheck();
		//testCheckmate();

		//This is the actual game
		play();
		MySystem.println("\n\n\nEND OF GAME FILE\n\n\n",MySystem.getFileName(),MySystem.getLineNumber());
	}
}