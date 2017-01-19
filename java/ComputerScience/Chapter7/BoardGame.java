package ComputerScience.Chapter7;

/**
 * Creates a board which alternates every position between one and zero
 *
 * @author Logan Traffas
 * @version 1/19/2017
 * assignment: Chapter07-Arrays-7SC31
 */
public class BoardGame {
	public static void main(String[] args){
		final int DIMENSION = 8;
		int[][] board = new int[DIMENSION][DIMENSION];
		for(int i = 0; i < DIMENSION; i++){
			for(int j = 0; j < DIMENSION; j++){
				if((i + j) % 2 == 0) board[i][j] = 0;
				else board[i][j] = 1;
			}
		}
		System.out.println("Result:");
		for(int[] row: board){
			for(int pos: row){
				System.out.print(pos);
			}
			System.out.print("\n");
		}
	}
}
