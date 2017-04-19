package ComputerScience.Labs.Elevens.Activity2;

import Util.Util;

import java.util.ArrayList;

/**
 * The Deck class represents a shuffled deck of cards.
 * It provides several operations including
 *      initialize, shuffle, deal, and check if empty.
 */
public class Deck {
	public static final Card.Rank[] ALL_RANKS = {Card.Rank.ACE, Card.Rank.TWO, Card.Rank.THREE, Card.Rank.FOUR, Card.Rank.FIVE, Card.Rank.SIX, Card.Rank.SEVEN, Card.Rank.EIGHT, Card.Rank.NINE, Card.Rank.TEN, Card.Rank.JACK, Card.Rank.QUEEN, Card.Rank.KING};

	public static final Card.Suit[] ALL_SUITS = {Card.Suit.HEARTS, Card.Suit.CLUBS, Card.Suit.DIAMONDS, Card.Suit.SPADES};

	/**
	 * cards contains all the cards in the deck.
	 */
	private ArrayList<Card> cards;

	/**
	 * size is the number of not-yet-dealt cards.
	 * Cards are dealt from the top (highest index) down.
	 * The next card to be dealt is at size - 1.
	 */
	private int size;

	public static int[] ranksToValues(){
		int[] values = new int[ALL_RANKS.length];
		for(int i = 0; i < ALL_RANKS.length; i++){
			values[i] = Card.rankToPointValue(ALL_RANKS[i]);
		}
		return values;
	}

	/**
	 * Creates a new <code>Deck</code> instance.<BR>
	 */
	public Deck() {
		this(ALL_RANKS,ALL_SUITS,Deck.ranksToValues());
	}

	/**
	 * Creates a new <code>Deck</code> instance.<BR>
	 * It pairs each element of ranks with each element of suits,
	 * and produces one of the corresponding card.
	 * @param ranks is an array containing all of the card ranks.
	 * @param suits is an array containing all of the card suits.
	 */
	public Deck(Card.Rank[] ranks, Card.Suit[] suits) {
		this(ranks,suits,Deck.ranksToValues());
	}

	/**
	 * Creates a new <code>Deck</code> instance.<BR>
	 * It pairs each element of ranks with each element of suits,
	 * and produces one of the corresponding card.
	 * @param ranks is an array containing all of the card ranks.
	 * @param suits is an array containing all of the card suits.
	 * @param values is an array containing all of the card point values.
	 */
	public Deck(Card.Rank[] ranks, Card.Suit[] suits, int[] values) {
		if(ranks.length != values.length){
			Util.error("Error: Deck constructor passed an array of ranks and an array of values of different lengths",Util.getFileName(),Util.getLineNumber());
		}
		this.cards = new ArrayList<>();
		for(Card.Suit suit: suits){
			for(int i = 0; i < ranks.length; i++){
				Card.Rank rank = ranks[i];
				int value = values[i];
				this.cards.add(new Card(rank,suit,value));
			}
		}
		this.size = this.cards.size();
		//shuffle();//TODO
	}

	/**
	 * Determines if this deck is empty (no undealt cards).
	 * @return true if this deck is empty, false otherwise.
	 */
	public boolean isEmpty() {
		return this.size == 0;
	}

	/**
	 * Accesses the number of undealt cards in this deck.
	 * @return the number of undealt cards in this deck.
	 */
	public int size() {
		return this.size;
	}

	/**
	 * Randomly permute the given collection of cards
	 * and reset the size to represent the entire deck.
	 */
	public void shuffle() { //TODO
		Util.nyi(Util.getFileName(),Util.getLineNumber());
	}

	/**
	 * Deals a card from this deck.
	 * @return the card just dealt, or null if all the cards have been
	 *         previously dealt.
	 */
	public Card deal() {
		if(isEmpty()){
			return null;
		}
		size--;
		return this.cards.get(size);
	}

	/**
	 * Generates and returns a string representation of this deck.
	 * @return a string representation of this deck.
	 */
	@Override
	public String toString() {
		String s = "Deck(\n";
		s += "\tsize = " + this.size;

		s += "\n\tUn-dealt cards: \n\t\t";
		if(this.size - 1 ==0){
			s += "None\n";
		} else {
			for (int i = this.size - 1; i >= 0; i--) {
				s += this.cards.get(i);
				boolean notEnd = i > 0;
				if (notEnd) {
					s += ", ";
				}
				boolean startNewLine = (this.size - i) % 2 == 0;
				if(startNewLine) {
					// Insert carriage returns so entire deck is visible on console.
					s += "\n";
				}
				if(notEnd && startNewLine){
					s += "\t\t";
				}
			}
		}

		s += "\n\tDealt cards: \n\t\t";
		if(this.cards.size() == this.size) {
			s += "None\n";
		} else {
			for (int i = this.cards.size() - 1; i >= this.size; i--) {
				s += this.cards.get(i);
				boolean notEnd = i != this.size;
				if (notEnd) {
					s += ", ";
				}
				boolean startNewLine = (i - this.cards.size()) % 3 == 0;
				if(startNewLine){
					// Insert carriage returns so entire deck is visible on console.
					s += "\n";
				}
				if(startNewLine && notEnd){
					s += "\t\t";
				}
			}
		}

		s += ")";
		return s;
	}
}
