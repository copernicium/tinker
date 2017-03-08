package ComputerScience.Chapter9.Appointment;

/**
 Daily appointment.
 */
public class Daily extends Appointment{
	/**
	 Initializes appointment for a given date.
	 @param year the year
	 @param month the month
	 @param day the day
	 @param description the text description of the appointment
	 */
	public Daily(int year, int month, int day, String description){
		super(year, month, day, description);
	}

	/**
	 Determines if the date is later than the appointment date
	 @param year the year
	 @param month the month
	 @param day the day
	 @return true if base appointment is earlier than the appointment date
	 */
	@Override
	public boolean occursOn(int year, int month, int day)
	{
		if (year > getYear()){
			return true;
		}
		if (year == getYear())
		{
			if (month > getMonth()){
				return true;
			}
			if (month == getMonth()){
				return day >= getDay();
			}
		}
		return false;
	}
}

