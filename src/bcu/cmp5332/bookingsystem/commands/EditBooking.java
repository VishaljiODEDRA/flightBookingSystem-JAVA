package bcu.cmp5332.bookingsystem.commands;

import java.time.LocalDate;


import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.*;
/**
 * 
 * @author VishaljiODEDRA
 * @author DhananjayTiwari
 */

public class EditBooking implements Command {
	private int bookingId;
	private int flightId;
	private LocalDate today=LocalDate.now();
	
	/**
	 * 
	 * @param bookingId, takes Booking Id as int
	 * @param flightId, takes Flight Id as int
	 */
	
	public EditBooking(int bookingId,int flightId) {
		this.bookingId=bookingId;
		this.flightId=flightId;	
		
		
	}
	
	/**
	 * Updates a booking for a customer on a flight.
	 * it Uses 'for loop' and 'if' condition to get particular booking for the customer
	 *
	 * @param flightBookingSystem, the flight booking system
	 * @throws FlightBookingSystemException if an error occurs while updating the booking
	 */
	
	@Override
	public void execute(FlightBookingSystem flightBookingSystem)throws FlightBookingSystemException{
		int oldFlightId=0;
		int customerId = flightBookingSystem.getCustomerByBookingId(bookingId);
		Customer customer=flightBookingSystem.getCustomerByID(customerId);
		Flight newFlight=flightBookingSystem.getFlightByID(flightId);
		
		for(Booking b:customer.getBookings()){
			if(b.getId()==bookingId){
				oldFlightId=b.getFlight().getId();
				b.setFlight(newFlight);
				b.setBookingDate(today);
				break;
			}

		}
		Flight oldFlight=flightBookingSystem.getFlightByID(oldFlightId);
		oldFlight.removePassenger(customer);
		
		newFlight.addPassenger(customer);
		
		
		System.out.println("Booking Updated Successsfully for Customer ID " + customer.getId()+ " with Flight ID " + newFlight.getId() + ".");
	}
	
}
