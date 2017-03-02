package ComputerScience.Chapter9.Vehicles;

import Util.Util;

/**
 * @Author Logan Traffas
 * @Date 3/1/2017.
 * @Version 1.0.0
 * @Assignment Ch 9: Introduction to Inheritance
 */
public class Vehicle {
	protected String make;
	protected String model;
	protected int year;
	protected double price;
	protected double weight;
	protected double fuelRange;
	protected double tankCapacity;
	protected double currentGasAmount;
	protected double maxSpeed;
	protected double currentSpeed;
	protected char drive;//TODO: determine type

	public String getMake(){
		return this.make;
	}

	public String getModel(){
		return this.model;
	}

	public int getYear(){
		return this.year;
	}

	public double getPrice(){
		return this.price;
	}

	public double getWeight(){
		return this.weight;
	}

	public double getFuelRange(){
		return this.fuelRange;
	}

	public double getTankCapacity(){
		return this.tankCapacity;
	}

	public double getCurrentGasAmount(){
		return this.currentGasAmount;
	}

	public double getMaxSpeed(){
		return this.maxSpeed;
	}

	public double getCurrentSpeed(){
		return this.currentSpeed;
	}

	public char getDrive(){
		return this.drive;
	}

	public void setMake(String make){
		this.make = make;
	}

	public void setModel(String model){
		this.model = model;
	}

	public void setYear(int year){
		this.year = year;
	}

	public void setPrice(double price){
		this.price = price;
	}

	public void setWeight(double weight){
		this.weight = weight;
	}

	public void setFuelRange(double fuelRange){
		this.fuelRange = fuelRange;
	}

	public void setTankCapacity(double tankCapacity){
		this.tankCapacity = tankCapacity;
	}

	public void  setCurrentGasAmount(double currentGasAmount){
		this.currentGasAmount = currentGasAmount;
	}

	public void setMaxSpeed(double maxSpeed){
		this.maxSpeed = maxSpeed;
	}

	public void setCurrentSpeed(double currentSpeed){
		this.currentSpeed = currentSpeed;
	}

	public void setDrive(char drive){
		this.drive = drive;
	}

	public void drive(){
		//TODO
		Util.nyi(Util.getFileName(),Util.getLineNumber());
	}

	public void fillGas(){
		//TODO
		Util.nyi(Util.getFileName(),Util.getLineNumber());
	}

	public Vehicle(){
		this.make = "";
		this.model = "";
		this.year = 0;
		this.price = 0;
		this.weight = 0;
		this.fuelRange = 0;
		this.tankCapacity = 0;
		this.currentGasAmount = 0;
		this.maxSpeed = 0;
		this.currentSpeed = 0;
		this.drive ='P';
	}
}
