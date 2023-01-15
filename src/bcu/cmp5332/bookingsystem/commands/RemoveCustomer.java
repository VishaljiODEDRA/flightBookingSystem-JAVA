package bcu.cmp5332.bookingsystem.commands;



import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Customer;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

/**
 * 
 * @author VishaljiODEDRA
 * @author DhananjayTiwari
 */

public class RemoveCustomer implements Command{
	
	private int customerId;
	
	/**
	 * 
	 * @param customerId, takes Customer ID  to filter customer from the list of all customers
	 */
	public RemoveCustomer(int customerId) {
		this.customerId=customerId;
	}
	
	/**
	 * Executes the command to remove (hide) a customer from the flight booking system.
	 *
	 * @param flightBookingSystem the flight booking system
	 * @throws FlightBookingSystemException if there is an error removing the customer
	 */

	@Override
	public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException {
		// TODO Auto-generated method stub
		Customer customer = flightBookingSystem.getCustomerByID(customerId);
		if (customer.getStatus() == true) {
			throw new FlightBookingSystemException("No any Customer with Customer ID " + customer.getId()+ ".");
		}
		else {
		customer.setStatus(true);
		System.out.println("Customer removed Successsfully with Customer ID " + customer.getId()+ ".");
		}
	}
}
