package bcu.cmp5332.bookingsystem.commands;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Booking;
import bcu.cmp5332.bookingsystem.model.Customer;
import bcu.cmp5332.bookingsystem.model.Flight;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

/**
 * 
 * @author VishaljiODEDRA
 * @author DhananjayTiwari
 */
public class RemoveFlight implements Command{
	private int flightId;
	
	/**
	 * 
	 * @param flightId, takes flight ID to filter a particular flight from all flights
	 */
	public RemoveFlight(int flightId) {
		this.flightId=flightId;
		
	}
	
	/**
	 * This method is used to remove (hide) a flight from the flight booking system.
	 * 
	 * @param flightBookingSystem the flight booking system object
	 * @throws FlightBookingSystemException if an error occurs while removing the flight
	*/
	
	@Override
	public void execute(FlightBookingSystem flightBookingSystem)throws FlightBookingSystemException{
		Flight flight=flightBookingSystem.getFlightByID(flightId);
		if (flight.getStatus() == true) {
			throw new FlightBookingSystemException("No any Flight with Flight ID " + flight.getId()+ ".");
		}
		else {
			flight.setStatus(true);
			System.out.println("Flight Removed Successsfully with Flight ID " + flight.getId() + ".");
		}
	}

}
