package ComputerScience.Chapter6;

import java.util.Scanner;

/**
 * Prompts the user for a month and day and then prints the season
 *
 * @author Logan Traffas
 * @version 12/27/2016
 * assignment: Chapter06--Loops-6R6.2
 */
public class Sums {
	/**
	 * Calculates the sum of all the even numbers between (inclusive) two given numbers
	 * @param start the number to start at
	 * @param end the number to finish at or before
	 */
	public static void getSumOfAllEvensBetween(final int start, final int end){
		int i = start;
		int sum = 0;
		while(i <= end){
			if(i%2 == 0) sum += i;
			i++;
		}
		System.out.println("Sum of all even ints from " + start + " to " + end + " is " + sum);
	}

	/**
	 * Calculates the sum of all the square numbers between (inclusive) two given numbers
	 * @param start the number to start at
	 * @param end the number to finish at or before
	 */
	public static void getSumOfAllSquaresBetween(final int start,final int end){
		int i = start;
		int sum = 0;
		while(i<= end){
			if(i*i <= end) sum += i*i;
			i++;
		}
		System.out.println("Sum of all square ints from " + start + " to " + end + " is " + sum);
	}

	/**
	 * Calculates the sum of all the odd numbers between (inclusive) two given numbers
	 * @param start the number to start at
	 * @param end the number to finish at or before
	 */
	public static void getSumOfAllOddsBetween(final int start, final int end){
		int i = start;
		int sum = 0;
		while(i <= end){
			if(i%2 == 1) sum += i;
			i++;
		}
		System.out.println("Sum of all odd ints from " + start + " to " + end + " is " + sum);
	}

	/**
	 * Calculates the sum of all the odd digits in a given number
	 * @param n the number to sum the odd digits of
	 */
	public static void getSumOfAllOddDigits(final int n){
		int sum = 0;
		int num = n;
		int ORDER_OF_MAGNITUDE = 10;
		while(num > 0){
			int toAdd = num%ORDER_OF_MAGNITUDE;
			num /= ORDER_OF_MAGNITUDE;
			if(toAdd % 2 == 1) sum += toAdd;

		}
		System.out.println("Sum of all odd digits in " + n + " is " + sum);
	}

	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		{
			final int START = 2, END = 100;
			Sums.getSumOfAllEvensBetween(START, END);
		}
		{
			final int START = 1, END = 100;
			Sums.getSumOfAllSquaresBetween(START, END);
		}
		{
			System.out.print("Input a starting number for odd number sum : ");
			int start = in.nextInt();
			System.out.print("Input an ending number for odd number sum : ");
			int end = in.nextInt();
			Sums.getSumOfAllOddsBetween(start, end);
		}
		{
			System.out.print("Input a number to receive the sum of all its odd digits: ");
			int n = in.nextInt();
			Sums.getSumOfAllOddDigits(n);
		}
	}
}
