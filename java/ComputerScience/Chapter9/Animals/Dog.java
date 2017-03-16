package ComputerScience.Chapter9.Animals;

/**
 * @Author Logan Traffas
 * @Date 3/1/2017.
 * Represents a generic dog
 */
public class Dog extends Animal {
	String bark = "Ruff Ruff, I'm a dog.";

	@Override
	public void eat(){
		System.out.println("A dog eats....(Dog class)");
	}

	@Override
	public void sleep(){
		System.out.println("A dog sleeps...(Dog class)");
	}

	public void speak(){
		System.out.println("The dog says " + bark);
	}

	public Dog(){
		super();
		System.out.println("A new dog has been created! (Dog class)");
	}
}
