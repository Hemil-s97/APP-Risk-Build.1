package controller;

import model.Territories;

/**
 * This class is used to start the <b>Fortification</b> phase.
 *
 */
public class Fortification {
	Territories territory;

	/**
	 * This method returns the object of the territory class
	 * 
	 * @return Current Territory Object
	 */
	public Territories getTerritory() {
		return territory;
	}

	/**
	 * This method allow player to move army from one territory to any other
	 * adjacent territory.
	 * 
	 * @param army                       No. of Armies Player want to move
	 * @param fromOneTerritory           Territory Name from which army want to move
	 * @param toAnotherAdjacentTerritory Territory Name to which armies is moved.
	 * 
	 */
	public void startFortification(int army, String fromOneTerritory, String toAnotherAdjacentTerritory) {
		territory.updateTerritoryArmy(toAnotherAdjacentTerritory, army, "ADD");
		territory.updateTerritoryArmy(fromOneTerritory, army, "DELETE");
	}

	/**
	 * This method set Territory Object
	 * 
	 * @param territory Current Territory Object
	 */
	public void setTerritory(Territories territory) {
		this.territory = territory;
	}

	/**
	 * This constructor is used to set the initial data of the class.
	 * 
	 * @param territory Object of the territory class
	 */
	public Fortification(Territories territory) {
		super();
		this.territory = territory;
	}
}
