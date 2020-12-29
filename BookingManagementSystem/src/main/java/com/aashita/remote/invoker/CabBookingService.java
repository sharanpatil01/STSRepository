package com.aashita.remote.invoker;


public interface CabBookingService {
    Booking bookRide(String pickUpLocation) throws BookingException;
}
