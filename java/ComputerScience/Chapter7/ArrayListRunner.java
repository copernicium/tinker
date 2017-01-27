package ComputerScience.Chapter7;

import java.util.ArrayList;

/**
 * A class that tests a few of ArrayList's methods
 *
 * @author Logan Traffas
 * @version 1/27/2017
 * assignment: Chapter07--Arrays-7L7.8 --Working with ArrayLists
 */
public class ArrayListRunner
{
	public static void main(String[] args)
	{
		ArrayList<String> names = new ArrayList<>();
		System.out.println("Original: " + names);
		names.add("Alice");
		names.add("Bob");
		names.add("Connie");
		names.add("David");
		names.add("Edward");
		names.add("Fran");
		names.add("Gomez");
		names.add("Harry");
		System.out.println("a: " + names);
		for(int i = 0; i < names.size(); i++) {
			System.out.println("b" + i + ": " + names.get(i));
		}
		System.out.println("c: " + names.size());
		System.out.println("d: " + names.get(names.size()-1));

		names.set(0,"Alice B. Toklas");
		System.out.println("e: " + names.toString());
		names.add(4,"Doug");
		System.out.println("f: " + names.toString());
		for(String a: names) {
			System.out.println("g: " + a);
		}
		ArrayList<String> names2 = new ArrayList<>(names);
		System.out.println("h: " + names2.toString());
		names.remove(0);
		System.out.println("i (names): " + names.toString());
		System.out.println("h (names2): " + names2.toString());
	}
}
