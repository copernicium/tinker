import java.util.Vector;
/**
 * A class to represent a basic chess piece
 */
public class ChessPiece
{
    public enum Color{WHITE,BlACK};
    protected Color color;
    protected ChessPosition position;
    
    public Vector<ChessPosition> getNewPositions(){
        //TODO: finish this
        return new Vector<ChessPosition>(0);
    }
    
    public void move(ChessPosition position){
        for(ChessPosition a: getNewPositions()){
            if(position == a){
                this.position = a;
                return;
            }
        }
        System.err.println("Move failed. Not a valid move.");
        System.exit(1);
    }
    
    public Color getColor(){
        return color;
    }
    
    public ChessPosition getPosition(){
        return position;
    }
    
    public ChessPiece(){
        position = new ChessPosition();
        color = Color.WHITE;
    }
    public ChessPiece(ChessPosition position,Color color){
        this.position = position;
        this.color = color;
    }
}