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

	/**
	 * Merges two sequences by alternating their values
	 * @param other the sequence to merge with
	 * @return the new sequence after merger
	 */
	public Sequence merge(Sequence other){
		ArrayList a = this.getValues(), b = other.getValues();
		Sequence s = new Sequence();
		for(int i = 0; i < a.size() || i < b.size(); i++){
			if(i < a.size()) s.add((Integer)a.get(i));
			if(i < b.size()) s.add((Integer)b.get(i));
		}
		return s;
	}

	/**
	 * Merges two sequences so that their integer elements are in ascending order
	 * @param other the sequence to be merged with
	 * @return the sorted and merged sequence
	 */
	public Sequence mergeSorted(Sequence other){
		Sequence s = new Sequence();
		ArrayList a = this.getValues(), b = other.getValues();
		int last = 0;
		boolean set = false;
		for(int i = 0; i < a.size() || i < b.size(); i++){
			boolean aExists = i < a.size(), bExists = i < b.size();
			int bVal = bExists ? (int)b.get(i) : 0, aVal = aExists ? (int)a.get(i) : 0;
			if(aExists && (!bExists || aVal <= bVal)){
				s.add(aVal);
				if(bExists) s.add(bVal);
			}
			if(bExists && (!aExists || bVal <= aVal)){
				s.add(bVal);
				if(aExists) s.add(aVal);
			}
		}
		return s;
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
		System.out.println("a: " + a.toString() + " b: " + b.toString() + " append (c): " + c.toString());

		Sequence d = a.merge(b);
		System.out.println("a: " + a.toString() + " b: " + b.toString() + " merge (d): " + d.toString());

		Sequence e = a.mergeSorted(b);
		System.out.println("a: " + a.toString() + " b: " + b.toString() + " mergeSorted (e): " + e.toString());
	}
}
