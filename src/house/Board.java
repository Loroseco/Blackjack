package house;

import java.util.ArrayList;
import java.util.HashMap;

class Board {
	
	private int nOfPlayers;
	private HashMap<String, Integer> cardValues = new HashMap<String, Integer>();
	private ArrayList<ArrayList<String>> playerHands = new ArrayList<ArrayList<String>>();
	
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
	
	int getScore(int player) {
		int score = 0;
		ArrayList<String> hand = playerHands.get(player);
		for (String card : hand) {
			score += cardValues.get(card);
		}
		return score;
	}
	
	void printBoardBeforeTurn(int p) {
		//TODO: Implement
	}
	
	void printBoardAfterTurn(int p) {
		//TODO: Implement
	}
	
	void addHand(ArrayList<String> hand) {
		playerHands.add(hand);
		
	}
	
	void addCard(String card, int hand) {
		playerHands.get(hand).add(card);
	}

}
