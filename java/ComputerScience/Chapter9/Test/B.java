package ComputerScience.Chapter9.Test;

/**
 * Subclass for A
 */
public class B extends A{
	public int i;//creates a new i which is initialized in the constructor and then hidden by the i declared in A
	public B(){
		i = 20;
	}
}
