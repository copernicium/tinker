package ComputerScience.Labs.Elevens.Activity3;

import java.util.Arrays;

/**
 * This class provides a convenient way to test shuffling methods.
 * @Author Logan Traffas
 * @Date 4/3/2017.
 * @Version 1.0.0
 * @Assignment Elevens Lab-Activity 3-Shuffle the Deck
 */
public class Shuffler {
	/**
	 * The number of consecutive shuffle steps to be performed in each call to each sorting procedure.
	 */
	private static final int SHUFFLE_COUNT = 4;

	/**
	 * The number of values to shuffle.
	 */
	private static final int VALUE_COUNT = 4;

	/**
	 * Tests shuffling methods.
	 * @param args is not used.
	 */
	public static void main(String[] args) {
		{
			System.out.println("Shuffler");
			{
				int[] values = new int[VALUE_COUNT];
				for(int i = 0; i < values.length; i++){
					values[i] = i;
				}
				System.out.println("Original: " + Arrays.toString(values));
				for (int i = 1; i <= SHUFFLE_COUNT; i++) {
					values = perfectShuffle(values);
					System.out.println("Results of " + i + " consecutive perfect shuffles: " + Arrays.toString(values));
				}
			}
			{

				Integer[] values = new Integer[VALUE_COUNT];
				for (int i = 0; i < values.length; i++) {
					values[i] = i;
				}
				System.out.println("Original: " + Arrays.toString(values));
				for (int i = 1; i <= SHUFFLE_COUNT; i++) {
					values = selectionShuffle(values);
					System.out.println("Results of " + i + " consecutive efficient selection shuffles: " + Arrays.toString(values));
				}
			}
		}
		{
			System.out.println("Question 1");
			for (int i = 0; i < 10; i++) {
				flip();
			}
		}
		{
			System.out.println("Question 2");
			int[] a = {0,1,2,3,4,5};
			int[] b = {1,2,4,0,5,3};
			int[] c = {9,8,1,3,7,0};
			System.out.println("a: " + Arrays.toString(a) + " vs b:" + Arrays.toString(b) + " arePermutations:" + arePermutations(a,b));
			System.out.println("a: " + Arrays.toString(a) + " vs c:" + Arrays.toString(b) + " arePermutations:" + arePermutations(a,c));
		}
		{
			System.out.println("Question 3");
			System.out.println("Random numbers 3, then 2, then 1, then 0 in the efficient selection shuffle would grab the integers in [ 1 2 3 4 ] and move them to [ 4 3 2 1 ]");
		}
	}

	/**
	 * Apply a "perfect shuffle" to the argument.
	 * The perfect shuffle algorithm splits the deck in half, then interleaves
	 * the cards in one half with the cards in the other.
	 * @param values is an array of integers simulating cards to be shuffled.
	 */
	public static int[] perfectShuffle(int[] values) {
		int[] shuffled = new int[values.length];
		int k = 0;
		for(int j = 0; j < (values.length + 1 ) /2; j++){
			shuffled[k] = values[j];
			k += 2;
		}
		k = 1;
		for(int j = (values.length + 1) / 2;  j < values.length; j++){
			shuffled[k] = values[j];
			k += 2;
		}
		return shuffled;
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
	public static Integer[] selectionShuffle(Integer[] values) {
		Integer[] shuffled = new Integer[values.length];
		for(int k = 0; k < shuffled.length; k++) {
			int j = 0;
			while (true) {
				j = (int) (Math.random() * values.length);
				if (values[j] != null) break;
			}
			shuffled[k] = values[j];
			values[j] = null;
		}
		return shuffled;
	}

	public static void flip(){
		int TOTAL = 3;
		int i = (int)(Math.random() * TOTAL);
		if(i == 0) System.out.println("Tails");
		else System.out.println("Heads");
	}

	/**
	 * Given two int arrays of the same length with no duplicate elements
	 * @param a
	 * @param b
	 * @return Returns true if one array is a permutation of the other (i.e., the
	arrays differ only in how their contents are arranged). Otherwise, it should return false.
	 */
	public static boolean arePermutations(int[] a, int[] b){
		for(int x : a){
			boolean contains = false;
			for(int y: b){
				if(x == y){
					contains = true;
					break;
				}
			}
			if(!contains) return false;
		}
		for(int x : b){
			boolean contains = false;
			for(int y: a){
				if(x == y) {
					contains = true;
					break;
				}
			}
			if(!contains) return false;
		}
		return true;
	}
}
