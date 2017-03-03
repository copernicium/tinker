package ComputerScience.Chapter9.Cards;

/**
 * @Author Logan Traffas
 * @Date 3/3/2017.
 * @Version 1.0.0
 * @Assignment Ch 9 Lab: Card Superclass
 */
public class DriverLicense extends Card {
	private int expirationYear;

	public int getExpirationYear(){
		return this.expirationYear;
	}

	public void setExpirationYear(int expirationYear){
		this.expirationYear = expirationYear;
	}

	@Override
	public String format(){
		return "";//TODO
	}

	public DriverLicense(String name,int expirationYear){
		super(name);
		this.expirationYear = expirationYear;
	}

	public DriverLicense(){
		this("",0);
	}
}
