package com.aashita.remote.invoker;

import java.io.Serializable;

public class Booking implements Serializable {

	private static final long serialVersionUID = 3763884006580209519L;
	
	private String bookingCode;
 
    public Booking(String codestr) {
    	bookingCode = codestr;
    }

	@Override public String toString() {
        return String.format("Ride confirmed: code '%s'.", bookingCode);
    }
 
    // standard getters/setters and a constructor
}