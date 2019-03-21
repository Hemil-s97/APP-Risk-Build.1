package TestController;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controller.StartingPhase;
import model.Continent;
import model.RiskPlayers;
import model.Territories;

public class StartingPhaseTest {

	Continent continent;
	RiskPlayers players;
	Territories territory;
	StartingPhase startUpPhase;
	
	String WesternAfrica = "Western Africa";
	String Niger = "Niger";
	String Nigeria = "Nigeria";
	String chad = "chad";
	
	ArrayList<String> playerList;
	ArrayList<String> territoryList;
	Map<String, Integer> territoryArmy;
	String NorthernAfrica = "Northern Africa";
	String Morroco = "Morroco";
	String Algeria = "Algeria";
	String Tunisia = "Tunisia";
	
	String SouthernAfrica = "Southern Africa";
	String WesternSahara = "Western Sahara";
	String Mauritania = "Mauritania";
	String Senegal = "Senegal";
	
	@BeforeEach
	public void beforeEachTest() {
		continent = new Continent();
		territory = new Territories();
		players = new RiskPlayers();
		playerList = new ArrayList<>();
		territoryList = new ArrayList<>();
		territoryArmy = new HashMap<String, Integer>();
		
		playerList.add("Nikunj");
		playerList.add("Hemil");
		playerList.add("Charandeep");
		playerList.add("Varinder");
		players.setPlayerList(playerList);
		territory.addTerritory(Algeria);
		territoryList.add(Algeria);
		territory.addTerritoryCont(Algeria, NorthernAfrica);
		territory.addTerritory(Mauritania);
		territoryList.add(Mauritania);
		territory.addTerritoryCont(Mauritania, NorthernAfrica);
		territory.addTerritory(Morroco);
		territoryList.add(Morroco);
		territory.addTerritoryCont(Morroco, NorthernAfrica);
		territory.addTerritory(Niger);
		territoryList.add(Niger);
		territory.addTerritoryCont(Niger, SouthernAfrica);
		territory.addTerritory(Nigeria);
		territoryList.add(Nigeria);
		territory.addTerritoryCont(Nigeria, SouthernAfrica);
		territory.addTerritory(Senegal);
		territoryList.add(Senegal);
		territory.addTerritoryCont(Senegal, SouthernAfrica);
		territory.addTerritory(Tunisia);
		territoryList.add(Tunisia);
		territory.addTerritoryCont(Tunisia, WesternAfrica);
		territory.addTerritory(WesternSahara);
		territoryList.add(WesternSahara);
		territory.addTerritoryCont(WesternSahara, WesternAfrica);
		startUpPhase = new StartingPhase(players, territory, continent);
		
		
		int i = 0;
		while(i < territoryList.size()) {
			territoryArmy.put(territoryList.get(i), 15);
			i++;
		}
	}
	
	@Test
	public void testInitialStartingPhase() {
		startUpPhase.initialStartingPhase();
		assertEquals(territoryArmy, territory.getTerritoryArmy());
	}
}
