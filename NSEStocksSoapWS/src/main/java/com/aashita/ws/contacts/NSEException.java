/**
 * 
 */
package com.aashita.ws.contacts;

/**
 * @author SHARANABASAPPAPATIL
 *
 */
public class NSEException extends Exception {

	/**
	 * 
	 */
	public NSEException() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 */
	public NSEException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cause
	 */
	public NSEException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 */
	public NSEException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public NSEException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String getMessage() {
		return super.getMessage()
				+ "\n\n NSEStockException Occured!!";
	}

}
