package controller;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import model.Continent;
import model.RiskPlayers;
import model.SelectionOfArmies;
import model.Territories;
import view.Panels;

/**
 * This class is used to start starting phase of game where every player and
 * their continents, territories and armies are initialized.
 *
 */
public class StartingPhase extends Panels{
	RiskPlayers players;
	Territories territory;
	Continent continent;
	SelectionOfArmies armies;

	/**
	 * This method is used to start initial Reinforcement phase: Each player is
	 * allocated a number of initial armies, based on the total number of players.
	 * Then each player place their armies in the territories allocated to them in
	 * round-robin fashion.
	 * 
	 */
	public void initialStartingPhase() {
		int noOfPlayers = players.getPlayerList().size();
		armies = new SelectionOfArmies(noOfPlayers);
		/*String temp = players.getPlayerList().get(2);
		switch (temp) {
		case "Neutral Player":
			armies = new SelectionOfArmies(2);
			break;

		default:
			armies = new SelectionOfArmies(noOfPlayers);
			break;
		}*/
		/*
		 * if (players.getPlayerList().get(2).equals("Neutral Player")) { armies = new
		 * ArmiesSelection(2); } else { armies = new ArmiesSelection(noOfPlayers); }
		 */
		int j = 0;
		while (j < noOfPlayers) {
			String playerName = players.getPlayerList().get(j);
			players.setCurrentPhase("StartUp Phase");
			players.addPlayerContinent(playerName, new Continent());
			players.initialArmy(playerName, armies.getArmiesOfPlayers());
			j++;
		}
		Map<String, String> territoryContinent = territory.getTerritoryOfContinent();
		int playerCount = 0, m = 0;
		while (m < territory.getTotalTerritories().size()) {
			String playerName = players.getPlayerList().get(playerCount);
			// Retrieve Random Territory
			Object randomTerritory = territoryContinent.keySet().toArray()[new Random()
					.nextInt(territoryContinent.keySet().toArray().length)];
			if (players.getPlayerArmy(playerName) >= 1) {
				Continent temporaryContinent = players.getPlayerContinent(playerName);
				temporaryContinent.addContinentOccupiedTerritories(territoryContinent.get(randomTerritory),
						randomTerritory.toString(), true);
				riskLogger(playerName + " assigned Territory: " + randomTerritory);
				territoryContinent.remove(randomTerritory);
				territory.updateTerritoryUser(playerName, randomTerritory.toString());
				territory.updateTerritoryArmy(randomTerritory.toString(), 1, "ADD");
				players.updatePlayerContinent(playerName, temporaryContinent);
				players.updateArmy(playerName, 1, "Remove");
				playerCount++;
				if (playerCount >= noOfPlayers)
					playerCount = 0;
			} else {
				territory.updateTerritoryUser("No Player", randomTerritory.toString());
				territory.updateTerritoryArmy(randomTerritory.toString(), 0, "ADD");
			}
			m++;
		}

		int x = 0;
		while (x < noOfPlayers) {
			String nameOfThePlayer = players.getPlayers(x);
			int noOfArmy = players.getPlayerArmy(nameOfThePlayer);
			if (noOfArmy > 0) {
				do {
					for (Entry<String, String> entry : territory.getUserOfTerritory().entrySet()) {
						if (entry.getValue().equalsIgnoreCase(nameOfThePlayer)
								&& players.getPlayerArmy(nameOfThePlayer) > 0) {
							players.updateArmy(nameOfThePlayer, 1, "DELETE");
							territory.updateTerritoryArmy(entry.getKey(), 1, "ADD");
						}
					}
					noOfArmy = players.getPlayerArmy(nameOfThePlayer);
				} while (noOfArmy > 0);

			}
			x++;
		}
	}

	/**
	 * This constructor is used to set the initial data to object of continent,
	 * players and territory class.
	 * 
	 * @param players   object of Players.
	 * @param continent object of Continent.
	 * @param territory object of Territory.
	 * 
	 */
	public StartingPhase(RiskPlayers players, Territories territory, Continent continent) {
		this.continent = continent;
		this.territory = territory;
		this.players = players;
	}
}
