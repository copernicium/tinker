package ComputerScience.Chapter7;

import java.util.Arrays;

/**
 * Runs a few common array algorithms on an array
 *
 * @author Logan Traffas
 * @version 1/28/2017
 * assignment: Chapter 07--Common Array Algorithms
 */
public class ArrayAlgorithms{
	public static void main(String[] args){
		final int[] ARRAY = {14, 25, 44, 88, 2, 54, 15, 40, 0, 3};
		{
			int[] array = new int[10];
			for(int base = 0; base < array.length; base++){
				array[base] = base * base;
			}
			System.out.println("Filling - " + Arrays.toString(array));
		}
		{
			int sum = 0;
			for(int a: ARRAY){
				sum+= a;
			}
			double average = (double) sum / ARRAY.length;
			System.out.println("Sum and Average - " + Arrays.toString(ARRAY) + " sum: " + sum + " average: " + average);
		}
		{
			int min = 0, max = 0;
			boolean set = false;
			for(int a: ARRAY){
				if(!set || a <= min){
					set = true;
					min = a;
				}
				if(!set || a >= max){
					set = true;
					max = a;
				}
			}
			System.out.println("Minimum and Maximum - " + Arrays.toString(ARRAY) +" min: " + min + " max: " + max + ( set ? "" : " These are not real values"));
		}
		{
			System.out.print("Element Separators - ");
			int i = 0;
			for(int a: ARRAY){
				System.out.print(a);
				if(i < ARRAY.length - 1)System.out.print(" | ");
				i++;
			}
			System.out.print("\n");
		}
		{
			int valueToFind = 15;
			int index = -1;
			for(int i = 0; i < ARRAY.length; i++){
				if(ARRAY[i] == valueToFind) index = i;
			}
			System.out.println("Linear Search - " + Arrays.toString(ARRAY) + " value to find: " + valueToFind + " index: " + index);
		}
		{
			int first = 3, second = 8;
			int[] newArray = ARRAY;
			int temp = newArray[first];
			newArray[first] = newArray[second];
			newArray[second] = temp;
			System.out.println("Swapping Elements - " + Arrays.toString(newArray));
		}
		{
			int[] array1 = {1,2,3,4,5};
			int[] array2 = Arrays.copyOf(array1,array1.length * 2);
			for(int i = array1.length; i < array2.length; i++){
				array2[i] = 0;
			}
			System.out.println("Copying Arrays - array1: " + Arrays.toString(array1) + " array2: " + Arrays.toString(array2));
		}
	}
}
