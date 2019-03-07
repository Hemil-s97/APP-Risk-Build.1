package model;

/**
 * This Model class is used to select the number of starting initial
 * Armies depending on number of RiskPlayers playing the RiskGame.
 *
 */
public class SelectionOfArmies {
	private int armies;

	/**
	 * This constructor set the number of armies according to playerPlaying.
	 * @param playerPlaying Number of RiskPlayers playing the game.
	 */
	public SelectionOfArmies(int playerPlaying) {
		super();
		armies = 50 - (playerPlaying * 5);
	}

	/**
	 * Returns the Number of initial armies per Player.
	 * @return number of initial armies
	 */
	public int getArmiesOfPlayers() {
		return armies;
	}

	/**
	 * Set the Number of initial Armies assigned per Player.
	 * @param armies number of initial armies
	 */
	public void setPlayerArmies(int armies) {
		this.armies = armies;
	}
}
