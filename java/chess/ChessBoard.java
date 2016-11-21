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
	public static abstract class PiecePlacement{//placement of pieces on the white and left side.
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

	public enum Status{IN_PROGRESS,WHITE_WIN,BLACK_WIN};

	private ChessPiece.Color playerTurn;
	private Status status;
	private ConditionStorage conditions;

	/**
	 * Fetches the current player whose turn it is
	 * @return the color of the player whose turn it is
	 */
	public ChessPiece.Color getPlayerTurn(){
		return playerTurn;
	}


	/**
	 * prints a new chess board given a set of pieces
	 * @param PIECES the pieces to print
	 */
	public static void print(final ChessPiece[] PIECES){
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
		ChessBoard.print(this.conditions.getLast().toArray());
	}

	/**
	 * unimplemented: will be a graphical representation of the chess baord
	 */
	public void draw(){//TODO
		MySystem.nyi(MySystem.getFileName(),MySystem.getLineNumber());
	}

	/**
	 * Fetches all of the pieces on the chess board
	 * @return the array of all the chess pieces
	 */
	public ChessPieces getPieces(){
		return this.conditions.getLast();
	}

	/**
	 * Given the current player, finds all of the pieces they can move
	 * @return an array of moveable pieces
	 */
	public Vector<ChessPiece> getMoveablePiecesByPlayer(){
		Vector<ChessPiece> moveablePieces = new Vector<>();
		//ChessPiece[] allPieces = pieces.toArray();
		for(int i = 0; i < this.conditions.getLast().toArray().length; i++){
			ChessPiece chessPiece = this.conditions.getLast().toArray()[i];
			if(chessPiece.getColor().equals(this.playerTurn) && this.conditions.getMovesAt(i).size() > 0) moveablePieces.addElement(chessPiece);
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
	 * Asks both kings if they are in checkmate. If one is, then it ends the game.
	 */
	public void updateStatus(){
		this.conditions.updateKings();

		if((this.conditions.getLast().getKing(ChessPiece.Color.BLACK)).getCheckmate()) status = Status.WHITE_WIN;
		else if((this.conditions.getLast().getKing(ChessPiece.Color.WHITE)).getCheckmate()) status = Status.BLACK_WIN;
		else status = Status.IN_PROGRESS;
	}

	/**
	 * Update the chess board given a chess piece and the position to move it to
	 * @param chessPiece the chess piece to move
	 * @param moveThere the position to move the piece to
	 */
    public void move(ChessPiece chessPiece,ChessPosition moveThere){
		ChessPieces pieces = ChessPieces.makePieces(this.conditions.getLast());
		//TODO: if in check, then only allow movement out of it
		MySystem.myAssert(this.status == Status.IN_PROGRESS,MySystem.getFileName(), MySystem.getLineNumber());
		MySystem.myAssert(chessPiece.getColor() == playerTurn,MySystem.getFileName(),MySystem.getLineNumber());
		MySystem.myAssert(pieces.checkExists(chessPiece),MySystem.getFileName(),MySystem.getLineNumber());
		if(chessPiece.checkMoveDeep(moveThere,pieces,this.conditions)){
			int position = pieces.getIndexOf(chessPiece);
			chessPiece.move(moveThere,pieces);
			pieces.set(position,chessPiece);
			if(pieces.isOccupied(pieces.getPieceAt(position).getPosition(), ChessPiece.Color.not(chessPiece.getColor()))) pieces.captureAt(pieces.getIndexOf(moveThere));
		} else {
			MySystem.error("Error: trying to move piece to invalid location: piece:" + chessPiece.toString() + " cannot move to " + moveThere.toString(),MySystem.getFileName(),MySystem.getLineNumber());//user inputs invalid move
		}
		if(this.conditions.changed(pieces)){
			this.conditions.update(pieces,false);
			this.updateStatus();
		}
		playerTurn = ChessPiece.Color.not(playerTurn);
    }

    public ConditionStorage getConditions(){
		return this.conditions;
	}

	/**
	 * Checks if the game is over
	 * @return true if the game has ended
	 */
	public Status getStatus(){
		this.updateStatus();
		return this.status;
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
		final int NUMBER_OF_CORNERS = 4;
		ChessPiece[] fourPositions = new ChessPiece[NUMBER_OF_CORNERS ];
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
    private static ChessPieces fillBoard(){
		ChessPieces chessPieces = new ChessPieces(NumbersOfPieces.Total.TOTAL);
			{
			for(int i = 0; i < NumbersOfPieces.Half.PAWNS; i++){
				chessPieces.setNextPiece(new Pawn(new ChessPosition(PiecePlacement.Row.PAWN, new ChessPosition.Column(i)), ChessPiece.Color.WHITE));
				chessPieces.setNextPiece(new Pawn(new ChessPosition(ChessPosition.mirror(PiecePlacement.Row.PAWN), new ChessPosition.Column(i)), ChessPiece.Color.BLACK));
			}
			{
				for(ChessPiece a: makeFour(new ChessPiece(new ChessPosition(PiecePlacement.Row.ALL,PiecePlacement.Column.ROOK), ChessPiece.Color.WHITE))){
					chessPieces.setNextPiece(new Rook(a));
				}
			}
			{
				for(ChessPiece a: makeFour(new ChessPiece(new ChessPosition(PiecePlacement.Row.ALL,PiecePlacement.Column.KNIGHT), ChessPiece.Color.WHITE))){
					chessPieces.setNextPiece(new Knight(a));
				}
			}
			{
				for(ChessPiece a: makeFour(new ChessPiece(new ChessPosition(PiecePlacement.Row.ALL,PiecePlacement.Column.BISHOP), ChessPiece.Color.WHITE))){
					chessPieces.setNextPiece(new Bishop(a));
				}
			}
			{
				chessPieces.setNextPiece(new Queen(new ChessPosition(PiecePlacement.Row.ALL,PiecePlacement.Column.QUEEN), ChessPiece.Color.WHITE));
				chessPieces.setNextPiece(new Queen(new ChessPosition(ChessPosition.mirror(PiecePlacement.Row.ALL),PiecePlacement.Column.QUEEN), ChessPiece.Color.BLACK));
			}
			{
				chessPieces.setNextPiece(new King(new ChessPosition(PiecePlacement.Row.ALL,PiecePlacement.Column.KING), ChessPiece.Color.WHITE));
				chessPieces.setNextPiece(new King(new ChessPosition(ChessPosition.mirror(PiecePlacement.Row.ALL),PiecePlacement.Column.KING), ChessPiece.Color.BLACK));
			}
		}
        return new ChessPieces(chessPieces);
    }

	/**
	 * Creates a new chess board ready to be used
	 */
	public ChessBoard(){
		ChessPieces pieces = new ChessPieces(ChessBoard.fillBoard());
		this.playerTurn = ChessPiece.Color.WHITE;
		this.status = Status.IN_PROGRESS;
		this.conditions = new ConditionStorage(pieces);
    }

	/**
	 * Creates a chess board given an array of pieces instead of creating one itself
	 * @param pieces the pieces to be used in place of the default
	 */
	public ChessBoard(ChessPieces pieces){
		this(ChessPiece.Color.WHITE,new ConditionStorage(pieces));
	}
	/**
	 * Creates a chess board given an array of pieces instead of creating one itself and the current player's turn
	 * @param playerTurn the color that the player's turn should be set to
	 */
    public ChessBoard(ChessPiece.Color playerTurn,ConditionStorage conditions){
		this.playerTurn = playerTurn;
		this.status = Status.IN_PROGRESS;
		this.conditions = conditions;
	}
}