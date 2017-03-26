package ComputerScience.Chapter5;
import java.util.Scanner;
/**
 * Reads a float and prints whether it is negative, zero, or positive and if it is large or small
 *
 * @author Logan Traffas
 * @version 11/16/2016
 * assignment: Chapter 05--Chapter 05--Decisions-E5.2
 */
public class NumberEvaluator {

	private static boolean closeEnough(final double A,final double B){
		final double EPSILON = 1E-14;
		return (Math.abs(A-B) < EPSILON);
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.print("Please input any number: ");
		double number = in.nextDouble();

		final double ZERO = 0.0, SMALL_NUMBER_BOUNDARY = 1.0, LARGE_NUMBER_BOUNDARY = 1000000;

		if (NumberEvaluator.closeEnough(number, ZERO)) {
			System.out.println("The value is zero.");
		} else {
			String state = "";
			String size = "";
			if (number < ZERO) {
				state = "negative";
			} else {
				state = "positive";
			}
			if(Math.abs(number) < SMALL_NUMBER_BOUNDARY){
				size = "small";
			} else if(Math.abs(number) > LARGE_NUMBER_BOUNDARY){
				size = "large";
			}
			System.out.println("The value is a " + size + " " + state + " number.");
		}
	}
}
