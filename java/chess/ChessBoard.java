/**
 * A representation of a chess board
 */
public class ChessBoard
{
    private static class NumbersOfPieces{
        public static final int PAWNS=8;
        public static final int ROOKS=2;
        public static final int KNIGHTS=2;
        public static final int BISHOPS=2;
        public static final int QUEENS=1;
        public static final int KINGS=1;
        public static final int TOTAL=16;
    }
    private ChessPiece[] pieces;
	private ChessPiece.Color playerTurn;
	
	public static boolean isOccupied(ChessPosition checkPosition,ChessPiece.Color color,ChessPiece[] pieces){
		for(ChessPiece a: pieces){
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
			board[ChessPosition.Row.DIMENSION - a.getPosition().getRow().get()-1][a.getPosition().getColumn().get()] = a.toString();
		}
		for(String[] a: board){
			for(String b:a){
				System.out.print(b);
			}
			System.out.print("\n");
		}
	}
	
	public void draw(){//TODO
		System.out.println("NYI");
	}
    
	private boolean checkExists(ChessPiece checkPiece){
		for(ChessPiece a: pieces){
			if(checkPiece.equals(a)) return true;
		}
		return false;
	}
	
    public void move(ChessPiece chessPiece,ChessPosition chessPosition){
		MySystem.myAssert(chessPiece.getColor() == playerTurn && checkExists(chessPiece),MySystem.getFileName(),MySystem.getLineNumber());
		int i = 0;
		for(; i < pieces.length; i++){
			if(chessPiece.getPosition().equals(pieces[i].getPosition())) break;
			if(i == pieces.length-1) MySystem.myAssert(false,MySystem.getFileName(),MySystem.getLineNumber());
		}
		pieces[i].move(chessPosition,pieces);
		if(isOccupied(chessPosition,pieces[i].getColor(),pieces)) capture(chessPosition);//capture the opposite color piece
		playerTurn = ChessPiece.Color.not(playerTurn);
    }
    
    private void capture(ChessPosition chessPosition){
        //TODO add a way of capturing pieces
    }
    
    private ChessPiece[] fillBoard(){
        ChessPiece[] chessPieces = new ChessPiece[NumbersOfPieces.TOTAL*2];
		for(int i = 0; i < 2*NumbersOfPieces.TOTAL; i++){
			chessPieces[i] = new ChessPiece();
		}
        int piecesFilled = 0;
		final ChessPiece.Color[] ALLCOLORS = {ChessPiece.Color.WHITE,ChessPiece.Color.BLACK};
		for(ChessPiece.Color color: ALLCOLORS){
			ChessPosition.Row pawnRow = (color.equals(ChessPiece.Color.WHITE)) ? new ChessPosition.Row(ChessPosition.Row._2) : new ChessPosition.Row(ChessPosition.Row._7);
			for(int i =0; i < NumbersOfPieces.PAWNS; i++) chessPieces[i+piecesFilled] = new Pawn(new ChessPosition(pawnRow.get(),i),color);
			piecesFilled += NumbersOfPieces.PAWNS;
			for(int i =0; i < NumbersOfPieces.ROOKS; i++) chessPieces[i+piecesFilled] = new ChessPiece(new ChessPosition(),color);
			piecesFilled += NumbersOfPieces.ROOKS;
			for(int i =0; i < NumbersOfPieces.KNIGHTS; i++) chessPieces[i+piecesFilled] = new ChessPiece(new ChessPosition(),color);
			piecesFilled += NumbersOfPieces.KNIGHTS;
			for(int i =0; i < NumbersOfPieces.BISHOPS; i++) chessPieces[i+piecesFilled] = new ChessPiece(new ChessPosition(),color);
			piecesFilled += NumbersOfPieces.BISHOPS;
			for(int i =0; i < NumbersOfPieces.QUEENS; i++) chessPieces[i+piecesFilled] = new ChessPiece(new ChessPosition(),color);
			piecesFilled += NumbersOfPieces.QUEENS;
			for(int i =0; i < NumbersOfPieces.KINGS; i++) chessPieces[i+piecesFilled] = new ChessPiece(new ChessPosition(),color);
		}		
        return chessPieces;
    }
    
    public ChessBoard(){
		pieces = fillBoard();
		playerTurn = ChessPiece.Color.WHITE;
    }
}