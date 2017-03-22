package ComputerScience.Labs.Elevens.Activity1;

/**
 * @Author Logan Traffas
 * @Date 3/20/2017.
 * @Version 1.0.0
 * @Assignment Elevens-Activity1-Design & Create a Card Class
 */
public class CardTester {

	/**
	 * The main method in this class checks the Card operations for consistency.
	 *	@param args is not used.
	 */
	public static void main(String[] args) {
		Card ace = new Card(Card.Rank.ACE, Card.Suit.CLUBS,11);
		Card queen = new Card(Card.Rank.QUEEN, Card.Suit.HEARTS,9);
		Card two = new Card(Card.Rank._2, Card.Suit.SPADES,2);
		System.out.println(ace.toString());
		System.out.println("Queen's rank: " + queen.rank());
		System.out.println("Two's point value: " + two.pointValue());
		System.out.println("Ace's suit: " + ace.suit());
		System.out.print("Ace vs queen (expected false): " + ace.equals(queen));

		Card ace2 = new Card(Card.Rank.ACE, Card.Suit.CLUBS,11);

		System.out.println("Ace vs Ace2 (expected true): " + ace.equals(ace2));
	}
}
