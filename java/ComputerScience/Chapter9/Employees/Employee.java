package ComputerScience.Chapter9.Employees;

import Util.Util;

/**
 * @Author Logan Traffas
 * @Date 3/1/2017.
 * @Version 1.0.0
 * @Assignment Ch 9: Introduction to Inheritance
 */
public class Employee {
	protected double hireDate;//TODO: determine type
	protected int age;
	protected char gender;
	protected String location;
	protected double salary;
	protected int monthsSinceReview;

	public double getHireDate(){
		return this.hireDate;
	}

	public int getAge(){
		return this.age;
	}

	public char getGender(){
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

	public void setHireDate(double hireDate){
		this.hireDate = hireDate;
	}

	public void setAge(int age){
		this.age = age;
	}

	public void setGender(char gender){
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
		//TODO
		Util.nyi(Util.getFileName(),Util.getLineNumber());
		return 0;
	}

	public Employee(){
		this.hireDate = 0;
		this.age = 0;
		this.gender = ' ';
		this.location = "";
		this.salary = 0;
		this.monthsSinceReview = 0;
	}
}
