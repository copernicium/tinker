package computer_science.chapter5;
import java.util.Scanner;
/**
 * Reads an integer and prints whether it is negative, zero, or positive
 *
 * @author Logan Traffas
 * @version 11/16/2016
 * assignment: Chapter 05--Chapter 05--Decisions-E5.1
 */
public class SignEvaulator {

	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		System.out.print("Please input an integer: ");
		int number = in.nextInt();

		String state = "";
		if(number < 0){
			state = "negative";
		} else if( number == 0){
			state = "zero";
		} else {
			state = "positive";
		}
		System.out.println(number + " is " + state + ".");
	}
}
