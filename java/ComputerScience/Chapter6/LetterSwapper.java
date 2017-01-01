package ComputerScience.Chapter6;

import MySystem.MySystem;
import java.util.concurrent.ThreadLocalRandom;
import java.util.Scanner;

/**
 * Prompts the user for a string and then randomly permutes it
 *
 * @author Logan Traffas
 * @version 12/31/2016
 * assignment: Chapter06--Loops-6E6.7--Swap letters
 */
public class LetterSwapper{
	/**
	 * Swaps random letters in a string
	 * @param input the string to be garbled
	 */
	public static void permute(final String input){
		if(input.length() < 2){
			MySystem.error("Error: length of input string is less than two meaning that two random numbers cannot be selected such that the second is greater than the first",MySystem.getFileName(),MySystem.getLineNumber());
		}

		final int max = input.length();
		String swappedString = input;
		for(int i = 0; i < input.length(); i++){
			int min = 0;
			int rand1 = ThreadLocalRandom.current().nextInt(min, max - 1);//minus one to make certain that rand2 can be larger than it
			min = rand1 + 1;//so that rand2 > rand1
			int rand2 = ThreadLocalRandom.current().nextInt(min, max);

			swappedString = swappedString.substring(0,rand1) + swappedString.charAt(rand2) + swappedString.substring(rand1 + 1,rand2) + swappedString.charAt(rand1) + swappedString.substring(rand2 + 1);
		}
		System.out.println("The swapped string after permutation is \"" + swappedString +"\"");
	}

	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		System.out.print("Input a string of letters: ");
		LetterSwapper.permute(in.next().trim());
	}
}
