package ComputerScience.Chapter14.SelectionSortCoins;

import java.util.Random;

/**
 * This class contains utility methods for array manipulation.
 * @Author Logan Traffas
 * @Date 4/19/2017.
 * @Version 1.0.0
 * @Assignment
 */
public class ArrayUtil {
	private static Random generator = new Random();

	/**
	 Creates an array filled with random values.
	 @param length the length of the array
	 @param n the number of possible random values
	 @return an array filled with length numbers between
	 0 and n - 1
	 */
	public static int[] randomIntArray(int length, int n) {
		int[] a = new int[length];
		for (int i = 0; i < a.length; i++) {
			a[i] = generator.nextInt(n);
		}

		return a;
	}

	/**
	 Swaps two entries of an array.
	 @param a the array
	 @param i the first position to swap
	 @param j the second position to swap
	 */
	public static <T extends Object> void swap(T[] a, int i, int j) {
		T temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}
}
