package ComputerScience.Labs.Elevens.Activity1;

/**
 * @Author Logan Traffas
 * @Date 3/20/2017.
 * @Version 1.0.0
 * @Assignment Elevens-Activity1-Design & Create a Card Class
 */
public class Card {
	public enum Suit{HEARTS,CLUBS,DIAMONDS,SPADES}

	public enum Rank{ACE,_2,_3,_4,_5,_6,_7,_8,_9,JACK,QUEEN,KING}

	/**
	 * String value that holds the suit of the card
	 */
	private Suit suit;

	/**
	 * String value that holds the rank of the card
	 */
	private Rank rank;

	/**
	 * int value that holds the point value.
	 */
	private int pointValue;

	/**
	 * Creates a new Card instance.
	 *
	 * @param cardRank  the rank of the card
	 * @param cardSuit  the suit of the card
	 * @param cardPointValue  the point value of the card
	 */
	public Card(Rank cardRank, Suit cardSuit, int cardPointValue) {
		this.rank = cardRank;
		this.suit = cardSuit;
		this.pointValue = cardPointValue;
	}

	/**
	 * Accesses this Card's suit.
	 * @return this Card's suit.
	 */
	public Suit suit() {
		return this.suit;
	}

	/**
	 * Accesses this Card's rank.
	 * @return this Card's rank.
	 */
	public Rank rank() {
		return this.rank;
	}

	/**
	 * Accesses this Card's point value.
	 * @return this Card's point value.
	 */
	public int pointValue() {
		return this.pointValue;
	}

	/** Compare this card with the argument.
	 * @param o the other card to compare to this
	 * @return true if the rank, suit, and point value of this card
	 *         are equal to those of the argument;
	 *         false otherwise.
	 */
	@Override
	public boolean equals(Object o) {
		if(o == null || o.getClass() != this.getClass()) return false;
		Card otherCard = (Card)o;
		if(this.suit != otherCard.suit) return false;
		if(this.rank != otherCard.rank) return false;
		if(this.pointValue != otherCard.pointValue) return false;
		return true;
	}

	/**
	 * Converts the rank, suit, and point value into a string in the format
	 *     "[Rank] of [Suit] (point value = [PointValue])".
	 * This provides a useful way of printing the contents
	 * of a Deck in an easily readable format or performing
	 * other similar functions.
	 *
	 * @return a String containing the rank, suit,
	 *         and point value of the card.
	 */
	@Override
	public String toString() {
		String s = "Card(";
		s += this.rank;
		s += " of " + this.suit;
		s += " (point value = " + this.pointValue + ")";
		s += ")";
		return s;
	}
}
