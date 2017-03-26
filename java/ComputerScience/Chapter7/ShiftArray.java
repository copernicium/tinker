package ComputerScience.Chapter7;

import java.util.Arrays;

/**
 *
 *
 * @author Logan Traffas
 * @version 1/28/2017
 * assignment: Chapter 07--E7.2b--ShiftArray
 */
public class ShiftArray{
	/**
	 * Shift all elements of an array to the right by one and move the last element to the first position.
	 * @param array the array to shift
	 * @return the shifted array
	 */
	public static int[] shiftArray(int[] array){
		int[] newArray = new int[array.length];
		for(int i = 0; i < array.length - 1; i++){
			newArray[i+1] = array[i];
		}
		newArray[0] = array[9];
		return newArray;
	}

	public static void main(String[] args){
		int[] array = {1, 4, 9, 16, 25, 17, 8, 2, 3, 10};
		System.out.println("Original: " + Arrays.toString(array));
		int[] shiftedArray = shiftArray(array);
		System.out.println("Shifted array: " + Arrays.toString(shiftedArray));
	}
}
