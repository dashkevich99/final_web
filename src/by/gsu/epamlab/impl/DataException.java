package by.gsu.epamlab.impl;

public class DataException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	public DataException() {}
	public DataException(String login) {
    	super("Wrong " + login);
    }
}
