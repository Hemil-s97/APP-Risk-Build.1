package controller;

import java.util.Map.Entry;

import model.Continent;
import model.RiskPlayers;
import model.Territories;

/**
 * This class is used to generate armies of each player during the
 * initialization and then assign army to particular territory while every turn
 * in Reinforcement phase.
 * 
 */
public class Reinforcement {
	RiskPlayers players;
	Territories territory;
	Continent continent;
	String nameOfPlayer;
	String phase = "Reinforcement";

	/**
	 * This method allows player to perform Reinforcement Phase of Game
	 * 
	 * @param currentPlayer Player Name
	 * @param territoryName Territory Name
	 * @param army          Number of Armies
	 */
	public void startReinforcement(String currentPlayer, String territoryName, int army) {
		players.setCurrentPhase(getPhase());
		players.updateArmy(currentPlayer, army, "DELETE");
		territory.updateTerritoryArmy(territoryName, army, "ADD");
	}

	/**
	 * This method returns the players object.
	 * 
	 * @return Object of players class
	 */
	public RiskPlayers getPlayers() {
		return players;
	}

	/**
	 * This method assigns the passed players object to the object created.
	 * 
	 * @param players Object of players class
	 */
	public void setPlayers(RiskPlayers players) {
		this.players = players;
	}

	/**
	 * This method returns the object of territory class.
	 * 
	 * @return Object of territory class
	 */
	public Territories getTerritory() {
		return territory;
	}

	/**
	 * This method assigns the passed territory object to the object created.
	 * 
	 * @param territory Object of territory class
	 */
	public void setTerritory(Territories territory) {
		this.territory = territory;
	}

	/**
	 * This constructor is used to set the initial data of the class.
	 * 
	 * @param nameOfPlayer Name of player
	 * @param continent    object of Continent Model
	 * @param territory    object of Territory Model
	 * @param players      object of Players Model
	 * 
	 */
	public Reinforcement(String nameOfPlayer, Continent continent, Territories territory, RiskPlayers players) {
		this.nameOfPlayer = nameOfPlayer;
		this.continent = continent;
		this.territory = territory;
		this.players = players;
	}

	/**
	 * This method return the current phase
	 * 
	 * @return Current Phase of Player
	 */
	public String getPhase() {
		return phase;
	}

	/**
	 * This method set the current phase
	 * 
	 * @param phase Set Current Phase
	 */
	public void setPhase(String phase) {
		this.phase = phase;
	}

	/**
	 * This method calculate the player armies for Reinforcement phase. Calculation
	 * method : Count of User-Owned Territory/3
	 * 
	 * @return number of reinforcement armies
	 */
	public int armyGeneration() {
		int count = 0;
		for (Entry<String, String> entry : territory.getUserOfTerritory().entrySet()) {
			if (entry.getValue().equalsIgnoreCase(nameOfPlayer)) {
				count++;
			}
		}

		Double value = new Double(Math.floor(count / 3));
		return value.intValue() > 3 ? value.intValue() : 3;
	}

	/**
	 * This method returns the object of Continent class.
	 * 
	 * @return Object of Continent class.
	 */
	public Continent getContinent() {
		return continent;
	}

	/**
	 * This method sets the object of Continent class.
	 * 
	 * @param continent object of Continent class.
	 */
	public void setContinent(Continent continent) {
		this.continent = continent;
	}
}
