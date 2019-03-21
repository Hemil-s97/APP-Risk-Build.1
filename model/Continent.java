package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * This is a model class for valueOfContinent, continentTerritories,
 * continentOccupiedTerritories, playerOccupiedContTerr as its data member.
 *
 */
public class Continent {

	/**
	 * @param valueOfContinent             value of armies when continent is fully
	 *                                     acquired by player.
	 * @param continentTerritories         no.of territories for each continent
	 * @param continentOccupiedTerritories no. of territories occupied by player for
	 *                                     particular continent
	 * @param playerOccupiedContTerr       no. of territory occupied by player
	 */
	Map<String, Integer> valueOfContinent;
	Map<String, ArrayList<String>> continentTerritories;
	Map<String, ArrayList<String>> continentOccupiedTerritories;
	
	Map<String, Integer> contTerrValue; /** changed playerOccupiedContTerr to contTerrValue and in Map changed Map<String, Territories> 
	
	 to Map<String, Integer> */
	//Map<String, Territories> playerOccupiedContTerr;

	public Continent() {
		valueOfContinent = new HashMap<>();
		continentTerritories = new HashMap<>();
		continentOccupiedTerritories = new HashMap<>();
		
		contTerrValue = new HashMap<>();
		//playerOccupiedContTerr = new HashMap<>();
	}
	
	// changed if and else statements
	
		public void  addContTerritoryValue(String continent) {
		if (!(contTerrValue.containsKey(continent))) {
			
			contTerrValue.put(continent, 1);
			
			
			
		} 
			
			
			else if((contTerrValue.containsKey(continent))){
				
				contTerrValue.replace(continent, contTerrValue.get(continent), contTerrValue.get(continent) + 1);
			
		}
	}
	

	/**
	 * @return Map playerOccupiedTerr
	 */
	public Map<String, Territories> getPlayerOccupiedContTerr() {
		return playerOccupiedContTerr;
	}

	/**
	 * @param playerOccupiedContTerr a HashMap contains No. of territories occupied
	 *                               by player for particular continent
	 */
	public void setPlayerOccupiedContTerr(Map<String, Territories> playerOccupiedContTerr) {
		this.playerOccupiedContTerr = playerOccupiedContTerr;
	}

	/**
	 * 
	 * @return Map valueOfContinent
	 */
	public Map<String, Integer> getValueOfContinent() {
		return valueOfContinent;
	}

	/**
	 * This method used to create a Map valueOfContinent.
	 * 
	 * @param continent Name of Continent
	 * @param value     value of armies when continent is fully acquired by player
	 */
	public void setContinentValue(String continent, int value) {
		if (valueOfContinent.containsKey(continent)) {
			valueOfContinent.replace(continent, valueOfContinent.get(continent), value);
		} else {
			valueOfContinent.put(continent, value);
		}
	}

	/**
	 * This is used to find which continent has how many territory and Create a Map
	 * continentTerritories
	 * 
	 * @param continent name of Continent
	 * @param territory name of Territories
	 * @return a Map continentTerritories
	 */
	public Map<String, ArrayList<String>> addContinentTerritory(String continent, String territory) {
		if (continentTerritories.containsKey(continent)) {
			ArrayList<String> tempArray1 = continentTerritories.get(continent);
			if (!tempArray1.contains(territory)) {
				tempArray1.add(territory);
				continentTerritories.put(continent, tempArray1);
			}
		} else {
			ArrayList<String> tempArray2 = new ArrayList<>();
			tempArray2.add(territory);
			continentTerritories.put(continent, tempArray2);
		}
		return continentTerritories;
	}

	/**
	 * 
	 * @return a Map continentTerritories
	 */
	public Map<String, ArrayList<String>> getContinentTerritories() {
		return continentTerritories;
	}

	/**
	 * @param valueOfContinent a HashMap contains value of armies when continent is
	 *                         fully acquired by player.
	 */
	public void setValueOfContinent(Map<String, Integer> valueOfContinent) {
		this.valueOfContinent = valueOfContinent;
	}

	/**
	 * Used to identify which continent has how many territory of particular player
	 * and store it to HashMap continentOwnedterritory used to add or remove
	 * territory of particular player
	 * 
	 * @param continent Name of Continent
	 * @param territory Name of Territories
	 * @param value     boolean value
	 * @return a HashMap continentOwnedterritory
	 */
	public Map<String, ArrayList<String>> addContinentOccupiedTerritories(String continent, String territory,
			boolean value) {

		if (value) {
			if ((continentOccupiedTerritories.containsKey(continent))) {
				ArrayList<String> arrayOne = continentOccupiedTerritories.get(continent);
				if (!arrayOne.contains(territory)) {
					arrayOne.add(territory);
					continentOccupiedTerritories.put(continent, arrayOne);
				} else {
					ArrayList<String> arrayTwo = new ArrayList<>();
					arrayTwo.add(territory);
					continentOccupiedTerritories.put(continent, arrayTwo);
				}
			}
		} else {
			if (continentOccupiedTerritories.containsKey(continent)) {
				ArrayList<String> arrayThree = continentOccupiedTerritories.get(continent);
				if (arrayThree.contains(territory)) {
					arrayThree.remove(territory);
					if (!arrayThree.isEmpty())
						continentOccupiedTerritories.replace(continent, continentOccupiedTerritories.get(continent),
								arrayThree);
					else
						continentOccupiedTerritories.remove(continent);
				}
			}
		}
		return continentOccupiedTerritories;
	}

	/**
	 * 
	 * @param continentTerritories a Map which contains no.of Territories for each
	 *                             Continent
	 */
	public void setContinentTerritories(Map<String, ArrayList<String>> continentTerritories) {
		this.continentTerritories = continentTerritories;
	}

	/**
	 * 
	 * @return a map continentOwnedterritory
	 */
	public Map<String, ArrayList<String>> getContinentOccupiedterritories() {
		return continentOccupiedTerritories;
	}

	/**
	 * 
	 * @param continentOccupiedterritory no. of Territories owned by Player for
	 *                                   particular Continent
	 */
	public void setContinentOwnedterritory(Map<String, ArrayList<String>> continentOccupiedterritory) {
		this.continentOccupiedTerritories = continentOccupiedterritory;
	}

	/**
	 * This method used to update keys and value of map
	 * 
	 * @param oldContinent Old key
	 * @param newContinent New key which need to replace by old key
	 * @param newValue     New value of Continent
	 */
	public void updateContinentValue(String oldContinent, String newContinent, int newValue) {
		valueOfContinent.remove(oldContinent);
		valueOfContinent.put(newContinent, newValue);
	}
}
