package ComputerScience.Chapter14.SelectionSortCoins;

/**
 * @Author Logan Traffas
 * @Date 4/19/2017.
 * @Version 1.0.0
 * @Assignment
 */
public class Coin {
	private String name;
	private double value;
	public String getName(){
		return this.name;
	}
	@Override
	public String toString(){
		return "Coin(" + this.name + " " + this.value + ")";
	}

	public double getValue(){
		return this.value;
	}
	public Coin(){
		this("",0);
	}
	public Coin(String name, double value){
		this.name = name;
		this.value = value;
	}
}
