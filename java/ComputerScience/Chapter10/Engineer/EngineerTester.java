package ComputerScience.Chapter10.Engineer;

/**
 * @Author Logan Traffas
 * @Date 3/20/2017.
 * @Version 1.0.0
 * @Assignment Ch10-Exercise1-Java Interface Tutorials--Engineer Interface
 */
public class EngineerTester{
	public static void test(Engineer engineer,int years){
		System.out.println(engineer.getTitle());
		engineer.designProduct();
		engineer.payDues(years);
		engineer.useCAD();
		System.out.print("\n");
	}

	public static void main(String[] args){
		test(new ElectricalEngineer(),3);
		test(new MechanicalEngineer(),5);
		test(new SoftwareEngineer(),2);

	}
}
