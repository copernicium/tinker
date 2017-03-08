package ComputerScience.Chapter9.Appointment;

/**
 A onetime appointment.
 */
public class Onetime extends Appointment{
	/**
	 Initializes appointment for a given date.
	 @param year the year
	 @param month the month
	 @param day the day
	 @param description the text description of the appointment
	 */
	public Onetime(int year, int month, int day, String description){
		super(year, month, day, description);
	}
}

