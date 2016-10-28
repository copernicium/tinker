package computer_science.chapter4;
import java.util.Scanner;
/*
Your program’s inputs should be:
•	The cost of a new car
•	The estimated miles driven per year
•	The estimated gas price five years from now
•	The efficiency in miles per gallon
•	The estimated resale value after 5 years
Compute the total cost of owning the car for five years. (For simplicity, we will not take the cost of financing into account.)

Obtain realistic prices for a new and used hybrid and a comparable car from the Web.
Run your program twice, using today’s gas price and 15,000 miles per year.

Include pseudocode and the program runs with your assignment.
 */
/**
 * A class which helps a person decide whether to buy a hybrid car.
 *
 * @author Logan Traffas
 * @version 10/27/2016
 * assignment: Chapter 04--Fundamental Data Types--P4.1
 */
public class CarHelper {

	private double estimatedMilesPerYear = 0.0;//miles per year
	private double cost = 0.0;//dollars
	private double estimatedGasPriceInFiveYears = 0.0;//dollars per gallon
	private double fuelEfficiency = 0.0;//miles per gallon
	private double resaleValueInFiveYears = 0.0;//dollars

	public void setCost(double cost){
		this.cost = cost;
	}
	public void setEstimatedMilesPerYear(double estimatedMilesPerYear){
		this.estimatedMilesPerYear = estimatedMilesPerYear;
	}

	public void setFuelEfficiency(double fuelEfficiency){
		this.fuelEfficiency = fuelEfficiency;
	}

	public void setResaleValueInFiveYears(double resaleValueInFiveYears){
		this.resaleValueInFiveYears = resaleValueInFiveYears;
	}

	public void setEstimatedGasPriceInFiveYears(double estimatedGasPriceInFiveYears){
		this.estimatedGasPriceInFiveYears = estimatedGasPriceInFiveYears;
	}

	public double calculateTotalCost(){
		double calculatedTotalCost = cost;
		final double TIMESCALE = 5.0;//years
		calculatedTotalCost += (estimatedMilesPerYear / fuelEfficiency) * estimatedGasPriceInFiveYears * TIMESCALE;
		calculatedTotalCost -= resaleValueInFiveYears;
		return calculatedTotalCost;
	}

	public CarHelper(){
		cost = 0;
		fuelEfficiency = 0;
		estimatedGasPriceInFiveYears = 0;
		estimatedMilesPerYear = 0;
		resaleValueInFiveYears = 0;
	}
}
