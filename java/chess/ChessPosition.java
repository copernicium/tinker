/**
 * Represents a position of the chess board
 */
public class ChessPosition{
	private static class Dimension{
		protected int value;
		public static final int DIMENSION=8;
		
		public void set(int value){
			assert(value < DIMENSION);
			this.value = value;
		}
		
		public Dimension(){
			value = 0;
		}
		public Dimension(int value){
			set(value);
		}
	}
    public static class Column extends Dimension{
		public static final int A=0;
		public static final int B=1;
		public static final int C=2;
		public static final int D=3;
		public static final int E=4;
		public static final int F=5;
		public static final int G=6;
		public static final int H=7;
		public Column(){
			super();
		}
		public Column(int value){
			super(value);
		}
	};
    private Column column;
	public static class Row extends Dimension{
		public static final int _1=0;
		public static final int _2=1;
		public static final int _3=2;
		public static final int _4=3;
		public static final int _5=4;
		public static final int _6=5;
		public static final int _7=6;
		public static final int _8=7;
		public Row(){
			super();
		}
		public Row(int value){
			super(value);
		}
	}
    private Row row;
    
    public Column getColumn(){
        return column;
    }
    
    public Row getRow(){
        return row;
    }
    
    public ChessPosition(){
        column.set(Column.A);
        row.set(Row._1);
    }
    public ChessPosition(Column column,Row row){
        this.column = column;
        this.row = row;
    }
}