package bcu.cmp5332.bookingsystem.commands;

import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;
import bcu.cmp5332.bookingsystem.gui.MainWindow;
import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;


/**
 * 
 * @author VishaljiODEDRA
 * @author DhananjayTiwari
 */
public class LoadGUI implements Command {
	
	/**
	 * Executes the command to launch the GUI version of the flight booking system.
	 *
	 * @param flightBookingSystem the flight booking system
	 */
	
    @Override
    public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException {
        new MainWindow(flightBookingSystem);
    }
    
}
