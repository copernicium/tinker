package ComputerScience.Chapter9.Animals;

/**
 * @Author Logan Traffas
 * @Date 3/1/2017.
 * Represents a Poodle
 */
public class Poodle extends Dog{
	String bark = "Arf Arf, I'm a poodle.";

	@Override
	public void eat(){
		System.out.println("A poodle eats....(Poodle class)");
	}

	@Override
	public void sleep(){
		System.out.println("A poodle sleeps...(Poodle class)");
	}

	@Override
	public void speak(){
		System.out.println("The poodle says " + bark);
	}

	public void eatFood(){
		super.eat();
	}

	public Poodle(){
		super();
		System.out.println("A new poodle has been created! (Poodle class)");
	}
}
