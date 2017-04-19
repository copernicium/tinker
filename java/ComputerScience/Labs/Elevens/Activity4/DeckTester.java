package ComputerScience.Labs.Elevens.Activity4;

/**
 * This is a class that tests the Deck class.
 */
public class DeckTester {
	/**
	 * Tests the different deck methods and prints their results
	 * @param d the deck to test
	 */
	static void printDeckMethods(Deck d){
		System.out.println("  toString:\n" + d.toString());
		System.out.println("  isEmpty: " + d.isEmpty());
		System.out.println("  size: " + d.size());
		System.out.println();
	}

	/**
	 * The main method in this class checks the Deck operations for consistency.
	 *	@param args is not used.
	 */
	public static void main(String[] args) {
		{
			System.out.println("Exercise 1");
			Card.Rank[] ranks = {Card.Rank.JACK, Card.Rank.QUEEN, Card.Rank.KING};
			Card.Suit[] suits = {Card.Suit.CLUBS, Card.Suit.DIAMONDS, Card.Suit.HEARTS, Card.Suit.SPADES};
			int[] pointValues = {11, 12, 13};
			Deck d = new Deck(ranks, suits, pointValues);

			System.out.println("Original Deck Methods ~ ");
			printDeckMethods(d);
			System.out.println();

			System.out.println("Deal a Card ~ ");
			System.out.println("  deal: " + d.deal());
			System.out.println();

			System.out.println("Deck Methods After Dealing One Card ~ ");
			printDeckMethods(d);
			System.out.println();

			System.out.println("Deal All Remaining Cards ~");
			{
				int remaining = d.size();
				for (int i = 0; i < remaining; i++) {
					System.out.println("  deal: " + d.deal());
				}
			}
			System.out.println();

			System.out.println("Deck Methods After All Cards Dealt~");
			printDeckMethods(d);
			System.out.println();

			System.out.println("Deal a Card From Empty Deck~");
			System.out.println("  deal: " + d.deal());
			System.out.println();

			d.shuffle();
			System.out.println("The deck after re-shuffling~");
			printDeckMethods(d);
			System.out.println();
		}
		{
			System.out.println("Exercise 2");
			Deck deck = new Deck();
			System.out.println("Original~\n" + deck.toString());
			final int ITERATIONS = 4;
			for(int i = 0; i < ITERATIONS; i++){
				deck.shuffle();
				System.out.println("After " + (i + 1) + " more shuffles~" + deck.toString());
			}
		}

	}
}
