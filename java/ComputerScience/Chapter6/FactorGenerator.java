package ComputerScience.Chapter6;

import MySystem.MySystem;

import java.util.Scanner;

/**
 * Finds all of the factors of a given integer
 *
 * @author Logan Traffas
 * @version 12/31/2016
 * assignment: Chapter06--Loops-6P6.3-Factoring Integers
 */
public class FactorGenerator{
	private int numberToFactor;
	private int lastVerifiedFactor;
	private int testFactor;

	/**
	 * Returns the next factor of the number
	 * @return the next factor of the number
	 */
	public int nextFactor(){
		while(this.testFactor > 0){
			if(this.numberToFactor % testFactor == 0){
				this.lastVerifiedFactor = this.testFactor;
				this.testFactor--;
				return this.lastVerifiedFactor;
			}
			this.testFactor--;
		}
		MySystem.nyi(MySystem.getFileName(),MySystem.getLineNumber());
		return 0;//should never reach this line
	}

	/**
	 * Checks to see if the number has any more potential factors
	 * @return true if there are potentially more factors left in the number
	 */
	public boolean hasMoreFactors(){
		int testingFactor = this.testFactor;//use a different int because this only checks to see if there are more factors. It does not find them.
		while(testingFactor > 0){
			if(this.numberToFactor % testingFactor == 0) return true;
			testingFactor--;
		}
		return false;
	}

	/**
	 * Constructs a new FactorGenerator with a number that needs factoring
	 * @param numberToFactor the number to factor
	 */
	public FactorGenerator(int numberToFactor){
		if(numberToFactor == 0){
			MySystem.error("There are no factors of zero",MySystem.getFileName(),MySystem.getLineNumber());
		}
		this.numberToFactor = numberToFactor;
		this.lastVerifiedFactor = numberToFactor;
		this.testFactor = numberToFactor;
	}

	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		System.out.print("Input an integer to factor: ");
		int input = in.nextInt();
		FactorGenerator factorGenerator = new FactorGenerator(input);
		System.out.print("The factors of " + input + " are ");
		while(factorGenerator.hasMoreFactors()){
			System.out.print(factorGenerator.nextFactor() + " ");
		}
	}
}
