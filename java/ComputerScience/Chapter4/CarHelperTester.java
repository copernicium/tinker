package ComputerScience.Chapter4;
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
 * A class which tests CarHelper
 *
 * @author Logan Traffas
 * @version 10/27/2016
 * assignment: Chapter 04--Fundamental Data Types--P4.1
 */
public class CarHelperTester{
	public static void main(String[] args){
		CarHelper carHelper = new CarHelper();
		Scanner input = new Scanner(System.in);

		System.out.print("What is the cost of the car (dollars)? ");
		carHelper.setCost(input.nextDouble());
		System.out.print("\nHow far do you estimate it will travel per year (miles)? ");
		carHelper.setEstimatedMilesPerYear(input.nextDouble());
		System.out.print("\nWhat do you estimate the cost of gas will be in five years (dollars/gallon)? ");
		carHelper.setEstimatedGasPriceInFiveYears(input.nextDouble());
		System.out.print("\nWhat is the fuel efficiency of the car (miles per gallon)? ");
		carHelper.setFuelEfficiency(input.nextDouble());
		System.out.print("\nWhat is its estimated resale value in five years (dollars)? ");
		carHelper.setResaleValueInFiveYears(input.nextDouble());
		System.out.println("The estimated total cost of owning this car for five years is " + carHelper.calculateTotalCost() + " dollars.");
	}
}
