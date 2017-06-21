package TicTacToe;

import Util.Util;

/**
 *
 */
public enum Mark {
	O, X, NULL;

	@Override
	public String toString(){
		switch (this){
			case O:
				return "O";
			case X:
				return "X";
			case NULL:
				return " ";
			default:
				Util.nyi(Util.getFileName(),Util.getLineNumber());
		}
		return null;//will never reach this line
	}

	public static Mark toggle(Mark mark){
		switch (mark){
			case X:
				return O;
			case O:
				return X;
			case NULL:
				Util.error("Cannot toggle " + mark.toString(),Util.getFileName(),Util.getLineNumber());
			default:
				Util.nyi(Util.getFileName(),Util.getLineNumber());
		}
		return null;//will never reach this line
	}
}
