package ComputerScience.Chapter9.Animals;

/**
 * @Author Logan Traffas
 * @Date 3/1/2017.
 * Represents a parrot
 */
public class Parrot extends Bird{
	String chirp = "Polly wants a cracker.";

	@Override
	public void eat(){
		System.out.println("A parrot eats....(Parrot class)");
	}

	@Override
	public void sleep(){
		System.out.println("A parrot sleeps...(Parrot class)");
	}

	public void chirp(){
		System.out.println("The parrot chirps: " + chirp);
	}

	public Parrot(){
		super();
		System.out.println("A new parrot has been created! (Parrot class)");
	}
}
