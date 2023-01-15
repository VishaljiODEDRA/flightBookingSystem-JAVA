package bcu.cmp5332.bookingsystem.commands;


import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;

import bcu.cmp5332.bookingsystem.model.Customer;
import bcu.cmp5332.bookingsystem.model.Flight;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

/**
 * 
 * @author VishaljiODEDRA
 * @author DhananjayTiwari
 */

public class CancelBooking implements Command {
	private int customerId;
	private int flightId;

	 /**
		 * 
		 * This Method adds a customer to FlightBookingSystem and it takes 3 parameters
		 * @param customerId, takes customer ID as int
		 * @param fligthId, takes flight ID as int
		 * 
		 */
	
	public CancelBooking(int customerId,int flightId) {
		this.customerId=customerId;
		this.flightId=flightId;
		
	}
	
	/**
	 * Cancels a booking for a customer on a flight.
	 *
	 * @param flightBookingSystem the flight booking system
	 * @throws FlightBookingSystemException if an error occurs while cancelling the booking
	 */
	@Override
	public void execute(FlightBookingSystem flightBookingSystem)throws FlightBookingSystemException{
		Customer customer=flightBookingSystem.getCustomerByID(customerId);
		Flight flight=flightBookingSystem.getFlightByID(flightId);
		flight.removePassenger(customer);
		customer.cancelBookingForFlight(flight);
		System.out.println("Booking Successsfully cancelled for Customer ID " + customer.getId() + " with Flight Id " + flight.getId() + ".");
	}
	

}
