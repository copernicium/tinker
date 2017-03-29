package ComputerScience.Chapter10.Engineer;

/**
 * @Author Logan Traffas
 * @Date 3/20/2017.
 * @Version 1.0.0
 * @Assignment Ch10-Exercise1-Java Interface Tutorials--Engineer Interface
 */
public class ElectricalEngineer implements Engineer{
	private String title;

	public void useCAD(){
		System.out.println("The " + this.getTitle() + " doesn't know how to use CAD.");
	}

	public void designProduct(){
		System.out.println("The " + this.getTitle() + "'s new circuit board could now manage traffic lights and run Pong.");
	}

	public void payDues(int years){
		System.out.println("After " + years + " years of work, the " + this.getTitle() + " was finally promoted.");
	}

	public String getTitle(){
		return this.title;
	}

	public ElectricalEngineer(){
		this.title = "Electrical Engineer";
	}
}
