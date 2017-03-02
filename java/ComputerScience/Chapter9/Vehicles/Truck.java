package ComputerScience.Chapter9.Vehicles;

/**
 * @Author Logan Traffas
 * @Date 3/1/2017.
 * @Version 1.0.0
 * @Assignment Ch 9: Introduction to Inheritance
 */
public class Truck extends Vehicle{
	enum FuelType{DIESEL,REGULAR,PREMIUM}
	protected FuelType fuelType;
	protected double load;

	public FuelType getFuelType(){
		return this.fuelType;
	}

	public double getLoad(){
		return this.load;
	}

	public void setFuelType(FuelType fuelType){
		this.fuelType = fuelType;
	}

	public void setLoad(double load){
		this.load = load;
	}

	public Truck(){
		super();
		this.fuelType = FuelType.DIESEL;
		this.load = 0;
	}
}
