package observer;

import observer.Subject;

/**
 * This is observer class
 */
public abstract class Observer {
	protected Subject observerSubject;

	/**
	 * This method update new changes.
	 * 
	 */
	public abstract void update();
	
	/**
	 * This method update card changes.
	 *  
	 */
	public abstract void tradeInCardUpdate();
	
	/**
	 * This method update attack changes.
	 *  
	 */
	public abstract void attackUpdate();

	/**
	 * This method update fortification changes.
	 *  
	 */
	public abstract void fortificationUpdate();
}
