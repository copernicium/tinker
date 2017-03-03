package ComputerScience.Chapter9.Cards;

/**
 * @Author Logan Traffas
 * @Date 3/3/2017.
 * @Version 1.0.0
 * @Assignment Ch 9 Lab: Card Superclass
 */
public class IDCard extends Card {
	private String IDNumber;

	public String getIDNumber(){
		return this.IDNumber;
	}

	public void setIDNumber(String IDNumber){
		this.IDNumber = IDNumber;
	}

	@Override
	public String format(){
		return "";//TODO
	}

	public IDCard(String name,String IDNumber){
		super(name);
		this.IDNumber = IDNumber;
	}

	public IDCard(){
		this("","");
	}
}
