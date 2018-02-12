package house;

import java.util.ArrayList;
import java.util.Collections;

class Deck {
	private int nOfDecks;
	private String[] singleSuit = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
	private ArrayList<String> deck;
	
	/**
	 * Deck constructor
	 * @param nOfDecks	Number of decks to be shuffled into the game
	 */
	Deck(int nOfDecks) {
		this.nOfDecks = nOfDecks;
	}
	
	void makeDeck() {
		this.deck = new ArrayList<String>(nOfDecks * 54);
		for (int deck = 0; deck < nOfDecks; deck++) {
			for (int suit = 0; suit < 4; suit++) {
				for (int card = 0; card < 31; card++) {
					this.deck.add(singleSuit[card]);
				}
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
