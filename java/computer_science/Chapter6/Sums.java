package computer_science.Chapter6;

/*
"Write three separate loops that compute in a single program:
a. The sum of all even numbers between 2 and 100 (inclusive).
b. The sum of all squares between 1 and 100 (inclusive).
c. Ask  user for inputs a and b and compute the sum of
       all odd numbers between a and b (inclusive).
d. The sum of all odd digits of n. (For example, if n is 32677,
   the sum would be3+7+7=17.)"
   HINTS for d) Use number % 10 to obtain the rightmost digit,
                 test if odd using if(digit % 2 == 1),
                 shift the digits to the right by dividing by 10
 */

import java.util.Scanner;

/**
 * Prompts the user for a month and day and then prints the season
 *
 * @author Logan Traffas
 * @version 12/14/2016
 * assignment: Chapter06--Loops-6R6.2
 */
public class Sums {
	public static void getSumOfAllEvensBetween(final int start, final int end){
		int i = start;
		int sum = 0;
		while(i <= end){
			if(i%2 == 0) sum += i;
		}
		System.out.println("Sum of all even ints from " + start + " to " + end + " is " + sum);
	}
	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
	}
}
