package client;

public abstract class Player {
	/**
	 * Fetches move
	 * @param obj	Scanner for human, player score for dealer/AI
	 * @return		Chosen move
	 */
	public abstract String getMove(Object obj);
}
