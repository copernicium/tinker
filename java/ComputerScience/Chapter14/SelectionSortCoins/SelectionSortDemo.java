package ComputerScience.Chapter14.SelectionSortCoins;

import java.util.Arrays;

/**
 * This program demonstrates the selection sort algorithm by sorting an array that is filled with random numbers.
 * @Author Logan Traffas
 * @Date 4/19/2017.
 * @Version 1.0.0
 * @Assignment
 */
public class SelectionSortDemo
{
	public static void main(String[] args)
	{
		Coin[] a = {new Coin("nickelUS",0.05), new Coin("toonyCAN",2.44), new Coin("loonyCAN",1.22), new Coin("quarterUS",0.25),new Coin("dollarUS",1.00),new Coin("dimeUS",0.10),new Coin("halfdollarUS	",0.50)};
		System.out.println(Arrays.toString(a));

		SelectionSorter.sort(a);

		System.out.println(Arrays.toString(a));
	}
}


