package ComputerScience.Chapter9.Test;

/**
 * This is for running the inheritance test of what happens when instance variables are overridden (do not do this)
 */
public class Runner {
	public static void main(String[] args){
		{
			A a = new A();
			A b = new B();

			System.out.println("a and b are both of type A, but a is set to a new A and b is set to a new B");
			System.out.println("b.i = " + b.i);//prints 10
			System.out.println("((B)b).i = " + ((B)b).i);//prints 20
			System.out.println("a.j = " + a.j);//prints 5
			System.out.println("b.j = " + b.j);//prints 15
		}
	}
}
