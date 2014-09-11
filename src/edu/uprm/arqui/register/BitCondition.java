package edu.uprm.arqui.register;

/**
 * Created by eduardobreijo on 9/10/14.
 */
public class BitCondition {

	private Boolean bitCondition;
	private static BitCondition instance = null;
	
	/**
	 * Private constructor that sets the condition bit to false
	 */
	private BitCondition() {
		bitCondition = false;
	}
	
	/**
     * Returns an instance of the condition bit,
     * if the instance does not exists, creates one.
     * 
     * @return
     */
	public static BitCondition getInstance() {
		if(instance == null) {
			instance = new BitCondition();
		}
		return instance;
	}
	
	/**
	 * Set the condition bit
	 * 
	 * @param value a boolean
	 */
	public void setBitCondtion(Boolean value) {
		bitCondition = value;
	}
	
	/**
	 * Get the condition bit
	 * 
	 * @return boolean value
	 */
	public Boolean getBitCondition() {
		return bitCondition;
	}
}
