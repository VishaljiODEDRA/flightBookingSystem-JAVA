package bcu.cmp5332.bookingsystem.commands;



import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Flight;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

/**
 * 
 * @author VishaljiODEDRA
 * @author DhananjayTiwari
 */

public class ShowFlight implements Command {
	
	private int flightId;
	
	/**
	 * 
	 * @param flightId, takes Flight ID for getting details of particular flight
	 */
	
	public ShowFlight(int flightId) {
		this.flightId = flightId;
	}
	
	/**
	 * The execute method of the ShowFlight command prints the detailed information of the specified flight.
	 * It takes a flight ID as input, and prints the details of the corresponding flight.
	 * If the flight ID is invalid, it throws a {@code FlightBookingSystemException}.
	 * @param flightBookingSystem the FlightBookingSystem object to retrieve the flight from
	 * @throws FlightBookingSystemException if the flight with the specified id does not exist in the system
	 */
	
	@Override
	public void execute(FlightBookingSystem flightBookingSystem)throws FlightBookingSystemException{
		 Flight flight = flightBookingSystem.getFlightByID(flightId);
		 if(flight.getStatus()==false) {
			 System.out.println(flight.getDetailsLong());
		 }
	}
}
