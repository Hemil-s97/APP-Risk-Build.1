package observer;

import java.util.ArrayList;
import java.util.List;

/**
 * This class used to implement observer pattern
 */
public class Subject {
	private List<Observer> listOfObservers = new ArrayList<>();
	private String state;
	private boolean messageFlag;
	private boolean flag;
	private String phase;
	private String attackMsg;
	private String tradeInMsg;
	private String fortificationMsg;
	private String message;
	
	/**
	 * get method for messageFlag
	 * @return messageFlag message flag boolean
	 */
	public boolean getMessageFlag() {
		return messageFlag;
	}

	/**
	 * set method for message flag
	 * @param messageFlag message flag boolean
	 */
	public void setMessageFlag(boolean messageFlag) {
		this.messageFlag = messageFlag;
	}
	
	/**
	 * get method for trade message
	 * @return tradeInMsg trade message 
	 */
	public String getTradeInMsg() {
		return tradeInMsg;
	}

	/**
	 * set method for trade message
	 * @param tradeInMsg trade message
	 */
	public void setTradeInMsg(String tradeInMsg) {
		this.tradeInMsg = tradeInMsg;
		notifyForChangeInTradein();
	}

	/**
	 * get method for attack message
	 * @return attackmsg attack message
	 */
	public String getAttackMessage() {
		return attackMsg;
	}

	/**
	 * set method for attack message 
	 * @param attackMsg attack message
	 */
	public void setAttackMessage(String attackMsg) {
		this.attackMsg = attackMsg;
		notifyForChangeInAttack();
	}
	
	/**
	 * notify observers about fortification changes
	 */
	public void notifyForChangeInFortification() {
		for (Observer observer : listOfObservers) {
			observer.fortificationUpdate();
		}
	}

	/**
	 *  get method for state
	 * @return state state
	 */
	public String getState() {
		return state;
	}
	
	/**
	 *  get method for phase
	 * @return phase phase
	 */
	public String getPhase() {
		return phase;
	}

	/**
	 *  set method for phase
	 * @param phase phase
	 * @param message message
	 */
	public void setPhase(String phase, String message) {
		this.phase = phase;
		this.message = message;
	}

	/**
	 *  set method for state
	 * @param state state
	 * @param flag flag boolean
	 */
	public void setState(String state, boolean flag) {
		this.state = state;
		this.flag = flag;
		notifyAllObservers();
	}

	/**
	 *  get method for message
	 * @return message message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 *  get method for message
	 * @param flag flag
	 * @param newMessage message
	 */
	public void setMessage(String newMessage, boolean flag) {
		message = /* message + */ newMessage + "\n";
		messageFlag = flag;
	}
	
	/**
	 * notify observers about card trade changes
	 */
	public void notifyForChangeInTradein() {
		for (Observer observer : listOfObservers) {
			observer.tradeInCardUpdate();
		}
	}
	
	/**
	 * observer registration
	 * @param observer observer name
	 */
	public void attach(Observer observer) {
		listOfObservers.add(observer);
	}

	/**
	 *  get method for flag
	 * @return flag boolean value
	 */
	public boolean getFlag() {
		return flag;
	}

	/**
	 * notify all registered observers
	 * 
	 */
	public void notifyAllObservers() {
		for (Observer observer : listOfObservers) {
			observer.update();
		}
	}

	/**
	 * notify observers about attack changes
	 */
	public void notifyForChangeInAttack() {
		for (Observer observer : listOfObservers) {
			observer.attackUpdate();
		}
	}

	/**
	 *  get method for fortification message
	 * @return fortificationmsg message 
	 */
	public String getFortificationMessage() {
		return fortificationMsg;
	}

	/**
	 * set method for fortification message
	 * @param fortificationMsg message
	 */
	public void setFortificationMessage(String fortificationMsg) {
		this.fortificationMsg = fortificationMsg;
		notifyForChangeInFortification();
	}
}
