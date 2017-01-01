package ComputerScience.Chapter6;

import MySystem.MySystem;

import java.util.Scanner;
import java.util.Vector;

/**
 * Prompts the user to enter the temperatures for each month of a year and then returns the hottest one
 *
 * @author Logan Traffas
 * @version 12/31/2016
 * assignment: Chapter06--Loops=6E6.4--Highest Temperature
 */
public class HottestMonthCalculator{
	public static final int NUMBER_OF_MONTHS = 12;
	public enum Month{
		JAN, FEB, MAR, APR, MAY, JUN, JUL, AUG, SEP, OCT, NOV, DEC;

		public static  Month parseMonth(int month){
			switch(month){
				case 1:
					return JAN;
				case 2:
					return FEB;
				case 3:
					return MAR;
				case 4:
					return APR;
				case 5:
					return MAY;
				case 6:
					return JUN;
				case 7:
					return JUL;
				case 8:
					return AUG;
				case 9:
					return SEP;
				case 10:
					return OCT;
				case 11:
					return NOV;
				case 12:
					return DEC;
				default:
					MySystem.error("Failed to parse month",MySystem.getFileName(),MySystem.getLineNumber());
			}
			return JAN;//should never reach this line
		}
	}

	/**
	 * Searches through a vector of temperatures for each month and returns the month with the highest temperature
	 * @param temps a vector of temperatures for each month
	 * @return the hottest month
	 */
	public static Month findHottestMonth(Vector<Double> temps){
		MySystem.myAssert(temps.size() == NUMBER_OF_MONTHS,MySystem.getFileName(),MySystem.getLineNumber());
		double hottestTemp = temps.elementAt(0);
		Month hottestMonth = Month.JAN;
		for(int i = 0; i < temps.size(); i++){
			if(temps.elementAt(i)> hottestTemp){
				hottestTemp = temps.elementAt(i);
				hottestMonth = Month.parseMonth(i + 1);
			}
		}
		return hottestMonth;
	}

	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		Vector<Double> temps = new Vector<>();
		for(int i = 0; i < NUMBER_OF_MONTHS; i++){
			System.out.print("Please input the temperature for " + Month.parseMonth(i + 1) + ": ");
			double temp = in.nextDouble();
			temps.add(temp);
		}
		System.out.println("The hottest month was " + HottestMonthCalculator.findHottestMonth(temps));
	}
}
