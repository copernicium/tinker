package TicTacToe;

import java.io.*;
import java.text.ParseException;
import java.util.ArrayList;
import TicTacToe.Game.Move;
import Util.Maybe;

/**
 *
 */
public class Database{
	private static String DATA_SOURCE = "java/TicTacToe/game_data.csv";
	private static String SPLITTER = "\\|";

	public static ArrayList<Game> readGames(){
		ArrayList<Game> games = new ArrayList<>();

		BufferedReader reader = null;
		try{
			reader = new BufferedReader(new FileReader(DATA_SOURCE));
			String gameString = reader.readLine();
			while(gameString != null) {
				ArrayList<Move> moves = new ArrayList<>();
				Maybe<Board.Status> status = new Maybe<Board.Status>();

				String[] fields = gameString.split(SPLITTER);
				for(String a: fields){
					if(Move.isParseable(a)){
						try{
							moves.add(Move.parse(a));
						} catch(ParseException e){
							e.printStackTrace(System.err);
						}
					}

					try{
						status.set(Board.Status.valueOf(a));
					} catch(IllegalArgumentException e){
						//ignore it
					}
				}

				if(status.isValid()){
					games.add(new Game(moves, status.get()));
				}

				gameString = reader.readLine();
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

	public static void writeGame(Game game){//TODO: make sure we check that the current game has not already been stored
		BufferedWriter writer = null;
		try{
			writer = new BufferedWriter(new FileWriter(DATA_SOURCE,true));
			String out = "";
			for(Move move: game.getMoves()){
				out += move + SPLITTER;
			}
			out += game.getBoard().getStatus();
			out += "\n";
			writer.write(out);
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
