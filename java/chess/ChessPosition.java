package chess;

import java.net.CacheRequest;

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

		public Column(Column toCopy){
			this.value = toCopy.value;
		}

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
    }

    public static class Row extends Dimension{//called ranks
        public static final int _1=0;
        public static final int _2=1;
        public static final int _3=2;
        public static final int _4=3;
        public static final int _5=4;
        public static final int _6=5;
        public static final int _7=6;
        public static final int _8=7;

		public Row(Row toCopy){
			this.value = toCopy.value;
		}

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
		public Tester(int row,int column){
			this.row = row;
			this.column = column;
		}
	}
	private Column column;
    private Row row;

	public ChessPosition(ChessPosition toCopy){
		this.column = new Column(toCopy.column);
		this.row = new Row(toCopy.row);
	}

	public static ChessPosition.Column mirror(ChessPosition.Column column){
		return new ChessPosition.Column(ChessPosition.Column.H - column.get());
	}

	public static ChessPosition.Row mirror(ChessPosition.Row row){
		return new ChessPosition.Row(ChessPosition.Row._8 - row.get());
	}

	public static boolean testConversion(final String INPUT){
		if(INPUT.length() != 2) return false;
		final int COLUMN_LOC = 0, ROW_LOC = 1;
		char testColumn = Character.toUpperCase(INPUT.charAt(COLUMN_LOC));
		final char[] VALID_COLUMNS = {'A','B','C','D','E','F','G','H'};
		boolean a = (char test, char[] VALIDS) -> {
			if(test == VALIDS[0]) return false;
			return true;
		}
		//if(!contains(testColumn,VALID_COLUMNS) return false;
		final char[] VALID_ROWS = {'1','2','3','4','5','6','7','8'};
		return false;
	}

	private static Column toColumn(final char INPUT){
		switch(Character.toUpperCase(INPUT)){
			case 'A':
				return new Column(Column.A);
			case 'B':
				return new Column(Column.B);
			case 'C':
				return new Column(Column.C);
			case 'D':
				return new Column(Column.D);
			case 'E':
				return new Column(Column.E);
			case 'F':
				return new Column(Column.F);
			case 'G':
				return new Column(Column.G);
			case 'H':
				return new Column(Column.H);
			default:
				MySystem.nyi(MySystem.getFileName(),MySystem.getLineNumber());
		}
		return new Column();
	}

	private static Row toRow(final char INPUT){
		switch(Character.toUpperCase(INPUT)){
			case '1':
				return new Row(Row._1);
			case '2':
				return new Row(Row._2);
			case '3':
				return new Row(Row._3);
			case '4':
				return new Row(Row._4);
			case '5':
				return new Row(Row._5);
			case '6':
				return  new Row(Row._6);
			case '7':
				return new Row(Row._7);
			case '8':
				return new Row(Row._8);
			default: MySystem.myAssert(false,MySystem.getFileName(),MySystem.getLineNumber());
		}
		return new Row();
	}

	public static ChessPosition toChessPosition(String input){
		MySystem.myAssert(input.length() == 2, MySystem.getFileName(),MySystem.getLineNumber());//make sure its only two characters
		final int COLUMN_LOC = 0, ROW_LOC = 1;
		return new ChessPosition(toRow(input.charAt(ROW_LOC)),toColumn(input.charAt(COLUMN_LOC)));
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