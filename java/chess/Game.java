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
			ChessPiece test = ChessPiece.makePiece(new ChessPosition(ChessPosition.Row._2, ChessPosition.Column.F), ChessPiece.Color.WHITE, chessBoard.getPieces());
			ChessPosition testMove = new ChessPosition(ChessPosition.Row._3, ChessPosition.Column.F);
			testMove(chessBoard,test,testMove);
		}
		{
			ChessPiece test = ChessPiece.makePiece(new ChessPosition(ChessPosition.Row._7, ChessPosition.Column.E), ChessPiece.Color.BLACK, chessBoard.getPieces());
			ChessPosition testMove = new ChessPosition(ChessPosition.Row._5, ChessPosition.Column.E);
			testMove(chessBoard,test,testMove);
		}
		{
			ChessPiece test = ChessPiece.makePiece(new ChessPosition(ChessPosition.Row._2, ChessPosition.Column.G), ChessPiece.Color.WHITE, chessBoard.getPieces());
			ChessPosition testMove = new ChessPosition(ChessPosition.Row._4, ChessPosition.Column.G);
			testMove(chessBoard,test,testMove);
		}
		{
			ChessPiece test = ChessPiece.makePiece(new ChessPosition(ChessPosition.Row._8, ChessPosition.Column.D), ChessPiece.Color.BLACK, chessBoard.getPieces());
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
			ChessPiece test = ChessPiece.makePiece(new ChessPosition(ChessPosition.Row._2, ChessPosition.Column.F), ChessPiece.Color.WHITE, chessBoard.getPieces());
			ChessPosition testMove = new ChessPosition(ChessPosition.Row._3, ChessPosition.Column.F);
			testMove(chessBoard,test,testMove);
		}
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
			System.out.print("Input a piece to move (the piece's location): ");
			ChessPiece chessPiece = ChessPiece.makePiece(Game.getInput(),chessBoard.getPlayerTurn(),chessBoard.getPieces());
			{//TODO: make user input valid things
				boolean checkIfPieceIsValid = false;
				for(ChessPiece a : chessBoard.getMoveablePiecesByPlayer()){
					if(chessPiece.equals(a))checkIfPieceIsValid = true;
				}
				//MySystem.myAssert(checkIfPieceIsValid,MySystem.getFileName(),MySystem.getLineNumber());
			}
			System.out.print("Input the location to move that piece to (available positions are " + ChessPiece.limitMovesToLeavingCheck(chessPiece,chessBoard.getPieces())+ ": ");
			ChessPosition chessPosition = Game.getInput();
			{
				boolean checkIfMoveIsValid = false;
				for(ChessPosition a : ChessPiece.limitMovesToLeavingCheck(chessPiece,chessBoard.getPieces())){
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
			ChessPiece test = ChessPiece.makePiece(new ChessPosition(ChessPosition.Row._2, ChessPosition.Column.A), ChessPiece.Color.WHITE, chessBoard.getPieces());
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
			ChessPiece test = ChessPiece.makePiece(new ChessPosition(ChessPosition.Row._2, ChessPosition.Column.D), ChessPiece.Color.WHITE, chessBoard.getPieces());
			ChessPosition testMove = new ChessPosition(ChessPosition.Row._4, ChessPosition.Column.D);
			testMove(chessBoard,test,testMove);
		}
		{
			ChessPiece test = ChessPiece.makePiece(new ChessPosition(ChessPosition.Row._7, ChessPosition.Column.E), ChessPiece.Color.BLACK, chessBoard.getPieces());
			ChessPosition testMove = new ChessPosition(ChessPosition.Row._5, ChessPosition.Column.E);
			testMove(chessBoard,test,testMove);
		}
		{
			ChessPiece test = ChessPiece.makePiece(new ChessPosition(ChessPosition.Row._4, ChessPosition.Column.D), ChessPiece.Color.WHITE, chessBoard.getPieces());
			ChessPosition testMove = new ChessPosition(ChessPosition.Row._5, ChessPosition.Column.E);
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
		//testCheckmate();
		//testCheck();
		//testSingleMove();
		//testCopy();
		//testCapture();

		//the actual game
		play();
		MySystem.println("\n\n\nEND OF GAME FILE\n\n\n",MySystem.getFileName(),MySystem.getLineNumber());
	}
}
/*"C:\Program Files\BlueJ\jdk\bin\java" -Didea.launcher.port=7534 "-Didea.launcher.bin.path=C:\Program Files\JetBrains\IntelliJ IDEA Community Edition 2016.2.5\bin" -Dfile.encoding=UTF-8 -classpath "C:\Program Files\BlueJ\jdk\jre\lib\charsets.jar;C:\Program Files\BlueJ\jdk\jre\lib\deploy.jar;C:\Program Files\BlueJ\jdk\jre\lib\ext\access-bridge-32.jar;C:\Program Files\BlueJ\jdk\jre\lib\ext\cldrdata.jar;C:\Program Files\BlueJ\jdk\jre\lib\ext\dnsns.jar;C:\Program Files\BlueJ\jdk\jre\lib\ext\jaccess.jar;C:\Program Files\BlueJ\jdk\jre\lib\ext\jfxrt.jar;C:\Program Files\BlueJ\jdk\jre\lib\ext\localedata.jar;C:\Program Files\BlueJ\jdk\jre\lib\ext\nashorn.jar;C:\Program Files\BlueJ\jdk\jre\lib\ext\sunec.jar;C:\Program Files\BlueJ\jdk\jre\lib\ext\sunjce_provider.jar;C:\Program Files\BlueJ\jdk\jre\lib\ext\sunmscapi.jar;C:\Program Files\BlueJ\jdk\jre\lib\ext\sunpkcs11.jar;C:\Program Files\BlueJ\jdk\jre\lib\ext\zipfs.jar;C:\Program Files\BlueJ\jdk\jre\lib\javaws.jar;C:\Program Files\BlueJ\jdk\jre\lib\jce.jar;C:\Program Files\BlueJ\jdk\jre\lib\jfr.jar;C:\Program Files\BlueJ\jdk\jre\lib\jfxswt.jar;C:\Program Files\BlueJ\jdk\jre\lib\jsse.jar;C:\Program Files\BlueJ\jdk\jre\lib\management-agent.jar;C:\Program Files\BlueJ\jdk\jre\lib\plugin.jar;C:\Program Files\BlueJ\jdk\jre\lib\resources.jar;C:\Program Files\BlueJ\jdk\jre\lib\rt.jar;C:\Documents and Settings\Administrator\IdeaProjects\tinkerbell\out;C:\Program Files\JetBrains\IntelliJ IDEA Community Edition 2016.2.5\lib\idea_rt.jar" com.intellij.rt.execution.application.AppMain chess.Game
RNBQKBNR
PPPPPPPP




PPPPPPPP
RNBQKBNR
It is WHITE's turn.
Available pieces to move are: [A2, B2, C2, D2, E2, F2, G2, H2, B1, G1]
Input a piece to move (the piece's location): E2
Input the location to move that piece to (available positions are [E4, E3]: E4
RNBQKBNR
PPPPPPPP


    P

PPPP PPP
RNBQKBNR
It is BLACK's turn.
Available pieces to move are: [A7, B7, C7, D7, E7, F7, G7, H7, B8, G8]
Input a piece to move (the piece's location): D7
Input the location to move that piece to (available positions are [D5, D6]: D5
RNBQKBNR
PPP PPPP

   P
    P

PPPP PPP
RNBQKBNR
It is WHITE's turn.
Available pieces to move are: [A2, B2, C2, D2, E4, F2, G2, H2, B1, G1, F1, D1, E1]
Input a piece to move (the piece's location): E1
Input the location to move that piece to (available positions are [E2]: E2
RNBQKBNR
PPP PPPP

   P
    P

PPPPKPPP
RNBQ BNR
It is BLACK's turn.
Available pieces to move are: [A7, B7, C7, D5, E7, F7, G7, H7, B8, G8, C8, D8, E8]
Input a piece to move (the piece's location): D8
Input the location to move that piece to (available positions are [D7, D6]: D6
RNB KBNR
PPP PPPP
   Q
   P
    P

PPPPKPPP
RNBQ BNR
It is WHITE's turn.
Available pieces to move are: [A2, B2, C2, D2, E4, F2, G2, H2, B1, G1, D1, E2]
Input a piece to move (the piece's location): E2
Input the location to move that piece to (available positions are [D3, E1, E3, F3]: F3
RNB KBNR
PPP PPPP
   Q
   P
    P
     K
PPPP PPP
RNBQ BNR
It is BLACK's turn.
King.java:76:Moving out of check withChessPiece( type:PAWN color:WHITE position:E4 alive:true firstMove:false) moving to E5
King.java:76:Moving out of check withChessPiece( type:PAWN color:WHITE position:E4 alive:true firstMove:false) moving to E5
Available pieces to move are: [A7, B7, C7, D5, E7, F7, G7, H7, B8, G8, C8, D6, E8]
Input a piece to move (the piece's location): D6
King.java:76:Moving out of check withChessPiece( type:PAWN color:WHITE position:E4 alive:true firstMove:false) moving to E5
King.java:76:Moving out of check withChessPiece( type:PAWN color:WHITE position:E4 alive:true firstMove:false) moving to E5
Input the location to move that piece to (available positions are [C5, B4, A3, E5, F4, G3, H2, D7, D8, C6, B6, A6, E6, F6, G6, H6]: F4
RNB KBNR
PPP PPPP

   P
    PQ
     K
PPPP PPP
RNBQ BNR
It is WHITE's turn.
Pawn.java:103: Move failed: Not a valid move: trying to move from C4 to D5

Process finished with exit code 1
*/