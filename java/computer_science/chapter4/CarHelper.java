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
	public double calculateTotalCost(double estimatedMilesPerYear, double cost, double estimatedGasPriceInFiveYears, double fuelEfficiency, double resaleValueInFiveYears){
		double calculatedTotalCost = cost;
		final double TIMESCALE = 5.0;//years
		calculatedTotalCost =+ (estimatedMilesPerYear/fuelEfficiency)*estimatedGasPriceInFiveYears*TIMESCALE;
		calculatedTotalCost -= resaleValueInFiveYears;
		return calculatedTotalCost;

	}
	public static void main(String[] args){
		double estimatedMilesPerYear = 0.0;//miles per year
		double cost = 0.0;//dollars
		double estimatedGasPriceInFiveYears = 0.0;//dollars per gallon
		double fuelEfficiency = 0.0;//miles per gallon
		double resaleValueInFiveYears = 0.0;//dollars
		Scanner input = new Scanner(System.in);

		System.out.print("What is the cost of the car (dollars)? ");
		cost = input.nextDouble();
		System.out.print("\nHow far do you estimate it will travel per year (miles)? ");
		cost = input.nextDouble();
		System.out.print("\nWhat do you estimate the cost of gas will be in five years (dollars/gallon)? ");
		estimatedGasPriceInFiveYears = input.nextDouble();
		System.out.print("\nWhat is the fuel efficiency of the car (miles per gallon)? ");
		fuelEfficiency = input.nextDouble();
		System.out.print("\nWhat is its estimated resale value in five years (dollars)? ");
		resaleValueInFiveYears = input.nextDouble();
	}
}
