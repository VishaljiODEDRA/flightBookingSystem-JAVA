package bcu.cmp5332.bookingsystem.commands;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Customer;

import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

/**
 * 
 * @author VishaljiODEDRA
 * @author DhananjayTiwari
 */
public class ShowCustomer implements Command {

	private int cusId;
	
	/**
	 * 
	 * @param cusId, takes customer ID to see details for particular customer
	 */
	public ShowCustomer(int cusId) {
		this.cusId = cusId;
	}
	
	/**
	 * This command is used to display the details of a specific customer.
	 * It takes a customer ID as input, and prints the details of the corresponding customer.
	 * If the customer ID is invalid, it throws a {@code FlightBookingSystemException}.
	 * @param flightBookingSystem the flight booking system object
	 * @throws FlightBookingSystemException if the customer ID is invalid
	 */
	
	@Override
	public void execute(FlightBookingSystem flightBookingSystem)throws FlightBookingSystemException{
		 Customer customer = flightBookingSystem.getCustomerByID(cusId);
		 if(customer.getStatus()==false) {
			 System.out.println(customer.getDetailsLong());
		 }
	}
}
