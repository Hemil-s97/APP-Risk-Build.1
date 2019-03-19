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
	 * method for strategy object
	 * @param strategy pattern object
	 */
	public Context(Strategy strategy) {
		this.strategy = strategy;
	}

	/**
	 * Strategy pattern for reinforcement
	 * @param currentPlayer name of current player
	 * @param currentTerritory name of current territory
	 * @param army no. of armies 
	 * @param territory name of territory
	 */
	public void executeReinforcement(String currentPlayer, String currentTerritory, int army, Territories territory) {
		strategy.doReinforcement(currentPlayer, currentTerritory, army, territory);
	}

	/**
	 * Strategy pattern for reinforcement army generation
	 * @param currentPlayer name of current player
	 * @param currentContinent name of current Continent
	 */
	public void executeReinforcementArmy(String currentPlayer, Continent currentContinent) {
		strategy.generateReinforcementArmy(currentPlayer, currentContinent);
	}

	/**
	 * Strategy pattern for move army after attack
	 * @param playerName name of player
	 * @param currentTerritory name of current territory
	 * @param fromTerritory name of territory
	 * @param toTerritory name of territory
	 * @param armies no. of armies
	 */
	public void executeArmyAfterAttack(String playerName, Territories currentTerritory, String fromTerritory,
			String toTerritory, int armies) {
		strategy.moveArmyAfterAttack(playerName, currentTerritory, fromTerritory, toTerritory, armies);
	}

	/**
	 * Strategy pattern for attack
	 * @param currentTerritory name of current territory
	 * @param fromTerritory name of territory 
	 * @param toTerritory name of territory
	 * @param attackerDice no. of attacker's dices
	 * @param defenderDice no. of defender's dices
	 */
	public void executeAttack(Territories currentTerritory, String fromTerritory, String toTerritory, int attackerDice,
			int defenderDice) {
		strategy.doAttack(currentTerritory, fromTerritory, toTerritory, attackerDice, defenderDice);
	}

	/**
	 * Strategy pattern for fortification
	 * @param territory name of territory
	 * @param fromTerritory name of territory
	 * @param toTerritory name of territory
	 * @param getArmySelect no.of selected armies
	 */
	public void executeFortification(Territories territory, String fromTerritory, String toTerritory, int getArmySelect) {
		strategy.doForitification(territory, fromTerritory, toTerritory, getArmySelect);
	}
}