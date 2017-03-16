package ComputerScience.Chapter9.Cards;

/**
 * @Author Logan Traffas
 * @Date 3/3/2017.
 * @Version 1.0.0
 * @Assignment Ch 9 Lab: Card Superclass
 */
public class CallingCard extends Card {
	private String PIN;
	private int cardNumber;

	public String getPIN(){
		return this.PIN;
	}

	public int getCardNumber(){
		return this.cardNumber;
	}

	public void setPIN(String PIN){
		this.PIN = PIN;
	}

	public void setCardNumber(int cardNumber){
		this.cardNumber = cardNumber;
	}

	@Override
	public String format(){
		return "";//TODO
	}

	public CallingCard(String name,String PIN,int cardNumber){
		super(name);
		this.PIN = PIN;
		this.cardNumber = cardNumber;
	}

	public CallingCard(){
		this("","",0);
	}
}
