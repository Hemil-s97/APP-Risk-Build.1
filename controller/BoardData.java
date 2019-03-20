package controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Logger;

import exception.InvalidMapException;
import model.Continent;
import model.Territories;
import validation.ValidationOfMap;

/**
 * This class is used to <b>Parse map file</b>, <b>Generate Data</b> and send
 * the request to <b>validate map file</b>.
 *
 */
public class BoardData {
	StringBuilder infoOfTerritory;
	BufferedReader reader;

	private String pathOfMapFile;
	boolean continentFlag = false;
	int noOfTerritories = 0;
	boolean territoriesFlag = false;
	boolean isMapValid = false;

	Continent objectOfContinent;
	Territories objectOfTerritory;

	String[] listOfContinents;
	String[] listOfTerritories;
	StringBuilder continentData;
	
	private static final Logger LOGGER = Logger.getLogger(BoardData.class.getName());

	/**
	 * Get Territory Data from file
	 * 
	 * @return infoOfTerritory
	 */
	public StringBuilder getinfoOfTerritory() {
		return infoOfTerritory;
	}
	
	/**
	 * This constructor set the User specified path of map File.
	 * 
	 * @param pathOfMapFile path of map File.
	 */
	public BoardData(String pathOfMapFile) {
		super();
		this.pathOfMapFile = pathOfMapFile;
	}

	/**
	 * Set Territory Data from File
	 * 
	 * @param infoOfTerritory all territory data
	 */
	public void setinfoOfTerritory(StringBuilder infoOfTerritory) {
		this.infoOfTerritory = infoOfTerritory;
	}

	/**
	 * Get Continent Data from file
	 * 
	 * @return continentData
	 */
	public StringBuilder getContinentData() {
		return continentData;
	}

	/**
	 * set Continent Data from file
	 * 
	 * @param continentData all continent Data
	 */
	public void setContinentData(StringBuilder continentData) {
		this.continentData = continentData;
	}

	/**
	 * This method is used to Read and Generate Data from map file, set the data to
	 * Continent and Territory Model, and calls mapValidate() method to check if
	 * Generated Data is Correct or not?.
	 * 
	 * @return true if map data is valid, else return false.
	 */
	public boolean boardDataGeneration() {
		continentData = new StringBuilder();
		infoOfTerritory = new StringBuilder();
		objectOfContinent = new Continent();
		objectOfTerritory = new Territories();
		objectOfTerritory.addCardValue("Infantry", 1);
		objectOfTerritory.addCardValue("Cavalry", 5);
		objectOfTerritory.addCardValue("Artillery", 10);
		objectOfTerritory.addCardValue("Wild Card", 0);
		ArrayList<String> temporaryCardName = new ArrayList<>();
		temporaryCardName.add("Infantry");
		temporaryCardName.add("Cavalry");
		temporaryCardName.add("Artillery");
		int cardNo = 0;
		
		String currentLine;
		try {
			reader = new BufferedReader(new FileReader(pathOfMapFile));
			while ((currentLine = reader.readLine()) != null) {

				if (currentLine.equals("[Continents]")) {
					continentFlag = true;
					territoriesFlag = false;
				}
				if (currentLine.equals("[Territories]")) {
					territoriesFlag = true;
					continentFlag = false;
				}

				// assign Territories List.
				if (territoriesFlag && !(currentLine.isEmpty() || currentLine.equals("[Territories]"))) {
					infoOfTerritory.append(currentLine + "\n");
					listOfTerritories = currentLine.split(",");
					// to assign Adjacent Territories HashMap
					int i = 4;
					while (i < listOfTerritories.length) {
						objectOfTerritory.addAdjacentTerritory(listOfTerritories[0], listOfTerritories[i]);
						i++;
					}
					objectOfTerritory.addTerritory(listOfTerritories[0]);
					objectOfTerritory.addTerritoryCont(listOfTerritories[0], listOfTerritories[3]);
					objectOfContinent.addContTerritoryValue(listOfTerritories[3]);
					objectOfTerritory.addTerritoryCard(listOfTerritories[0], temporaryCardName.get(cardNo));
					cardNo++;
					if (cardNo >= 3)
						cardNo = 0;
					objectOfContinent.addContinentTerritory(listOfTerritories[3], listOfTerritories[0]);
					objectOfTerritory.addDuplicateTerritoryContinent(listOfTerritories[0], listOfTerritories[3]);
					objectOfTerritory.addNumberOfTerritory(listOfTerritories[0], noOfTerritories++);
				}

				if (continentFlag && !(currentLine.isEmpty()) && !currentLine.equalsIgnoreCase("[Continents]")) {
					continentData.append(currentLine + "\n");
					listOfContinents = currentLine.split("=");
					objectOfContinent.setContinentValue(listOfContinents[0], Integer.parseInt(listOfContinents[1]));

				}
			}
			objectOfTerritory.addTerritoryCard("Wild Card 1", "Wild Card");
			objectOfTerritory.addTerritoryCard("Wild Card 2", "Wild Card");

			ValidationOfMap mapValidator = new ValidationOfMap(objectOfContinent, objectOfTerritory);
			isMapValid = mapValidator.mapValidation();
			reader.close();
		} catch (IOException | InvalidMapException e) {
			LOGGER.warning("Map is Not Validated. Please Check data");
		}
		return isMapValid;
	}

	/**
	 * This method returns the path of map File.
	 * 
	 * @return pathOfMapFile path of map File.
	 */
	public String getpathOfMapFile() {
		return pathOfMapFile;
	}

	/**
	 * This method sets the pathOfMapFile.
	 * 
	 * @param pathOfMapFile path of map File.
	 */
	public void setpathOfMapFile(String pathOfMapFile) {
		this.pathOfMapFile = pathOfMapFile;
	}
}
