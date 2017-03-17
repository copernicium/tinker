package ComputerScience.Chapter9.Vehicles.Bicycles;
/**
 * @Author Logan Traffas
 * @Date 3/1/2017.
 * @Version 1.0.0
 * @Assignment  Ch 9: Introduction to Inheritance
 */
public class BikeTester {
	//This is the code that will run your program
	public static void main(String[] args)
	{
		// main program code goes here
		MountainBike mBike = new MountainBike(10, 20, 25, 15);
		System.out.println("Mountain bike start gear is "+ mBike.gear);
		mBike.gear = 200;
		System.out.println("Mountain bike start gear is "+ mBike.gear);
	}
}
