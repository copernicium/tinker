package ComputerScience.Chapter7;

/**
 * Fills arrays with an assigned pattern of integers
 *
 * @author Logan Traffas
 * @version 1/18/2017
 * assignment: Chapter07-Arrays-7R7.1--Filling arrays
 */
public class ArrayFiller{
	/**
	 * Takes an array and returns a string which represents it
	 * @param array the array to print
	 * @param <T> the type of the array elements
	 * @return the array in string form
	 */
	public static <T extends Object> String arrayToString(T[] array){
		String s = "[";
		for(int i = 0; i < array.length; i++){
			s += array[i].toString();
			if(i < array.length - 1) s += ", ";
		}
		s += "]";
		return s;
	}

	public static void main(String[] args){
		{
			Integer[] ints = new Integer[10];
			for(int i = 1; i < ints.length + 1; i++){
				ints[i - 1] = i;
			}
			System.out.println("1. " + arrayToString(ints));
		}
		{
			Integer[] ints = new Integer[11];
			for(int i = 0; i < ints.length; i++){
				ints[i] = i * 2;
			}
			System.out.println("2. " + arrayToString(ints));
		}
		{
			Integer[] ints = new Integer[10];
			for(int i = 1; i < ints.length + 1; i++){
				ints[i - 1] = i * i;
			}
			System.out.println("3. " + arrayToString(ints));
		}
		{
			Integer[] ints = new Integer[10];
			for(int i = 0; i < ints.length; i++){
				ints[i] = 0;
			}
			System.out.println("4. " + arrayToString(ints));
		}
		{
			Integer[] ints = {1, 4, 9, 16, 9, 7, 4, 9, 11};
			System.out.println("5. " + arrayToString(ints));
		}
		{
			Integer[] ints = new Integer[10];
			boolean b = false;
			for(int i = 0; i < ints.length; i++){
				ints[i] = b ? 1 : 0;
				b = !b;
			}
			System.out.println("6. " + arrayToString(ints));
		}
		{
			Integer[] ints = new Integer[10];
			int next = 0;
			for(int i = 0; i < ints.length; i++){
				ints[i] = next;
				next++;
				if(next > 4) next = 0;
			}
			System.out.println("7. " + arrayToString(ints));
		}
	}
}
