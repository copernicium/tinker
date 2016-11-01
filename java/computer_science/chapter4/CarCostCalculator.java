package computer_science.chapter4;

import java.util.Scanner;

/**
 * Takes in user input to calculate the cost of driving a car 100mi
 *
 * @author Logan Traffas
 * @version 11/1/2016
 * assignment: Chapter 04--Fundamental Data Types--E4.10
 */
public class CarCostCalculator {
	public double getNumberOfGallons(){
		Scanner input = new Scanner(System.in);
		System.out.print("Input the number of gallons of gas in your gas tank (gal): ");
		double numberOfGallons = input.nextDouble();
		System.out.print("\n");
		return numberOfGallons;
	}
	public double getFuelEfficiency(){
		Scanner input = new Scanner(System.in);
		System.out.print("Input the fuel efficiency of your car (mi/gal): ");
		double fuelEfficiency = input.nextDouble();
		System.out.print("\n");
		return fuelEfficiency;
	}
	public double getCostOfGas(){
		Scanner input = new Scanner(System.in);
		System.out.print("Input the cost of gas (dollars/gal): ");
		double gasPrice = input.nextDouble();
		System.out.print("\n");
		return gasPrice;
	}

}
