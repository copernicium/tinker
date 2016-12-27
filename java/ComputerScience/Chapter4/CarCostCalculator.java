package ComputerScience.Chapter4;

import java.util.Scanner;

/**
 * Takes in user input to calculate the cost of driving a car 100mi
 *
 * @author Logan Traffas
 * @version 11/1/2016
 * assignment: Chapter 04--Fundamental Data Types--E4.10
 */
public class CarCostCalculator {
	private double numberOfGallons;//gallons
	private double fuelEfficiency;//miles per gallon
	private double gasPrice;//dollars per gallon

	/**
	 * Prompts the use to input the number of gallons of gas in their car and saves it
	 */
	public void setNumberOfGallons(){
		Scanner input = new Scanner(System.in);
		System.out.print("Input the number of gallons of gas in your gas tank (gal): ");
		double numberOfGallons = input.nextDouble();
		System.out.print("\n");
		this.numberOfGallons = numberOfGallons;
	}
	/**
	 * Prompts the use to input the fuel efficiency of their car and saves it
	 */
	public void setFuelEfficiency(){
		Scanner input = new Scanner(System.in);
		System.out.print("Input the fuel efficiency of your car (mi/gal): ");
		double fuelEfficiency = input.nextDouble();
		System.out.print("\n");
		this.fuelEfficiency = fuelEfficiency;
	}
	/**
	 * Prompts the use to input the cost of gas per mile and saves it
	 */
	public void setCostOfGas(){
		Scanner input = new Scanner(System.in);
		System.out.print("Input the cost of gas (dollars/gal): ");
		double gasPrice = input.nextDouble();
		System.out.print("\n");
		this.gasPrice = gasPrice;
	}

	/**
	 * Calculates the distance the user can drive based on their car's fuel efficiency and the amount of gas in their tank
	 * @return the distance the car can drive
	 */
	public double calculateDistance(){
		double distance = 0.0;
		distance = numberOfGallons * fuelEfficiency;
		return distance;
	}

	/**
	 * Calculates the cost of driving 100 miles given the cost of gas and the fuel efficiency of the car
	 * @return the cost of driving 100 miles
	 */
	public double calculateCost(){
		final double MILES_DRIVEN = 100.0;//miles
		double cost = (MILES_DRIVEN / fuelEfficiency) * gasPrice;
		return cost;
	}

	public CarCostCalculator(){
		numberOfGallons = 0;
		fuelEfficiency = 0;
		gasPrice = 0;
	}

	public static void main(String[] args){
		CarCostCalculator carCostCalculator = new CarCostCalculator();
		carCostCalculator.setNumberOfGallons();
		carCostCalculator.setFuelEfficiency();
		carCostCalculator.setCostOfGas();
		System.out.println("Cost of driving 100 miles: " + carCostCalculator.calculateCost() + " dollars");
		System.out.println("Number of miles able to drive: " + carCostCalculator.calculateDistance() + " miles");
	}

}
