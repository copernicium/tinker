package TicTacToe;

import MySystem.MySystem;

/**
 * Represents a tic tac toe board
 */
public class Board{
	public enum Mark{X,O,BLANK}
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

		private int value;

		public int get(){
			return this.value;
		}

		public boolean equals(Object o){
			if(o.getClass() != this.getClass()) return false;
			Position b = (Position)o;
			return b.get() == this.get();
		}

		public void set(int value){
			MySystem.myAssert(value >= _1 && value <= _9,MySystem.getFileName(),MySystem.getLineNumber());
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

	public static class Status{
		private Mark mark;
		private Position position;

		public void set(Mark mark){
			MySystem.myAssert(this.mark == Mark.BLANK,MySystem.getFileName(),MySystem.getLineNumber());
			this.mark = mark;
		}

		public boolean equals(Object o){
			if(o.getClass() != this.getClass()) return false;
			Status b = (Status)o;
			if(b.getPosition() != this.getPosition()) return false;
			return b.get() == this.get();
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

	public void set(Position position,Mark mark){
		for(Status status: this.statuses){
			if(status.getPosition().equals(position)){
				status.set(mark);
				return;
			}
		}
		MySystem.nyi(MySystem.getFileName(),MySystem.getLineNumber());
	}

	@Override
	public String toString(){
		return "";//TODO
	}

	public void print(){
		//TODO
	}

	private Status[] statuses;

	public Mark get(Position position){
		for(Status status: this.statuses){
			if(status.getPosition().equals(position)) return status.get();
		}
		MySystem.nyi(MySystem.getFileName(),MySystem.getLineNumber());
		return Mark.BLANK;//should never reach this line
	}

	public Board(){
		this.statuses = new Status[Position.POSITIONS];
		for(int i = 0; i < Position.POSITIONS; i++){
			this.statuses[i] = new Status(new Position(i));
		}
	}
}
