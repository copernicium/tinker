package ComputerScience.Chapter6;

import java.util.Scanner;
import java.util.Vector;

/**
 * Prompts the user for a series of integers and then prints out information about that collection
 *
 * @author Logan Traffas
 * @version 12/31/2016
 * assignment: Chapter06--Loops-6E6.2--Loops and integer operations
 */
public class IntAnalyzer{

	/**
	 * Searches through a string and returns a vector of all the integers in it separated by spaces
	 * @param input the string to search through
	 * @return all of the ints in the string
	 */
	public static Vector<Integer> toVector(String input){
		input = input.trim();
		if(input.equals("")){
			System.out.println("No input");
			return new Vector<>();
		}

		Vector<String> strings = new Vector<>();
		{
			String s = "";
			for(char c: input.toCharArray()){
				s += c;
				s = s.trim();
				if(c == ' ' && !s.equals("")){
					strings.add(s);
					s = "";
				}
			}
			strings.add(s);//add the last int
		}

		Vector<Integer> ints = new Vector<>();
		for(String str: strings){
			ints.add(Integer.parseInt(str));
		}

		return ints;
	}

	/**
	 * Prompts the user to enter a series of ints for analysis
	 * @return the ints entered by the user
	 */
	public static Vector<Integer> getIntsInput(){
		Scanner in = new Scanner(System.in);
		System.out.print("Input a set of integers (ex \"1 5 8 14 2\"):");
		String str = in.nextLine().trim();
		return toVector(str);
	}

	/**
	 * Searches through a vector of integers and prints the largest and smallest integers in it
	 * @param ints
	 */
	public static void printLargestAndSmallest(Vector<Integer> ints){
		int smallest = ints.elementAt(0);
		for(int a: ints){
			if(a < smallest) smallest = a;
		}

		int largest = ints.elementAt(0);
		for(int a: ints){
			if(a > largest) largest = a;
		}

		System.out.println("The largest integer in " + ints + " is " + largest + ". The smallest is " + smallest);
	}

	/**
	 * Prints the total number of even ints and odd ints in a vector of integers
	 * @param ints the integers to examine
	 */
	public static void printNumberOfEvenAndOdds(Vector<Integer> ints){
		int odds = 0, evens = 0;
		for(int a: ints){
			if(a % 2 == 0) evens++;
			else odds++;
		}
		System.out.println("In " + ints + " there are "+ evens + " even number(s) and " + odds + " odd number(s).");
	}

	/**
	 * Totals the number of ints as it iterates through them and prints the cumulative sum
	 * @param ints the integers to analyze
	 */
	public static void printCululativeTotals(Vector<Integer> ints){
		int sum = 0;
		for(int a: ints){
			sum += a;
			System.out.println("Cumulative total: " + sum);
		}
	}

	/**
	 * Prints all of the adjacent duplicates in a vector of ints
	 * @param ints the ints to analyze
	 */
	public static void printAdjacentDulicates(Vector<Integer> ints){
		Vector<Integer> adjacentDuplicates = new Vector<>();
		int last = 0;
		boolean begun = false;
		for(int a: ints){
			if(a == last && begun) adjacentDuplicates.add(a);
			last = a;
			begun = true;
		}
		System.out.println("All of the adjacent duplicates in " + ints + " are " + adjacentDuplicates);
	}

	public static void main(String[] args){
		IntAnalyzer.printLargestAndSmallest(IntAnalyzer.getIntsInput());
		IntAnalyzer.printNumberOfEvenAndOdds(IntAnalyzer.getIntsInput());
		IntAnalyzer.printCululativeTotals(IntAnalyzer.getIntsInput());
		IntAnalyzer.printAdjacentDulicates(IntAnalyzer.getIntsInput());
	}
}
