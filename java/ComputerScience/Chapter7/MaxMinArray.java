package ComputerScience.Chapter7;

import MySystem.MySystem;

/**
 * Simultaneously computes the maximum and the minimum of an array
 *
 * @author Logan Traffas
 * @version 1/18/2017
 * assignment: Chapter07-Arrays-7R7.1--Filling arrays
 */
public class MaxMinArray {
	public static void main(String[] args){
		Double[] data = {-1.0,2.0,-3.0,4.0,5.0,66.0,7.0,8.0,9.0};
		MySystem.myAssert(data.length > 0, MySystem.getFileName(), MySystem.getLineNumber());
		System.out.println("Array is: " + MySystem.arrayToString(data));
		double min = data[0], max = data[0];
		for(double a: data){
			if(a < min) min = a;
			if(a > max) max = a;
		}
		System.out.println("Min is: " + min + " Max is: " + max);
	}
}
