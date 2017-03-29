package ComputerScience.Chapter10.Engineer;

/**
 * @Author Logan Traffas
 * @Date 3/20/2017.
 * @Version 1.0.0
 * @Assignment Ch10-Exercise1-Java Interface Tutorials--Engineer Interface
 */
public class MechanicalEngineer implements Engineer {
	private String title;

	public void useCAD(){
		System.out.println("The " + this.getTitle() + " tried to open CAD, but it crashed.");
	}

	public void designProduct(){
		System.out.println("The " + this.getTitle() + " CADed a piston 10x more efficient than their current competitors'.");
	}

	public void payDues(int years){
		System.out.println("After " + years + " years of work, the " + this.getTitle() + " was finally promoted.");
	}

	public String getTitle(){
		return this.title;
	}

	public MechanicalEngineer(){
		this.title = "Mechanical Engineer";
	}
}
