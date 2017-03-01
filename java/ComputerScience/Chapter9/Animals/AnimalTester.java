package ComputerScience.Chapter9.Animals;

/**
 * @Author Logan Traffas
 * @Date 3/1/2017.
 * Used to test inheritance with the Animal classes
 */
public class AnimalTester {
	public static void main(String[] args){
		System.out.println("Creating a new Animal");
		Animal animal = new Animal();

		System.out.println("Creating a new Dog");
		Dog dog = new Dog();

		System.out.println("Creating a new Poodle");
		Poodle poodle = new Poodle();

		System.out.println("Creating a new Bird");
		Bird bird = new Bird();

		System.out.println("Creating a new Parrot");
		Parrot parrot = new Parrot();

		System.out.println("Animal eat and sleep");
		animal.eat();
		animal.sleep();

		System.out.println("Dog eat and sleep");
		dog.eat();
		dog.sleep();

		System.out.println("Poodle eat and sleep");
		poodle.eat();
		poodle.sleep();

		System.out.println("Poodle eatFood");
		poodle.eatFood();

		System.out.println("Bird eat and sleep");
		bird.eat();
		bird.sleep();

		System.out.println("Parrot eat and sleep");
		parrot.eat();
		parrot.sleep();

		System.out.println("Bark test");
		dog.speak();
		poodle.speak();

		System.out.println("Chirp test");
		bird.chirp();
		parrot.chirp();
	}
}
