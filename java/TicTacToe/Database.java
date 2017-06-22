package TicTacToe;

import java.io.*;
import java.util.ArrayList;

/**
 *
 */
public class Database{
	private static String DATA_SOURCE = "java/TicTacToe/game_data.csv";
	private static String SPLITER = ",";

	public static ArrayList<Game> readGames(){
		ArrayList<Game> games = new ArrayList<>();

		BufferedReader reader = null;
		try{
			reader = new BufferedReader(new FileReader(DATA_SOURCE));
			String line = "";
			while(line != null) {
				line = reader.readLine();
				System.out.println(line);
			}
		} catch(IOException e){
			e.printStackTrace(System.err);//TODO
		} finally {
			if(reader != null){
				try{
					reader.close();
				} catch(IOException e){
					e.printStackTrace(System.err);
				}
			}
		}

		return games;
	}

	public static void writeGame(Game game){
		//TODO
		BufferedWriter writer = null;
		try{
			writer = new BufferedWriter(new FileWriter(DATA_SOURCE));
			//TODO
		} catch(IOException e){
			e.printStackTrace(System.err);//TODO
		} finally {
			if(writer != null){
				try{
					writer.close();
				} catch(IOException e){
					e.printStackTrace(System.err);
				}
			}
		}
	}
}
