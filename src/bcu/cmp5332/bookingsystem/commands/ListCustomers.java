
package bcu.cmp5332.bookingsystem.commands;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;

import bcu.cmp5332.bookingsystem.model.Customer;

import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

import java.util.List;


/**
 * 
 * @author VishaljiODEDRA
 * @author DhananjayTiwari
 */
public class ListCustomers implements Command {
	
	/**
	 * Lists all customers in the flight booking system.
	 *
	 * @param flightBookingSystem the flight booking system
	 * @throws FlightBookingSystemException if an error occurs while listing the customers
	 */
	@Override
	public void execute(FlightBookingSystem flightBookingSystem)throws FlightBookingSystemException{
			int size = 0;
		 List<Customer> customers = flightBookingSystem.getCustomers();
	        for (Customer customer : customers) {
	            if(customer.getStatus()==false) {
	            	System.out.println(customer.getDetailsShort());
	            	size +=1;
	            }
	        }
	        System.out.println(size + " passenger(s)");
	}

}
