package ComputerScience.Chapter10.Engineer;

/**
 * @Author Logan Traffas
 * @Date 3/20/2017.
 * @Version 1.0.0
 * @Assignment Ch10-Exercise1-Java Interface Tutorials--Engineer Interface
 */
public class SoftwareEngineer implements Engineer{
	private String title;

	public void useCAD(){
		System.out.println("The Software Engineer accidentally opened CAD. Having realized their mistake, they closed it and booted up Intellij.");
	}

	public void designProduct(){
		System.out.println("The " + this.getTitle() + "'s new mobile app would be an instant hit! Literally! It was a simulator which mimed hitting the user.");
	}

	public void payDues(int years){
		System.out.println("After " + years + " years of work, the Software Engineer was finally promoted.");
	}

	public String getTitle(){
		return this.title;
	}

	public SoftwareEngineer(){
		this.title = "Software Engineer";
	}
}
