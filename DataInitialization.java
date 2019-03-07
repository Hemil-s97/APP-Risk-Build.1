package controller;

import model.Continent;
import model.RiskPlayers;
import model.Territories;

/**
 * This class initializes the data for Reinforcement phase if the map is valid
 * and the graph is connected.
 * 
 */
public class DataInitialization {
	String mapFilePath;
	Continent continent;
	Territories territory;
	int noOfPlayers;
	int noOfArmyPerPlayer;
	RiskPlayers players;

	/**
	 * This method returns the count of players in a game.
	 * 
	 * @return Number of Players in game.
	 */
	public int getnoOfPlayers() {
		return noOfPlayers;
	}

	/**
	 * This method is used to set the count of players in a game.
	 * 
	 * @param noOfPlayers Number of Players in game.
	 */
	public void setnoOfPlayers(int noOfPlayers) {
		this.noOfPlayers = noOfPlayers;
	}

	/**
	 * This constructor is used to set the initial data of the class.
	 * 
	 * @param noOfPlayers       Number of Players in the game.
	 * @param players           Model object of Players.
	 * @param noOfArmyPerPlayer Number of noOfArmyPerPlayer per Player.
	 * @param mapFilePath       Full path of the Map file.
	 * 
	 */
	public DataInitialization(int noOfPlayers, RiskPlayers players, int noOfArmyPerPlayer, String mapFilePath) {
		this.noOfPlayers = noOfPlayers;
		this.players = players;
		this.noOfArmyPerPlayer = noOfArmyPerPlayer;
		this.mapFilePath = mapFilePath;
	}

	/**
	 * This method returns the object of Players class.
	 * 
	 * @return A object of Players class.
	 */
	public RiskPlayers getPlayers() {
		return players;
	}

	/**
	 * Set the object players of Model Players.
	 * 
	 * @param players object of Model Players
	 */
	public void setPlayers(RiskPlayers players) {
		this.players = players;
	}

	/**
	 * This method returns the variable noOfArmyPerPlayer.
	 * 
	 * @return Number of army per player.
	 */
	public int getnoOfArmyPerPlayer() {
		return noOfArmyPerPlayer;
	}

	/**
	 * This method set the noOfArmyPerPlayer.
	 * 
	 * @param noOfArmyPerPlayer Number of army per player.
	 */
	public void setnoOfArmyPerPlayer(int noOfArmyPerPlayer) {
		this.noOfArmyPerPlayer = noOfArmyPerPlayer;
	}

	/**
	 * This method returns the path of Map file.
	 * 
	 * @return path of map File.
	 */
	public String getmapFilePath() {
		return mapFilePath;
	}

	/**
	 * This method sets the path of Map file.
	 * 
	 * @param mapFilePath path of map File.
	 */
	public void setmapFilePath(String mapFilePath) {
		this.mapFilePath = mapFilePath;
	}

	/**
	 * This method returns the object of Territory class.
	 * 
	 * @return a object territory of Model Territory.
	 */
	public Territories getTerritory() {
		return territory;
	}

	/**
	 * This method sets the object of Territory class.
	 * 
	 * @param territory object of Model Territory.
	 */
	public void setTerritory(Territories territory) {
		this.territory = territory;
	}

	/**
	 * This method returns the object of Continent class.
	 * 
	 * @return a object of Model Continent.
	 */
	public Continent getContinent() {
		return continent;
	}

	/**
	 * This method sets the object of Territory class.
	 * 
	 * @param continent object of Model Continent.
	 */
	public void setContinent(Continent continent) {
		this.continent = continent;
	}

	/**
	 * This method gives the path of map file to BoardData class, invoke the method
	 * {@link BoardData#boardDataGeneration()} of class BoardData which generates
	 * the data and Validate the map Data from map file. if map is valid and graph
	 * is connected then it starts the process of Initial Reinforcement: invoke the
	 * method {@linkplain Reinforcement#armyGeneration()} of class Reinforcement.
	 *
	 * @return true if map Data is Valid else, return false.
	 */
	public boolean dataGeneration() {

		BoardData boardData = new BoardData(mapFilePath);
		boolean isMapValid = boardData.boardDataGeneration();
		System.out.println(isMapValid);

		if (isMapValid) {
			territory = boardData.objectOfTerritory;
			continent = boardData.objectOfContinent;
			players.selectPlayers(players.getPlayerList(), noOfPlayers);
			StartingPhase startUpPhase = new StartingPhase(players, territory, continent);
			startUpPhase.initialStartingPhase();
			setTerritory(startUpPhase.territory);
			setContinent(startUpPhase.continent);
			Reinforcement reinforcement = new Reinforcement(players.getPlayers(0), getContinent(), getTerritory(),
					players);
			players.updateArmy(players.getPlayers(0), reinforcement.armyGeneration(), "ADD");
			setPlayers(startUpPhase.players);
		} else {
			System.out.println("Map is not Valid");
		}
		return isMapValid;
	}
}
