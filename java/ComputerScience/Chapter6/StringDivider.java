package ComputerScience.Chapter6;


import java.util.Scanner;

/**
 * Prompts the user for a string and then prints it out divided onto separate lines
 *
 * @author Logan Traffas
 * @version 12/31/2016
 * assignment: Chapter06-Loops-6E6.8--Print letters of a word on separate lines
 */
public class StringDivider{
	/**
	 * Prints each character in a string on a separate line
	 * @param str the string to divide into characters on separate lines
	 */
	public static void divideAndPrintDividedString(String str){
		for(char c: str.toCharArray()){
			System.out.println(c);
		}
	}
	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		System.out.print("Please input any string: ");
		String input = in.next();
		StringDivider.divideAndPrintDividedString(input);
	}
}
