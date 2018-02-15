package house;

import java.util.Scanner;

import client.*;

/**
 * Class containing the "play" function, controls the order of actions in the game and calculates the winner
 * @author Loroseco
 *
 */
public class Blackjack {
	
	private Player[] players;
	private Board board;
	private int[] playerScores;
	private boolean[] stillInPlay;
	private Scanner scan;
	
	/**
	 * Class called to initiate a game
	 * @param nOfPlayers	Number of players (not including dealer)
	 * @param nOfDecks		Number of decks to be shuffled together to create the main deck
	 * @param scan			Scanner object
	 */
	void play(Board board, Scanner scan) {
		int nOfPlayers = board.getNOfPlayers();
		players = new Player[nOfPlayers + 1];
		players[0] = new AI();
		for (int n = 0; n < nOfPlayers; n++) {
			players[n + 1] = new Human();
		}
		
		this.board = board;
		this.scan = scan;
		
		playerScores = new int[nOfPlayers + 1];
		stillInPlay = new boolean[nOfPlayers + 1];
		
		for (int i = 0; i < nOfPlayers + 1; i++) {
			playerScores[i] = 0;
			stillInPlay[i] = true;
		}
		
		startGame();
		
		int playerWinner = playHumans();

		if (playerWinner == 0) {
			declareWinner(0);
		} else {
			playDealer();
			if (playerScores[0] >= playerScores[playerWinner] && playerScores[0] < 21) {
				declareWinner(0);
			} else {
				declareWinner(1);
			}
		}
	}
	
	/**
	 * Populates the board with players' initial hands
	 */
	private void startGame() {
		board.newGame();
		for (int p = 0; p < players.length; p++) {
			playerScores[p] = board.getScore(p);
		}
		board.printBoardBeforeTurn(0);
	}
	
	/**
	 * Cycles through all human player turns until 21 is reached, all players have stood/folded or all are bust
	 * @return	Winning player
	 */
	private int playHumans() {
		boolean end = findBlackjack();
		while (!end) {
			for (int p = 1; p < players.length; p++) {
				if (stillInPlay[p]) {
					board.printBoardBeforeTurn(p);
					playTurn(p);
					board.printBoard(p);
				}
			}
			end = !anyStillInPlay();
		}
		
		return calculatePlayerWinner();
	}
	
	private boolean findBlackjack() {
		for (int p = 0; p < players.length; p++) {
			if (playerScores[p] == 21) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Checks if there are any players still in play
	 * @return	boolean
	 */
	private boolean anyStillInPlay() {
		boolean inPlay = false;
		for (int p = 1; p < players.length; p++) {
			if (playerScores[p] == 21) {
				inPlay = false;
				break;
			} else if (stillInPlay[p]) {
				inPlay = true;
			}
		}
		return inPlay;
	}
	/**
	 * Calculates which human player has won the game
	 * @param turnNumber	Turn number - important since 21 on first turn affects the dealer's play
	 * @return				Winning player
	 */
	private int calculatePlayerWinner() {
		int winner = 0;
		for (int p = 1; p < players.length; p++) {
			if (playerScores[p] > playerScores[winner] && playerScores[p] < 22 && playerScores[p] != -1) {
				winner = p;
			}
		}
		return winner;
	}
	
	/**
	 * Plays a single turn 
	 * @param p	Player number
	 */
	private void playTurn(int p) {
		while (true) {
			String move = getMove(p);
			if (move.toLowerCase().equals("h")) {
				board.hitMe(p);
				playerScores[p] = board.getScore(p);
				if (playerScores[p] > 21) {
					stillInPlay[p] = false;
				}
				break;
			} else if (move.toLowerCase().equals("s")) {
				stillInPlay[p] = false;
				break;
			} else if (move.toLowerCase().equals("f")) {
				playerScores[p] = -1;
				stillInPlay[p] = false;
				break;
			} else {
				System.out.println("INVALID MOVE.");
			}
		}		
	}
	
	/**
	 * Fetches move from player class
	 * @param p	Player number
	 * @return	Chosen move
	 */
	private String getMove(int p) {
		String move;
		if (p == 0) {
			move = players[0].getMove(playerScores[0]);
		} else {
			move = players[p].getMove(scan);
		}
		return move;
	}
	
	/**
	 * Declares a winner
	 * @param p	Winning player
	 */
	private void declareWinner(int p) {
		board.printBoard(0);
		if (p == 0) {
			System.out.println("THE HOUSE ALWAYS WINS.");
		} else {
			System.out.println("WINNER: PLAYER " + Integer.toString(p) + ".");
		}
	}
	
	/**
	 * Plays the dealer until he stands or is bust
	 * @param playerHas21	Did a human player get 21 in their first turn (only gives dealer 1 turn if so)
	 * @return
	 */
	private int playDealer() {
		while (stillInPlay[0]) {
			playTurn(0);
		}
		return 0;
	}
}
