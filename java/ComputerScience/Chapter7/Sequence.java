package ComputerScience.Chapter7;


import MySystem.Maybe;
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
		ArrayList<Integer> c = new ArrayList<>();
		{
			ArrayList a = new ArrayList(this.getValues()), b = new ArrayList(other.getValues());
			Maybe<Integer> last = new Maybe<Integer>();
			while(a.size() > 0 || b.size() > 0){
				Maybe<Integer> next = new Maybe<Integer>(), aVal = new Maybe<Integer>(), bVal = new Maybe<Integer>();

				if(a.size() > 0) aVal.set((int)a.get(0));
				if(b.size() > 0) bVal.set((int)b.get(0));

				if(aVal.isValid() && (!bVal.isValid() || aVal.get() <= bVal.get())){
					next.set(aVal.get());
					a.remove(0);
				} else if(bVal.isValid() && (!aVal.isValid() || bVal.get() <= aVal.get())){
					next.set(bVal.get());
					b.remove(0);
				}

				if(next.isValid()){
					if(last.isValid() && next.get() < last.get()) break;
					c.add(next.get());
					last.set(next.get());
				}
			}
		}
		for(int a: c){
			s.add(a);
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
