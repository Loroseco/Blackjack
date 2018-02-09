package house;

import java.util.ArrayList;
import java.util.Collections;

class Deck {
	private int nOfDecks;
	private String[] singleDeck = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
	private ArrayList<String> deck;
	
	Deck(int nOfDecks) {
		this.nOfDecks = nOfDecks;
	}
	
	void makeDeck() {
		deck = new ArrayList<String>(nOfDecks * 13);
		for (int d = 0; d < nOfDecks; d++) {
			for (int c = 0; c < 13; c++) {
				deck.add(singleDeck[c]);
			}
		}
		Collections.shuffle(deck);
	}
	
	private ArrayList<String> getCards(int nOfCards) {
		ArrayList<String> cards = new ArrayList<String>(nOfCards);
		for (int n = 0; n < nOfCards; n++) {
			cards.add(deck.get(0));
			deck.remove(0);
		}
		if (deck.size() <= nOfDecks * 13 / 4) {
			makeDeck();
		}
		return cards;
	}
	
	ArrayList<String> getHand() {
		return getCards(2);
	}
	
	String hitMe() {
		return getCards(1).get(0);
	}
	
}
