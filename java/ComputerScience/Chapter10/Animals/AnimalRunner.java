package ComputerScience.Chapter10.Animals;
import java.util.*;

public class AnimalRunner{
	public static void main(String[] args){
		ArrayList<Speakable> dogcatList = new ArrayList<>();
		dogcatList.add(new Dog("Fred"));
		dogcatList.add(new Cat("Wanda"));
		for (Speakable obj : dogcatList)
		{
			 obj.speak();
		}
	}
}