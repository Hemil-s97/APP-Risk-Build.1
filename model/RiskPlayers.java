package com.risk.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;
import com.risk.strategy.Strategy;

/**
 * This is a model class for playerContinent, playerArmy,
 * playerList, playerPlaying as its data member.
 *
 */
public class RiskPlayers implements Strategy {
	/**
	 * @param playerOfContinent player had territories in particular continent
	 * @param playerArmy player having no. of armies
	 * @param playerList no. of player currently playing game
	 * @param playerPlaying no. of player playing game
 * @param cards no. of card player has
	 * @param territoryCards territory and card type mapping
	 * @param wonTheAttack return true if players has won attack and acquired territories
	 * @param wonTheCard return true if players acquired territories in his/her turn and gets card
	 * @param tradeInArmies number of tradein armies
	 * @param messageOFAttacker message for attacker
	 * @param messageOfDefender message for defender
	 * @param tradeIn number of tradein is done in game
	 */
	 
	Map<String, Continent> playerOfContinent; // playerContinent
	Map<String, Integer> playerArmy;  // 
	ArrayList<String> playerList; 
	ArrayList<String> playerPlaying;
	String currentPhase;
	private boolean wonTheAttack;// isWonAttack to wonTheAttack
	private boolean wonTheCard;// isWonCard to wonTheCard
	int  tradeInArmies = 4;
    String messageOFAttacker = "";//messageOFAttacker to messageOFAttacker
	String messageOfDefender = "";      //messageOfDefender to messageOfDefender
String fortificationMsg ="";
	String reinforcementMsg ="";
    int tradeIn = 0;
	Map<String, String> cards;
	Map<String, String> territoryCards;
	public Double value;
	Map<String,String> typeOfPlayer;// change karna hai
/**
	 * This method use to get message when event performed in reinforcement phase
	 * @return reinforcementMsg
	 */
	public String getReinforcementMsg() {
		return reinforcementMsg;
	}
	/**
	 * This method use to set message when event performed in reinforcement phase
	 * @param reinforcementMsg text which is print in logs
	 */
	public void setReinforcementMsg(String reinforcementMsg) {
		this.reinforcementMsg = reinforcementMsg;
	}
	/**
	 * This method use to get message when event performed in fortification phase
	 * @return fortificationMsg
	 */
	public String getFortificationMsg() {
		return fortificationMsg;
	}
	/**
	 * This method use to set message when event performed in fortification phase
	 * @param fortificationMsg text which is print in logs
	 */
	public void setFortificationMsg(String fortificationMsg) {
		this.fortificationMsg = fortificationMsg;
	}

/**
	 * This method is used to get Type of players
	 * @return playerType
	 */
	public Map<String, String> getTypeOfPlayer() {
		return typeOfPlayer;
	}
	/**
	 * This method is used to set Type of players
	 * @param typeOfPlayer Type of players
	 */
	public void setTypeOfPlayer(Map<String, String> typeOfPlayer) {
		this.typeOfPlayer = typeOfPlayer;
	}// change karna haii
	public RiskPlayers() {
		playerOfContinent = new HashMap<>();
		playerArmy = new HashMap<>();
		playerList = new ArrayList<>();
		playerPlaying = new ArrayList<>();
	    cards = new HashMap<>();
		territoryCards = new HashMap<>();
typeOfPlayer = new HashMap<>();// change karna hai
	}
/**
	 * This method is used to put player name and its type in playerType Map
	 * @param playerName player Name
	 * @param type player type
	 */
	public void addPlayerType(String playerName,String type) {
		typeOfPlayer.put(playerName, type);
	}
	/**
	//change karna hai upperwala
	
	 * This Method return number of trade in armies generated
	 * @return tradeInArmies number of trade in armies
	 */
	public int getTradeInArmies() {
		return tradeInArmies;
	}
	/**
	 * This Method set  number of trade in armies
	 * @param tradeInArmies number of trade in armies
	 */
	public void setTradeInArmies(int tradeInArmies) {
		this.tradeInArmies = tradeInArmies;
	}
	/**
	 *  This Method return true if players acquired territories in his/her turn and gets card
	 * @return true if players acquired territories in his/her turn and gets card
	 */
	public boolean isWonTheCard() {
		return wonTheCard;
	}
	/**
	 * This method set true if players acquired territories in his/her turn and gets card otherwise false
	 * @param wonTheCard true if players acquired territories in his/her turn and gets card
	 */
	public void setWonTheCard(boolean isWonCard) {
		this.wonTheCard = isWonCard;
	}
	/**
	 * This method get territory and card type mapping
	 * @return territoryCards territory and card type mapping
	 */
	public Map<String, String> getTerritoryCards() {
		return territoryCards;
	}
	/**
	 * This method set territory and card type mapping
	 * @param territoryCards territory and card type mapping
	 */
	public void setTerritoryCards(Map<String, String> territoryCards) {
		this.territoryCards = territoryCards;
	}
	/**
	 * 
	 * @return playerContinent player had territories in particular continent
	 */
	public Map<String, Continent> getPlayerContinent() {
		return playerOfContinent;
	}
	/**
	 * This method set territories in particular continent
	 * @param playerContinent Territories in particular continent
	 */
	public void setPlayerContinent(Map<String, Continent> playerContinent) {
		this.playerOfContinent = playerContinent;
	}

	/**
	 * 
	 * @return a List of RiskPlayers playing.
	 */
	public ArrayList<String> getPlayerPlaying() {
		return playerPlaying;
	}
/**
	 * This method is used to get number of trade in performed in game
	 * @return tradeIn
	 */
public int getTradeIn() {
		return tradeIn;
	}

/**
	 *  This method increment number of trade in whenever player trade in their cards
	 */
	public void setTradeIn() {
		this.tradeIn += 1;
	}
	/**
	 * 
	 * @param playerPlaying a List of RiskPlayers playing.
	 */
	public void setPlayerPlaying(ArrayList<String> playerPlaying) {
		this.playerPlaying = playerPlaying;
	}

	/**
	 * 
	 * @return a List of RiskPlayers.
	 */
	public ArrayList<String> getPlayerList() {
		return playerList;
	}

	/**
	 * 
	 * @param playerList a List of RiskPlayers.
	 */
	public void setPlayerList(ArrayList<String> playerList) {
		this.playerList = playerList;
	}

	/**
	 * This method create the List of RiskPlayers.
	 * @param name Name of Player.
	 * @return playerList.
	 */
	public ArrayList<String> addPlayers(String name) {
		playerList.add(name);
		return playerList;
	}

	/**
	 * This method selects the RiskPlayers who will be playing from the playerList
	 * and return a list of player playing.
	 * @param names contains a List of players.
	 * @param count number of players playing.
	 * @return a list playerPlaying.
	 */
	public ArrayList<String> selectPlayers(ArrayList<String> names , int count) {

		for(int i = 0 ; i < count ; i++ ) {
			playerPlaying.add(names.get(i));
		}
		return playerPlaying;
	}

	/**
	 * This method update the Map updatePlayerContinent.
	 * @param name Name of player
	 * @param continent object of model class Continent
	 * @return a Map playerContinent
	 */
	public Map<String, Continent> updatePlayerContinent(String name, Continent continent){
		playerOfContinent.replace(name, continent);
		return playerOfContinent;
	}

	/**
	 * This method assign a Player to Particular Continent's Territories.
	 * @param name Name of Player
	 * @param continent object of model class Continent
	 * @return a Map playerContinent
	 */
	public Map<String, Continent> addPlayerContinent(String name, Continent continent){
		playerOfContinent.put(name, continent);
		return playerOfContinent;
	}

	/**
	 * This method returns the Territories aquired by particular player.
	 * @param name Name of Player
	 * @return a Map PlayerContinent
	 */
	public Continent getPlayerContinent(String name){

		return playerOfContinent.get(name);
	}

	/**
	 * This method used to assign the armies by player the name of Player.
	 * @param name Name of player
	 * @param armyCount Number of Armies 
	 * @return a Map playerArmy
	 */
	public Map<String, Integer> initialArmy(String name,int armyCount){
		playerArmy.put(name, armyCount);
		return playerArmy;
	}

	/**
	 * This method is used to find the number of armies by the name of Player.
	 * @param name Name of Player.
	 * @return a Map playerArmy
	 */
	public int getPlayerArmy(String name){
		return playerArmy.get(name);
	}

	
	/**
	 * This method update the Map playerArmy.
	 * @param name Name of Player
	 * @param armyCount Number of Armies
	 * @param operation Name of Operation
	 * @return a Map playerArmy
	 */
	public Map<String, Integer> updateArmy(String name,int armyCount,String operation){
		if(operation.equalsIgnoreCase("add")) {
			playerArmy.replace(name, playerArmy.get(name), playerArmy.get(name) + armyCount);
		} else {
			playerArmy.replace(name, playerArmy.get(name), playerArmy.get(name) - armyCount);
		}
		return playerArmy;
	}

	/**
	 * Returns the List of RiskPlayers.
	 * @param number Number of RiskPlayers.
	 * @return a List of RiskPlayers.
	 */
	public String getPlayers(int number) {
		return playerList.get(number);	
	}

	/**
	 * This method is used to assign the current phase of game.
	 * @param currentPhase Assign the current phase of game.
	 */
    public void setCurrentPhase(String currentPhase) {
		this.currentPhase = currentPhase;
    }

	/**
	 * Returns the current phase of game.
	 * @return  currentPhase of player.
	 */
	public String getCurrentPhase() {
    	return currentPhase;
	}
/**
	 * return if attack is won.
	 * @return true if attack is won, else false;
	 */
	public boolean isWonTheAttack() {
		return wonTheAttack;
	}

	/**
	 * set default value of wonTheAttack is false;
	 * @param wonTheAttack attack is won or not?
	 */
	public void setWonTheAttack(boolean isAttackWon) {
		this.wonTheAttack = isAttackWon;
	}
	/**
	 * This Method return which players has which territory card 
	 * @return cards territory card
	 */
	public Map<String, String> getCards() {
		return cards;
	}
	/** 
	 * This Method set territory card for particular player 
	 * @param cards teritory card
	 */
	public void setCards(Map<String, String> cards) {
		this.cards = cards;
	}

	/**
	 * Attack phase using Strategy Pattern 
	 */
	@Override
	public void doAttack(Territories currentTerritory, String fromTerritory, String toTerritory, int attackerDice, int defenderDice) {
		wonTheAttack = false;
		setMessageOfDefender("");
		setMessageOFAttacker("");
		ArrayList<Integer> attackerDiceList = new ArrayList<>();
		ArrayList<Integer> defenderDiceList = new ArrayList<>();
		int[] arrayOfAttackerDice = new int[attackerDice];// attackerDiceArray to arrayOfAttackerDice
		int[] arrayOfDefenderDice = new int[defenderDice];// defenderDiceArray to arrayOfDefenderDice
		for (int ad = 0; ad < attackerDice; ad++) {
			arrayOfAttackerDice[ad] = (int) (Math.random() * 6 + 1);
			messageOFAttacker += "DIE " + (ad + 1) + " : " + arrayOfAttackerDice[ad] + "\n";
			attackerDiceList.add(arrayOfAttackerDice[ad]);
		}
		for (int dd = 0; dd < defenderDice; dd++) {
			arrayOfDefenderDice[dd] = (int) (Math.random() * 6 + 1);
			messageOfDefender += "DIE " + (dd + 1) + " : " + arrayOfDefenderDice[dd] + "\n";
			defenderDiceList.add(arrayOfDefenderDice[dd]);
		}

		Collections.sort(attackerDiceList);
		Collections.sort(defenderDiceList);
		Collections.reverse(attackerDiceList);
		Collections.reverse(defenderDiceList);
		int maximumAttack = attackerDiceList.size() >= defenderDiceList.size() ? defenderDiceList.size() : attackerDiceList.size();  
		for(int i=0;i<maximumAttack;i++) {
			if(attackerDiceList.get(i) > defenderDiceList.get(i)) {
				currentTerritory.updateTerritoryArmy(toTerritory, 1, "DELETE");
			}else {
				currentTerritory.updateTerritoryArmy(fromTerritory, 1, "DELETE");
			}
		}
		if(currentTerritory.getTerritoryArmy().get(toTerritory) == 0) {
			wonTheAttack = true;
			String toContinentPlayer = "";
			String fromContinentPlayer = "";
			String toPlayer = currentTerritory.getUserOfTerritory().get(toTerritory);
			String fromPlayer = currentTerritory.getUserOfTerritory().get(fromTerritory);
			for(Entry<String, ArrayList<String>> entry : playerOfContinent.get(fromPlayer).getContinentOccupiedterritories().entrySet()) {
				fromContinentPlayer = entry.getValue().contains(fromTerritory) ? entry.getKey() : fromContinentPlayer;
			}
			for(Entry<String, ArrayList<String>> entry : playerOfContinent.get(toPlayer).getContinentOccupiedterritories().entrySet()) {
				toContinentPlayer = entry.getValue().contains(toTerritory) ? entry.getKey() : toContinentPlayer;
			}
			playerOfContinent.get(toPlayer).getContinentOccupiedterritories().get(toContinentPlayer).remove(toTerritory);
			playerOfContinent.get(fromPlayer).getContinentOccupiedterritories().get(fromContinentPlayer).add(toTerritory);
			currentTerritory.updateTerritoryUser(currentTerritory.getUserOfTerritory().get(fromTerritory),toTerritory);
			int rand = 0;
			if(!currentTerritory.getCardOfTerritory().isEmpty())
				rand = new Random().nextInt(currentTerritory.getCardOfTerritory().keySet().toArray().length)+1;
			if(currentTerritory.getCardOfTerritory().size() == 1) {
				rand = 0;
			}
			if(currentTerritory.getCardOfTerritory().size() == rand) {
				rand -=1;
			}
			if(rand >= 0 ) {		
				Object randomTerritory = currentTerritory.getCardOfTerritory().keySet().toArray()[rand];
			if (!wonTheCard) {
				cards.put(randomTerritory.toString(), fromPlayer);
				territoryCards.put(randomTerritory.toString().trim(),currentTerritory.getCardOfTerritory().get(randomTerritory));
				setWonTheCard(true);
			}
			currentTerritory.getCardOfTerritory().remove(randomTerritory);
		}

	}}
	/**
	 * This Method used to get message when attack is done
	 * @return messageOFAttacker message which need to be print in logs
	 */
	public String getMessageOFAttacker() {
		return messageOFAttacker;
	}
	/**
	 * This Method used to set message when attack is done
	 * @param messageOFAttacker message which need  to be print in logs
	 */
	public void setMessageOFAttacker(String messageOFAttacker) {
		this.messageOFAttacker = messageOFAttacker;
	}
	/**
	 * This Method used to get message when attack is done
	 * @return messageOfDefender message which need  to be print in logs
	 */
	public String getMessageOfDefender() {
		return messageOfDefender;
	}
	/**
	 * This Method used to set message when attack is done
	 * @param messageOfDefender message which need  to be print in logs
	 */
	public void setMessageOfDefender(String messageOfDefender) {
		this.messageOfDefender = messageOfDefender;
	}

	
	/**
	 * Fortification using Strategy Pattern 
	 */
	//changed doFortification to fortificationPhase
	@Override
	public void fortificationPhase(Territories currentTerritories, String fromTerritory, String toTerritory, int getArmySelect) {
		currentTerritories.updateTerritoryArmy(fromTerritory, getArmySelect, "DELETE");
		currentTerritories.updateTerritoryArmy(toTerritory, getArmySelect, "ADD");

	}

	/**
	 * Reinforcement  using Strategy Pattern 
	 */
	@Override
	public void reinforcementPhase(String currentPlayer, String currentTerritoryName, int army, Territories currentTerritory) {
		updateArmy(currentPlayer, army, "DELETE");
		currentTerritory.updateTerritoryArmy(currentTerritoryName, army, "ADD");	    
setReinforcementMsg("Armies Placed on "+currentTerritoryName +" : "+army);
//updated
	}

	/**
	 * generate Reinforcement Army using Strategy Pattern 
	 */
	@Override
	public void generateReinforcementArmy(String currentPlayer, Continent currentContinent) {
		int count = 0;
		setCurrentPhase("Reinforcement");
		Map<String, ArrayList<String>> tempData = getPlayerContinent().get(currentPlayer).getContinentOccupiedterritories();
		for (Entry<String, ArrayList<String>> entry : tempData.entrySet()) {
			if (!entry.getValue().isEmpty()) {
				count += entry.getValue().size();
			}
		}
		value = new Double(Math.floor(count / 3));
		updateArmy(currentPlayer, value.intValue() > 3 ? (value.intValue() + checkContinentAcquired(currentPlayer,currentContinent))   : (3 + checkContinentAcquired(currentPlayer, currentContinent)), "ADD");	    
	}
	/**
	 * This method add armies to current player if a particular continent is Acquired fully by player
	 * @param currentPlayer currentPlayer
	 * @param currentContinent currentContinent
	 * @return count if count is more than 0 else return 0.
	 */
	public int checkContinentAcquired(String currentPlayer, Continent currentContinent){
		int value = 0;// changed count to value
		Map<String, ArrayList<String>> tempData = getPlayerContinent(currentPlayer).getContinentOccupiedterritories();
		for (Entry<String, ArrayList<String>> entry : tempData.entrySet()) {
			if (entry.getValue().size() == currentContinent.getContTerrValue().get(entry.getKey())) {
				value += currentContinent.getValueOfContinent().get(entry.getKey());
			//	System.out.println("Continent Acquired - " + entry.getKey());
			}
		}
		return value > 0 ? value : 0;
	}

	/**
	 * This Method is used to Move army when attacker successfully capture territory
	 */
	@Override
	public void moveArmyAfterAttack(String currentPlayer, Territories currentTerritory, String fromTerritory, String toTerritory, int armies) {
		currentTerritory.updateTerritoryArmy(fromTerritory, armies, "DELETE");
		currentTerritory.updateTerritoryArmy(toTerritory, armies, "ADD");
	}


// copied exact have to change
	/**
	 * This method is used to perform reinforcement phase for BOT player
	 */
	@Override
	public void doBotReinforcement(String currentPlayer1, Territories currentTerritory) {
		String tempTerritory = "";
		int tempArmy = 0;
		String[] tempSplit;
		StringBuilder message = new StringBuilder();
		ArrayList<String> tempTerrList = new ArrayList<>();
		//changed if
		if (typeOfPlayer.get(currentPlayer1).equals("AGGRESSIVE")||typeOfPlayer.get(currentPlayer1).equals("agressive")) {
			for (Entry<String, String> entry : currentTerritory.getUserOfTerritory().entrySet()) {
				if (currentTerritory.getUserOfTerritory().get(entry.getKey()).equalsIgnoreCase(currentPlayer1)) {
					tempTerrList.add(currentTerritory.getTerritoryArmy().get(entry.getKey()) + "-" + entry.getKey());
				}
			}
			Collections.sort(tempTerrList);
			Collections.reverse(tempTerrList);
			if(!tempTerrList.isEmpty()) {
				int rand = 0;
				rand = new Random().nextInt(2)+1;
				rand = tempTerrList.size() == 1 ? 0 : (rand-1);
				tempSplit = tempTerrList.get(rand).split("-");
				reinforcementPhase(currentPlayer1, tempSplit[1], playerArmy.get(currentPlayer1), currentTerritory);
			}
		} 
		//changed else
		else if (typeOfPlayer.get(currentPlayer1).equals("BENEVOLENT")||typeOfPlayer.get(currentPlayer1).equals("benovolent")) {
			for (Entry<String, String> entry : currentTerritory.getUserOfTerritory().entrySet()) {
				if (currentTerritory.getUserOfTerritory().get(entry.getKey()).equalsIgnoreCase(currentPlayer1)) {
					tempTerrList.add(currentTerritory.getTerritoryArmy().get(entry.getKey()) + "-" + entry.getKey());
				}
			}
			Collections.sort(tempTerrList);
			if (!tempTerrList.isEmpty()) {
				tempSplit = tempTerrList.get(0).split("-");
				reinforcementPhase(currentPlayer1, tempSplit[1], playerArmy.get(currentPlayer1), currentTerritory);
			}
		}
		//changed the else if
		else if(getTypeOfPlayer().get(currentPlayer1).equals("RANDOM")||getTypeOfPlayer().get(currentPlayer1).equals("random")) {
			for (Entry<String, String> entry : currentTerritory.getUserOfTerritory().entrySet()) {
				if (currentTerritory.getUserOfTerritory().get(entry.getKey()).equalsIgnoreCase(currentPlayer1)) {
					tempTerrList.add(entry.getKey());
				}
			}
			int rand = 0;
			do{
				rand = new Random().nextInt(tempTerrList.size())+1;
				if(tempTerrList.size() == 1 || tempTerrList.size() == rand) {
					rand = 0;
				}
				reinforcementPhase(currentPlayer1, tempTerrList.get(rand), 1, currentTerritory);
				message.append(getReinforcementMsg()+"\n");
			} while(getPlayerArmy(currentPlayer1) >= 1);
			setReinforcementMsg(message.toString());
		} else if(getTypeOfPlayer().get(currentPlayer1).equalsIgnoreCase("CHEATER")) {
			for (Entry<String, String> entry : currentTerritory.getUserOfTerritory().entrySet()) {
				if (currentTerritory.getUserOfTerritory().get(entry.getKey()).equalsIgnoreCase(currentPlayer1)) {
					tempTerrList.add(entry.getKey());
				}
			}
			if(!tempTerrList.isEmpty()) {
				updateArmy(currentPlayer1, getPlayerArmy(currentPlayer1), "DELETE");
			}
			for(int i = 0;i< tempTerrList.size();i++) {
				currentTerritory.updateTerritoryArmy(tempTerrList.get(i), currentTerritory.getTerritoryArmy().get(tempTerrList.get(i)), "ADD");
				message.append("Armies are on  "+tempTerrList.get(i) +" : "+currentTerritory.getTerritoryArmy().get(tempTerrList.get(i))+"\n");
			}
			setReinforcementMsg(message.toString());
		}

	}
	//change karna hai
		/**
	 * The method is used to perform attack phase for BOT Player
	 */
	
	@Override
	public void attackOfBot(Territories currentTerritory, String fromTerritory, String toTerritory, int attackerDie,
			int defenderIsDead, String currentPlayer) {
		String type= typeOfPlayer.get(currentPlayer);
		//changed if 
		if(type.equals("AGGRESSIVE")|| type.equals("aggressive")) {
			ArrayList<String> tempTerrList = new ArrayList<>();
			for (Entry<String, String> entry : currentTerritory.getUserOfTerritory().entrySet()) {
				if (currentTerritory.getUserOfTerritory().get(entry.getKey()).equalsIgnoreCase(currentPlayer)) {
					tempTerrList.add(currentTerritory.getTerritoryArmy().get(entry.getKey()) + "-" + entry.getKey());
				}
			}
			
			
			if(!tempTerrList.isEmpty()) {
				Collections.sort(tempTerrList);
				Collections.reverse(tempTerrList);
				String[] tempSplit = tempTerrList.get(0).split("-");
				String tempdefenderTerr="";
				for(int i =0;i < currentTerritory.getNeighborTerritories().get(tempSplit[1]).size();i++) {
					if(!currentTerritory.getUserOfTerritory().get(currentTerritory.getNeighborTerritories().get(tempSplit[1]).get(i)).equalsIgnoreCase(currentPlayer)) {
						tempdefenderTerr = currentTerritory.getNeighborTerritories().get(tempSplit[1]).get(i);
						if(currentTerritory.getTerritoryArmy().get(tempdefenderTerr) >= 1 ) {
							break;
						}
					}
				}
				if(StringUtils.isNotBlank(tempdefenderTerr)) {
					int attackerDice = currentTerritory.getTerritoryArmy().get(tempSplit[1]) > 4 ? 3 : (currentTerritory.getTerritoryArmy().get(tempSplit[1]) - 1);
					int defenderDice = currentTerritory.getTerritoryArmy().get(tempdefenderTerr) > 3 ? 3 : currentTerritory.getTerritoryArmy().get(tempdefenderTerr);
					String message1 = "Before the  Attack is performed \n " + tempSplit[1] +" : "+currentTerritory.getTerritoryArmy().get(tempSplit[1]) +"\n"+tempdefenderTerr+" : "+currentTerritory.getTerritoryArmy().get(tempdefenderTerr)+"\n";
					doAttack(currentTerritory,tempSplit[1] , tempdefenderTerr, attackerDice, defenderDice);
					String message2 = "After the Attack is performed \n " + tempSplit[1] +" : "+currentTerritory.getTerritoryArmy().get(tempSplit[1]) +"\n"+tempdefenderTerr+" : "+currentTerritory.getTerritoryArmy().get(tempdefenderTerr)+"\n";
					setMessageOFAttacker(message1 + "\n" +message2);
				} else {
					setMessageOFAttacker("Nothing to Attack from Strongest Territory");
				}
			}
		} else if(type.equalsIgnoreCase("RANDOM")) {
			String message1 = "Before Attack \n " + fromTerritory +" : "+currentTerritory.getTerritoryArmy().get(fromTerritory) +"\n"+toTerritory+" : "+currentTerritory.getTerritoryArmy().get(toTerritory)+"\n";
			doAttack(currentTerritory, fromTerritory, toTerritory, attackerDie, defenderIsDead);
			String message2 = "After Attack \n " + fromTerritory +" : "+currentTerritory.getTerritoryArmy().get(fromTerritory) +"\n"+toTerritory+" : "+currentTerritory.getTerritoryArmy().get(toTerritory)+"\n";
			if(wonTheAttack) {
				currentTerritory.updateTerritoryArmy(fromTerritory, 1, "DELETE");
				currentTerritory.updateTerritoryArmy(toTerritory, 1, "ADD");    
			}
			setMessageOFAttacker(message1 + "\n" +message2);
		} else 	if(type.equalsIgnoreCase("CHEATER")) {
			StringBuilder message3 = new StringBuilder();
			ArrayList<String> tempArray = new ArrayList<>();
			for(Entry<String, String> entry : currentTerritory.getUserOfTerritory().entrySet()) {
				if(entry.getValue().equalsIgnoreCase(fromTerritory)) {
					tempArray.add(entry.getKey());
				}
			}
			if(!tempArray.isEmpty()) {
				boolean tempFlag = false;
				for(int i = 0 ;i< tempArray.size();i++) {
					String fromPlayer = fromTerritory;
					for(int j=0;j<currentTerritory.getNeighborTerritories().get(tempArray.get(i)).size();j++) {
						String tempTerr = currentTerritory.getNeighborTerritories().get(tempArray.get(i)).get(j);
						String toPlayer = currentTerritory.getUserOfTerritory().get(tempTerr);
						String fromContinentPlayer = "";
						String toContinentPlayer = "";
						if(!fromPlayer.equalsIgnoreCase(toPlayer)) {
							for(Entry<String, ArrayList<String>> entry : playerOfContinent.get(fromPlayer).getContinentOccupiedterritories().entrySet()) {
								fromContinentPlayer = entry.getValue().contains(tempArray.get(i)) ? entry.getKey() : fromContinentPlayer;
							}
							for(Entry<String, ArrayList<String>> entry : playerOfContinent.get(toPlayer).getContinentOccupiedterritories().entrySet()) {
								toContinentPlayer = entry.getValue().contains(tempTerr) ? entry.getKey() : toContinentPlayer;
							}
							playerOfContinent.get(toPlayer).getContinentOccupiedterritories().get(toContinentPlayer).remove(tempTerr);
							playerOfContinent.get(fromPlayer).getContinentOccupiedterritories().get(fromContinentPlayer).add(tempTerr);
							currentTerritory.updateTerritoryUser(currentTerritory.getUserOfTerritory().get(tempArray.get(i)),tempTerr);
							message3.append("Cheater Player encountered : " + tempTerr + " Territory\n");
							tempFlag = true;
							//changed string
						}
					}
				}
				setMessageOFAttacker(message3.toString());
				if(tempFlag && currentTerritory.getCardOfTerritory().size() > 0) {
					int rand = 0;

					rand = new Random().nextInt(currentTerritory.getCardOfTerritory().keySet().toArray().length)+1;
					if(currentTerritory.getCardOfTerritory().size() == 1) {
						rand = 0;
					}
					if(currentTerritory.getCardOfTerritory().size() == rand) {
						rand -=1;
					}
					Object randomTerritory = currentTerritory.getCardOfTerritory().keySet().toArray()[rand];
					if (!wonTheCard) {
						cards.put(randomTerritory.toString(), fromTerritory);
						territoryCards.put(randomTerritory.toString().trim(),currentTerritory.getCardOfTerritory().get(randomTerritory));
						setWonTheCard(true);
					}
					currentTerritory.getCardOfTerritory().remove(randomTerritory);
				}
			}
		}

	}
	/**
	 * The method is used to do the fortification of Bot Player
	 */
	@Override
	public void doBotForitification(String currentPlayer, Territories currentTerritory) {
		ArrayList<String> tempTerritory = new ArrayList<>();
		String message ="";
		if(getTypeOfPlayer().get(currentPlayer).equalsIgnoreCase("AGGRESSIVE") || getTypeOfPlayer().get(currentPlayer).equalsIgnoreCase("BENEVOLENT")) {
			for(Entry<String, String> entry : currentTerritory.getUserOfTerritory().entrySet()) {
				if(entry.getValue().equalsIgnoreCase(currentPlayer)) {
					tempTerritory.add(currentTerritory.getTerritoryArmy().get(entry.getKey())+"-"+entry.getKey());
				}
			}
			Collections.sort(tempTerritory);
			if(getTypeOfPlayer().get(currentPlayer).equalsIgnoreCase("AGGRESSIVE")) {
				Collections.reverse(tempTerritory);    
			}
			if(tempTerritory.size() >= 1) {
				String[] splitTerr = tempTerritory.get(0).split("-");
				for(int i=0;i<currentTerritory.getNeighborTerritories().get(splitTerr[1]).size();i++) {
					String temp = currentTerritory.getNeighborTerritories().get(splitTerr[1]).get(i);
					if(currentTerritory.getUserOfTerritory().get(temp).equalsIgnoreCase(currentPlayer) && currentTerritory.getTerritoryArmy().get(temp) > 1) {
						message += " Before Fortification \n"+splitTerr[1]+ " : " + currentTerritory.getTerritoryArmy().get(splitTerr[1])+"\n" + temp + " : "+currentTerritory.getTerritoryArmy().get(temp)+"\n"  ;
						fortificationPhase(currentTerritory,temp, splitTerr[1], currentTerritory.getTerritoryArmy().get(temp)-1); 
						message += " After Fortification \n"+splitTerr[1]+ " : " + currentTerritory.getTerritoryArmy().get(splitTerr[1])+"\n" + temp + " : "+currentTerritory.getTerritoryArmy().get(temp)+"\n"  ;
						break;
					}
				}
				setFortificationMsg(message);
			} else {
				setFortificationMsg("No Armies to Move");
			}

		} else if(getTypeOfPlayer().get(currentPlayer).equalsIgnoreCase("RANDOM")) {
			for(Entry<String, String> entry : currentTerritory.getUserOfTerritory().entrySet()) {
				if(entry.getValue().equalsIgnoreCase(currentPlayer)) {
					tempTerritory.add(entry.getKey());
				}
			}
			int rand = 0;
			rand = new Random().nextInt(tempTerritory.size())+1;
			if(tempTerritory.size() == 1 || rand == tempTerritory.size()) {
				rand = 0;
			}
			if(tempTerritory.size() >= 1) {
				for(int i=0;i<currentTerritory.getNeighborTerritories().get(tempTerritory.get(rand)).size();i++) {
					String temp = currentTerritory.getNeighborTerritories().get(tempTerritory.get(rand)).get(i);
					if(currentTerritory.getUserOfTerritory().get(temp).equalsIgnoreCase(currentPlayer) && currentTerritory.getTerritoryArmy().get(temp) > 1) {
						message += " Before Fortification \n"+tempTerritory.get(rand)+ " : " + currentTerritory.getTerritoryArmy().get(tempTerritory.get(rand))+"\n" + temp + " : "+currentTerritory.getTerritoryArmy().get(temp)+"\n"  ;
						fortificationPhase(currentTerritory,temp, tempTerritory.get(rand), currentTerritory.getTerritoryArmy().get(temp)-1); 
						message += " After Fortification \n"+tempTerritory.get(rand)+ " : " + currentTerritory.getTerritoryArmy().get(tempTerritory.get(rand))+"\n" + temp + " : "+currentTerritory.getTerritoryArmy().get(temp)+"\n"  ;
						break;
					}
				}
				setFortificationMsg(message);
			} else {
				setFortificationMsg("No Armies to Move");
			}
		} else if (getTypeOfPlayer().get(currentPlayer).equalsIgnoreCase("CHEATER")) {
			boolean tempFlag = false;
			for(Entry<String, String> entry : currentTerritory.getUserOfTerritory().entrySet()) {
				if(entry.getValue().equalsIgnoreCase(currentPlayer)) {
					tempTerritory.add(entry.getKey());
				}
			}
			for(int i =0;i< tempTerritory.size();i++) {
				for(int j=0;j<currentTerritory.getNeighborTerritories().get(tempTerritory.get(i)).size();j++) {
					if(!currentTerritory.getUserOfTerritory().get(currentTerritory.getNeighborTerritories().get(tempTerritory.get(i)).get(j)).equalsIgnoreCase(currentPlayer)) {
						tempFlag = true;
						currentTerritory.updateTerritoryArmy(tempTerritory.get(i), currentTerritory.getTerritoryArmy().get(tempTerritory.get(i)), "ADD");
						message+= currentPlayer +" has double armies in " +tempTerritory.get(i)+ ". Now Current Armies are "+currentTerritory.getTerritoryArmy().get(tempTerritory.get(i))+"\n"; 
						break;
					}
					if(tempFlag) {
						break;
					}
				}
			}
			setFortificationMsg(message);
		}
	}

}



