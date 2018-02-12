package house;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Class to handle player hand storage / display, and player score calculation
 * @author Loroseco
 *
 */
class Board {
	
	private int nOfPlayers;
	private HashMap<String, Integer> cardValues = new HashMap<String, Integer>();
	private ArrayList<ArrayList<String>> playerHands = new ArrayList<ArrayList<String>>();
	
	/**
	 * Board constructor. Populates cardValues in order to calculate score later
	 * @param nOfPlayers	Number of players not including dealer
	 */
	Board(int nOfPlayers) {
		this.nOfPlayers = nOfPlayers;
		cardValues.put("A", 11);
		cardValues.put("2", 2);
		cardValues.put("3", 3);
		cardValues.put("4", 4);
		cardValues.put("5", 5);
		cardValues.put("6", 6);
		cardValues.put("7", 7);
		cardValues.put("8", 8);
		cardValues.put("9", 9);
		cardValues.put("10", 10);
		cardValues.put("J", 10);
		cardValues.put("Q", 10);
		cardValues.put("K", 10);
	}
	
	/**
	 * Calculates the value of a chosen player's hand
	 * @param player	Chosen player
	 * @return			Vaue of player's hand
	 */
	int getScore(int player) {
		int score = 0;
		ArrayList<String> hand = playerHands.get(player);
		for (String card : hand) {
			score += cardValues.get(card);
		}
		return score;
	}
	
	/**
	 * Prints the board after a confirmation, in order to give players enough time to pass the keyboard around
	 * @param p	Chosen player
	 */
	void printBoardBeforeTurn(int p) {
		//TODO: Do something here as a confirmation that the correct player is at the keyboard
		printBoard(p);
	}
	
	/**
	 * Prints the board, displaying only the chosen player's hand, and one card from the dealer's hand
	 * @param p	Chosen player
	 */
	void printBoard(int p) {
		//TODO: Implement
	}
	
	/**
	 * Adds a new player hand to the playerHands ArrayList
	 * @param hand	New hand
	 */
	void addHand(ArrayList<String> hand) {
		playerHands.add(hand);
		
	}
	
	/**
	 * Adds a new card to a chosen player's hand
	 * @param card	New card
	 * @param hand	Chosen player's hand
	 */
	void addCard(String card, int hand) {
		playerHands.get(hand).add(card);
	}

}
