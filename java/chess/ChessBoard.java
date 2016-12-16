package Chess;

import java.util.Vector;
import java.util.TreeSet;
import MySystem.*;

/**
 * A representation of a Chess board
 */
public class ChessBoard
{
	/**
	 * Stores the numbers and types of Chess pieces on a Chess board at start
	 */
    private abstract static class NumbersOfPieces{
		/**
		 * Stores the numbers and types of Chess pieces on a half of a Chess board at start
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
		 * Stores the numbers and types of Chess pieces total on a Chess board at start
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
	 * Stores the starting positions of different types of Chess pieces at start
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

	public enum Status{IN_PROGRESS,WHITE_WIN,BLACK_WIN}

	private ChessPieces pieces;
	private ChessPiece.Color playerTurn;
	private Status status;

	/**
	 * Fetches the current player whose turn it is
	 * @return the color of the player whose turn it is
	 */
	public ChessPiece.Color getPlayerTurn(){
		return playerTurn;
	}

	/**
	 * prints a new Chess board given a set of pieces
	 * @param PIECES the pieces to print
	 */
	private static void print(final ChessPiece[] PIECES){
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
	 * Prints the current Chess board
	 */
	public void print(){
		ChessBoard.print(this.pieces.toArray());
	}

	/**
	 * unimplemented: will be a graphical representation of the Chess board
	 */
	public void draw(){//TODO
		MySystem.nyi(MySystem.getFileName(), MySystem.getLineNumber());
	}

	/**
	 * Fetches all of the pieces on the Chess board
	 * @return the array of all the Chess pieces
	 */
	public ChessPieces getPieces(){
		return this.pieces;
	}

	/**
	 * Given the current player, finds all of the pieces they can move
	 * @return an array of moveable pieces
	 */
	public Vector<ChessPiece> getMoveablePiecesByPlayer(){
		Vector<ChessPiece> moveablePieces = new Vector<>();
		for(ChessPiece chessPiece: this.pieces.toArray()){
			if(chessPiece.getColor().equals(this.playerTurn) && chessPiece.getLimitedMoves().size() > 0 && chessPiece.getAlive()) moveablePieces.addElement(chessPiece);
		}
		return moveablePieces;
	}
	/**
	 * Given the current player, finds all of the positions of pieces they can move
	 * @return an array of the positions of the moveable pieces
	 */
	public TreeSet<ChessPosition> getMoveablePositionsByPlayer(){
		TreeSet<ChessPosition> moveablePositions = new TreeSet<>();
		for(ChessPiece chessPiece: getMoveablePiecesByPlayer()){
			moveablePositions.add(chessPiece.getPosition());
		}
		return moveablePositions;
	}

	/**
	 * Asks both kings if they are in checkmate. If one is, then it ends the game.
	 */
	private void updateStatus(){
		this.pieces.updateKings();

		if((this.pieces.getKing(ChessPiece.Color.BLACK)).getCheckmate()) status = Status.WHITE_WIN;
		else if((this.pieces.getKing(ChessPiece.Color.WHITE)).getCheckmate()) status = Status.BLACK_WIN;
		else status = Status.IN_PROGRESS;
	}

	/**
	 * Update the Chess board given a Chess piece and the position to move it to
	 * @param move the starting and end positions
	 */
	public void move(ChessPiece.Move move){
		this.move(move.getStart(),move.getTarget());
	}

	/**
	 * Update the Chess board given a Chess piece and the position to move it to
	 * @param startPosition the position of the Chess piece to move
	 * @param moveThere the position to move the piece to
	 */
	public void move(final ChessPosition startPosition,ChessPosition moveThere){
		this.move(this.pieces.getPieceAt(startPosition),moveThere);
	}

	/**
	 * Update the Chess board given a Chess piece and the position to move it to
	 * @param PIECE_TO_MOVE the Chess piece to move
	 * @param moveThere the position to move the piece to
	 */
    public void move(final ChessPiece PIECE_TO_MOVE,ChessPosition moveThere){
		MySystem.myAssert(this.status == Status.IN_PROGRESS, MySystem.getFileName(), MySystem.getLineNumber());
		MySystem.myAssert(PIECE_TO_MOVE.getColor() == playerTurn, MySystem.getFileName(), MySystem.getLineNumber());
		MySystem.myAssert(pieces.checkExists(PIECE_TO_MOVE), MySystem.getFileName(), MySystem.getLineNumber());
		ChessPiece chessPiece = this.pieces.getPieceAt(PIECE_TO_MOVE);
		if(chessPiece.checkMoveDeep(moveThere,pieces)){
			int index = pieces.getIndexOf(chessPiece);
			chessPiece.move(moveThere);
			pieces.set(index,chessPiece);
			if(pieces.isOccupied(pieces.getPieceAt(index).getPosition(), ChessPiece.Color.not(chessPiece.getColor()))) pieces.capture(moveThere);//TODO: make this if statement a method of ChessPieces
		} else {
			MySystem.error("Error: trying to move piece to invalid location: piece:" + chessPiece.toString() + " cannot move to " + moveThere.toString() + " from possible " + chessPiece.getPossibleMoves().toString(), MySystem.getFileName(), MySystem.getLineNumber());//user inputs invalid move
		}
		this.pieces.updateAllMoves();//TODO: consider only updating one king in meaning you only have to update half of pieces (color) to save time
		this.updateStatus();
		playerTurn = ChessPiece.Color.not(playerTurn);
    }

	/**
	 * Checks if the game is over
	 * @return true if the game has ended
	 */
	public Status getStatus(){
		return this.status;
	}

	/**
	 * Mirrors a column across the center of the Chess board
	 * @param column the column to mirror
	 * @return the mirrored position
	 */
	public static ChessPosition.Column mirror(ChessPosition.Column column){
		return new ChessPosition.Column(ChessPosition.Column.H - column.get());
	}

	/**
	 * Mirrors a row across the center of the Chess board
	 * @param row the row to mirror
	 * @return the mirrored position
	 */
	public static ChessPosition.Row mirror(ChessPosition.Row row){
		return new ChessPosition.Row(ChessPosition.Row._8 - row.get());
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
		fourPositions[1] = ChessPiece.makePiece(pos1.getType(),new ChessPiece(new ChessPosition((pos1.getPosition().getRow()),mirror(pos1.getPosition().getColumn())), ChessPiece.Color.WHITE, new TreeSet<>(),new TreeSet<>()));
		fourPositions[2] = ChessPiece.makePiece(pos1.getType(),new ChessPiece(new ChessPosition(mirror(pos1.getPosition().getRow()),pos1.getPosition().getColumn()), ChessPiece.Color.BLACK, new TreeSet<>(),new TreeSet<>()));
		fourPositions[3] = ChessPiece.makePiece(pos1.getType(),new ChessPiece(new ChessPosition(mirror(pos1.getPosition().getRow()),mirror(pos1.getPosition().getColumn())), ChessPiece.Color.BLACK, new TreeSet<>(),new TreeSet<>()));
		return fourPositions;
	}

	/**
	 * Creates an array of pieces representing all the Chess pieces at the beginning of a game
	 * @return an array of Chess pieces representing the start of a game
	 */
    private static ChessPieces fillBoard(){
		ChessPieces chessPieces = new ChessPieces(NumbersOfPieces.Total.TOTAL);
			{
				for(int i = 0; i < NumbersOfPieces.Half.PAWNS; i++){
					chessPieces.setNextPiece(new Pawn(new ChessPosition(PiecePlacement.Row.PAWN, new ChessPosition.Column(i)), ChessPiece.Color.WHITE, new TreeSet<>(),new TreeSet<>()));
					chessPieces.setNextPiece(new Pawn(new ChessPosition(mirror(PiecePlacement.Row.PAWN), new ChessPosition.Column(i)), ChessPiece.Color.BLACK, new TreeSet<>(),new TreeSet<>()));
				}
			{
				final ChessPiece[] PIECES_IN_CORNERS = {
						new Rook(new ChessPosition(PiecePlacement.Row.ALL, PiecePlacement.Column.ROOK), ChessPiece.Color.WHITE, new TreeSet<>(), new TreeSet<>()),
						new Knight(new ChessPosition(PiecePlacement.Row.ALL, PiecePlacement.Column.KNIGHT), ChessPiece.Color.WHITE, new TreeSet<>(), new TreeSet<>()),
						new Bishop(new ChessPosition(PiecePlacement.Row.ALL, PiecePlacement.Column.BISHOP), ChessPiece.Color.WHITE, new TreeSet<>(), new TreeSet<>())

				};
				for(ChessPiece original : PIECES_IN_CORNERS){
					for(ChessPiece a : makeFour(original)){
						chessPieces.setNextPiece(a);
					}
				}
			}
			{
				chessPieces.setNextPiece(new Queen(new ChessPosition(PiecePlacement.Row.ALL,PiecePlacement.Column.QUEEN), ChessPiece.Color.WHITE, new TreeSet<>(),new TreeSet<>()));
				chessPieces.setNextPiece(new Queen(new ChessPosition(mirror(PiecePlacement.Row.ALL),PiecePlacement.Column.QUEEN), ChessPiece.Color.BLACK, new TreeSet<>(),new TreeSet<>()));
			}
			{
				chessPieces.setNextPiece(new King(new ChessPosition(PiecePlacement.Row.ALL,PiecePlacement.Column.KING), ChessPiece.Color.WHITE, new TreeSet<>(),new TreeSet<>()));
				chessPieces.setNextPiece(new King(new ChessPosition(mirror(PiecePlacement.Row.ALL),PiecePlacement.Column.KING), ChessPiece.Color.BLACK, new TreeSet<>(),new TreeSet<>()));
			}
		}
        return new ChessPieces(chessPieces);
    }

	/**
	 * Creates a new Chess board ready to be used
	 */
	public ChessBoard(){
		this(new ChessPieces(ChessBoard.fillBoard()));
    }

	/**
	 * Creates a Chess board given an array of pieces instead of creating one itself
	 * @param pieces the pieces to be used in place of the default
	 */
	public ChessBoard(ChessPieces pieces){
		this.pieces = ChessPieces.makePieces(pieces);
		this.pieces.updateAllMoves();
		this.playerTurn = ChessPiece.Color.WHITE;
		this.status = Status.IN_PROGRESS;
	}
}