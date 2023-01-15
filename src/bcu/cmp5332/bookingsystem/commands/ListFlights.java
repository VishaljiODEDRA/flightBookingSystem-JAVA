package bcu.cmp5332.bookingsystem.commands;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Flight;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

import java.time.LocalDate;
import java.util.List;

/**
 * 
 * @author VishaljiODEDRA
 * @author DhananjayTiwari
 */

public class ListFlights implements Command {
	LocalDate today=LocalDate.now();
	
	/**
	 * Lists all flights in the flight booking system.
	 *
	 * @param flightBookingSystem the flight booking system
	 * @throws FlightBookingSystemException if an error occurs while listing the customers
	 */
	
    @Override
    public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException {
        List<Flight> flights = flightBookingSystem.getFlights();
        int size=0;
        for (Flight flight : flights) {
        	if(flight.getStatus()==false) {
        		if(flight.getDepartureDate().isAfter(today)||flight.getDepartureDate().isEqual(today)) {
           		 	System.out.println(flight.getDetailsShort());
                    size+=1;
        		}
        	}
        } 
        System.out.println(size+ " flight(s)");
    }
}
