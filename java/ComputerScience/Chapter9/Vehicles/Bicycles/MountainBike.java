package ComputerScience.Chapter9.Vehicles.Bicycles;

/**
 * @Author Logan Traffas
 * @Date 3/1/2017.
 * @Version 1.0.0
 * @Assignment  Ch 9: Introduction to Inheritance
 */
public class MountainBike extends Bicycle {
	// the MountainBike subclass adds one field
	public int seatHeight;

	// the MountainBike subclass has one constructor
	public MountainBike(int startHeight,
						int startCadence,
						int startSpeed,
						int startGear) {
		super(startCadence, startSpeed, startGear);
		seatHeight = startHeight;
	}

	// the MountainBike subclass adds one method
	public void setHeight(int newValue) {
		seatHeight = newValue;
	}
}