package ComputerScience.Chapter7;

/**
 * Prints different sets of numbers out of an array
 *
 * @author Logan Traffas
 * @version 1/28/2017
 * assignment: Chapter 07--E7.1--Four Array Methods
 */
public class ArrayTester{
	public static void main(String[] args){
		int[] array = {3,4,7,9,11,15,19,23,6,1};
		{
			System.out.print("1. ");
			for(int i = 0; i < array.length; i++){
				System.out.print(array[i] + " ");
			}
		}
		{
			System.out.print("\n2. ");
			for(int i = 0; i < array.length; i++){
				if(i % 2 == 0) System.out.print(array[i] + " ");
			}
		}
		{
			System.out.print("\n3. ");
			for(int i = 0; i < array.length; i++){
				if(array[i] % 2 == 0) System.out.print(array[i] + " ");
			}
		}
		{
			System.out.print("\n4. ");
			for(int i = array.length - 1; i >= 0; i--){
				System.out.print(array[i] + " ");
			}
		}
		{
			System.out.print("\n5. " + array[0] + " " + array[array.length-1]);
		}
	}
}
