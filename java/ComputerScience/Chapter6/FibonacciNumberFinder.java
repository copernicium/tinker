package ComputerScience.Chapter6;

import java.util.Scanner;

/**
 * Prompts the user for a number n and gets the nth fibonacci number
 *
 * @author Logan Traffas
 * @version 12/31/2016
 * assignment: Chapter06--Loops-6P6.2--Fibonacci Numbers
 */
public class FibonacciNumberFinder{
	/**
	 * Calculates the fibonacci number at a given index
	 * @param n the index of the wanted fibonacci number
	 * @return the fibonacci number at the nth index
	 */
	public static int getFibonacciNumberAt(int n){
		int f1 = 1, f2 = 0;
		for(int i = 1; i < n; i++){
			int newF2 = f1;
			f1 = f1 + f2;
			f2 = newF2;
		}
		return f1;
	}

	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		System.out.print("Input a number n to get the nth fibonacci number: ");
		int n = in.nextInt();
		System.out.println("The Fibonacci number is " + FibonacciNumberFinder.getFibonacciNumberAt(n));
	}
}
