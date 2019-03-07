package validation;

import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;

import exception.InvalidMapException;
import model.Continent;
import model.Territories;

/**
 * This class is used to Validate Map.
 */
public class ValidationOfMap {
	Map<String, Integer> continentValue;
	Map<String, ArrayList<String>> continentTerritories;
	Map<String, Integer> noOfTerritory;
	Map<String, ArrayList<String>> duplicateTerritoryContinent;
	Map<String, ArrayList<String>> territoryAdjacencyList;

	boolean isMapValid;
	Continent continent;
	Territories territory;

	/**
	 * This method validates the Continents.
	 *
	 * @throws InvalidMapException throws exception when map is invalid
	 * @return isMapValid returns true if continents configurations are valid else
	 *         false.
	 * 
	 */
	public boolean continentValidation() throws InvalidMapException {
		if (continentTerritories != null) {
			if (continentTerritories.size() == continentValue.size()) {
				for (Entry<String, ArrayList<String>> entryTerritory : continentTerritories.entrySet()) {
					String key = entryTerritory.getKey();
					if (continentValue.containsKey(key)) {
						isMapValid = true;
					} else {
						isMapValid = false;
						throw new InvalidMapException("Continents are not Valid");
					}
					territoryValidation();
				}
			} else {
				isMapValid = false;
				throw new InvalidMapException("Continent are not Compatible");
			}
		} else {
			isMapValid = false;
			throw new InvalidMapException("Territories should not be null");
		}
		return isMapValid;
	}

	/**
	 * This constructor is used to initialize the data of map.
	 * 
	 * @param continentObject object of continent
	 * @param territoryObject object of territory
	 */
	public ValidationOfMap(Continent continentObject, Territories territoryObject) {
		this.noOfTerritory = territoryObject.getNumberOfTerritory();
		this.duplicateTerritoryContinent = territoryObject.getCheckDuplicateTerritoryContinent();
		this.territoryAdjacencyList = territoryObject.getNeighborTerritories();
		this.continentTerritories = continentObject.getContinentTerritories();
		this.continentValue = continentObject.getValueOfContinent();
	}

	/**
	 * This method validates the continent Winning Value. Winning Value must be
	 * at-least one.
	 * 
	 * @return true if continent value is greater than zero else false
	 * @throws InvalidMapException throws exception when map is invalid
	 * 
	 */
	public boolean continentValueValidation() throws InvalidMapException {
		for (Entry<String, Integer> entry : continentValue.entrySet()) {
			String key = entry.getKey();
			if (continentValue.get(key) >= 1) {
				isMapValid = continentValidation();
				this.isMapValid = true;
			} else {
				this.isMapValid = false;
				throw new InvalidMapException("Continent winning value should be more than one.");
			}
		}
		return isMapValid;
	}

	/**
	 * This method is used to check if Graph is Connected or not.
	 *
	 * @throws InvalidMapException invalid map exception
	 * @return isMapValid returns true if graph is connected else false
	 * 
	 */
	public boolean isGraphConnected() throws InvalidMapException {
		IsGraphConnected graph = new IsGraphConnected(noOfTerritory.size());
		for (Entry<String, ArrayList<String>> entry : territoryAdjacencyList.entrySet()) {
			int i = 0;
			while (i < entry.getValue().size()) {
				graph.addConnectionLine(noOfTerritory.get(entry.getKey()), noOfTerritory.get(entry.getValue().get(i)));
				i++;
			}
			/*
			 * for (int i = 0; i < entry.getValue().size(); i++) {
			 * graph.addConnectionLine(noOfTerritory.get(entry.getKey()),
			 * noOfTerritory.get(entry.getValue().get(i))); }
			 */
		}
		if (graph.checkGraphConnection()) {
			this.isMapValid = true;
		} else {
			this.isMapValid = false;
			throw new InvalidMapException("Graph is not connected");
		}
		return isMapValid;
	}

	/**
	 * This method is used to validate whether territories have adjacent territory or not.
	 *
	 * @return true if adjacent territories configurations are valid else false
	 * @throws InvalidMapException invalid map exception
	 * 
	 */
	public boolean adjacentTerritoryValidation() throws InvalidMapException {
		if (territoryAdjacencyList != null) {
			for (Entry<String, ArrayList<String>> entry : territoryAdjacencyList.entrySet()) {
				if (entry.getValue().size() > 0) {
					this.isMapValid = true;
				} else {
					this.isMapValid = false;
					throw new InvalidMapException("adjacent territories should not be null");
				}
			}
			this.isGraphConnected();
		}
		return isMapValid;
	}

	/**
	 * This method is used to validate territories. 
	 * 
	 * @return true if one territory is present in one continent else false
	 * @throws InvalidMapException invalid map exception
	 * 
	 */
	public boolean territoryValidation() throws InvalidMapException {
		if (continentTerritories.size() > 0) {
			for (Entry<String, ArrayList<String>> entryContinent : continentTerritories.entrySet()) {
				if (entryContinent.getValue().size() > 0) {
					for (Entry<String, ArrayList<String>> entryTerritory : duplicateTerritoryContinent.entrySet()) {
						int size = entryTerritory.getValue().size();
						switch (size) {
						case 1:
							this.isMapValid = true;
							break;

						default:
							this.isMapValid = false;
							throw new InvalidMapException(
									"Error: Territory Duplicacy." + "One Territory should be in One Continent only");
						}
						/*if (entryTerritory.getValue().size() == 1) {
							this.isMapValid = true;
						} else {
							this.isMapValid = false;
							throw new InvalidMapException(
									"Error: Territory Duplicacy." + "One Territory should be in One Continent only");
						}*/
					}
				} else {
					this.isMapValid = false;
					throw new InvalidMapException("Continent should have at-least one Territory");
				}
			}
			isMapValid = adjacentTerritoryValidation();
		} else {
			this.isMapValid = false;
			throw new InvalidMapException("Territories should not be null");
		}
		return isMapValid;
	}

	/**
	 * This method validates the Content of Map.
	 * 
	 * @return true if map is valid after validation else false
	 * @throws InvalidMapException throws exception when map is invalid
	 * 
	 */
	public boolean mapValidation() throws InvalidMapException {
		if (continentValue != null) {
			isMapValid = continentValueValidation();
		} else {
			this.isMapValid = false;
			throw new InvalidMapException("Map should contain atleast one Continent.");
		}
		return isMapValid;
	}
}
