package ComputerScience.Chapter9.Test;

/**
 * This is for running the inheritance test of what happens when instance variables are overridden (do not do this)
 */
public class Runner {
	public static void main(String[] args){
		A a = new B();
		System.out.println("The value of i in A: " + a.i);
		System.out.println("The value of i in B: " + ((B)a).i);
		System.out.println("Notice that these are not the same variable with different values. They are different variables with different values.");
	}
}
