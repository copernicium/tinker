package ComputerScience.Chapter5;
import java.util.Scanner;
/**
 * Reads an integer and prints how many digits the number
 *
 * @author Logan Traffas
 * @version 11/16/2016
 * assignment: Chapter 05--Decisions-E5.3
 */
public class MagnitudeCalculator {
	private static boolean closeEnough(final double A,final double B){
		final double EPSILON = 1E-14;
		return (Math.abs(A-B) < EPSILON);
	}
	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		System.out.print("Please input any number: ");
		double number = in.nextDouble();
		double positiveNumber = (number < 0) ? -1*number : number;
		int digits = 0;
		if(positiveNumber >= 1000000) {
			System.err.println("Digits for number has not been handled by if statements.");
		} else if(positiveNumber >= 100000){
			digits = 6;
		} else if(positiveNumber >= 10000){
			digits = 5;
		} else if(positiveNumber >= 1000){
			digits = 4;
		} else if(positiveNumber >= 100){
			digits = 3;
		} else if(positiveNumber >= 10){
			digits = 2;
		} else {
			digits = 1;
		}
		System.out.println("The value has " + digits + " digits.");

		final int ZERO_DIGITS = 1, ZERO = 0;
		int betterDigits = MagnitudeCalculator.closeEnough(positiveNumber,ZERO) ? ZERO_DIGITS : (int)Math.log10(positiveNumber) + 1;
		System.out.println("The value has " + betterDigits + " digits.");
	}
}
