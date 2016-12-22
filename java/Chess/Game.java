package Chess;

import java.util.Scanner;
import java.util.TreeSet;
import java.util.Iterator;
import MySystem.*;

/**
 * Runs a Chess game
 */
public class Game{
	/**
	 * Prompts the user to enter in a Chess position
	 * @return the Chess position entered by the user
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

	private static ChessPiece.Move getValidMove(ChessBoard chessBoard){
		ChessPiece inputPiece = new ChessPiece();
		{
			boolean checkIfPieceIsValid = false;
			while(!checkIfPieceIsValid){
				System.out.print("Input a piece to move (the piece's location): ");
				inputPiece = chessBoard.getPieces().getPieceAt(Game.getInput());
				for(ChessPiece a : chessBoard.getMoveablePiecesByPlayer()){
					if(inputPiece.equals(a))checkIfPieceIsValid = true;
				}
				if(!checkIfPieceIsValid) System.out.print("Not a valid move. ");
			}
		}
		ChessPosition moveTarget = new ChessPosition();
		{
			boolean checkIfMoveIsValid = false;
			while(!checkIfMoveIsValid){
				System.out.print("Input the location to move that piece to (available positions are " + inputPiece.getLimitedMoves().toString() + ": ");
				moveTarget = Game.getInput();
				for(ChessPosition a : inputPiece.getLimitedMoves()){
					if(moveTarget.equals(a)) checkIfMoveIsValid = true;
				}
				if(!checkIfMoveIsValid) System.out.print("Not a valid move. ");
			}
		}
		return new ChessPiece.Move(inputPiece,moveTarget);
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
	 * Updates and prints a Chess board given some test values
	 * @param chessBoard the board to update
	 * @param test the piece to move
	 * @param testMove the position to move the piece to
	 */
	private static void testMove(ChessBoard chessBoard,ChessPiece test, ChessPosition testMove, boolean exitOnCompletion){
		chessBoard.move(test, testMove);
		System.out.println("---------------------");
		chessBoard.print();
		Game.handelStatus(chessBoard,exitOnCompletion);
	}

	/**
	 * Tests a series of pre-programmed moves that should work
	 */
	private static void testMovement(){
		final boolean EXIT_ON_COMPLETION = true;
		ChessBoard chessBoard = new ChessBoard();
		chessBoard.print();
		{
			ChessPiece test = chessBoard.getPieces().getPieceAt(new ChessPosition(ChessPosition.Row._2, ChessPosition.Column.A));
			ChessPosition testMove = new ChessPosition(ChessPosition.Row._4, ChessPosition.Column.A);
			testMove(chessBoard,test,testMove,EXIT_ON_COMPLETION );//chessBoard.move(test, testMove);
		}
		{
			ChessPiece test = chessBoard.getPieces().getPieceAt(new ChessPosition(ChessPosition.Row._7, ChessPosition.Column.H));
			ChessPosition testMove = new ChessPosition(ChessPosition.Row._6, ChessPosition.Column.H);
			testMove(chessBoard,test,testMove,EXIT_ON_COMPLETION );//chessBoard.move(test, testMove);
		}
		{
			ChessPiece test = chessBoard.getPieces().getPieceAt(new ChessPosition(ChessPosition.Row._1,ChessPosition.Column.A));
			ChessPosition testMove = new ChessPosition(ChessPosition.Row._3,ChessPosition.Column.A);
			testMove(chessBoard,test,testMove,EXIT_ON_COMPLETION );
		}
		{
			ChessPiece test = chessBoard.getPieces().getPieceAt(new ChessPosition(ChessPosition.Row._7, ChessPosition.Column.G));
			ChessPosition testMove = new ChessPosition(ChessPosition.Row._6, ChessPosition.Column.G);
			testMove(chessBoard,test,testMove,EXIT_ON_COMPLETION);
		}
		{
			ChessPiece test = chessBoard.getPieces().getPieceAt(new ChessPosition(ChessPosition.Row._2, ChessPosition.Column.D));
			ChessPosition testMove = new ChessPosition(ChessPosition.Row._3, ChessPosition.Column.D);
			testMove(chessBoard,test,testMove,EXIT_ON_COMPLETION);
		}
		{
			ChessPiece test = chessBoard.getPieces().getPieceAt(new ChessPosition(ChessPosition.Row._7, ChessPosition.Column.F));
			ChessPosition testMove = new ChessPosition(ChessPosition.Row._6, ChessPosition.Column.F);
			testMove(chessBoard,test,testMove,EXIT_ON_COMPLETION );
		}
		{
			ChessPiece test = chessBoard.getPieces().getPieceAt(new ChessPosition(ChessPosition.Row._3,ChessPosition.Column.A));
			ChessPosition testMove = new ChessPosition(ChessPosition.Row._3,ChessPosition.Column.C);
			testMove(chessBoard,test,testMove,EXIT_ON_COMPLETION);
		}
		{
			ChessPiece test = chessBoard.getPieces().getPieceAt(new ChessPosition(ChessPosition.Row._8,ChessPosition.Column.B));
			ChessPosition testMove = new ChessPosition(ChessPosition.Row._6,ChessPosition.Column.C);
			testMove(chessBoard,test,testMove,EXIT_ON_COMPLETION);
		}
		{
			ChessPiece test = chessBoard.getPieces().getPieceAt(new ChessPosition(ChessPosition.Row._3, ChessPosition.Column.C));
			ChessPosition testMove = new ChessPosition(ChessPosition.Row._6, ChessPosition.Column.C);
			testMove(chessBoard,test,testMove,EXIT_ON_COMPLETION);
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

	private static void handelStatus(ChessBoard board, boolean exitOnCompletion){
		switch(board.getStatus()){
			case BLACK_WIN:
				System.out.println("Game over. Black wins.");
				break;
			case WHITE_WIN:
				System.out.println("Game over. White wins.");
				break;
			case IN_PROGRESS:
				break;
			case DRAW:
				System.out.println("Game over. Draw.");
				break;
			default:
				MySystem.nyi(MySystem.getFileName(), MySystem.getLineNumber());
		}
		if(!Game.continueGame(board) && exitOnCompletion){
			System.out.println("Thanks for playing.");
			System.exit(1);
		}
	}

	/**
	 * Tests checkmate logic given a series of moves that result in checkmate
	 */
	private static void testCheckmate(){
		final boolean EXIT_ON_COMPLETION = false;
		ChessBoard chessBoard = new ChessBoard();
		chessBoard.print();
		{
			ChessPiece test = chessBoard.getPieces().getPieceAt(new ChessPosition(ChessPosition.Row._2, ChessPosition.Column.F));
			ChessPosition testMove = new ChessPosition(ChessPosition.Row._3, ChessPosition.Column.F);
			testMove(chessBoard, test, testMove,EXIT_ON_COMPLETION);
		}
		{
			ChessPiece test = chessBoard.getPieces().getPieceAt(new ChessPosition(ChessPosition.Row._7, ChessPosition.Column.E));
			ChessPosition testMove = new ChessPosition(ChessPosition.Row._5, ChessPosition.Column.E);
			testMove(chessBoard, test, testMove,EXIT_ON_COMPLETION );
		}
		{
			ChessPiece test = chessBoard.getPieces().getPieceAt(new ChessPosition(ChessPosition.Row._2, ChessPosition.Column.G));
			ChessPosition testMove = new ChessPosition(ChessPosition.Row._4, ChessPosition.Column.G);
			testMove(chessBoard, test, testMove,EXIT_ON_COMPLETION );
		}
		{
			ChessPiece test = chessBoard.getPieces().getPieceAt(new ChessPosition(ChessPosition.Row._8, ChessPosition.Column.D));
			ChessPosition testMove = new ChessPosition(ChessPosition.Row._4, ChessPosition.Column.H);
			testMove(chessBoard, test, testMove,EXIT_ON_COMPLETION );
		}
		System.out.println("Finishing with status " + chessBoard.getStatus().toString() + " Expected: BLACK_WIN");
		System.out.println("White king check: " + chessBoard.getPieces().getKing(ChessPiece.Color.WHITE).getCheck() + " expected: true  White king checkmate: " + chessBoard.getPieces().getKing(ChessPiece.Color.WHITE).getCheckmate() + " expected: true");
		if(!chessBoard.getStatus().equals(ChessBoard.Status.BLACK_WIN)){
			MySystem.error("Test failed: did not end in a black win as expected", MySystem.getFileName(), MySystem.getLineNumber());
		}
	}

	/**
	 * Tests check logic given a series of moves that result in check
	 */
	private static void testCheck(){
		final boolean EXIT_ON_COMPLETION = false;
		ChessBoard chessBoard = new ChessBoard();
		chessBoard.print();
		{
			ChessPiece test = chessBoard.getPieces().getPieceAt(new ChessPosition(ChessPosition.Row._2, ChessPosition.Column.E));
			ChessPosition testMove = new ChessPosition(ChessPosition.Row._4, ChessPosition.Column.E);
			testMove(chessBoard,test,testMove,EXIT_ON_COMPLETION );
		}
		{
			ChessPiece test = chessBoard.getPieces().getPieceAt(new ChessPosition(ChessPosition.Row._7, ChessPosition.Column.D));
			ChessPosition testMove = new ChessPosition(ChessPosition.Row._5, ChessPosition.Column.D);
			testMove(chessBoard,test,testMove,EXIT_ON_COMPLETION );
		}
		{
			ChessPiece test = chessBoard.getPieces().getPieceAt(new ChessPosition(ChessPosition.Row._1, ChessPosition.Column.E));
			ChessPosition testMove = new ChessPosition(ChessPosition.Row._2, ChessPosition.Column.E);
			testMove(chessBoard,test,testMove,EXIT_ON_COMPLETION );
		}
		{
			ChessPiece test = chessBoard.getPieces().getPieceAt(new ChessPosition(ChessPosition.Row._8, ChessPosition.Column.D));
			ChessPosition testMove = new ChessPosition(ChessPosition.Row._6, ChessPosition.Column.D);
			testMove(chessBoard,test,testMove,EXIT_ON_COMPLETION );
		}
		{
			ChessPiece test = chessBoard.getPieces().getPieceAt(new ChessPosition(ChessPosition.Row._2, ChessPosition.Column.E));
			ChessPosition testMove = new ChessPosition(ChessPosition.Row._3, ChessPosition.Column.F);
			testMove(chessBoard,test,testMove,EXIT_ON_COMPLETION );
		}
		{
			ChessPiece test = chessBoard.getPieces().getPieceAt(new ChessPosition(ChessPosition.Row._6, ChessPosition.Column.D));
			ChessPosition testMove = new ChessPosition(ChessPosition.Row._4, ChessPosition.Column.F);
			testMove(chessBoard,test,testMove,EXIT_ON_COMPLETION );
		}
		System.out.println("Finishing with status " + chessBoard.getStatus().toString());

		System.out.print("Positions of pieces that can be moved: " + chessBoard.getMoveablePositionsByPlayer().toString() + " first piece in that array can be moved to ");
		Iterator<ChessPiece> firstPieceIt = chessBoard.getMoveablePiecesByPlayer().iterator();
		ChessPiece firstPiece = firstPieceIt.next();
		System.out.print(firstPiece.getLimitedMoves().toString() + "\n");

		final ChessPosition EXPECTED_MOVEABLE_POS = ChessPosition.toChessPosition("F3");
		final TreeSet<ChessPosition> EXPECTED_POSSIBLE_MOVES = new TreeSet<>();
		EXPECTED_POSSIBLE_MOVES.add(ChessPosition.toChessPosition("E2"));
		EXPECTED_POSSIBLE_MOVES.add(ChessPosition.toChessPosition("F4"));

		System.out.println("Expected: Position (of first piece, but there's only one) " + EXPECTED_MOVEABLE_POS.toString() + " can be moved to " + EXPECTED_POSSIBLE_MOVES.toString());
		System.out.println("White king check: " + chessBoard.getPieces().getKing(ChessPiece.Color.WHITE).getCheck() + " expected: true");

		if(!chessBoard.getPieces().getKing(ChessPiece.Color.WHITE).getCheck()){
			MySystem.error("Test failed: did not finish in check as expected",MySystem.getFileName(),MySystem.getLineNumber());
		}
		if(!EXPECTED_MOVEABLE_POS.equals(firstPiece.getPosition())){
			MySystem.error("Test failed: the move-able pieces' positions did not match the expected one",MySystem.getFileName(),MySystem.getLineNumber());
		}
		if(!MySystem.treeSetEquals(EXPECTED_POSSIBLE_MOVES,firstPiece.getLimitedMoves())){
			MySystem.error("Test failed: calculated limited moves did not match expected ones",MySystem.getFileName(),MySystem.getLineNumber());
		}
	}

	/**
	 * Prompts users to input the necessary information and manages their Chess game
	 */
	private static void play(){//TODO: finish
		ChessBoard chessBoard = new ChessBoard();
		while(Game.continueGame(chessBoard)){
			chessBoard.print();
			System.out.println("It is " + chessBoard.getPlayerTurn() + "'s turn.");
			System.out.println("Available pieces to move are: " + chessBoard.getMoveablePositionsByPlayer().toString());
			chessBoard.move(getValidMove(chessBoard));
		}
		System.out.println("Game over");
	}

	/**
	 * Tests copy-by-value methods
	 */
	public static void testCopy(){
		final boolean USE_LIMITED = false;//it doesn't matter
		{
			Pawn a = new Pawn(new ChessPosition(ChessPosition.Row._4, ChessPosition.Column.D), ChessPiece.Color.WHITE);
			a.updatePossibleMoves(new ChessPieces());
			ChessPiece b = new Pawn(a);
			b.updatePossibleMoves(new ChessPieces());
			a.move(new ChessPosition(ChessPosition.Row._5, ChessPosition.Column.D),USE_LIMITED);
			System.out.println("a(" + a.toString() + ") b(" + b.toString() + ")");
			System.out.println("==:" + (a == b) + " .equals():" + b.equals(a));
		}
		System.out.print("\n");
		{
			ChessPieces a = new ChessBoard().getPieces();
			ChessPieces b = ChessPieces.makePieces(a);
			System.out.println((a.getPieceAt(0)==b.getPieceAt(0)) + " " + (a.getPieceAt(0).equals(b.getPieceAt(0))));
			a.getPieceAt(0).move(new ChessPosition(ChessPosition.Row._4, ChessPosition.Column.A),USE_LIMITED);
			System.out.println((a.getPieceAt(0)==b.getPieceAt(0)) + " " + (a.getPieceAt(0).equals(b.getPieceAt(0))));
		}
	}

	/**
	 * Tests a single move that is valid
	 */
	public static void testSingleMove(){
		ChessBoard chessBoard = new ChessBoard();
		final boolean EXIT_ON_COMPLETION = true;
		{
			ChessPiece test = chessBoard.getPieces().getPieceAt(new ChessPosition(ChessPosition.Row._2, ChessPosition.Column.A));
			ChessPosition testMove = new ChessPosition(ChessPosition.Row._4, ChessPosition.Column.A);
			testMove(chessBoard,test,testMove,EXIT_ON_COMPLETION );
		}
	}

	/**
	 * Tests movements that lead to a pawn capture
	 */
	public static void testCapture(){
		ChessBoard chessBoard = new ChessBoard();
		final boolean EXIT_ON_COMPLETION = true;
		{
			ChessPiece test = chessBoard.getPieces().getPieceAt(new ChessPosition(ChessPosition.Row._2, ChessPosition.Column.D));
			ChessPosition testMove = new ChessPosition(ChessPosition.Row._4, ChessPosition.Column.D);
			testMove(chessBoard,test,testMove,EXIT_ON_COMPLETION );
		}
		{
			ChessPiece test = chessBoard.getPieces().getPieceAt(new ChessPosition(ChessPosition.Row._7, ChessPosition.Column.E));
			ChessPosition testMove = new ChessPosition(ChessPosition.Row._5, ChessPosition.Column.E);
			testMove(chessBoard,test,testMove,EXIT_ON_COMPLETION );
		}
		{
			ChessPiece test = chessBoard.getPieces().getPieceAt(new ChessPosition(ChessPosition.Row._4, ChessPosition.Column.D));
			ChessPosition testMove = new ChessPosition(ChessPosition.Row._5, ChessPosition.Column.E);
			testMove(chessBoard,test,testMove,EXIT_ON_COMPLETION );
		}
		System.out.print("Captured pieces: ");
		for(ChessPiece piece: chessBoard.getPieces().toArray()){
			if(!piece.getAlive()) System.out.print("[" + piece.getSymbol() + ", " + piece.toString() + "]");
		}
		System.out.print("\n");
	}

	public static void playAgainstAI(){
		//TODO: add in option to choose color
		ChessBoard chessBoard = new ChessBoard();
		final ChessPiece.Color PLAYER_COLOR = ChessPiece.Color.WHITE;
		Opponent ben = new Opponent(ChessPiece.Color.not(PLAYER_COLOR));

		while(Game.continueGame(chessBoard)){
			{
				chessBoard.print();
				System.out.println("It is " + chessBoard.getPlayerTurn() + "'s turn.");
				System.out.println("Available pieces to move are: " + chessBoard.getMoveablePositionsByPlayer().toString());
				chessBoard.move(getValidMove(chessBoard));
			}
			if(Game.continueGame(chessBoard)){
				chessBoard.print();
				System.out.println("Opponent's turn.");
				ChessPiece.Move move = ben.getRandomMove(chessBoard.getPieces());
				System.out.println("Moving " + chessBoard.getPieces().getPieceAt(move.getStart().getPosition()).getType().toString() + " from " + move.getStart().toString() + " to " + move.getTarget().toString() + ".");
				chessBoard.move(move);
			}

		}
		System.out.println("Game over");
	}

	public static void aiAgainstAI(){
		ChessBoard chessBoard = new ChessBoard();
		final ChessPiece.Color AI_ONE_COLOR = ChessPiece.Color.WHITE;
		Opponent ben = new Opponent(AI_ONE_COLOR );
		Opponent logan = new Opponent(ChessPiece.Color.not(AI_ONE_COLOR));

		while(Game.continueGame(chessBoard)){
			{
				chessBoard.print();
				System.out.println("-----------------------\nBen's turn.");
				ChessPiece.Move move = ben.getRandomMove(chessBoard.getPieces());
				System.out.println("Moving " + chessBoard.getPieces().getPieceAt(move.getStart().getPosition()).getType().toString() + " from " + move.getStart().toString() + " to " + move.getTarget().toString() + ".");
				chessBoard.move(move);
			}
			{
				chessBoard.print();
				System.out.println("-----------------------\nLogan's turn.");
				ChessPiece.Move move = logan.getRandomMove(chessBoard.getPieces());
				System.out.println("Moving " + chessBoard.getPieces().getPieceAt(move.getStart().getPosition()).getType().toString() + " from " + move.getStart().toString() + " to " + move.getTarget().toString() + ".");
				chessBoard.move(move);
			}
		}
		System.out.println("Game over");
	}

	/**
	 * The primary method that tests the Chess system and instantiates a new game
	 * @param args user arguments (no effect)
	 */
	public static void main(String[] args){
		System.out.println("Starting Game");
		//These are the tests:
		//testMovement();
		//testInput();
		//testCopy();
		//testSingleMove();
		//testCapture();
		//testCheck();
		testCheckmate();

		//This is the actual game
		//play();
		//playAgainstAI();
		//aiAgainstAI();
		MySystem.println("\n\n\nEND OF GAME FILE", MySystem.getFileName(), MySystem.getLineNumber());
	}
}
