package ComputerScience.Chapter7;

import java.util.Scanner;

/**
 * Generates a seating chart for a theater
 *
 * @author Logan Traffas
 * @version 1/28/2017
 * assignment: Chapter 07--7P5--Seating Chart
 */
public class SeatingChartGenerator{
	public static String theaterToString(int[][] theater){
		String s = "";
		for(int[] row: theater){
			for(int cost: row){
				s += cost + " ";
			}
			s += "\n";
		}
		return s;
	}

	public static void main(String[] args){
		Scanner in = new Scanner(System.in);

		int[][] theater = {
				{10, 10, 10, 10, 10, 10, 10, 10, 10, 10},
				{10, 10, 10, 10, 10, 10, 10, 10, 10, 10},
				{10, 10, 10, 10, 10, 10, 10, 10, 10, 10},
				{10, 10, 20, 20, 20, 20, 20, 20, 10, 10},
				{10, 10, 20, 20, 20, 20, 20, 20, 10, 10},
				{10, 10, 20, 20, 20, 20, 20, 20, 10, 10},
				{20, 20, 30, 30, 40, 40, 30, 30, 20, 20},
				{20, 30, 30, 40, 50, 50, 40, 30, 30, 20},
				{30, 40, 50, 50, 50, 50, 50, 50, 40, 30}
		};

		System.out.println(theaterToString(theater));

		int purchasingMode = 0;
		while(purchasingMode != 1 && purchasingMode!= 2){
			System.out.print("Enter \"1\" to pick a seat by its row and column or \"2\" to pick by price: ");
			purchasingMode = in.nextInt();
		}

		if(purchasingMode == 1){
			int row = -1, column = -1;
			while(row < 0 || row > 9){
				System.out.print("Enter the seat row: ");
				row = in.nextInt();
			}
			while(column < 0 || column > 9){
				System.out.print("Enter the seat column: ");
				column = in.nextInt();
			}
			theater[row][column] = 0;
		}
		else if(purchasingMode == 2){
			int price = 0;
			while(price != 10 && price != 20 && price != 30 && price != 40 && price != 50){
				System.out.print("Enter the seat price: ");
				price = in.nextInt();
			}

			boolean purchased = false;
			for(int y = 0; y < theater.length; y++){
				if(purchased) break;
				for(int x = 0; x < theater[y].length; x++){
					if(purchased) break;
					if(theater[y][x] == price){
						theater[y][x] = 0;
						purchased = true;
					}
				}
			}
		}
		System.out.println("\n\n" + theaterToString(theater));
	}
}
