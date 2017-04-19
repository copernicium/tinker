package ComputerScience.Chapter14.SelectionSort;

/**
 * The sort method of this class sorts an array, using the selection sort algorithm.
 * @Author Logan Traffas
 * @Date 4/19/2017.
 * @Version 1.0.0
 * @Assignment Ch14-14E1-Sorting & Searching--Modifying Selection Sort Descending
 */
public class SelectionSorter {
	/**
	 Sorts an array, using selection sort.
	 @param a the array to sort
	 */
	public static void descendingSort(int[] a) {
		for (int i = 0; i < a.length - 1; i++) {
			int maxPos = maximumPosition(a, i);
			ArrayUtil.swap(a, maxPos, i);
		}
	}

	/**
	 Sorts an array, using selection sort.
	 @param a the array to sort
	 */
	public static void ascendingSort(int[] a) {
		for (int i = 0; i < a.length - 1; i++) {
			int minPos = minimumPosition(a, i);
			ArrayUtil.swap(a, minPos, i);
		}
	}

	/**
	 Finds the largest element in a tail range of the array.
	 @param a the array to sort
	 @param from the first position in a to compare
	 @return the position of the smallest element in the
	 range a[from] . . . a[a.length - 1]
	 */
	private static int maximumPosition(int[] a, int from) {
		int maxPos = from;
		for (int i = from + 1; i < a.length; i++) {
			if (a[i] > a[maxPos]) {
				maxPos = i;
			}
		}
		return maxPos;
	}

	/**
	 Finds the smallest element in a tail range of the array.
	 @param a the array to sort
	 @param from the first position in a to compare
	 @return the position of the smallest element in the
	 range a[from] . . . a[a.length - 1]
	 */
	private static int minimumPosition(int[] a, int from) {
		int minPos = from;
		for (int i = from + 1; i < a.length; i++) {
			if (a[i] < a[minPos]) {
				minPos = i;
			}
		}
		return minPos;
	}
}
