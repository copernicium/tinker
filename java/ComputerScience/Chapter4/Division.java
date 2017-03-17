package ComputerScience.Chapter4;

/**
 * A class which demonstrates the characteristics of arithmetic in Java
 *
 * @author Logan Traffas
 * @version 10/27/2016
 * assignment: Section 4.2.3 Division
 */
public class Division
{
	public static void main(String[] args){
		float a = 3;
		float b = 2;
		System.out.println("Expectation: 3/2 will become to 1.5 (or close to it) as both are floats");
		System.out.println(a / b);

		int a1 = 3;
		float b1 = 2;
		System.out.println("Expectation:3/2 will become 1.5 (or close to it) because java will use the variable with the higher precision (in this case, a float)");
		System.out.println(a1 / b1);//Note: changed "1" to "b1"

		float a2 = 3;
		int b2 = 2;
		System.out.println("Expectation: 3/2 will become 1.5 (or close to it) because java uses the variable with the higher precision (in this case, a float)");
		System.out.println(a2 / b2);

		int a3 = 3;
		int b3 = 2;
		System.out.println("Expectation: 3/2 will be truncated to 1 as both are ints");
		System.out.println(a3 / b3);
	}
}
