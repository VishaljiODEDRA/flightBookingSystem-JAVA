package bcu.cmp5332.bookingsystem.commands;

import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

/**
 * 
 * @author VishaljiODEDRA
 * @author DhananjayTiwari
 */
public class Help implements Command {
	
	/**
	 * Prints the list of available commands.
	 *
	 * @param flightBookingSystem the flight booking system
	 */
    @Override
    public void execute(FlightBookingSystem flightBookingSystem) {
        System.out.println(Command.HELP_MESSAGE);
    }
}
