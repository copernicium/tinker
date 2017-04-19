package ComputerScience.Chapter14.SelectionSort;

import java.util.Arrays;

/**
 * This program demonstrates the selection sort algorithm by sorting an array that is filled with random numbers.
 * @Author Logan Traffas
 * @Date 4/19/2017.
 * @Version 1.0.0
 * @Assignment
 */
public class SelectionSortDemo {
	public static void main(String[] args) {
		int[] a = ArrayUtil.randomIntArray(20, 100);
		System.out.println(Arrays.toString(a));

		SelectionSorter.ascendingSort(a);

		System.out.println(Arrays.toString(a));

		SelectionSorter.descendingSort(a);

		System.out.println(Arrays.toString(a));
	}
}


