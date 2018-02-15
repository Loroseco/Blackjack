package house;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Deck class stores the deck itself consisting of a specified number of 54-card decks.
 * @author Loroseco
 *
 */
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
	
	/**
	 * Creates a shuffled ArrayList consisting of the specified number of decks
	 */
	void createDeck() {
		this.deck = new ArrayList<String>(nOfDecks * 54);
		for (int deck = 0; deck < nOfDecks; deck++) {
			for (int suit = 0; suit < 4; suit++) {
				for (int card = 0; card < 13; card++) {
					this.deck.add(singleSuit[card]);
				}
			}
		}
		Collections.shuffle(deck);
	}
	
	/**
	 * Returns the specified number of cards from the deck. 
	 * Fetched cards are removed from the deck.
	 * If the deck is less than 1/4th of its original size, it is remade
	 * @param nOfCards	Number of cards to fetch
	 * @return			ArrayList of cards
	 */
	private ArrayList<String> getCards(int nOfCards) {
		ArrayList<String> cards = new ArrayList<String>(nOfCards);
		for (int n = 0; n < nOfCards; n++) {
			cards.add(deck.get(0));
			deck.remove(0);
		}
		if (deck.size() <= nOfDecks * 13) {
			System.out.println("RESHUFFLING DECK.");
			createDeck();
		}
		return cards;
	}
	
	/**
	 * Gets two cards for a hand and removes them from the deck
	 * @return	ArrayList of 2 cards
	 */
	ArrayList<String> getHand() {
		return getCards(2);
	}
	
	/**
	 * Gets a card and removes it from the deck
	 * @return
	 */
	String hitMe() {
		return getCards(1).get(0);
	}
	
}
