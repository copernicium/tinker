package ComputerScience.Chapter6;

/**
 * Prompts the user for a month and day and then prints the season
 *
 * @author Logan Traffas
 * @version 12/31/2016
 * assignment: Chapter06-Loops-6R15-Rewrite for loop into while loop
 */
public class ForLoopToWhileLoop{
	public static void original(){
		/*Submit both your code and output showing the loop variable and value
		value of s for each iteration:

		Rewrite the following for loop into a while loop.*/

		int s = 0;
		for (int i = 1; i <= 10; i++){
			s = s + i;
			System.out.println(i + "  " + s);
		}
	}

	public static void asWhile(){
		int i = 1, s = 0;
		while(i <=10){
			s += i;
			System.out.println(i + " 1 " + s);
			i++;
		}
	}
	public static void main(String[] args){
		System.out.println("Original");
		ForLoopToWhileLoop.original();
		System.out.println("While");
		ForLoopToWhileLoop.asWhile();
	}
}
