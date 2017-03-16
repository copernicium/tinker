package ComputerScience.Chapter9.Animals;

/**
 * @Author Logan Traffas
 * @Date 3/1/2017.
 * Represents a generic bird
 */
public class Bird extends Animal{
	String chirp = "Cheep cheep.";

	@Override
	public void eat(){
		System.out.println("A bird eats....(Bird class)");
	}

	@Override
	public void sleep(){
		System.out.println("A bird sleeps...(Bird class)");
	}

	public void chirp(){
		System.out.println("The bird chirps: " + chirp);
	}

	public Bird(){
		super();
		System.out.println("A new bird has been created! (Bird class)");
	}
}
