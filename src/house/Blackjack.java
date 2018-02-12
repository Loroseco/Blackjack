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
	private Deck deck;
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
	void play(int nOfPlayers, int nOfDecks, Scanner scan) {
		
		players = new Player[nOfPlayers + 1];
		players[0] = new Dealer();
		players[1] = new Human();
		playerScores = new int[nOfPlayers + 1];
		stillInPlay = new boolean[nOfPlayers + 1];
		deck = new Deck(nOfDecks);
		board = new Board(nOfPlayers);
		this.scan = scan;
		
		for (int i = 0; i < nOfPlayers + 1; i++) {
			playerScores[i] = 0;
			stillInPlay[i] = false;
		}
		
		startGame();
		
		int playerWinner = playHumans();
		
		if (playerWinner == 0) {
			declareWinner(0);
		} else {
			if (playerScores[playerWinner] == 22) {
				playDealer(true);
			} else {
				playDealer(false);
			}

			if (playerScores[0] >= playerScores[playerWinner]) {
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
		for (int p = 0; p < players.length; p++) {
			board.addHand(deck.getHand());
		}
		board.printBoardBeforeTurn(0);
	}
	
	/**
	 * Cycles through all human player turns until 21 is reached, all players have stood/folded or all are bust
	 * @return	Winning player
	 */
	private int playHumans() {
		boolean end = false;
		int turnNumber = 0;
		while (!end) {
			turnNumber++;
			for (int p = 1; p < players.length; p++) {
				if (stillInPlay[p]) {
					board.printBoardBeforeTurn(p);
					playTurn(p);
					board.printBoard(p);
				}
			}
			
			end = !anyStillInPlay();
		}
		
		return calculatePlayerWinner(turnNumber);
	}
	
	/**
	 * Checks if there are any players still in play
	 * @return	boolean
	 */
	private boolean anyStillInPlay() {
		boolean end = false;
		for (int p = 1; p < players.length; p++) {
			if (playerScores[p] == 21) {
				end = false;
				break;
			} else if (stillInPlay[p]) {
				end = true;
			}
		}
		return end;
	}
	/**
	 * Calculates which human player has won the game
	 * @param turnNumber	Turn number - important since 21 on first turn affects the dealer's play
	 * @return				Winning player
	 */
	private int calculatePlayerWinner(int turnNumber) {
		int winner = 0;
		for (int p = 1; p < players.length; p++) {
			if (playerScores[p] > playerScores[winner] && playerScores[p] < 22) {
				winner = p;
			}
		}
		if (playerScores[winner] == 21 && turnNumber == 1) {
			playerScores[winner] = 22;
		}
		return winner;
	}
	
	/**
	 * Plays a single turn 
	 * @param p	Player number
	 */
	private void playTurn(int p) {
		String move = getMove(p);
		makeMove(p, move);
		playerScores[p] = board.getScore(p);		
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
	 * Applies player move
	 * @param p		Player number
	 * @param move	Chosen move
	 */
	private void makeMove(int p, String move) {
		if (move.equals("H")) {
			board.addCard(deck.hitMe(), p);
		} else if (move.equals("S")) {
			stillInPlay[p] = false;
		} else if (move.equals("F")) {
			//TODO: Implement fold
			stillInPlay[p] = false;
		}
	}
	
	/**
	 * Declares a winner
	 * @param p	Winning player
	 */
	private void declareWinner(int p) {
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
	private int playDealer(boolean playerHas21) {
		while (playerScores[0] < 21) {
			playTurn(0);
			if (playerHas21) {
				if (playerScores[0] == 21) {
					playerScores[0] = 22;
				}
				break;
			}
		}
		return 0;
	}
}
