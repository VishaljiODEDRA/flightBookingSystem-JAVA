package bcu.cmp5332.bookingsystem.data;



import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;

import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author VishaljiODEDRA
 * @author DhananjayTiwari
 */
	
/**
 * 
 * This class is responsible for managing the loading and storing of data for the {@link FlightBookingSystem}.
 * It maintains a list of {@link DataManager} objects, which handle the loading and storing of data for specific
 * data types (e.g. flights, customers, bookings). The data managers are added to the list in the static block of
 * this class, and the {@link #load()} and {@link #store(FlightBookingSystem)} methods delegate the loading and
 * storing of data to the corresponding methods in the data managers.
*/
public class FlightBookingSystemData {
    
    private static final List<DataManager> dataManagers = new ArrayList<>();
    
    // runs only once when the object gets loaded to memory
    static {
        dataManagers.add(new FlightDataManager());
        
        /* Uncomment the two lines below when the implementation of their 
        loadData() and storeData() methods is complete */
        dataManagers.add(new CustomerDataManager());
        dataManagers.add(new BookingDataManager());
    }
    
    /**
     * 
     * Loads the data for the {@link FlightBookingSystem} from the data resource files.
     * @return a {@link FlightBookingSystem} object with data loaded from the resource files
     * @throws FlightBookingSystemException if there is an error while loading the data
     * @throws IOException if there is an error while reading from the resource files
     */
    public static FlightBookingSystem load() throws FlightBookingSystemException, IOException {

        FlightBookingSystem fbs = new FlightBookingSystem();
        for (DataManager dm : dataManagers) {
            dm.loadData(fbs);
        }
        return fbs;
    }
    
    /**
     * 
     * Stores the data from the {@link FlightBookingSystem} to data resource files.
     * @param fbs The {@link FlightBookingSystem} for which data needs to be stored
     * @throws IOException if there is an error while reading from the resource files
     */

    public static void store(FlightBookingSystem fbs) throws IOException {

        for (DataManager dm : dataManagers) {
            dm.storeData(fbs);
        }
    }
    
}
