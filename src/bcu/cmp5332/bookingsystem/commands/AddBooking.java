package bcu.cmp5332.bookingsystem.commands;
import bcu.cmp5332.bookingsystem.model.*;

import bcu.cmp5332.bookingsystem.main.*;
import java.time.LocalDate;


/**
 * 
 * @author VishaljiODEDRA
 * @author DhananjayTiwari
 */


public class AddBooking implements Command{
	private int customerId;
	private int flightId;
	private LocalDate bookingDate;

	/**
	 * 
	 * @param customerId, takes Customer Id as int
	 * @param flightId, takes Flight Id as int
	 * @param bookingDate, takes Booking date as current date in form of LocalDate
	 */
	public AddBooking(int customerId,int flightId,LocalDate bookingDate) {
		this.customerId=customerId;
		this.flightId=flightId;
		this.bookingDate=bookingDate;
		
	}
	
	/**
	 * Executes the command to add a booking for a customer on a flight.
	 *
	 * @param flightBookingSystem the flight booking system
	 * @throws FlightBookingSystemException if the customer or flight is not found
	 */
	
	@Override
	public void execute(FlightBookingSystem flightBookingSystem)throws FlightBookingSystemException{
		int maxId = 0;
		for (Customer c : flightBookingSystem.getCustomers()) {
		    if (c.getBookings().size() > 0) {
		    	maxId = maxId + c.getBookings().size();
		    	
		    }
		}
		Customer customer=flightBookingSystem.getCustomerByID(customerId);
		Flight flight=flightBookingSystem.getFlightByID(flightId);
		Booking booking=new Booking(++maxId,customer,flight,bookingDate);
		
		customer.addBooking(booking);
		flight.addPassenger(customer);
		System.out.println("Booking Issued Successsfully with Booking Id " + booking.getId() + " to Customer Id " + customer.getId()+ " for Flight Id " + flight.getId() + ".");
	}

}
