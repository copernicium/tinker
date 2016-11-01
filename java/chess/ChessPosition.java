package chess;
/**
 * Represents a position of the chess board
 */
public class ChessPosition{
    private static abstract class Dimension{
        protected int value;
        public static final int DIMENSION=8;
        
        public int get(){
            return value;
        }
		
		public abstract String toString();
		
		public boolean equals(Dimension b){
			return this.get()==b.get();
		}
        
        public void set(int value){
            MySystem.myAssert(value < DIMENSION && value >= 0,MySystem.getFileName(),MySystem.getLineNumber());
            this.value = value;
        }
        
        public Dimension(){
            value = 0;
        }
        public Dimension(int value){
            set(value);
        }
    }
    public static class Column extends Dimension{//called files
        public static final int A=0;
        public static final int B=1;
        public static final int C=2;
        public static final int D=3;
        public static final int E=4;
        public static final int F=5;
        public static final int G=6;
        public static final int H=7;
		@Override 
		public String toString(){
			final String[] COLUMNNAMES = {"A","B","C","D","E","F","G","H","DIMENSION"};
			return COLUMNNAMES[get()];
		}
        public Column(){
            super();
        }
        public Column(int value){
            super(value);
        }
    };
    private Column column;
    public static class Row extends Dimension{//called ranks
        public static final int _1=0;
        public static final int _2=1;
        public static final int _3=2;
        public static final int _4=3;
        public static final int _5=4;
        public static final int _6=5;
        public static final int _7=6;
        public static final int _8=7;
		public String toString(){
			final String[] ROWNAMES = {"1","2","3","4","5","6","7","8","DIMENSION"};
			return ROWNAMES[get()];
		}
        public Row(){
            super();
        }
        public Row(int value){
            super(value);
        }
    }
	public static class Tester{
		private int column;
		private int row;
		public int getRow(){
			return row;
		}
		public int getColumn(){
			return column;
		}
		public boolean equals(Tester b){
			return this.row == b.getRow() && this.column == b.getColumn();
		}
		public Tester(){
			column = 0;
			row = 0;
		}
		public Tester(int row,int column){
			this.row = row;
			this.column = column;
		}
		public Tester(ChessPosition chessPosition){
			this.row = chessPosition.getRow().get();
			this.column = chessPosition.getColumn().get();
		}
	}
    private Row row;

	public static ChessPosition.Column mirror(ChessPosition.Column column){
		return new ChessPosition.Column(ChessPosition.Column.H - column.get());
	}

	public static ChessPosition.Row mirror(ChessPosition.Row row){
		return new ChessPosition.Row(ChessPosition.Row._8 - row.get());
	}

	public static ChessPosition toChessPosition(String input){
		MySystem.myAssert(input.length() == 2, MySystem.getFileName(),MySystem.getLineNumber());//make sure its only two characters
		Column column = new Column();
		switch(Character.toUpperCase(input.charAt(0))){
			case 'A':
				column = new Column(Column.A);
				break;
			case 'B':
				column = new Column(Column.B);
				break;
			case 'C':
				column = new Column(Column.C);
				break;
			case 'D':
				column = new Column(Column.D);
				break;
			case 'E':
				column = new Column(Column.E);
				break;
			case 'F':
				column = new Column(Column.F);
				break;
			case 'G':
				column = new Column(Column.G);
				break;
			case 'H':
				column = new Column(Column.H);
				break;
			default: MySystem.nyi(MySystem.getFileName(),MySystem.getLineNumber());
		}
		Row row = new Row();
		switch(input.charAt(1)){
			case '1':
				row = new Row(Row._1);
				break;
			case '2':
				row = new Row(Row._2);
				break;
			case '3':
				row = new Row(Row._3);
				break;
			case '4':
				row = new Row(Row._4);
				break;
			case '5':
				row = new Row(Row._5);
				break;
			case '6':
				row = new Row(Row._6);
				break;
			case '7':
				row = new Row(Row._7);
				break;
			case '8':
				row = new Row(Row._8);
				break;
			default: MySystem.myAssert(false,MySystem.getFileName(),MySystem.getLineNumber());
		}
		return new ChessPosition(row,column);
	}

    public boolean equals(ChessPosition b){
        if(!b.getRow().equals(this.getRow())) return false;
        if(!b.getColumn().equals(this.getColumn())) return false;
        return true;
    }
	
	public static boolean inBounds(int row, int column){
        if(column >= ChessPosition.Column.DIMENSION || column < 0) return false;
        if(row >= ChessPosition.Row.DIMENSION || row < 0) return false;
        return true;
    }
	
	public static boolean inBounds(Tester tester){
		return inBounds(tester.getRow(),tester.getColumn());
    }

	public String toString(){
		return column.toString() + row.toString();
	}
    
    public Column getColumn(){
        return column;
    }
    
    public Row getRow(){
        return row;
    }
    
    public ChessPosition(){
		this(Row._1,Column.A);
    }
    public ChessPosition(Row row,int column){
		this(row.get(),column);
	}
	public ChessPosition(int row,Column column){
		this(row,column.get());
	}
    public ChessPosition(Row row,Column column){
		this.column = new Column();
		this.row = new Row();
        this.column = column;
        this.row = row;
    }
    public ChessPosition(int row,int column){
		 MySystem.myAssert(inBounds(row,column),MySystem.getFileName(),MySystem.getLineNumber());
		 this.column = new Column();
		 this.row = new Row();
		 this.column.set(column);
		 this.row.set(row);
    }
	public ChessPosition(ChessPosition.Tester tester){
		this(tester.getRow(),tester.getColumn());
	}
}