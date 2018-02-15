package house;

/**
 * Bank class to manage funds and bets
 * @author Loroseco
 *
 */
public class Bank {
	private int[] accounts;
	private int[] amountBet;
	
	/**
	 * Bank class constructor
	 * @param nOfPlayers		Number of players
	 * @param startingAmount	Amount of funds each player starts with
	 */
	Bank(int nOfPlayers, int startingAmount) {
		accounts = new int[nOfPlayers];
		amountBet = new int[nOfPlayers];
		for (int p = 0; p < nOfPlayers; p++) {
			accounts[p] = startingAmount;
			amountBet[p] = 0;
		}
	}
	
	/**
	 * Accessor
	 * @param p	Player
	 * @return	Player funds
	 */
	int getAccount(int p) {
		return accounts[p - 1];
	}
	
	/**
	 * Accessor
	 * @param p	Player
	 * @return	Amount player has bet this turn
	 */
	int getAmountBet(int p) {
		return amountBet[p - 1];
	}
	
	/**
	 * Places a bet from the chosen player's funds
	 * @param p			Chosen player
	 * @param amount	Bet amount
	 */
	void placeBet(int p, int amount) {
		int player = p - 1;
		if (accounts[player] >= amount) {
			accounts[player] -= amount;
			amountBet[player] += amount;
		} else {
			amountBet[player] = accounts[p];
			accounts[player] = 0;
		}
	}
	
	/**
	 * Folds for a player, returning half of their bet to their account
	 * @param p	Player
	 */
	void fold(int p) {
		int player = p - 1;
		int foldAmount = amountBet[p] / 2;
		amountBet[player] -= foldAmount;
		accounts[player] += foldAmount;
	}
	
	/**
	 * Takes all funds in play and gives it to the winning player
	 * @param winner	Winning player
	 */
	void giveWinnings(int w) {
		int winner = w - 1;
		int winnings = 0;
		for (int p = 0; p < amountBet.length; p++) {
			winnings += amountBet[p];
			amountBet[p] = 0;
		}
		accounts[winner] += winnings;
	}
	
	
}
