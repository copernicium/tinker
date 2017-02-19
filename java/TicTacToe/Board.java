package TicTacToe;

import Util.Util;

/**
 * Represents a tic tac toe board
 */
public class Board{
	public enum Mark{
		X,O,BLANK;
		public String toString(){
			switch(this){
				case X:
					return "X";
				case O:
					return "O";
				case BLANK: return " ";
				default:
					Util.nyi(Util.getFileName(), Util.getLineNumber());
			}
			return "";//should never reach this line
		}
	}

	public enum GameStatus{IN_PROGRESS,X_WIN,O_WIN,DRAW}

	private GameStatus gameStatus;

	public static class Position{
		public static final int _1 = 0;
		public static final int _2 = 1;
		public static final int _3 = 2;
		public static final int _4 = 3;
		public static final int _5 = 4;
		public static final int _6 = 5;
		public static final int _7 = 6;
		public static final int _8 = 7;
		public static final int _9 = 8;
		public static final int POSITIONS = 9;
		/*Position laid out like so:
		"   |   |   "
		" 1 | 2 | 3 "
		"___|___|___"
		"   |   |   "
		" 4 | 5 | 6 "
		"___|___|___"
		"   |   |   "
		" 7 | 8 | 9 "
		"   |   |   "
		*/
		private int value;

		public static boolean canParsePosition(String in){
			int value = 0;
			try{
				value = Integer.parseInt(in);
			} catch(Exception NumberFormatException){
				return false;
			}

			value --; //- 1 because position is 0-indexed while the user interface is not
			return (value >= Position._1 && value < Position.POSITIONS);

		}

		public static Position parsePosition(String in){
			int value = Integer.parseInt(in);
			value --; //- 1 because position is 0-indexed while the user interface is not
			Util.myAssert(value >= Position._1 && value < Position.POSITIONS, Util.getFileName(), Util.getLineNumber());
			return new Position(value);
		}

		public int get(){
			return this.value;
		}

		public boolean equals(Object o){
			if(o.getClass() != this.getClass()) return false;
			Position b = (Position)o;
			return b.get() == this.get();
		}

		public void set(int value){
			Util.myAssert(value >= _1 && value <= _9, Util.getFileName(), Util.getLineNumber());
			this.value = value;
		}

		public Position(Position toCopy){
			this(toCopy.get());
		}

		public String toString(){
			final String[] POSITION_NAMES = {"1","2","3","4","5","6","7","8","9"};
			return POSITION_NAMES[this.get()];
		}

		public Position(int value){
			this.set(value);
		}

		public Position(){
			this.value = 0;
		}
	}

	private Mark playerTurn;

	private void switchPlayerTurn(){
		if(this.playerTurn == Mark.X){
			this.playerTurn = Mark.O;
			return;
		}
		if(this.playerTurn == Mark.O){
			this.playerTurn = Mark.X;
			return;
		}
		Util.nyi(Util.getFileName(), Util.getLineNumber());
	}

	public static class Status{
		private Mark mark;
		private Position position;

		public void set(Mark mark){
			Util.myAssert(this.mark == Mark.BLANK, Util.getFileName(), Util.getLineNumber());
			this.mark = mark;
		}

		public boolean equals(Object o){
			if(o.getClass() != this.getClass()) return false;
			Status b = (Status)o;
			if(b.getPosition() != this.getPosition()) return false;
			return b.get() == this.get();
		}

		public String toString(){
			String str = "Status(";
			str += "position:" + this.getPosition().toString();
			str += " mark:" + this.get();
			return str + ")";
		}

		public Mark get(){
			return this.mark;
		}

		public Position getPosition(){
			return this.position;
		}

		public Status(){
			this.mark = Mark.BLANK;
			this.position = new Position();
		}
		public Status(Position position){
			this.mark = Mark.BLANK;
			this.position = position;
		}
	}

	public GameStatus getGameStatus(){
		return this.gameStatus;
	}

	private void updateGameStatus(){
		//TODO
		this.gameStatus = GameStatus.IN_PROGRESS;//TODO remove
	}

	public void set(Position position){
		Util.myAssert(this.gameStatus == GameStatus.IN_PROGRESS, Util.getFileName(), Util.getLineNumber());
		for(Status status: this.statuses){
			if(status.getPosition().equals(position)){
				status.set(this.playerTurn);
				this.switchPlayerTurn();
				this.updateGameStatus();
				return;
			}
		}
		Util.nyi(Util.getFileName(), Util.getLineNumber());
	}

	@Override
	public String toString(){
		String str = "Board(";
		for(Status status: this.statuses){
			str += status.toString();
		}
		return str;
	}

	private String createMarkedRow(Position start,Mark[] marks){
		String str ="";
		final int NUMBER_OF_MARKS_IN_A_ROW = 3;
		Mark[] rowMarks = new Mark[NUMBER_OF_MARKS_IN_A_ROW];
		int arrayPos = 0;
		for(int i = 0; i < this.statuses.length; i++){
			if(i >= start.get() && i < start.get() + NUMBER_OF_MARKS_IN_A_ROW){
				rowMarks[arrayPos] = this.statuses[i].get();
				arrayPos ++;
			}
		}
		str += " " + rowMarks[0].toString() + " | " + rowMarks[1].toString() + " | " + rowMarks[2].toString() + " \n";
		return str;
	}

	public void print(){
		String str ="";
		Mark[] marks = new Mark[this.statuses.length];
		for(int i = 0; i < this.statuses.length; i++){
			marks[i] = this.statuses[i].get();
		}
		str += "   |   |   \n";
		str += createMarkedRow(new Position(Position._1),marks);
		str += "___|___|___\n";
		str += "   |   |   \n";
		str += createMarkedRow(new Position(Position._4),marks);
		str += "___|___|___\n";
		str += "   |   |   \n";
		str += createMarkedRow(new Position(Position._7),marks);
		str += "   |   |   \n";

		System.out.print(str);
	}

	public static void printLayout(){
		String str = "";
		str += "   |   |   \n";
		str += " 1 | 2 | 3 \n";
		str += "___|___|___\n";
		str += "   |   |   \n";
		str += " 4 | 5 | 6 \n";
		str += "___|___|___\n";
		str += "   |   |   \n";
		str += " 7 | 8 | 9 \n";
		str += "   |   |   \n";

		System.out.println(str);
	}

	private Status[] statuses;

	public Mark get(Position position){
		for(Status status: this.statuses){
			if(status.getPosition().equals(position)) return status.get();
		}
		Util.nyi(Util.getFileName(), Util.getLineNumber());
		return Mark.BLANK;//should never reach this line
	}

	public Board(){
		this.statuses = new Status[Position.POSITIONS];
		for(int i = 0; i < Position.POSITIONS; i++){
			this.statuses[i] = new Status(new Position(i));
		}
		this.playerTurn = Mark.X;
		this.gameStatus = GameStatus.IN_PROGRESS;
	}
}
