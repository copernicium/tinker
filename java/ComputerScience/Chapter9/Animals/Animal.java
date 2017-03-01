package ComputerScience.Chapter9.Animals;

/**
 * @Author Logan Traffas
 * @Date 3/1/2017.
 * Represents a generic animal
 */
public class Animal {
	public void eat(){
		System.out.println("An animal eats....(superclass)");
	}

	public void sleep(){
		System.out.println("An animal sleeps...(superclass)");
	}

	public Animal(){
		System.out.println("A new animal has been created! (superclass)");
	}
}
