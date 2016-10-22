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
    private ChessPiece[] whitePieces;
    private ChessPiece[] blackPieces;
	
	public static boolean isOccupied(ChessPosition checkPosition,ChessPiece.Color color, ChessPiece[] unavailablePositions){
        assert(unavailablePositions.length > 0 && unavailablePositions[0].getColor()==color);
		for(ChessPiece a: unavailablePositions){
			if(checkPosition.equals(a.getPosition())) return true;
		}
        return false;
    }
	
	public void print(){
		String[][] board = new String[ChessPosition.Row.DIMENSION][ChessPosition.Column.DIMENSION];
		ChessPiece[] chessPieces = whitePieces;
		for(int i = 0; i < board.length; i++){
			for(int j = 0; j < board[i].length; j++){
				board[i][j] = " ";
			}
		}
		for(int i = 0; i < 2; i++){
			for(ChessPiece a: chessPieces){
				//System.out.println(a.getPosition().toString() + " " + a.getType() + " " + a.getPosition().getColumn().get());
				board[ChessPosition.Row.DIMENSION - a.getPosition().getRow().get()-1][a.getPosition().getColumn().get()] = a.toString();
			}
			chessPieces = blackPieces;
		}
		for(String[] a: board){
			for(String b:a){
				System.out.print(b);
			}
			System.out.print("\n");
		}
	}
    
    public void move(ChessPiece chessPiece,ChessPosition chessPosition){
		switch(chessPiece.getColor()){
			case WHITE:
				chessPiece.move(chessPosition,whitePieces);
				break;
			case BLACK:
				chessPiece.move(chessPosition,blackPieces);
				break;
			default: break;
		}
        //if(this.isOccupied(chessPosition,)) kill(chessPosition);
    }
    
    private void kill(ChessPosition chessPosition){
        //TODO This
    }
    
    private ChessPiece[] fillHalfOfBoard(ChessPiece.Color color){
        ChessPiece[] chessPieces = new ChessPiece[NumbersOfPieces.TOTAL];
		for(int i = 0; i < NumbersOfPieces.TOTAL; i++){
			chessPieces[i] = new ChessPiece();
		}
        int piecesFilled = 0;
		ChessPosition.Row pawnRow = (color == ChessPiece.Color.WHITE ) ? new ChessPosition.Row(ChessPosition.Row._2) : new ChessPosition.Row(ChessPosition.Row._7);
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
        return chessPieces;
    }
    
    public ChessBoard(){
		whitePieces = fillHalfOfBoard(ChessPiece.Color.WHITE);
        blackPieces = fillHalfOfBoard(ChessPiece.Color.BLACK);
    }
}