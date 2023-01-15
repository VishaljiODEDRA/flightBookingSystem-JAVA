package bcu.cmp5332.bookingsystem.main;

/**
 * 
 * @author VishaljiODEDRA
 * @author DhananjayTiwari
 */

/**
 * FlightBookingSystemException extends {@link Exception} class and is a custom exception
 * that is used to notify the user about errors or invalid commands.
 * 
 */

/**
 * Exception thrown by the Flight Booking System.
 * 
 * This exception represents an error or problem that occurs within the Flight Booking System,
 * such as an invalid operation or an invalid input. It is used to signal to the calling code that
 * something has gone wrong, and provides a message describing the nature of the problem.
 * 
 */
public class FlightBookingSystemException extends Exception {

	/**
     * Constructs a new FlightBookingSystemException with the specified detail message.
     * 
     * @param message the detail message
     */
    public FlightBookingSystemException(String message) {
        super(message);
    }
}
