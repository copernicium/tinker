package ComputerScience.Chapter4;
import java.util.Scanner;
/*"Write a program that reads a number between 1,000 and 999,999
from the user, where the user enters a comma in the input.
Then print the number without a comma.*/
/**
 * Takes in user input with commas and deletes them, returning an int
 *
 * @author Logan Traffas
 * @version 11/1/2016
 * assignment: Chapter 04--Fundamental Data Types--E4.12
 */
public class CommaDeleter {
	/**Deletes all commas in a string
	 * @param INPUT the user input
	 * @return a string with no commas in it
	 */
	public String deleteCommas(final String INPUT){
		String noCommas = "";
		for(int i = 0; i < INPUT.length(); i++) {
			if(INPUT.charAt(i) != ',') noCommas += INPUT.charAt(i);
		}
		return noCommas;
	}
	/**Limited to the 1,000 to 999,999 range, deletes the comma in a string and converts it to an int
	 * @param INPUT the user input
	 * @return an with no commas in it
	 */
	public int commasToInt(final String INPUT){
		String firstHalf = "", secondHalf ="";
		int converted = 0;
		final int SECOND_HALF_LENGTH = 3;
		int firstHalfLength = INPUT.length()-SECOND_HALF_LENGTH-1;
		firstHalf = INPUT.substring(0,firstHalfLength);
		secondHalf = INPUT.substring(INPUT.length()-SECOND_HALF_LENGTH,INPUT.length());
		converted = Integer.parseInt(firstHalf + secondHalf);
		return converted;
	}
	/**Prompts the user to input a number and returns the string
	 * @return the user's input number string (with commas)
	 */
	public String getStringInt(){
		Scanner input = new Scanner(System.in);
		System.out.print("Please enter an integer between 1,000 and 999,999: ");
		String stringInt = input.next();
		System.out.println("\n");
		return stringInt;
	}

	public CommaDeleter(){}

	public static void main(String[] args){
		CommaDeleter commaDeleter = new CommaDeleter();
		String input = commaDeleter.getStringInt();
		System.out.println(commaDeleter.deleteCommas(input));
		System.out.println(commaDeleter.commasToInt(input));
	}
}
