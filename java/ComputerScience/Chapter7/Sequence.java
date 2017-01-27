package ComputerScience.Chapter7;

import java.util.ArrayList;

/**
 * A class that tests a few of ArrayList's methods
 *
 * @author Logan Traffas
 * @version 1/27/2017
 * assignment: Chapter07--Arrays-7L7.8 --Working with ArrayLists
 */
public class Sequence {
	private ArrayList<Integer> values;

	/**
	 * Adds a given int to the sequence
	 * @param n the int to add
	 */
	public void add(int n) {
		values.add(n);
	}

	/**
	 * Gets the values of the sequence
	 * @return the values of the sequence
	 */
	public ArrayList<Integer> getValues(){
		return this.values;
	}

	/**
	 * Converts the sequence to a readable string with useful information
	 * @return the sequence in string form
	 */
	public String toString() {
		return values.toString();
	}

	/**
	 * Constructs a new sequence with an empty integer array
	 */
	public Sequence() {
		values = new ArrayList<>();
	}

	/**
	 * Assembles a new Sequence from this and the argument, appending the latter to the former
	 * @param other the sequence to append to the end
	 * @return a new sequence containing this with other appended to it
	 */
	public Sequence append(Sequence other){
		Sequence a = new Sequence();
		for(Integer i: this.getValues()){
			a.add(i);
		}
		for(Integer i: other.getValues()){
			a.add(i);
		}
		return a;
	}

	public static void main(String[] args){
		Sequence a = new Sequence();
		a.add(1);
		a.add(4);
		a.add(9);
		a.add(16);

		Sequence b = new Sequence();
		b.add(9);
		b.add(7);
		b.add(4);
		b.add(9);
		b.add(11);

		System.out.println("a: " + a.toString() + " b: " + b.toString());
		Sequence c = a.append(b);
		System.out.println("a: " + a.toString() + " b: " + b.toString() + " c: " + c.toString());
	}
}
