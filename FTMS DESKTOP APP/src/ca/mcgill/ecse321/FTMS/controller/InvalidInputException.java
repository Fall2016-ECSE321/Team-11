package ca.mcgill.ecse321.FTMS.controller;
/**
 * Helper class to throw exception in the system
 * @author Group 11
 *
 */
public class InvalidInputException extends Exception {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2347585728015236376L;

	public InvalidInputException (String errorMessage){
		super(errorMessage);
	}

}
