package ComputerScience.Chapter6;

import java.util.Scanner;

/**
 * Prompts the user for a month and day and then prints the season
 *
 * @author Logan Traffas
 * @version 12/14/2016
 * assignment: Chapter06--Loops-6R6.1
 */
public class SquareNumbers {
	/**
	 * Takes in an integer and prints all of the positive square numbers less than it
	 * @param n the upper limit
	 */
	public static void getSquaresLessThan(int n){
		int base = 0;
		final int POWER = 2;
		while(Math.pow(base,POWER) < n){
			System.out.println(base + " squared is " + Math.pow(base,2) + " which is less than " + n + ".");
			base++;
		}
	}

	/**
	 * Prints all positive numbers that are divisible by 10 and less than a given number
	 * @param n the upper limit
	 */
	public static void getNaturalNumbersLessThan(int n){
		int a = 0;
		final int DIVISOR = 10;
		while(a < n){
			if(a%10 == 0) System.out.println(a + " is positive, divisible by " + DIVISOR + ", and less than " + n + ".");
			a++;
		}
	}

	/**
	 * Given an integer it prints all of the powers of two less than it
	 * @param n upper limit
	 */
	public static void getPowersOfTwoLessThan(int n){
		int power = 0;
		final int BASE = 2;
		while(Math.pow(BASE,power) < n){
			System.out.println(Math.pow(BASE,power) + " is a power of " + BASE + " and is less than " + n + ".");
			power++;
		}
	}

	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		{
			System.out.print("Enter any positive integer: ");
			int a = in.nextInt();
			SquareNumbers.getSquaresLessThan(a);
		}
		{
			System.out.print("Enter any positive integer: ");
			int a = in.nextInt();
			SquareNumbers.getNaturalNumbersLessThan(a);
		}
		{
			System.out.print("Enter any positive integer: ");
			int a = in.nextInt();
			SquareNumbers.getPowersOfTwoLessThan(a);
		}
	}
}
