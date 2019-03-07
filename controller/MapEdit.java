package controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Map.Entry;

import exception.InvalidMapException;
import model.Continent;
import model.Territories;
import validation.ValidationOfMap;

/**
 * This Class is used to edit map file.
 *
 */
public class MapEdit {
	Continent continent;
	String pathOfMapFile;
	Territories territory;

	/**
	 * Method get continent
	 * 
	 * @return continent
	 */
	public Continent getContinent() {
		return continent;
	}

	/**
	 * Method set continent
	 * 
	 * @param continent Continent Object
	 */
	public void setContinent(Continent continent) {
		this.continent = continent;
	}

	/**
	 * Default Constructor with one parameter, pathOfMapFile
	 * 
	 * @param pathOfMapFile Path of Map file.
	 */
	public MapEdit(String pathOfMapFile) {
		super();
		this.pathOfMapFile = pathOfMapFile;
	}

	/**
	 * This method is used to save the Map file that is edited by the user.
	 * 
	 * @param territory Territory Model Object
	 * @param continent Continent Model Object
	 * @return true if content edit is validated according to Risk Map Otherwise
	 *         return false;
	 * @throws InvalidMapException throws exception when map is invalid
	 */
	public boolean saveEditedMap(Territories territory, Continent continent) throws InvalidMapException {
		this.territory = territory;
		this.continent = continent;
		int i = 0;
		while (i < territory.getTotalTerritories().size()) {
			territory.addNumberOfTerritory(territory.getTotalTerritories().get(i), i);
			i++;
		}
		/*
		 * for (int i = 0; i < territory.getTerritoryList().size(); i++) {
		 * territory.addNumberOfTerritory(territory.getTerritoryList().get(i), i); }
		 */
		ValidationOfMap mapValidator = new ValidationOfMap(continent, territory);
		boolean flag = mapValidator.mapValidation();
		if (flag) {
			fileCreation();
		}
		return mapValidator.mapValidation();
	}

	/**
	 * Method get territory
	 * 
	 * @return territory is territory
	 */
	public Territories getTerritory() {
		return territory;
	}

	/**
	 * Method set territory
	 * 
	 * @param territory is territory
	 */
	public void setTerritory(Territories territory) {
		this.territory = territory;
	}

	/**
	 * This method used to generate Map Data and Valid File content
	 * 
	 * @return true if file data is validated corrected Otherwise return false
	 */
	public boolean dataGeneration() {
		BoardData boardData = new BoardData(pathOfMapFile);
		boolean isMapValid = boardData.boardDataGeneration();
		if (isMapValid) {
			continent = boardData.objectOfContinent;
			territory = boardData.objectOfTerritory;
		}
		return isMapValid;
	}

	/**
	 * This method returns the path of Map file.
	 * 
	 * @return pathOfMapFile path of file
	 */
	public String getpathOfMapFile() {
		return pathOfMapFile;
	}

	/**
	 * This method sets the passed path of Map file.
	 * 
	 * @param pathOfMapFile path of file
	 */
	public void setpathOfMapFile(String pathOfMapFile) {
		this.pathOfMapFile = pathOfMapFile;
	}

	/**
	 * This method generate map file with data.
	 */
	public void fileCreation() {
		try {
			System.out.println("File Creation Started");
			String defaultMapTag = "[Map]\n" + "author=Hemil\n" + "warn=yes\n" + "image=previous.bmp\n" + "wrap=no\n";
			StringBuilder listOfContinents = new StringBuilder();
			StringBuilder listOfTerritories = new StringBuilder();
			listOfContinents.append("[Continents]\n");
			listOfTerritories.append("[Territories]\n");
			File file = new File(pathOfMapFile);
			if (file.exists()) {
				System.out.println("Exist file deletd " + file.delete());
			}
			boolean isFileCreated = file.createNewFile();
			if (isFileCreated) {
				FileWriter fileWriter = new FileWriter(file);
				BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
				for (Entry<String, ArrayList<String>> entry : territory.getNeighborTerritories().entrySet()) {
					listOfTerritories
							.append(entry.getKey() + ",0,0," + territory.getTerritoryOfContinent().get(entry.getKey()));
					int i = 0;
					while (i < entry.getValue().size()) {
						listOfTerritories.append("," + entry.getValue().get(i));
						i++;
					}
					/*
					 * for (int i = 0; i < entry.getValue().size(); i++) {
					 * listOfTerritories.append("," + entry.getValue().get(i)); }
					 */
					listOfTerritories.append("\n");
				}
				for (Entry<String, Integer> entry : continent.getValueOfContinent().entrySet()) {
					listOfContinents.append(entry.getKey() + "=" + entry.getValue() + "\n");
				}
				bufferedWriter.write(defaultMapTag + "" + listOfContinents + "" + listOfTerritories);
				bufferedWriter.flush();
				bufferedWriter.close();
				System.out.println("File Created");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception is thrown while creating file : " + e);
		}

	}
}
