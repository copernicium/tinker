package chess;

import java.util.Vector;
/**
 * A pawn piece
 */
public class Pawn extends ChessPiece
{
    private boolean firstMove;//pawns can move differently on their first move

	private static final Type type = Type.PAWN;

	/**
	 * Fetches the type of this piece
	 * @return the type of this piece
	 */
	@Override
	public Type getType(){
		return Pawn.type;
	}

	@Override
	public String toString(){
		return "ChessPiece( type:" + this.getType() + " color:" + this.color + " position:" + this.position + " alive:" + this.alive + " firstMove:" + this.firstMove + ")";
	}

	/**
	 * The symbol to print given the type
	 * @return the letter representing this chess piece
	 */
	@Override
    public String print(){
        return "P";
    }
    @Override
    public void updatePossibleMoves(ChessPieces chessPieces){//TODO: en passant and maybe promotion
		ChessPiece original = ChessPiece.makePiece(this);
		Vector<ChessPosition> possibleMoves = new Vector<>(0);
		int direction = (this.getColor() == ChessPiece.Color.WHITE) ? 1 : -1;
		{
			final int DIAGONALDIST = 1;
			ChessPosition.Tester testPosition = new ChessPosition.Tester(this.position.getRow().get() + DIAGONALDIST,this.position.getColumn().get()+(DIAGONALDIST*direction));
			if(testPosition.inBounds() && chessPieces.isOccupied(new ChessPosition(testPosition),Color.not(this.color))){
				possibleMoves.addElement(new ChessPosition(testPosition));
			}
		}
		{
			final int DIAGONALDIST = -1;
			ChessPosition.Tester testPosition = new ChessPosition.Tester(this.position.getRow().get() + DIAGONALDIST,this.position.getColumn().get()+(DIAGONALDIST*direction));
			if(testPosition.inBounds() && chessPieces.isOccupied(new ChessPosition(testPosition),Color.not(this.color))){
				possibleMoves.addElement(new ChessPosition(testPosition));
			}
		}
		if(this.getFirstMove()){
			final int INITIALTWOSQUAREADVANCE = 2;
			ChessPosition.Tester testPosition = new ChessPosition.Tester(this.position.getRow().get()+(INITIALTWOSQUAREADVANCE * direction),this.position.getColumn().get());
			if(testPosition.inBounds() && !chessPieces.isOccupied(new ChessPosition(testPosition),this.color)){
				possibleMoves.addElement(new ChessPosition(testPosition));
			}
		}
		{
			final int REGULARMOVEMENT = 1;
			ChessPosition.Tester testPosition = new ChessPosition.Tester(this.position.getRow().get()+(REGULARMOVEMENT*direction),this.position.getColumn().get());
			if(testPosition.inBounds() && !chessPieces.isOccupied(new ChessPosition(testPosition),this.color)){
				possibleMoves.addElement(new ChessPosition(testPosition));
			}
		}
		if(!original.equalsByType(this)) MySystem.error("orignal:" + original.toString() + " this:" + this.toString(),MySystem.getFileName(),MySystem.getLineNumber());//TODO: remove or expand this test
		MySystem.myAssert(original.equalsByType(this),MySystem.getFileName(),MySystem.getLineNumber());
        this.possibleMoves = possibleMoves;
	}

    public boolean getFirstMove(){
		return this.firstMove;
	}

	/**
	 * Checks for equality by value with a given piece
	 * @param b the piece to be compared to
	 * @return true if the pieces are equal by value
	 */
    @Override
	public boolean equals(final ChessPiece b){
		if(!(b instanceof Pawn)) return false;
		Pawn testPiece = (Pawn)b;
		if(this.getType() != testPiece.getType()) return false;
		if(this.getColor()!=testPiece.getColor()) return false;
		if(!this.getPosition().equals(testPiece.getPosition())) return false;
		if(this.getAlive() != testPiece.getAlive()) return false;
		if(this.getFirstMove() != testPiece.getFirstMove()) return  false;
		if(!this.getPossibleMoves().equals(testPiece.getPossibleMoves())) return false;
		if(!this.getLimitedMoves().equals(testPiece.getLimitedMoves())) return false;
		return true;
	}

    @Override
    public void move(ChessPosition newPosition, ChessPieces chessPieces){
		for(ChessPosition a: this.getPossibleMoves()){
            if(newPosition.equals(a)){
                this.position = newPosition;
                this.firstMove = false;
                return;
            }
        }
		MySystem.error("Move failed: Not a valid move: trying to move from " + this.getPosition().toString() + " to " + newPosition.toString(),MySystem.getFileName(),MySystem.getLineNumber());
	}

	public Pawn(Pawn toCopy) {
		super();
		this.position = new ChessPosition(toCopy.position);
		this.alive = toCopy.alive;
		this.color = toCopy.color;
		this.firstMove = toCopy.firstMove;
	}

    public Pawn(){
        super();
        firstMove = true;
    }
	public Pawn(ChessPiece chessPiece){
		this(chessPiece.getPosition(),chessPiece.getColor(),chessPiece.getPossibleMoves(),chessPiece.getLimitedMoves());
		if(chessPiece instanceof Pawn){
			Pawn toCopy = (Pawn)chessPiece;
			this.position = new ChessPosition(toCopy.position);
			this.alive = toCopy.alive;
			this.color = toCopy.color;
			this.firstMove = toCopy.firstMove;
			this.possibleMoves = toCopy.getPossibleMoves();
			this.limitedMoves = toCopy.getLimitedMoves();
		}
	}
    public Pawn(ChessPosition position,Color color,Vector<ChessPosition> possibleMoves,Vector<ChessPosition> limitedMoves){
        super(position,color,possibleMoves,limitedMoves);
		this.firstMove = (position.getRow().equals(ChessBoard.PiecePlacement.Row.PAWN )|| position.getRow().equals(ChessBoard.mirror(ChessBoard.PiecePlacement.Row.PAWN)));
    }
}