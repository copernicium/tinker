package computer_science.chapter5;

import MySystem.MySystem;

import java.util.Scanner;

/*

"Unit conversion. Write a unit conversion program that asks the users
from which unit they want to convert (fl. oz, gal, oz, lb, in, ft, mi)
and to which unit they want to convert (ml, l, g, kg, mm, cm, m, km).
Reject incompatible conversions (such as gal → km). Ask for the value
to be converted, then display the result:

Convert from? gal
Convert to? ml
Value? 2.5
gal = 9462.5 ml

 */

/**
 * Converts values, given by user, between units, also given by user
 *
 * @author Logan Traffas
 * @version 12/1/2016
 * assignment: Chapter 05--Decisions-P5.4--Unit Conversion
 */
public class UnitConversion{
	public interface Unit{}
	public enum Volume implements Unit{FLUID_OUNCES, MILLILITERS, LITERS, GALLONS}
	public enum Distance implements Unit{INCHES, FEET, MILES, MILLIMETERS, CENTIMETERS, METERS, KILOMETERS}
	public enum Weight implements Unit{OUNCES, POUNDS, KILOGRAMS, GRAMS}
	public enum Undef implements Unit{UNDEF}

	private Unit startUnit;
	private Unit endUnit;
	private double value;

	private static String toLower(final String ORIGINAL){
		String lowerCase = "";
		for(char a: ORIGINAL.toCharArray()){
			lowerCase += Character.toLowerCase(a);
		}
		return lowerCase;
	}

	public static Unit toUnit(final String INPUT){
		String lowerCase = toLower(INPUT);
		final String FL_OZ = "fl_oz", ML = "ml", L = "l", GAL = "gal";
		final String IN = "in", FT = "ft", MI = "mi", MM = "mm", CM = "cm", M = "m", KM = "km";
		final String OZ = "oz", LB = "lb", KG = "kg", G = "g";
		switch (lowerCase){
			case FL_OZ:
				return Volume.FLUID_OUNCES;
			case ML:
				return Volume.MILLILITERS;
			case L:
				return Volume.LITERS;
			case GAL:
				return Volume.GALLONS;
			case IN:
				return Distance.INCHES;
			case FT:
				return Distance.FEET;
			case MI:
				return Distance.MILES;
			case MM:
				return Distance.MILLIMETERS;
			case CM:
				return Distance.CENTIMETERS;
			case M:
				return Distance.METERS;
			case KM:
				return Distance.KILOMETERS;
			case OZ:
				return Weight.OUNCES;
			case LB:
				return Weight.POUNDS;
			case KG:
				return Weight.KILOGRAMS;
			case G:
				return Weight.GRAMS;
			default:
				return Undef.UNDEF;
		}
	}

	public void getInput(){
		Scanner input = new Scanner(System.in);
		System.out.print("Input your starting unit (ex: gal): ");
		String startingUnit = input.next();
		this.startUnit = toUnit(startingUnit);
		System.out.print("Input your the value: ");
		String userValue = input.next();
		this.value = Double.parseDouble(userValue);
		System.out.print("Input your ending unit (ex: liter): ");
		String endingUnit = input.next();
		this.endUnit = toUnit(endingUnit);
	}

	private double convert(){
		MySystem.myAssert(/*TODO*/,MySystem.getFileName(),MySystem.getLineNumber());
		return 0;
	}

	public void getConversion(){
		MySystem.myAssert(this.startUnit != Undef.UNDEF && this.endUnit != Undef.UNDEF,MySystem.getFileName(),MySystem.getLineNumber());
		System.out.println(this.value + " in " + this.startUnit + " is " + convert() + " in " + this.endUnit);
	}

	public UnitConversion(){
		this.startUnit = Undef.UNDEF;
		this.endUnit = Undef.UNDEF;
		this.value = 0;
	}
}
