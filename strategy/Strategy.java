package strategy;

import model.Continent;
import model.Territories;

/**
 * This class is for strategy pattern
 *
 */
public interface Strategy {

	/**
	 * This method implements strategy pattern for reinforcement army generation
	 * 
	 * @param currentPlayer    current player name
	 * @param currentContinent current continent name
	 * 
	 */
	public void generationOfReinforcementArmy(String currentPlayer, Continent currentContinent);

	/**
	 * This method implements strategy pattern for attack phase
	 * 
	 * @param currentTerritory territory object
	 * @param fromTerritory    attacker territory
	 * @param attackerDice     amount of Dice of Attacker
	 * @param toTerritory      defender territory
	 * @param defenderDice     amount of Dice of defender
	 * 
	 */
	public void performAttack(Territories currentTerritory, String fromTerritory, int attackerDice, String toTerritory,
			int defenderDice);

	/**
	 * This method implements strategy pattern for reinforcement phase
	 * 
	 * @param territory        name of territory
	 * @param currentTerritory current territory name
	 * @param army             no of armies
	 * @param currentPlayer    current player name
	 * 
	 */
	public void performReinforcement(Territories territory, String currentTerritory, int army, String currentPlayer);

	/**
	 * This method implements strategy pattern for moving army after attack
	 * 
	 * @param playerName       name of the player
	 * @param fromTerritory    from territory
	 * @param toTerritory      to territory
	 * @param currentTerritory territory object
	 * @param armies           number of armies to move from
	 * 
	 */
	public void moveArmyAfterAttack(String playerName, String fromTerritory, String toTerritory,
			Territories currentTerritory, int armies);

	/**
	 * This method implements strategy pattern for fortification phase
	 * 
	 * @param fromTerritory name of territory
	 * @param toTerritory   name of territory
	 * @param territory     name of territory
	 * @param getArmySelect no of selected army
	 * 
	 */
	public void performForitification(String fromTerritory, String toTerritory, Territories territory,
			int getArmySelect);
}
