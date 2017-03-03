package ComputerScience.Chapter9.Cards;

/**
 * @Author Logan Traffas
 * @Date 3/3/2017.
 * @Version 1.0.0
 * @Assignment Ch 9 Lab: Card Superclass
 */
public class Card{
	protected String name;

	public Card(){
		this("");
	}

	public Card(String n){
		name = n;
	}

	public String getName(){
		return name;
	}

	public boolean isExpired(){
		return false;
	}

	public String format(){
		return "Card holder: " + name;
	}
}

