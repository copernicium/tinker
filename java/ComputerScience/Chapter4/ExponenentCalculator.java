package ComputerScience.Chapter4;
import java.util.Scanner;
/**
 * A class which reads an input and calculates a few of its powers
 * 
 * @author Logan Traffas
 * @version 10/24/2016
 * assignment: Chapter 04--Fundamental Data Types-E4.3
 */
public class ExponenentCalculator
{
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        double base = 0.0;
        System.out.print("Please input a base: ");
        base = input.nextDouble();//take user input for the base
        System.out.print("\n");
        for(int i = 1; i <= 4; i++){// i is the exponent
            System.out.println(base + " to the " + i + " power is " + Math.pow(base,i));//print the result
        }
    }
}
