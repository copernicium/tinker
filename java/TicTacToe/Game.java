package TicTacToe;

import Util.Point;

import java.util.ArrayList;
import Util.Util;

/**
 *
 */
public class Game {
	private Board board;

	public static class Move{
		private Point<Integer> position;
		private Mark mark;

		public Point<Integer> getPosition(){
			return this.position;
		}

		public Mark getMark(){
			return this.mark;
		}

		public Move(Point<Integer> position, Mark mark){
			this.position = position;
			this.mark = mark;
		}
	}

	private ArrayList<Move> moves;

	public static class Players{
		private enum ActivePlayer{
			_1,_2;

			public static ActivePlayer toggle(ActivePlayer activePlayer){
				switch(activePlayer){
					case _1:
						return _2;
					case _2:
						return _1;
					default:
						Util.nyi(Util.getFileName(),Util.getLineNumber());
				}
				return null;//will never reach this line
			}
		}
		private ActivePlayer activePlayer;
		private Player player1;
		private Player player2;

		public Player getActivePlayer(){
			switch(this.activePlayer){
				case _1:
					return this.player1;
				case _2:
					return this.player2;
				default:
					Util.nyi(Util.getFileName(),Util.getLineNumber());
			}
			return null;//will never reach this line
		}

		public void toggleActivePlayer(){
			this.activePlayer = ActivePlayer.toggle(this.activePlayer);
		}

		public Player getPlayer1(){
			return this.player1;
		}

		public Player getPlayer2(){
			return this.player2;
		}

		public Players(Player player1, Player player2){
			this.player1 = player1;
			this.player2 = player2;
			this.activePlayer = ActivePlayer._1;
		}
	}

	private Players players;

	private void runTurn(Player player){
		System.out.println(this.board.toPrintable());
		System.out.print("Insert a position to add " + this.board.getNextMark().toString() + ": ");
		Move move = player.getMove(this.board);
		this.board.set(move.getPosition());
		moves.add(move);
	}

	public void play(){
		while(this.board.getStatus() == Board.Status.UNFINISHED){
			runTurn(this.players.getActivePlayer());
			this.players.toggleActivePlayer();
		}
		System.out.println(this.board.toPrintable());
		System.out.println("Game finished with: " + this.board.getStatus());
	}

	public Game(){
		this.board = new Board();
		this.moves = new ArrayList<>();
		this.players = new Players(new AI(), new AI());
	}

	public static void store(Game game){
		Database.writeGame(game);
	}

	public static void main(String[] args){
		Game game = new Game();
		game.play();
		store(game);
	}
}
