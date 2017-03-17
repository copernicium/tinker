package ComputerScience.Chapter9.Employees;

/**
 * @Author Logan Traffas
 * @Date 3/1/2017.
 * @Version 1.0.0
 * @Assignment Ch 9: Introduction to Inheritance
 */
public class Programmer extends Employee{
	protected String project;
	protected String programmingLanguage;

	public String getProject(){
		return this.project;
	}

	public String getProgrammingLanguage(){
		return this.programmingLanguage;
	}

	public void setProgrammingLanguage(String programmingLanguage){
		this.programmingLanguage = programmingLanguage;
	}

	public void setProject(String project){
		this.project = project;
	}

	public double calculateRaise(){
		return 1.1 * this.salary;
	}

	public Programmer(){
		super();

		this.project = "";
		this.programmingLanguage = "";
	}
}
