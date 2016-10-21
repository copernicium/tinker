/**
 * Represents a position of the chess board
 */
public class ChessPosition{    
    public enum Column{A,B,C,D,E,F,G,H,DIMENSION};
    private  Column column;
    public enum Row{_1,_2,_3,_4,_5,_6,_7,_8,DIMENSION};   
    private Row row;
    
    public Column getColumn(){
        return column;
    }
    
    public Row getRow(){
        return row;
    }
    
    ChessPosition(){
        column = Column.A;
        row = Row._1;
    }
    ChessPosition(Column column,Row row){
        this.column = column;
        this.row = row;
    }
}