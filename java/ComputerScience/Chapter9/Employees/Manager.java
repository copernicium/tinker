package ComputerScience.Chapter9.Employees;

/**
 * @Author Logan Traffas
 * @Date 3/1/2017.
 * @Version 1.0.0
 * @Assignment Ch 9: Introduction to Inheritance
 */
public class Manager extends Employee{
	protected int numberEmployees;
	protected String title;

	public int getNumberEmployees(){
		return this.numberEmployees;
	}

	public String getTitle(){
		return this.title;
	}

	public void setNumberEmployees(int numberEmployees){
		this.numberEmployees = numberEmployees;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public double calculateBonus(){
		return 0;//we hate management so no bonuses for them
	}

	public Manager(){
		super();
		this.numberEmployees = 0;
		this.title = "";
	}
}
