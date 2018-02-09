package house;

import java.util.ArrayList;
import java.util.Scanner;

import client.*;

public class Blackjack {
	
	private Player[] players;
	private Deck deck;
	private Board board;
	private int[] playerScores;
	private boolean[] playerOutcomes;
	private Scanner scan;
	
	void play(int nOfPlayers, int nOfDecks, Scanner scan) {
		
		players = new Player[nOfPlayers + 1];
		players[0] = new Dealer();
		players[1] = new Human();
		playerScores = new int[nOfPlayers + 1];
		playerOutcomes = new boolean[nOfPlayers + 1];
		deck = new Deck(nOfDecks);
		board = new Board(nOfPlayers);
		this.scan = scan;
		
		for (int i = 0; i < nOfPlayers + 1; i++) {
			playerScores[i] = 0;
			playerOutcomes[i] = false;
		}
		
		startGame();
		
		int[] playerWinner = playHumans();
		
		if (playerWinner[1] > 22) {
			declareWinner(0);
		} else {
			int score = (playerWinner[1] == 21) ? playDealer(true) : playDealer(false);
			if (score == 21) {
				declareWinner(0);
			} else {
				declareWinner(1);
			}
		}
	}
	
	private void startGame() {
		for (int p = 0; p < players.length; p++) {
			board.addHand(deck.getHand());
		}
		board.printBoard();
	}
	
	private int[] playHumans() {
		boolean end = false;
		while (!end) {
			for (int p = 1; p < players.length; p++) {
				playTurn(p);
			}
			
			end = true;
			for (int p = 1; p < players.length; p++) {
				if (playerScores[p] == 21) {
					break;
				} else if (!playerOutcomes[p]) {
					end = false;
					break;
				}
			}
		}
		
		int[] winner = {0, 0};
		for (int p = 1; p < players.length; p++) {
			if (playerScores[p] > winner[1]) {
				winner[0] = p;
				winner[1] = playerScores[p];
			}
		}
		return winner;
	}
	
	private void playTurn(int p) {
		String move = getMove(p);
		makeMove(p, move);
		playerScores[p] = board.getScore(p);		
	}
	
	private String getMove(int p) {
		//TODO: Implement
		return "n";
	}
	
	private void makeMove(int p, String move) {
		//TODO: Implement
	}
	
	private void declareWinner(int p) {
		if (p == 0) {
			System.out.println("THE HOUSE ALWAYS WINS.");
		} else {
			System.out.println("WINNER: PLAYER " + Integer.toString(p) + ".");
		}
	}
	
	private int playDealer(boolean doesPlayerHave21) {
		//TODO: Implement
		return 0;
	}
}
