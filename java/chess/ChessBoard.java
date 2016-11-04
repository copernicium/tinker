package chess;

import java.util.Vector;

/**
 * A representation of a chess board
 */
public class ChessBoard
{
    private abstract static class NumbersOfPieces{
		public  abstract static class Half{
			public static final int PAWNS=8;
			public static final int ROOKS=2;
			public static final int KNIGHTS=2;
			public static final int BISHOPS=2;
			public static final int QUEENS=1;
			public static final int KINGS=1;
			public static final int TOTAL=16;
		}

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
    private static abstract class PiecePlacement{//placement of pieces on the white and left side.
		public static abstract class Row{
			public static final ChessPosition.Row ALL = new ChessPosition.Row(ChessPosition.Row._1);
			public static final ChessPosition.Row PAWN= new ChessPosition.Row(ChessPosition.Row._2);//pawns are in their own rows
		}
		public static abstract class Column{
			public static final ChessPosition.Column ROOK = new ChessPosition.Column(ChessPosition.Column.A);
			public static final ChessPosition.Column KNIGHT = new ChessPosition.Column(ChessPosition.Column.B);
			public static final ChessPosition.Column BISHOP= new ChessPosition.Column(ChessPosition.Column.C);
			public static final ChessPosition.Column KING = new ChessPosition.Column(ChessPosition.Column.D);
			public static final ChessPosition.Column QUEEN = new ChessPosition.Column(ChessPosition.Column.E);
		}
	}
    private ChessPiece[] pieces;
	private ChessPiece.Color playerTurn;

	public ChessPiece.Color getPlayerTurn(){
		return playerTurn;
	}

	public static boolean isOccupied(ChessPosition checkPosition,ChessPiece.Color color,ChessPiece[] pieces){
		for(ChessPiece a: pieces){
			if(!a.getAlive()) continue;
			if(color == a.getColor() && checkPosition.equals(a.getPosition())) return true;
		}
        return false;
    }
	
	public void print(){
		String[][] board = new String[ChessPosition.Row.DIMENSION][ChessPosition.Column.DIMENSION];
		for(int i = 0; i < board.length; i++){
			for(int j = 0; j < board[i].length; j++){
				board[i][j] = " ";
			}
		}
		for(ChessPiece a: pieces){
			if(a.getAlive()) board[ChessPosition.Row.DIMENSION - a.getPosition().getRow().get()-1][a.getPosition().getColumn().get()] = a.toString();
		}
		for(String[] a: board){
			for(String b:a){
				System.out.print(b);
			}
			System.out.print("\n");
		}
	}
	
	public void draw(){//TODO
		MySystem.nyi(MySystem.getFileName(),MySystem.getLineNumber());
	}
    
	private static boolean checkExists(ChessPiece checkPiece,ChessPiece[] chessPieces){
		for(ChessPiece a: chessPieces){
			if(!checkPiece.getAlive()) continue;
			if(checkPiece.equals(a)) return true;
		}
		return false;
	}

	private int find(ChessPiece.Type type, ChessPiece.Color color){
		for(int i = 0; i < pieces.length; i++){
			if(type.equals(pieces[i].getType()) && color == pieces[i].getColor()) return i;
		}
		MySystem.myAssert(false,MySystem.getFileName(),MySystem.getLineNumber());
		return -1;
	}

	private static int find(ChessPosition chessPosition, ChessPiece[] chessPieces){
		for(int i = 0; i < chessPieces.length; i++){
			if(chessPosition.equals(chessPieces[i].getPosition())) return i;
		}
		MySystem.myAssert(false,MySystem.getFileName(),MySystem.getLineNumber());
		return -1;
	}

	public ChessPiece[] getPieces(){
		return pieces;
	}

	public Vector<ChessPiece> getMoveablePiecesByPlayer(){
		Vector<ChessPiece> moveablePieces = new Vector<ChessPiece>();
		for(ChessPiece chessPiece: pieces){
			if(chessPiece.getNewPositions(pieces).size() > 0 && chessPiece.getColor().equals(this.playerTurn)) moveablePieces.addElement(chessPiece);
		}
		return moveablePieces;
	}

	public Vector<ChessPosition> getMoveablePositionsByPlayer(){
		Vector<ChessPosition> moveablePositions = new Vector<ChessPosition>();
		for(ChessPiece chessPiece: pieces){
			if(chessPiece.getNewPositions(pieces).size() > 0 && chessPiece.getColor().equals(this.playerTurn)) moveablePositions.addElement(chessPiece.getPosition());
		}
		return moveablePositions;
	}

	private static int find(ChessPiece chessPiece, ChessPiece[] chessPieces){
		for(int i =0; i < chessPieces.length; i++){
			if(chessPiece.equals(chessPieces[i])) return i;
		}
		MySystem.myAssert(false,MySystem.getFileName(),MySystem.getLineNumber());
		return -1;
	}

	public boolean checkIfGameOver(){
		King whiteKing = new King(pieces[find(ChessPiece.Type.KING, ChessPiece.Color.WHITE)]);
		King blackKing = new King(pieces[find(ChessPiece.Type.KING, ChessPiece.Color.BLACK)]);

		whiteKing.update(pieces);
		blackKing.update(pieces);

		if(whiteKing.getCheckMate()) System.out.println("Game over. " + ChessPiece.Color.not(whiteKing.getColor()).toString() + " wins.");
		else if(blackKing.getCheckMate()) System.out.println("Game over. " + ChessPiece.Color.not(blackKing.getColor()).toString() + " wins.");
		else return false;
		return true;
	}

	public static ChessPiece[] testMove(ChessPiece chessPiece,ChessPosition moveThere, ChessPiece[] chessPieces){
		MySystem.myAssert(checkExists(chessPiece,chessPieces),MySystem.getFileName(),MySystem.getLineNumber());
		int position = ChessBoard.find(chessPiece,chessPieces);
		if(chessPiece.checkMove(moveThere,chessPieces)){
			if(isOccupied(moveThere, ChessPiece.Color.not(chessPieces[position].getColor()),chessPieces)) chessPieces = ChessBoard.capture(moveThere,chessPieces);
			chessPieces[position].move(moveThere,chessPieces);
		} else {
			MySystem.nyi(MySystem.getFileName(),MySystem.getLineNumber());
		}
		return chessPieces;
	}

    public void move(ChessPiece chessPiece,ChessPosition moveThere){
		MySystem.myAssert(chessPiece.getColor() == playerTurn && checkExists(chessPiece,this.pieces),MySystem.getFileName(),MySystem.getLineNumber());
		int position = ChessBoard.find(chessPiece,this.pieces);
		if(chessPiece.checkMove(moveThere,pieces)){
			if(isOccupied(moveThere, ChessPiece.Color.not(pieces[position].getColor()),pieces)) capture(moveThere);
			pieces[position].move(moveThere,pieces);
		} else {
			MySystem.nyi(MySystem.getFileName(),MySystem.getLineNumber());//TODO: if user inputs invalid move
		}
		if(checkIfGameOver()) MySystem.nyi(MySystem.getFileName(),MySystem.getLineNumber());//TODO game over logic
		playerTurn = ChessPiece.Color.not(playerTurn);
    }
    
    private void capture(ChessPosition chessPosition){
		pieces[ChessBoard.find(chessPosition,this.pieces)].capture();
    }

	private static ChessPiece[] capture(ChessPosition chessPosition, ChessPiece[] chessPieces){
		chessPieces[ChessBoard.find(chessPosition,chessPieces)].capture();
		return chessPieces;
	}

    private int findNextUnassigned(ChessPiece[] chessPieces){
		for(int i = 0; i < chessPieces.length; i++){
			if(chessPieces[i].getType() == ChessPiece.Type.UNASSIGNED) return i;
		}
		MySystem.myAssert(false,MySystem.getFileName(),MySystem.getLineNumber());
		return -1;
	}
	
	private static ChessPiece[] makeFour(ChessPiece pos1){
		final int NUMBEROFCORNERS = 4;
		ChessPiece[] fourPositions = new ChessPiece[NUMBEROFCORNERS];
		fourPositions[0] = pos1;
		fourPositions[1] = new ChessPiece(new ChessPosition((pos1.getPosition().getRow()),ChessPosition.mirror(pos1.getPosition().getColumn())), ChessPiece.Color.WHITE);
		fourPositions[2] = new ChessPiece(new ChessPosition(ChessPosition.mirror(pos1.getPosition().getRow()),pos1.getPosition().getColumn()), ChessPiece.Color.BLACK);
		fourPositions[3] = new ChessPiece(new ChessPosition(ChessPosition.mirror(pos1.getPosition().getRow()),ChessPosition.mirror(pos1.getPosition().getColumn())), ChessPiece.Color.BLACK);
		return fourPositions;
	}

    private ChessPiece[] fillBoard(){
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
        return chessPieces;
    }
    
    public ChessBoard(){
		pieces = fillBoard();
		playerTurn = ChessPiece.Color.WHITE;
    }
    public ChessBoard(ChessPiece[] pieces, ChessPiece.Color playerTurn){
		this.pieces = pieces;
		this.playerTurn = playerTurn;
	}
}