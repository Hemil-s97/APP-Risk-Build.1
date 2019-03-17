package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Territories Model class.
 *
 */
public class Territories {
	/**
	 * @param userOfTerritory Territories belong to which user
	 * @param territoryOfContinent territory belong to which continent
	 * @param territoryArmy Armies for particular territory
	 * @param neighborTerritories Adjacent territory of territory
	 * @param totalTerritories List of total territory
	 */
	Map<String, String> userOfTerritory; 
	Map<String, String> territoryOfContinent; 
	Map<String, Integer> territoryArmy; 
	Map<String, ArrayList<String>> neighborTerritories; 
	ArrayList<String> totalTerritories;
	Map<String, Integer> territoryNumber; // territory with Number
	Map<String, ArrayList<String>> checkDuplicateTerritoryContinent; //it is used to check the duplicate records .
	boolean flag = false;
	
	public Territories() {
		flag = false;
		userOfTerritory = new HashMap<>();
		territoryOfContinent = new HashMap<>();
		territoryArmy = new HashMap<>();
		neighborTerritories = new HashMap<>();
		totalTerritories = new ArrayList<>();
		checkDuplicateTerritoryContinent = new HashMap<>();
		territoryNumber = new HashMap<>();
	}
	public Map<Integer, String> getCardValue() {
		return cardValue;
	}
	
	/**
	 * 
	 * @return return list all the territories.
	 */
	public ArrayList<String> getTotalTerritories() {
		return totalTerritories;
	}
	
	/**
	 * 
	 * @param totalTerritories list of all territories
	 */
	public void setTotalTerritories(ArrayList<String> totalTerritories) {
		this.totalTerritories = totalTerritories;
	}
	
	/**
	 * 
	 * @return Return user occupied Territories
	 */
	public Map<String, String> getUserOfTerritory() {
		return userOfTerritory;
	}
	
	/**
	 * 
	 * @param userOfTerritory User occupied Territories
	 */
	public void setUserOfTerritory(Map<String, String> userOfTerritory) {
		this.userOfTerritory = userOfTerritory;
	}
	
	/**
	 * 
	 * @return It returns the Continent of particular Territories.
	 */
	public Map<String, String> getTerritoryOfContinent() {
		return territoryOfContinent;
	}
	
	/**
	 * 
	 * @param territoryOfContinent takes the parameter territoryOfContinent Continent of particular Territories.
	 */
	public void setTerritoryOfContinent(Map<String, String> territoryOfContinent) {
		this.territoryOfContinent = territoryOfContinent;
	}
	
	/**
	 * 
	 * @return a HashMap which contains Territories and it's armies.
	 */
	public Map<String, Integer> getTerritoryArmy() {
		return territoryArmy;
	}
	
	/**
	 * 
	 * @param territoryArmy a HashMap which contains Territories and it's armies.
	 */
	public void setTerritoryArmy(Map<String, Integer> territoryArmy) {
		this.territoryArmy = territoryArmy;
	}
	
	/**
	 * The method is used to:
	 * <ul>
	 * <li><b>Update the value of count of an army for each territory.</b></li>
	 * <li><i>for initial startup phase of Game.</i></li>
	 * <li><b>It Updates the count of an army during reinforcement phase.</b></li>
	 * </ul>
	 * 
	 * @param territory Name of Territories
	 * @param army Number of armies
	 * @param operation Name of operation
	 * @return a HashMap which contains Territories and it's armies.
	 */
	public Map<String, Integer> updateTerritoryArmy (String territory,int army, String operation){
		if(operation.equalsIgnoreCase("add")) {
			if(territoryArmy.containsKey(territory)) {
				territoryArmy.replace(territory, territoryArmy.get(territory), territoryArmy.get(territory) + army);
			} else {
				territoryArmy.put(territory, army);
			}
		} else {
			territoryArmy.replace(territory, territoryArmy.get(territory), territoryArmy.get(territory) - army);
		}
		
		return territoryArmy;
	}
	
	/**
	 * The method is used to find Territory which belongs to which Continent.
	 * @param territory Name of Territories
	 * @param continent Name of Continent
	 * @return a HashMap contains Continent and it's Territories.
	 */
	public Map<String, String> addTerritoryCont (String territory,String continent){
		territoryOfContinent.put(territory, continent);
		return territoryOfContinent;
	}
	
	
	/**
	 * The method is used to:
	 * <ul>
	 * <li><b>It is used to identify which Territory belongs to which Player</b></li>
	 * <li>Update Player when a particular Territory wins by Player</li>
	 * </ul>
	 *
	 * @param user Name of Player. 
	 * @param territory Name of Territories.
	 * @return a HashMap contains RiskPlayers and it's Territories.
	 */
	public Map<String, String> updateTerritoryUser (String user,String territory){
		if(userOfTerritory.containsKey(territory)) {
			userOfTerritory.replace(territory, userOfTerritory.get(territory), user); 
		} else {
			userOfTerritory.put(territory, user);
		}
		return userOfTerritory;
	}
	
	/**
	 *  Method is used to add adjacent Territories of a Territories.
	 * @param  territory Gives the name of of Territory.
	 * @param adjTerritory Gives the name of adjacent Territories.
	 * @return a HashMap with Territories and it's adjacent Territories.
	 */
	public Map<String, ArrayList<String>> addAdjacentTerritory(String territory, String adjTerritory){
		if(neighborTerritories.containsKey(territory)) {
			neighborTerritories.get(territory).add(adjTerritory);
		} else {
			ArrayList<String> tempArray= new ArrayList<>();
			tempArray.add(adjTerritory);
			neighborTerritories.put(territory,tempArray);
		}
			
		return neighborTerritories;
	}
	
	/**
	 * 
	 * @return a HashMap with Territories and it's adjacent Territories.
	 */
	public Map<String, ArrayList<String>> getNeighborTerritories() {
		return neighborTerritories;
	}
	
	/**
	 * 
	 * @param neighborTerritories a HashMap with Territories and it's adjacent Territories.
	 */
	public void setNeighborTerritories(Map<String, ArrayList<String>> neighborTerritories) {
		this.neighborTerritories = neighborTerritories;
	}
	
	/**
	 * This method is used to add Territories to Territories List.
	 * @param territory Name of Territories.
	 * @return List of Territories.
	 */
	public ArrayList<String> addTerritory(String territory){
		totalTerritories.add(territory);
		return totalTerritories;
	}
	
	/**
	 * The  method is used to check whether the particular  Territories  have multiple Continents or not?
	 * It is to only used for map validation .
	 * 
	 * @param territory Name of Territories
	 * @param continent Name of Continent
	 */
	public void addDuplicateTerritoryContinent(String territory, String continent) {
		if(checkDuplicateTerritoryContinent.containsKey(territory)) {
			checkDuplicateTerritoryContinent.get(territory).add(continent);
		}else {
			ArrayList<String> continentList = new ArrayList<>();
			continentList.add(continent);
			checkDuplicateTerritoryContinent.put(territory, continentList);
		}
	}
	
	/**
	 * 
	 * @return a HashMap with Territories and it's Continent.
	 */
	public Map<String,ArrayList<String>> getCheckDuplicateTerritoryContinent(){
		return checkDuplicateTerritoryContinent;
	}

	/**
	 * Give a Number to Territories
	 * @param territory Name of Territories
	 * @param territoryNum Number starting from zero
	 */
	public void addNumberOfTerritory(String territory, int territoryNum) {
		territoryNumber.put(territory, territoryNum);
	}
	
	/**
	 * 
	 * @return a HashMap contains number of territory.
	 */
	public Map<String,Integer> getNumberOfTerritory(){
		return territoryNumber;
	}
	public void updateTerritoryContinent(String previousContinent, String newContinent) {
	    for(Entry<String, String> entry : territoryOfContinent.entrySet() ) {
			if(entry.getValue().equalsIgnoreCase(previousContinent)) {
			    entry.setValue(newContinent);
			}
	    }
	}
}
