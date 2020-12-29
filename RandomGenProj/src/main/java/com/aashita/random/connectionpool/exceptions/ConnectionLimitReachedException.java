package com.aashita.random.connectionpool.exceptions;

/**
 * @author x153826
 * Created on Oct 29, 2010
 */
public class ConnectionLimitReachedException extends Exception {

	private static final long serialVersionUID = 1L;

	public ConnectionLimitReachedException (String msg) {
		super(msg);
	}
}
