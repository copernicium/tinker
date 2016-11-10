package chess;

import java.util.Vector;

/**
 * A representation of a chess board
 */
public class ChessBoard
{
	/**
	 * Stores the numbers and types of chess pieces on a chess board at start
	 */
    private abstract static class NumbersOfPieces{
		/**
		 * Stores the numbers and types of chess pieces on a half of a chess board at start
		 */
		public  abstract static class Half{
			public static final int PAWNS=8;
			public static final int ROOKS=2;
			public static final int KNIGHTS=2;
			public static final int BISHOPS=2;
			public static final int QUEENS=1;
			public static final int KINGS=1;
			public static final int TOTAL=16;
		}
		/**
		 * Stores the numbers and types of chess pieces total on a chess board at start
		 */
		public abstract static class Total{
			public static final int PAWNS=16;
			public static final int ROOKS=4;
			public static final int KNIGHTS=4;
			public static final int BISHOPS=4;
			public static final int QUEENS=2;
			public static final int KINGS=2;
			public static final int TOTAL=32;
		}
    }

	/**
	 * Stores the starting positions of different types of chess pieces at start
	 */
	private static abstract class PiecePlacement{//placement of pieces on the white and left side.
		public static abstract class Row{
			public static final ChessPosition.Row ALL = new ChessPosition.Row(ChessPosition.Row._1);
			public static final ChessPosition.Row PAWN= new ChessPosition.Row(ChessPosition.Row._2);//pawns are in their own rows
		}
		public static abstract class Column{
			public static final ChessPosition.Column ROOK = new ChessPosition.Column(ChessPosition.Column.A);
			public static final ChessPosition.Column KNIGHT = new ChessPosition.Column(ChessPosition.Column.B);
			public static final ChessPosition.Column BISHOP= new ChessPosition.Column(ChessPosition.Column.C);
			public static final ChessPosition.Column QUEEN = new ChessPosition.Column(ChessPosition.Column.D);
			public static final ChessPosition.Column KING = new ChessPosition.Column(ChessPosition.Column.E);
		}
	}
    private ChessPiece[] pieces;
	private ChessPiece.Color playerTurn;
	private boolean gameOver;

	/**
	 * Fetches the current player whose turn it is
	 * @return the color of the player whose turn it is
	 */
	public ChessPiece.Color getPlayerTurn(){
		return playerTurn;
	}

	/**
	 * Checks to see if a current position is occupied (unavailable) by a given color
	 * @param checkPosition the position that is checked for occupancy
	 * @param color the color of the occupying piece must be
	 * @param pieces all of the pieces to check
	 * @return true if a given position is occupied by a piece of a given color
	 */
	public static boolean isOccupied(ChessPosition checkPosition,ChessPiece.Color color,ChessPiece[] pieces){
		for(ChessPiece a: pieces){
			if(!a.getAlive()) continue;
			if(color == a.getColor() && checkPosition.equals(a.getPosition())) return true;
		}
        return false;
    }

	/**
	 * prints a new chess board given a set of pieces
	 * @param PIECES the pieces to print
	 */
	public static void print(final ChessPiece[] PIECES){
		//ChessBoard chessBoard = new ChessBoard(PIECES);
		//chessBoard.print();
		String[][] board = new String[ChessPosition.Row.DIMENSION][ChessPosition.Column.DIMENSION];
		for(int i = 0; i < board.length; i++){
			for(int j = 0; j < board[i].length; j++){
				board[i][j] = " ";
			}
		}
		for(ChessPiece a: PIECES){
			if(a.getAlive()) board[ChessPosition.Row.DIMENSION - a.getPosition().getRow().get()-1][a.getPosition().getColumn().get()] = a.print();
		}
		for(String[] a: board){
			for(String b:a){
				System.out.print(b);
			}
			System.out.print("\n");
		}
	}

	/**
	 * Prints the current chess board
	 */
	public void print(){
		ChessBoard.print(this.pieces);
	}

	/**
	 * unimplemented: will be a graphical representation of the chess baord
	 */
	public void draw(){//TODO
		MySystem.nyi(MySystem.getFileName(),MySystem.getLineNumber());
	}

	/**
	 * Checks if a given piece exists in an array of pieces
	 * @param TEST_PIECE the piece which is checked for existence
	 * @param CHESS_PIECES the array of pieces to check
	 * @return true if the piece exists in the array
	 */
	public static boolean checkExists(final ChessPiece TEST_PIECE,final ChessPiece[] CHESS_PIECES){
		if(!TEST_PIECE.getAlive()) return false;
		for(ChessPiece a: CHESS_PIECES){
			if(!a.getAlive()) continue;
			if(TEST_PIECE.equalsByType(a)) return true;
		}
		return false;
	}

	/**
	 * Finds the location of a piece that meets certain criteria in an array. It crashes if it cannot be found.
	 * @param type the type of piece
	 * @param color the color of the piece
	 * @param CHESS_PIECES the array of pieces to check
	 * @return the index of the piece if it exits
	 */
	public static int find(ChessPiece.Type type, ChessPiece.Color color, final ChessPiece[] CHESS_PIECES){
		for(int i = 0; i < CHESS_PIECES.length; i++){
			if(type == CHESS_PIECES[i].getType() && color == CHESS_PIECES[i].getColor()) return i;
		}
		MySystem.error("Piece not found in array",MySystem.getFileName(),MySystem.getLineNumber());
		return -1;
	}

	/**
	 * Returns the index of a piece in an array which occupies a given position. It crashes if it cannot be found.
	 * @param chessPosition the position to look for
	 * @param chessPieces the array of pieces to check
	 * @return the index of the piece if it exits
	 */
	public static int find(ChessPosition chessPosition, ChessPiece[] chessPieces){
		for(int i = 0; i < chessPieces.length; i++){
			if(chessPosition.equals(chessPieces[i].getPosition())) return i;
		}
		MySystem.error("Piece not found in array",MySystem.getFileName(),MySystem.getLineNumber());
		return -1;
	}

	/**
	 * Fetches all of the pieces on the chess board
	 * @return the array of all the chess pieces
	 */
	public ChessPiece[] getPieces(){
		return pieces;
	}

	/**
	 * Given the current player, finds all of the pieces they can move
	 * @return an array of moveable pieces
	 */
	public Vector<ChessPiece> getMoveablePiecesByPlayer(){
		Vector<ChessPiece> moveablePieces = new Vector<>();
		for(ChessPiece chessPiece: pieces){
			if(chessPiece.getNewPositions(pieces).size() > 0 && chessPiece.getColor().equals(this.playerTurn)) moveablePieces.addElement(chessPiece);
		}
		return moveablePieces;
	}
	/**
	 * Given the current player, finds all of the positions of pieces they can move
	 * @return an array of the positions of the moveable pieces
	 */
	public Vector<ChessPosition> getMoveablePositionsByPlayer(){
		Vector<ChessPosition> moveablePositions = new Vector<>();
		for(ChessPiece chessPiece: getMoveablePiecesByPlayer()){
			moveablePositions.addElement(chessPiece.getPosition());
		}
		return moveablePositions;
	}

	/**
	 * Finds the index of a chess piece in an array, crashing if it cannot be found.
	 * @param FIND_ME the piece to find
	 * @param CHESS_PIECES the array of pieces to search through
	 * @return the index to the piece in the array
	 */
	public static int find(final ChessPiece FIND_ME, final ChessPiece[] CHESS_PIECES){
		for(int i =0; i < CHESS_PIECES.length; i++){
			if(FIND_ME.equalsByType(CHESS_PIECES[i]))return i;
		}
		MySystem.myAssert(false,MySystem.getFileName(),MySystem.getLineNumber());
		return -1;
	}

	/**
	 * Asks both kings if they are in checkmate. If one is, then it ends the game.
	 */
	private void checkIfGameOver(){
		King whiteKing = new King(pieces[ChessBoard.find(ChessPiece.Type.KING, ChessPiece.Color.WHITE,pieces)]);
		King blackKing = new King(pieces[ChessBoard.find(ChessPiece.Type.KING, ChessPiece.Color.BLACK,pieces)]);

		whiteKing.update(pieces);
		blackKing.update(pieces);

		gameOver = (blackKing.getCheckMate() || whiteKing.getCheckMate());
	}

	/**
	 * Tests a move on an array of chess pieces
	 * @param CHESS_PIECE the chess piece to mvoe
	 * @param MOVE_TO_POS the position to move the chess piece to
	 * @param CHESS_PIECES the array of chess pieces representing the borad
	 * @return the updated array of pieces
	 */
	public static ChessPiece[] testMove(final ChessPiece CHESS_PIECE,final ChessPosition MOVE_TO_POS, final ChessPiece[] CHESS_PIECES){
		ChessPiece[] postMovePieces = ChessPiece.makePieces(CHESS_PIECES);
		{
			if(CHESS_PIECE.checkMove(MOVE_TO_POS,CHESS_PIECES)){
				int position = ChessBoard.find(CHESS_PIECE,postMovePieces);
				if(ChessBoard.isOccupied(MOVE_TO_POS, ChessPiece.Color.not(CHESS_PIECE.getColor()),postMovePieces)) postMovePieces = ChessBoard.capture(MOVE_TO_POS,postMovePieces);
				postMovePieces[position].move(MOVE_TO_POS,postMovePieces);
				return postMovePieces;
			} else {
				MySystem.error("Error: trying to move piece to invalid location.",MySystem.getFileName(),MySystem.getLineNumber());
			}
		}
		MySystem.nyi(MySystem.getFileName(),MySystem.getLineNumber());
		return new ChessPiece[0];
	}

	/**
	 * Update the chess board given a chess piece and the position to move it to
	 * @param chessPiece the chess piece to move
	 * @param moveThere the position to move the piece to
	 */
    public void move(ChessPiece chessPiece,ChessPosition moveThere){
		MySystem.myAssert(!this.gameOver && chessPiece.getColor() == playerTurn && checkExists(chessPiece,this.pieces),MySystem.getFileName(),MySystem.getLineNumber());
		if(chessPiece.checkMove(moveThere,pieces)){
			int position = ChessBoard.find(chessPiece,this.pieces);
			pieces[position].move(moveThere,pieces);
			if(isOccupied(pieces[position].getPosition(), ChessPiece.Color.not(chessPiece.getColor()),pieces)) capture(moveThere);
		} else {
			MySystem.error("Error: trying to move piece to invalid location.",MySystem.getFileName(),MySystem.getLineNumber());//user inputs invalid move
		}
		checkIfGameOver();
		playerTurn = ChessPiece.Color.not(playerTurn);
    }

	/**
	 * Checks if the game is over
	 * @return true if the game has ended
	 */
	public boolean getGameOver(){
		checkIfGameOver();
		return this.gameOver;
	}

	/**
	 * Kills the piece occuppying a given chess positon
	 * @param chessPosition the position of the piece to be killed
	 */
	private void capture(ChessPosition chessPosition){
		this.pieces = ChessBoard.capture(chessPosition, this.pieces);
    }

	/**
	 * Updates an array of pieces by killing one of them
	 * @param chessPosition the position of the piece to be killed
	 * @param chessPieces the array of pieces to be updated
	 * @return the updated array
	 */
	public static ChessPiece[] capture(ChessPosition chessPosition, ChessPiece[] chessPieces){
		chessPieces[ChessBoard.find(chessPosition,chessPieces)].capture();
		return chessPieces;
	}

	/**
	 * Find the index of the first chess piece in an array that hasn't been assigned an identity
	 * @param chessPieces an array of pieces to check
	 * @return the index of the next unassigned piece
	 */
    private static int findNextUnassigned(ChessPiece[] chessPieces){
		for(int i = 0; i < chessPieces.length; i++){
			if(chessPieces[i].getType() == ChessPiece.Type.UNASSIGNED) return i;
		}
		MySystem.myAssert(false,MySystem.getFileName(),MySystem.getLineNumber());
		return -1;
	}

	/**
	 * Given a location, calculates the three others which are mirrored images just in the other corners of the board
	 * @param pos1 the position to mirror
	 * @return an array of the four duplicated (including the original) pieces
	 */
	private static ChessPiece[] makeFour(ChessPiece pos1){
		final int NUMBEROFCORNERS = 4;
		ChessPiece[] fourPositions = new ChessPiece[NUMBEROFCORNERS];
		fourPositions[0] = pos1;
		fourPositions[1] = new ChessPiece(new ChessPosition((pos1.getPosition().getRow()),ChessPosition.mirror(pos1.getPosition().getColumn())), ChessPiece.Color.WHITE);
		fourPositions[2] = new ChessPiece(new ChessPosition(ChessPosition.mirror(pos1.getPosition().getRow()),pos1.getPosition().getColumn()), ChessPiece.Color.BLACK);
		fourPositions[3] = new ChessPiece(new ChessPosition(ChessPosition.mirror(pos1.getPosition().getRow()),ChessPosition.mirror(pos1.getPosition().getColumn())), ChessPiece.Color.BLACK);
		return fourPositions;
	}

	/**
	 * Creates an array of pieces representing all the chess pieces at the beginnning of a game
	 * @return an array of chess pieces representing the start of a game
	 */
    private static ChessPiece[] fillBoard(){
		//TODO: do not allow assignment of pieces to locations occupied by any other piece or something
		ChessPiece[] chessPieces = new ChessPiece[NumbersOfPieces.Total.TOTAL];
		for(int i = 0; i < NumbersOfPieces.Total.TOTAL; i++){
			chessPieces[i] = new ChessPiece();
		}
		{
			for(int i = 0; i < NumbersOfPieces.Half.PAWNS; i++){
				chessPieces[findNextUnassigned(chessPieces)] = new Pawn(new ChessPosition(PiecePlacement.Row.PAWN, i), ChessPiece.Color.WHITE);
				chessPieces[findNextUnassigned(chessPieces)] = new Pawn(new ChessPosition(ChessPosition.mirror(PiecePlacement.Row.PAWN), i), ChessPiece.Color.BLACK);
			}
			{
				for(ChessPiece a: makeFour(new ChessPiece(new ChessPosition(PiecePlacement.Row.ALL,PiecePlacement.Column.ROOK), ChessPiece.Color.WHITE))){
					chessPieces[findNextUnassigned(chessPieces)] = new Rook(a);
				}
			}
			{
				for(ChessPiece a: makeFour(new ChessPiece(new ChessPosition(PiecePlacement.Row.ALL,PiecePlacement.Column.KNIGHT), ChessPiece.Color.WHITE))){
					chessPieces[findNextUnassigned(chessPieces)] = new Knight(a);
				}
			}
			{
				for(ChessPiece a: makeFour(new ChessPiece(new ChessPosition(PiecePlacement.Row.ALL,PiecePlacement.Column.BISHOP), ChessPiece.Color.WHITE))){
					chessPieces[findNextUnassigned(chessPieces)] = new Bishop(a);
				}
			}
			{
				chessPieces[findNextUnassigned(chessPieces)] = new Queen(new ChessPosition(PiecePlacement.Row.ALL,PiecePlacement.Column.QUEEN), ChessPiece.Color.WHITE);
				chessPieces[findNextUnassigned(chessPieces)] = new Queen(new ChessPosition(ChessPosition.mirror(PiecePlacement.Row.ALL),PiecePlacement.Column.QUEEN), ChessPiece.Color.BLACK);
			}
			{
				chessPieces[findNextUnassigned(chessPieces)] = new King(new ChessPosition(PiecePlacement.Row.ALL,PiecePlacement.Column.KING), ChessPiece.Color.WHITE);
				chessPieces[findNextUnassigned(chessPieces)] = new King(new ChessPosition(ChessPosition.mirror(PiecePlacement.Row.ALL),PiecePlacement.Column.KING), ChessPiece.Color.BLACK);
			}
		}
        return ChessPiece.makePieces(chessPieces);
    }

	/**
	 * Creates a new chess board ready to be used
	 */
	public ChessBoard(){
		this(ChessBoard.fillBoard(),ChessPiece.Color.WHITE);
    }

	/**
	 * Creates a chess board given an array of pieces instead of creating one itself
	 * @param pieces the pieces to be used in place of the default
	 */
	public ChessBoard(ChessPiece[] pieces){
		this(pieces,ChessPiece.Color.WHITE);
	}
	/**
	 * Creates a chess board given an array of pieces instead of creating one itself and the current player's turn
	 * @param pieces the pieces to be used in place of the default
	 * @param playerTurn the color that the player's turn should be set to
	 */
    public ChessBoard(ChessPiece[] pieces, ChessPiece.Color playerTurn){
		this.pieces = pieces;
		this.playerTurn = playerTurn;
		this.gameOver = false;
	}
}