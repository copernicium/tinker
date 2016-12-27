/**
 * Convert different volumes
 * 
 * @author Logan Traffas
 * @version 9/23/2016
 * assignment Unit Conversions
 */
/*
 * Number of cubic inches in a cubic centimeter  (1 in = 2.54 cm)
 * Number of cubic inches in a cubic yard (1 yard = 36 inches)
 * Number of cubic inches in 4.5 gallons  (1 cubic centimeter = 0.000264172052 gallons)
 */
import java.lang.Math;
class VolumeCalculator {
    public static double centimetersToInches(double centimeters){
        final double inchesPerCentimeter = 1.0/2.54;
        return centimeters*inchesPerCentimeter;
    }
     public static double yardsToInches(double yards){
        final double inchesPerYard = 36.0 / 1.0;
        return yards*inchesPerYard;
    }
     public static double gallonsToMilliliters(double gallons){
        final double millilitersPerGallon= 1.0 / 0.000264172052;
        return gallons*millilitersPerGallon;
    }
    public static void main(String args[]) {    
        {
            double centimeters = 1.0;
            double cubicCentimeters = Math.pow(centimeters,3);
            double cubicInches= Math.pow(centimetersToInches(centimeters),3);
      
            System.out.println("There are " + cubicInches + " cubic inches in a cubic centimeter."); 
        }
        {
            double yards = 1.0;
            double cubicYards = Math.pow(yards,3);
            double cubicInches = yardsToInches(yards)*yardsToInches(yards)*yardsToInches(yards);
            
            System.out.println("There are " + cubicInches + " cubic inches in a cubic yard."); 
        }
        {
           double gallons = 4.5;
           double cubicCentimeters = gallonsToMilliliters(gallons);
           double centimeters = Math.pow(cubicCentimeters, 1.0/3.0);
           double cubicInches = Math.pow(centimetersToInches(centimeters),3);
           
           System.out.println("There are " + cubicInches + " cubic inches in 4.5 gallons."); 
            
        }
    }
}