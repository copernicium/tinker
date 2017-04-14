	package ComputerScience.Labs.Elevens.Activity4;

import Util.Util;

/**
 * <code>Card</code> represents a playing card.
 * @Author Logan Traffas
 * @Date 4/3/2017.
 * @Version 1.0.0
 * @Assignment Elevens Lab--Activity 4 --Adding Shuffle Method to Deck Class
 */
public class Card {
	public enum Suit{HEARTS,CLUBS,DIAMONDS,SPADES}

	public enum Rank{ACE,TWO,THREE,FOUR,FIVE,SIX,SEVEN,EIGHT,NINE,TEN,JACK,QUEEN,KING}

	public static int rankToPointValue(Rank rank){
		switch(rank){
			case ACE:
				return 1;
			case TWO:
				return 2;
			case THREE:
				return 3;
			case FOUR:
				return 4;
			case FIVE:
				return 5;
			case SIX:
				return 6;
			case SEVEN:
				return 7;
			case EIGHT:
				return 8;
			case NINE:
				return 9;
			case TEN:
			case JACK:
			case QUEEN:
			case KING:
				return 10;
			default:
				Util.nyi(Util.getFileName(),Util.getLineNumber());
		}
		return -1;//will never reach this line
	}

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
	 * Creates a new <code>Card</code> instance.
	 *
	 * @param rank  a <code>Rank</code> value
	 *                  containing the rank of the card
	 * @param suit a <code>Suit</code> value
	 *                  containing the suit of the card
	 */
	public Card(Rank rank, Suit suit) {
		//initializes a new Card with the given rank, suit, and point value is determined by the rank
		this.rank = rank;
		this.suit = suit;
		pointValue = Card.rankToPointValue(rank);
	}

   /**
	 * Creates a new <code>Card</code> instance.
	 *
	 * @param rank  a <code>Rank</code> value
	 *                  containing the rank of the card
	 * @param suit a <code>Suit</code> value
	 *                  containing the suit of the card
	 * @param cardPointValue an <code>int</code> value
	 *                  containing the point value of the card
	 */
	public Card(Rank rank, Suit suit, int cardPointValue) {
		//initializes a new Card with the given rank, suit, and point value
		this.rank = rank;
		this.suit = suit;
		pointValue = cardPointValue;
	}


	/**
	 * Accesses this <code>Card's</code> suit.
	 * @return this <code>Card's</code> suit.
	 */
	public Suit suit() {
		return suit;
	}

	/**
	 * Accesses this <code>Card's</code> rank.
	 * @return this <code>Card's</code> rank.
	 */
	public Rank rank() {
		return rank;
	}

   /**
	 * Accesses this <code>Card's</code> point value.
	 * @return this <code>Card's</code> point value.
	 */
	public int pointValue() {
		return pointValue;
	}

	/** Compare this card with the argument.
	 * @param otherCard the other card to compare to this
	 * @return true if the rank, suit, and point value of this card
	 *              are equal to those of the argument;
	 *         false otherwise.
	 */
	public boolean matches(Card otherCard) {
		return otherCard.suit().equals(this.suit())
			&& otherCard.rank().equals(this.rank())
			&& otherCard.pointValue() == this.pointValue();
	}

	/**
	 * Converts the rank, suit, and point value into a string in the format
	 *     "[Rank] of [Suit] (point value = [PointValue])".
	 * This provides a useful way of printing the contents
	 * of a <code>Deck</code> in an easily readable format or performing
	 * other similar functions.
	 *
	 * @return a <code>String</code> containing the rank, suit,
	 *         and point value of the card.
	 */
	@Override
	public String toString() {
		return rank + " of " + suit + " (point value = " + pointValue + ")";
	}
}
