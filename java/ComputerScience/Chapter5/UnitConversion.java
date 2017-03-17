package ComputerScience.Chapter5;

import Util.Util;
import java.util.Scanner;

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
		final String FL_OZ = "fl oz", ML = "ml", L = "l", GAL = "gal";
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
		System.out.print("Input your starting unit (ex: mm): ");
		String startingUnit = input.nextLine().trim();
		this.startUnit = toUnit(startingUnit);
		System.out.print("Input your the value: ");
		String userValue = input.next();
		input.nextLine();//used to consume the newline character excluded by next()
		this.value = Double.parseDouble(userValue);
		System.out.print("Input your ending unit (ex: cm): ");
		String endingUnit = input.nextLine().trim();
		this.endUnit = toUnit(endingUnit);
	}

	private static double reverse(double a){
		return 1.0/a;
	}

	private static double convertWeight(final Weight startUnit, final Weight endUnit, final double value){
		final double OUNCES_PER_POUND = 16.0, GRAMS_PER_POUND = 453.592, GRAMS_PER_KILOGRAM = 1000, KILOGRAMS_PER_POUND = 0.453592;
		switch(startUnit){
			case POUNDS:
				switch(endUnit){
					case POUNDS:
						return value;
					case OUNCES:
						return value*OUNCES_PER_POUND;
					case GRAMS:
						return value*GRAMS_PER_POUND;
					case KILOGRAMS:
						return value*KILOGRAMS_PER_POUND;
					default:
						Util.nyi(Util.getFileName(), Util.getLineNumber());
				}
			case OUNCES:
				return convertWeight(Weight.POUNDS,endUnit,(value*reverse(OUNCES_PER_POUND)));//use recursion to get to the proper unit
			case KILOGRAMS:
				return convertWeight(Weight.POUNDS,endUnit,(value*reverse(KILOGRAMS_PER_POUND)));
			case GRAMS:
				return convertWeight(Weight.KILOGRAMS,endUnit,(value*reverse(GRAMS_PER_KILOGRAM)));
			default:
				Util.nyi(Util.getFileName(), Util.getLineNumber());
		}
		Util.error("Conversion failed", Util.getFileName(), Util.getLineNumber());
		return 0;
	}

	private static double convertDistance(final Distance startUnit, final Distance endUnit, final double value){
		final double FEET_PER_INCH = 0.0833333, MILES_PER_INCH = 1.57828e-5, MILLIMETERS_PER_INCH = 25.4, MILLIMETERS_PER_CENTIMETER = 10, CENTIMETERS_PER_METER = 100, METERS_PER_KILOMETER = 1000;
		switch(startUnit){
			case INCHES:
				switch(endUnit){
					case INCHES:
						return value;
					case FEET:
						return value*FEET_PER_INCH;
					case MILES:
						return value*MILES_PER_INCH;
					case MILLIMETERS:
						return value*MILLIMETERS_PER_INCH;
					case CENTIMETERS:
						return value*MILLIMETERS_PER_INCH*reverse(MILLIMETERS_PER_CENTIMETER);
					case METERS:
						return value*MILLIMETERS_PER_INCH*reverse(MILLIMETERS_PER_CENTIMETER)*reverse(CENTIMETERS_PER_METER);
					case KILOMETERS:
						return value*MILLIMETERS_PER_INCH*reverse(MILLIMETERS_PER_CENTIMETER)*reverse(CENTIMETERS_PER_METER)*reverse(METERS_PER_KILOMETER);
					default:
						Util.nyi(Util.getFileName(), Util.getLineNumber());
				}
			case FEET:
				return convertDistance(Distance.INCHES,endUnit,value*reverse(FEET_PER_INCH));//use recursion to get to the proper unit
			case MILES:
				return convertDistance(Distance.INCHES,endUnit,value*reverse(MILES_PER_INCH));
			case MILLIMETERS:
				return convertDistance(Distance.INCHES,endUnit,value*reverse(MILLIMETERS_PER_INCH));
			case CENTIMETERS:
				return convertDistance(Distance.MILLIMETERS,endUnit,value*MILLIMETERS_PER_CENTIMETER);
			case METERS:
				return convertDistance(Distance.CENTIMETERS,endUnit,value*CENTIMETERS_PER_METER);
			case KILOMETERS:
				return convertDistance(Distance.METERS,endUnit,value*METERS_PER_KILOMETER);
			default:
				Util.nyi(Util.getFileName(), Util.getLineNumber());
		}
		Util.error("Conversion failed", Util.getFileName(), Util.getLineNumber());
		return 0;
	}

	private static double convertVolume(final Volume startUnit, final Volume endUnit, final double value){
		final double MILLILITERS_PER_LITER = 1000, FLUID_OUNCES_PER_LITER = 33.814, FLUID_OUNCES_PER_GALLON = 128;
		switch(startUnit){
			case LITERS:
				switch(endUnit){
					case LITERS:
						return value;
					case MILLILITERS:
						return value*MILLILITERS_PER_LITER;
					case FLUID_OUNCES:
						return value*FLUID_OUNCES_PER_LITER;
					case GALLONS:
						return value*FLUID_OUNCES_PER_LITER*reverse(FLUID_OUNCES_PER_GALLON);
					default:
						Util.nyi(Util.getFileName(), Util.getLineNumber());
				}
			case MILLILITERS:
				return convertVolume(Volume.LITERS,endUnit,value*reverse(MILLILITERS_PER_LITER));//use recursion to get to the proper unit
			case FLUID_OUNCES:
				return convertVolume(Volume.LITERS,endUnit,value*reverse(FLUID_OUNCES_PER_LITER));
			case GALLONS:
				return convertVolume(Volume.FLUID_OUNCES,endUnit,value*FLUID_OUNCES_PER_GALLON);
			default:
				Util.nyi(Util.getFileName(), Util.getLineNumber());
		}
		Util.error("Conversion failed", Util.getFileName(), Util.getLineNumber());
		return 0;
	}

	private double convert(){
		if(this.startUnit.getClass() != this.endUnit.getClass()) Util.error("Unit types do not match", Util.getFileName(), Util.getLineNumber());
		if(this.startUnit == this.endUnit) return this.value;
		if(this.startUnit instanceof Weight) return convertWeight((Weight)this.startUnit,(Weight)this.endUnit,this.value);
		else if(this.startUnit instanceof Volume) return convertVolume((Volume)this.startUnit,(Volume)this.endUnit,this.value);
		else if(this.startUnit instanceof Distance) return convertDistance((Distance)this.startUnit,(Distance)this.endUnit,this.value);
		Util.nyi(Util.getFileName(), Util.getLineNumber());
		return 0;//should never reach here
	}

	public void getConversion(){
		if(this.startUnit == Undef.UNDEF || this.endUnit == Undef.UNDEF) Util.error("One or more units undefined", Util.getFileName(), Util.getLineNumber());
		System.out.println(this.value + " in " + this.startUnit + " is " + convert() + " in " + this.endUnit);
	}

	public UnitConversion(){
		this.startUnit = Undef.UNDEF;
		this.endUnit = Undef.UNDEF;
		this.value = 0;
	}
}
