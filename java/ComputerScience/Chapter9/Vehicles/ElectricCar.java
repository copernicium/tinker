package ComputerScience.Chapter9.Vehicles;

/**
 * @Author Logan Traffas
 * @Date 3/1/2017.
 * @Version 1.0.0
 * @Assignment Ch 9: Introduction to Inheritance
 */
public class ElectricCar extends Car{
	protected double batteryCapacity;
	protected double currentCharge;

	public double getBatteryCapacity(){
		return this.batteryCapacity;
	}

	public double getCurrentCharge(){
		return this.currentCharge;
	}

	public void setBatteryCapacity(double batteryCapacity){
		this.batteryCapacity = batteryCapacity;
	}

	public void setCurrentCharge(double currentCharge){
		this.currentCharge = currentCharge;
	}

	@Override
	public void drive(){
		final double DECREMENT = 10;
		this.currentCharge -= DECREMENT;
	}

	@Override
	public void fillGas(){
		System.out.println("Cannot fill gas: this is an electric car");
	}

	public ElectricCar(){
		super();
		this.batteryCapacity = 0;
		this.currentCharge = 0;
	}
}
