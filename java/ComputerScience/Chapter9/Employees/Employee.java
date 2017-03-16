package ComputerScience.Chapter9.Employees;

/**
 * @Author Logan Traffas
 * @Date 3/1/2017.
 * @Version 1.0.0
 * @Assignment Ch 9: Introduction to Inheritance
 */
public class Employee {
	enum Gender{MALE,FEMALE,OTHER};
	public static class Date{
		enum Month{JAN,FEB,MAR,APR,MAY,JUN,JUL,AUG,SEPT,OCT,NOV,DEC}
		private Month month;
		private int day;
		private int year;

		public Date(Month month,int day,int year){
			this.month = month;
			this.day = day;
			this.year = year;
		}
		public Date(){
			this(Month.JAN,0,0);
		}
	}
	protected Date hireDate;
	protected int age;
	protected Gender gender;
	protected String location;
	protected double salary;
	protected int monthsSinceReview;

	public Date getHireDate(){
		return this.hireDate;
	}

	public int getAge(){
		return this.age;
	}

	public Gender getGender(){
		return this.gender;
	}

	public String getLocation(){
		return this.location;
	}

	public double getSalary(){
		return this.salary;
	}

	public int getMonthsSinceReview(){
		return this.monthsSinceReview;
	}

	public void setHireDate(Date hireDate){
		this.hireDate = hireDate;
	}

	public void setAge(int age){
		this.age = age;
	}

	public void setGender(Gender gender){
		this.gender = gender;
	}

	public void setLocation(String location){
		this.location = location;
	}

	public void setSalary(double salary){
		this.salary = salary;
	}

	public void setMonthsSinceReview(int monthsSinceReview){
		this.monthsSinceReview = monthsSinceReview;
	}

	public int computeMonthsToNextReview(){
		return 12 - monthsSinceReview;
	}

	public Employee(){
		this.hireDate = new Date();
		this.age = 0;
		this.gender = Gender.OTHER;
		this.location = "";
		this.salary = 0;
		this.monthsSinceReview = 0;
	}
}
