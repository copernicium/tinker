package ComputerScience.ProblemOfTheWeek;

import java.util.Scanner;
/*
Write some code code that given a string, returns a "rotated right 2" version
where the last 2 chars are moved to the start. Assume the string length will be at least 2.

right2("Hello") → "loHel"
right2("java") → "vaja"
right2("Hi") → "Hi"

Hint: You might want to use the substring() method discussed on page 159 of the textbook.
 */
/**
 *
 *
 * @author Logan Traffas
 * @version 11/3/2016
 * assignment:  PROBLEM OF THE WEEK 11/1 - 11/4
 */
public class StringRotater{
	String string;

	/**
	 * Prompts the user to set the string that will be rotated
	 */
	public void setString(){
		Scanner in = new Scanner(System.in);
		System.out.print("Input string to rotate right 2: ");
		this.string = in.next();
	}
	/** Moves the last two characters of a string to the front
	 * @return the string that has been rotated
	 */
	public String rotateString(){
		final int CHARS_TO_ROTATE = 2;
		String newFront = string.substring(string.length()-CHARS_TO_ROTATE);
		String newBack = string.substring(0,string.length()-CHARS_TO_ROTATE);
		return newFront + newBack;
	}

	public StringRotater(){
		string = "";
	}

	public static void main(String[] args){
		StringRotater stringRotater = new StringRotater();
		stringRotater.setString();
		System.out.println("Rotated to: " + stringRotater.rotateString());
	}

}
