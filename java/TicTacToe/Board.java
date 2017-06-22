package TicTacToe;

import Util.Util;

import java.util.Arrays;
import java.util.Collections;

import Util.Point;

/**
 *
 */
public class Board {
	private static final int DIMENSION = 3;

	private static final Mark STARTING_MARK = Mark.X;

	private Mark[][] marks;
	private Mark nextMark;

	public enum Status{
		UNFINISHED, X_WIN, O_WIN, DRAW
	}

	private Status status;

	@Override
	public String toString(){
		String o = "Board(";
		o += "marks: " + Arrays.deepToString(this.marks);
		o += " nextMark: " + this.nextMark;
		o += " status:" + this.status;
		o += ")";
		return o;
	}

	private static boolean isBoardTooSmall(Mark[][] marks){
		return marks.length == 0 || marks[0].length == 0;
	}

	private static boolean isSquare(Mark[][] marks){
		if(isBoardTooSmall(marks)){
			Util.error("Board's size is too small",Util.getFileName(),Util.getLineNumber());
		}
		return marks.length == marks[0].length;
	}

	public static void checkMarksForFormattingError(Mark[][] marks){
		if(!isSquare(marks)){
			Util.error("Board not a square",Util.getFileName(),Util.getLineNumber());
		}
	}

	/**
	 * Rotates a given board (represented as an array of Marks) clockwise by 90 degrees
	 * @param marks the board to rotate
	 * @return the rotated board
	 */
	public static Mark[][] rotate90(Mark[][] marks){
		checkMarksForFormattingError(marks);
		int dimension = marks.length;
		Mark[][] rotated = new Mark[dimension][dimension];
		for(int y = 0; y < marks.length; y++){
			for(int x = 0; x < marks[y].length; x++){
				rotated[x][y] = marks[y][x];
			}
		}
		for(Mark[] row: rotated){
			Collections.reverse(Arrays.asList(row));
		}

		return rotated;
	}

	/**
	 * Rotates the board clockwise by 90 degrees
	 */
	public void rotate90(){
		this.marks = Board.rotate90(this.marks);
	}

	private static boolean checkRowsForWin(Mark[][] marks, Mark mark){
		checkMarksForFormattingError(marks);
		for(Mark[] row: marks){
			boolean rowWin = true;
			for(Mark a: row){
				if(a != mark){
					rowWin = false;
				}
			}
			if(rowWin){
				return true;
			}
		}
		return false;
	}

	private static boolean checkDiagonalsForWin(Mark[][] marks, Mark mark){
		checkMarksForFormattingError(marks);
		boolean win = true;
		for(int i = 0; i < marks.length; i++){
			if(marks[i][i] != mark){
				win = false;
				break;
			}
		}
		return win;
	}

	private static boolean checkForDraw(Mark[][] marks){
		boolean draw = true;
		for(Mark[] row : marks){
			for(Mark a: row){
				if(a == Mark.NULL){
					draw = false;
					break;
				}
			}
		}
		return draw;
	}

	private static boolean checkForWin(Mark[][] marks, Mark[][] rotated, Mark mark){
		return checkRowsForWin(marks,mark) || checkRowsForWin(rotated,mark) || checkDiagonalsForWin(marks,mark) || checkDiagonalsForWin(rotated,mark);
	}

	private static Status findStatus(Mark[][] marks){
		checkMarksForFormattingError(marks);
		{
			Mark[][] rotated = rotate90(marks);
			if(checkForWin(marks,rotated,Mark.X)){
				return Status.X_WIN;
			}
			if(checkForWin(marks,rotated,Mark.O)){
				return Status.O_WIN;
			}
		}
		if(checkForDraw(marks)){
			return Status.DRAW;
		}
		return Status.UNFINISHED;
	}

	private void updateStatus(){
		if(this.status == Status.UNFINISHED){
			this.status = findStatus(this.marks);
		}
	}

	public Status getStatus(){
		return this.status;
	}

	public Mark getNextMark(){
		return this.nextMark;
	}

	private boolean inBounds(int x, int y){
		return (x >= 0 && x < DIMENSION) && (y >= 0 && y < DIMENSION);
	}

	public static Point<Integer> intToPoint(int pos, int dimension){
		int x = pos % dimension;
		int y =(int)Math.floor((double)pos / (double)dimension);
		return new Point<Integer>(x,y);
	}

	public static Point<Integer> intToPoint(int pos){
		return intToPoint(pos,DIMENSION);
	}

	public Mark[][] getMarks(){
		return this.marks;
	}

	public void set(int pos){
		set(intToPoint(pos,DIMENSION));
	}

	public void set(Point<Integer> pos){
		set(pos.getX(),pos.getY());
	}

	public void set(int x, int y){
		set(x,y,nextMark);
		nextMark = Mark.toggle(nextMark);
	}

	public boolean isOccupied(int x,int y){
		return this.marks[y][x] != Mark.NULL;
	}

	public void set(int x, int y, Mark mark){
		if(!inBounds(x,y)){
			Util.error("Cannot set position (" + x + "," + y + ")--out of bounds",Util.getFileName(),Util.getLineNumber());
		}
		if(this.status != Status.UNFINISHED){
			Util.error("Cannot set position (" + x + "," + y + ")--game finished with status " + this.status.toString(),Util.getFileName(),Util.getLineNumber());
		}
		if(isOccupied(x,y)){
			Util.error("Cannot set position (" + x + "," + y + ")--already occupied by an " + this.marks[y][x].toString(),Util.getFileName(),Util.getLineNumber());
		}
		this.marks[y][x] = mark;
		updateStatus();
	}

	public String toPrintable(){
		final String EMPTY_LINE = "   |   |   ";
		final String DIVIDER = "---|---|---\n";
		String o = "";
		for(int i = 0; i < this.marks.length; i++){
			if(i != 0 && i < this.marks.length){
				o += DIVIDER;
			}
			o += EMPTY_LINE + "\n";
			for(int j = 0; j < this.marks[i].length; j++){
				o += " " + this.marks[i][j].toString() + " ";
				if(j < this.marks[i].length - 1){
					o += "|";
				} else {
					o += "\n";
				}
			}
			o += EMPTY_LINE;
			if(i < this.marks.length - 1){//don't add a newline at the very end
				o += "\n";
			}
		}
		return o;
	}

	public Board(Status status){
		this();
		this.status = status;
	}

	public Board(){
		this(DIMENSION);
	}

	public Board(int dimension){
		this.marks = new Mark[dimension][dimension];
		for(int i = 0; i < this.marks.length; i++){
			for(int j = 0; j < this.marks[i].length; j++){
				this.marks[i][j] = Mark.NULL;
			}
		}
		this.status = Status.UNFINISHED;
		this.nextMark = STARTING_MARK;
	}
}
