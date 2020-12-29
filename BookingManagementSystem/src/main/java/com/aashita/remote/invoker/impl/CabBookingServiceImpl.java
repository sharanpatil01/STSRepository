package com.aashita.remote.invoker.impl;

import java.util.UUID;

import com.aashita.remote.invoker.Booking;
import com.aashita.remote.invoker.BookingException;
import com.aashita.remote.invoker.CabBookingService;

public class CabBookingServiceImpl implements CabBookingService {


	@Override
	public Booking bookRide(String pickUpLocation) throws BookingException {
		if (Math.random() < 0.3)
			throw new BookingException("Cab unavailable");
		
		return new Booking(UUID.randomUUID().toString());
	}
}