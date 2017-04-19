package ComputerScience.Chapter14.SelectionSortCoins;

/**
 *  The sort method of this class sorts an array, using the selection sort algorithm.
 * @Author Logan Traffas
 * @Date 4/19/2017.
 * @Version 1.0.0
 * @Assignment
 */
public class SelectionSorter {
	/**
	 Sorts an array, using selection sort.
	 @param a the array to sort
	 */
	public static void sort(Coin[] a) {
		Double[] doubleArr = new Double[a.length];
		for(int i = 0; i < a.length; i++){
			doubleArr[i] = a[i].getValue();
		}
		for (int i = 0; i < a.length - 1; i++) {
			int minPos = minimumPosition(doubleArr, i);
			ArrayUtil.swap(a, minPos, i);
		}
	}
	/**
	 Sorts an array, using selection sort.
	 @param a the array to sort
	 */
	public static void sort(Integer[] a) {
		for (int i = 0; i < a.length - 1; i++) {
			int minPos = minimumPosition(a, i);
			ArrayUtil.swap(a, minPos, i);
		}
	}

	/**
	 Finds the smallest element in a tail range of the array.
	 @param a the array to sort
	 @param from the first position in a to compare
	 @return the position of the smallest element in the
	 range a[from] . . . a[a.length - 1]
	 */
	private static int minimumPosition(Integer[] a, int from) {
		int minPos = from;
		for (int i = from + 1; i < a.length; i++) {
			if (a[i] < a[minPos]) {
				minPos = i;
			}
		}
		return minPos;
	}

	/**
	 Finds the smallest element in a tail range of the array.
	 @param a the array to sort
	 @param from the first position in a to compare
	 @return the position of the smallest element in the
	 range a[from] . . . a[a.length - 1]
	 */
	private static int minimumPosition(Double[] a, int from) {
		int minPos = from;
		for (int i = from + 1; i < a.length; i++) {
			if (a[i] < a[minPos]) {
				minPos = i;
			}
		}
		return minPos;
	}
}
