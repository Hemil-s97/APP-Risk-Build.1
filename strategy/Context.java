package strategy;

import model.Continent;
import model.Territories;

/**
 * This class used in implementation of strategy pattern
 *
 */
public class Context {
	private Strategy strategy;

	/**
	 * Strategy pattern for generation of reinforcement army
	 * 
	 * @param currentPlayer    name of current player
	 * @param currentContinent name of current Continent
	 * 
	 */
	public void executeReinforcementArmy(String currentPlayer, Continent currentContinent) {
		strategy.generationOfReinforcementArmy(currentPlayer, currentContinent);
	}

	/**
	 * Constructor to initialize strategy object
	 * 
	 * @param strategy pattern object
	 * 
	 */
	public Context(Strategy strategy) {
		this.strategy = strategy;
	}

	/**
	 * Strategy pattern for fortification
	 * 
	 * @param fromTerritory name of territory
	 * @param toTerritory   name of territory
	 * @param territory     name of territory
	 * @param getArmySelect no.of selected armies
	 * 
	 */
	public void executeFortification(String fromTerritory, String toTerritory, Territories territory,
			int getArmySelect) {
		strategy.performForitification(fromTerritory, toTerritory, territory, getArmySelect);
	}

	/**
	 * Strategy pattern for reinforcement
	 * 
	 * @param territory        name of territory
	 * @param currentTerritory name of current territory
	 * @param army             no. of armies
	 * @param currentPlayer    name of current player
	 * 
	 */
	public void executeReinforcement(Territories territory, String currentTerritory, int army, String currentPlayer) {
		strategy.performReinforcement(territory, currentTerritory, army, currentPlayer);
	}

	/**
	 * Strategy pattern for attack
	 * 
	 * @param currentTerritory name of current territory
	 * @param fromTerritory    name of territory
	 * @param attackerDice     no. of attacker's dices
	 * @param toTerritory      name of territory
	 * @param defenderDice     no. of defender's dices
	 * 
	 */
	public void executeAttack(Territories currentTerritory, String fromTerritory, int attackerDice, String toTerritory,
			int defenderDice) {
		strategy.performAttack(currentTerritory, fromTerritory, attackerDice, toTerritory, defenderDice);
	}

	/**
	 * Strategy pattern for move army after attack
	 * 
	 * @param playerName       name of player
	 * @param fromTerritory    name of territory
	 * @param toTerritory      name of territory
	 * @param currentTerritory name of current territory
	 * @param armies           no. of armies
	 * 
	 */
	public void executeArmyAfterAttack(String playerName, String fromTerritory, String toTerritory,
			Territories currentTerritory, int armies) {
		strategy.moveArmyAfterAttack(playerName, fromTerritory, toTerritory, currentTerritory, armies);
	}
}
