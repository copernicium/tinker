package Chess;
import MySystem.*;

/**
 * Represents a position of the Chess board
 */
public class ChessPosition implements Comparable<ChessPosition>	{
	/**
	 * The template used in creation of the Column and Row classes
	 */
    private static abstract class Dimension implements Comparable<Dimension>{
        protected int value;
        public static final int DIMENSION=8;

		@Override
		public int compareTo(Dimension b){
			return Integer.compare(this.get(),b.get());
		}

		/**
		 * Fetches the value of the Dimension
		 * @return the value of the Dimension
		 */
		public int get(){
            return value;
        }

		/**
		 * The profile of the toString() method implemented in its subclasses
		 * @return not applicable
		 */
		public abstract String toString();

		/**
		 * Checks for equality with a given Dimension
		 * @param o the Dimension to compare to
		 * @return true if the two are equal (by value)
		 */
		public boolean equals(Object o){
			if(o.getClass() != this.getClass()) return false;
			Dimension b = (Dimension)o;
			return this.get()==b.get();
		}

		/**
		 * Changes the value of the Dimension
		 * @param value the value to set the Dimension to
		 */
		public void set(int value){
            MySystem.myAssert(value < DIMENSION && value >= 0, MySystem.getFileName(), MySystem.getLineNumber());
            this.value = value;
        }

		/**
		 * Creates a new Dimension
		 */
		public Dimension(){
            value = 0;
        }

		/**
		 * Creates a new Dimension given a value
		 * @param value the value to initialize the Dimension with
		 */
		public Dimension(int value){
            set(value);
        }
    }

	/**
	 * Used to represent a column (aka file) on the Chess board
	 */
	public static class Column extends Dimension{//called files
        public static final int A=0;
        public static final int B=1;
        public static final int C=2;
        public static final int D=3;
        public static final int E=4;
        public static final int F=5;
        public static final int G=6;
        public static final int H=7;

		/**
		 * Used to copy a Column by value
		 * @param toCopy the Column to copy
		 */
		public Column(Column toCopy){
			this.value = toCopy.value;
		}

		/**
		 * Converts the column's value to the appropriate letter
		 * @return the letter of the column
		 */
		@Override 
		public String toString(){
			final String[] COLUMN_NAMES = {"A","B","C","D","E","F","G","H","DIMENSION"};
			return COLUMN_NAMES[get()];
		}

		/**
		 * Converts a character to a column
		 * @param INPUT the character to convert
		 * @return a column set to the appropriate value
		 */
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
					MySystem.nyi(MySystem.getFileName(), MySystem.getLineNumber());
			}
			return new Column();
		}

		/**
		 * Creates a new Column
		 */
        public Column(){
            super();
        }
		/**
		 * Creates a new Column given a value
		 * @param value the value to initialize the Column with
		 */
        public Column(int value){
            super(value);
        }
    }

	/**
	 * Represents a row (aka rank) of the Chess board
	 */
    public static class Row extends Dimension{//called ranks
        public static final int _1=0;
        public static final int _2=1;
        public static final int _3=2;
        public static final int _4=3;
        public static final int _5=4;
        public static final int _6=5;
        public static final int _7=6;
        public static final int _8=7;

		/**
		 * Used to copy a Row by value
		 * @param toCopy the Row to copy
		 */
		public Row(Row toCopy){
			this.value = toCopy.value;
		}

		/**
		 * Converts the row's value to the appropriate number value
		 * @return the number of the row
		 */
		public String toString(){
			final String[] ROW_NAMES = {"1","2","3","4","5","6","7","8","DIMENSION"};
			return ROW_NAMES[get()];
		}

		/**
		 * Converts a character to a row
		 * @param INPUT the character to convert
		 * @return a row set to the appropriate value
		 */
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
				default: MySystem.myAssert(false, MySystem.getFileName(), MySystem.getLineNumber());
			}
			return new Row();
		}

		/**
		 * Creates a new Row given a value
		 */
        public Row(){
            super();
        }
		/**
		 * Creates a new Row given a value
		 * @param value the value to initialize the Row with
		 */
        public Row(int value){
            super(value);
        }
    }

	/**
	 * Used to test if given values are within the bounds of a Chess board
	 */
	public static class Tester{
		private int column;
		private int row;

		/**
		 * Fetches the value of the row
		 * @return the value of the row
		 */
		public int getRow(){
			return row;
		}

		/**
		 * Fetches the value of the column
		 * @return the value of the column
		 */
		public int getColumn(){
			return column;
		}

		/**
		 * Checks to see if a given position (Tester) is within the bounds of the Chess board
		 * @return true if it within bounds
		 */
		public boolean inBounds(){
			return ChessPosition.inBounds(this.getRow(),this.getColumn());
		}

		/**
		 * Checks for equality by value
		 * @param o the Tester to compare to
		 * @return true it they are equal
		 */
		public boolean equals(Object o){
			if(o.getClass() != this.getClass()) return false;
			Tester b = (Tester)o;
			return this.row == b.getRow() && this.column == b.getColumn();
		}

		public void set(int row,int column){
			this.row = row;
			this.column = column;
		}
		public Tester(){
			this(0,0);
		}
		/**
		 * Creates a new Tester given a row and column
		 * @param row the row to initialize Tester with
		 * @param column the column to initialize Tester with
		 */
		public Tester(int row,int column){
			this.row = row;
			this.column = column;
		}
	}
	private Column column;
    private Row row;

	/**
	 * Used to copy a ChessPosition by value
	 * @param toCopy the ChessPosition to copy
	 */
	public ChessPosition(ChessPosition toCopy){
		this.column = new Column(toCopy.column);
		this.row = new Row(toCopy.row);
	}

	/**
	 * Tests conversion of a String to a ChessPosition
	 * @param INPUT the String to check
	 * @return true if the String can be converted
	 */
	public static boolean testConversion(final String INPUT){
		if(INPUT.length() != 2) return false;
		{
			final int COLUMN_LOC = 0;
			char testColumn = Character.toUpperCase(INPUT.charAt(COLUMN_LOC));
			final char[] VALID_COLUMNS = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'};
			for(int i = 0; i < VALID_COLUMNS.length; i++){
				if(testColumn == VALID_COLUMNS[i]) break;
				if(i == VALID_COLUMNS.length - 1) return false;
			}
		}
		{
			final int ROW_LOC = 1;
			char testRow = INPUT.charAt(ROW_LOC);
			final char[] VALID_ROWS = {'1', '2', '3', '4', '5', '6', '7', '8'};
			for(int i = 0; i < VALID_ROWS.length; i++){
				if(testRow == VALID_ROWS[i]) break;
				if(i == VALID_ROWS.length - 1) return false;
			}
		}
		return true;
	}

	@Override
	public int compareTo(ChessPosition b){
		if(this.getColumn().compareTo(b.getColumn()) != 0 ) return this.getColumn().compareTo(b.getColumn());
		return this.getRow().compareTo(b.getRow());
	}

	/**
	 * Converts a String to a ChessPosition
	 * @param input the String to convert
	 * @return the corresponding ChessPosition
	 */
	public static ChessPosition toChessPosition(String input){
		MySystem.myAssert(input.length() == 2, MySystem.getFileName(), MySystem.getLineNumber());//make sure its only two characters
		final int COLUMN_LOC = 0, ROW_LOC = 1;
		return new ChessPosition(Row.toRow(input.charAt(ROW_LOC)),Column.toColumn(input.charAt(COLUMN_LOC)));
	}

	/**Checks for equality by value
	 * @param o the ChessPosition to compare to
	 * @return true if they are equal
	 */
    public boolean equals(Object o){
		if(o.getClass() != this.getClass()) return false;
		ChessPosition b = (ChessPosition)o;
		if(!b.getRow().equals(this.getRow())) return false;
		if(!b.getColumn().equals(this.getColumn())) return false;
		return true;
    }

	/**
	 * Checks to see if a given position (row and column) is within the bounds of the Chess board
	 * @param row the row of the position to check
	 * @param column the column of the position to check
	 * @return true if it is within bounds
	 */
	private static boolean inBounds(int row, int column){
        if(column >= ChessPosition.Column.DIMENSION || column < 0) return false;
        if(row >= ChessPosition.Row.DIMENSION || row < 0) return false;
        return true;
    }

	/**
	 * Converts the ChessPosition to the proper syntax
	 * @return the String corresponding to this ChessPosition
	 */
	public String toString(){
		return column.toString() + row.toString();
	}

	/**
	 * Fetches the column of this position
	 * @return the column of this position
	 */
	public Column getColumn(){
        return column;
    }

	/**
	 * Fetches the row of this position
	 * @return the row of this position
	 */
	public Row getRow(){
        return row;
    }

	/**
	 * Creates a new instance of a ChessPosition using default values
	 */
	public ChessPosition(){
		this(new Row(Row._1),new Column(Column.A));
    }

	/**
	 * Creates a new instance of a ChessPosition given a row and a column
	 * @param row the row to instantiate it with
	 * @param column the column to instantiate it with
	 */
    public ChessPosition(Row row,Column column){
		this.column = new Column(column);
		this.row = new Row(row);
    }

	/**
	 * Creates a new instance of a ChessPosition given a row and a column
	 * @param row the row to instantiate it with
	 * @param column the column to instantiate it with
	 */
	public ChessPosition(int row,int column){
		MySystem.myAssert(inBounds(row,column), MySystem.getFileName(), MySystem.getLineNumber());
		this.column = new Column(column);
		this.row = new Row(row);
	}

	/**
	 * Creates a new instance of a ChessPosition given a Tester
	 * @param tester the Tester to convert to a ChessPosition
	 */
	public ChessPosition(ChessPosition.Tester tester){
		this(tester.getRow(),tester.getColumn());
	}
}
