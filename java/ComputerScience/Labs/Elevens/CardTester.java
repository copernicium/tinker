package ComputerScience.Labs.Elevens;

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
		Card ace = new Card(Card.Rank.ACE, Card.Suit.CLUBS,110);
		Card queen = new Card(Card.Rank.QUEEN, Card.Suit.HEARTS,90);
		Card two = new Card(Card.Rank._2, Card.Suit.SPADES,20);
		System.out.println(ace.toString());
	}
}
