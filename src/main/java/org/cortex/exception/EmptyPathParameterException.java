package org.cortex.exception;

/**
 * @author shiyi
 *
 */
public class EmptyPathParameterException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EmptyPathParameterException() {
		super();
	}

	public EmptyPathParameterException(String message) {
		super(message);
	}
}
