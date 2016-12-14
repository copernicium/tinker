package computer_science.Chapter4;
import java.util.Scanner;
/**
 * A class which takes user input and prints the results of a few mathematical operators
 * 
 * @author Logan Traffas
 * @version 10/24/2016
 * assignment: Chapter 04--Fundamental Data Types--E4.4
 */
public class SimpleCalculator
{
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        int x = 0;
        int y = 0;
        System.out.print("Please input an integer: ");
        x = input.nextInt();
        System.out.print("\nPlease input another integer: ");
        y = input.nextInt();
        String variableTerm = " of " + x + " and " + y + " is ";
        System.out.println("The sum" + variableTerm + (x+y));
        System.out.println("The difference" + variableTerm + (x-y));
        System.out.println("The product" + variableTerm + (x*y));
        System.out.println("The average" + variableTerm + Math.abs((float)(x+y)/2));
        System.out.println("The maximum" + variableTerm + Math.max(x,y));
        System.out.println("The minimum" + variableTerm + Math.min(x,y));
    }
}
