package bcu.cmp5332.bookingsystem.data;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;
import java.io.IOException;

/**
 * 
 * @author VishaljiODEDRA
 * @author DhananjayTiwari
 */

/**
 * 
 * The DataManager interface defines the behavior for classes that handle data loading and storing for a {@link FlightBookingSystem}.
 * Implementing classes should provide methods for loading data from a resource and storing data to a resource.
 */

public interface DataManager {
    
    public static final String SEPARATOR = "::";
    
    /**
     * This interface defines the methods for loading and storing data for the Flight Booking System.
     * Implementing classes should be able to load data from a resource (such as a file) and store data to a resource.
     */
    public void loadData(FlightBookingSystem fbs) throws IOException, FlightBookingSystemException;
    public void storeData(FlightBookingSystem fbs) throws IOException;
    
}
