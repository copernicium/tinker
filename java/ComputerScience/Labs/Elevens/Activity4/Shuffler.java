package ComputerScience.Labs.Elevens.Activity4;

import java.util.Arrays;

/**
 * This class provides a convenient way to test shuffling methods.
 *
 * @Author Logan Traffas
 * @Date 4/14/2017.
 * @Version 1.0.0
 * @Assignment Elevens Lab--Activity 4 --Adding Shuffle Method to Deck Class
 */
public class Shuffler {

	/**
	 * The number of consecutive shuffle steps to be performed in each call
	 * to each sorting procedure.
	 */
	private static final int SHUFFLE_COUNT = 1;

	/**
	 * The number of values to shuffle.
	 */
	private static final int VALUE_COUNT = 4;

	/**
	 * Tests shuffling methods.
	 * @param args is not used.
	 */
	public static void main(String[] args) {
		System.out.println("Shuffler");
		{
			int[] values = new int[VALUE_COUNT];
			for(int i = 0; i < values.length; i++){
				values[i] = i;
			}
			System.out.println("Original: " + Arrays.toString(values));
			for (int i = 1; i <= SHUFFLE_COUNT; i++) {
				perfectShuffle(values);
				System.out.println("Results of " + i + " consecutive perfect shuffles: " + Arrays.toString(values));
			}
		}
		{

			int[] values = new int[VALUE_COUNT];
			for (int i = 0; i < values.length; i++) {
				values[i] = i;
			}
			System.out.println("Original: " + Arrays.toString(values));
			for (int i = 1; i <= SHUFFLE_COUNT; i++) {
				selectionShuffle(values);
				System.out.println("Results of " + i + " consecutive efficient selection shuffles: " + Arrays.toString(values));
			}
		}
	}


	/**
	 * Apply a "perfect shuffle" to the argument.
	 * The perfect shuffle algorithm splits the deck in half, then interleaves
	 * the cards in one half with the cards in the other.
	 * @param values is an array of integers simulating cards to be shuffled.
	 */
	public static void perfectShuffle(int[] values) {
		int[] temp = new int[values.length];
		int mid = (values.length + 1) / 2;

		// Interleave elements 0 ... mid-1 with elements mid ... length-1
		int unshuffledPos = 0;
		int k = 0;
		for ( ; k < mid; k++) {
			temp[unshuffledPos] = values[k];
			unshuffledPos += 2;
		}
		unshuffledPos = 1;
		for ( ; k < values.length; k++) {
			temp[unshuffledPos] = values[k];
			unshuffledPos += 2;
		}

		// Copy elements back to values
		for (k = 0; k < values.length; k++) {
			values[k] = temp[k];
		}
	}

	/**
	 * Apply an "efficient selection shuffle" to the argument.
	 * The selection shuffle algorithm conceptually maintains two sequences
	 * of cards: the selected cards (initially empty) and the not-yet-selected
	 * cards (initially the entire deck). It repeatedly does the following until
	 * all cards have been selected: randomly remove a card from those not yet
	 * selected and add it to the selected cards.
	 * An efficient version of this algorithm makes use of arrays to avoid
	 * searching for an as-yet-unselected card.
	 * @param values is an array of integers simulating cards to be shuffled.
	 */
	public static void selectionShuffle(int[] values) {
		for (int k = values.length - 1; k > 0; k--) {
			int pos = (int) (Math.random() * (k + 1));  // range 0 to k, inclusive
			int temp = values[pos];
			values[pos] = values[k];
			values[k] = temp;
		}
	}
}
