package bcu.cmp5332.bookingsystem.data;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Booking;
import bcu.cmp5332.bookingsystem.model.Customer;
import bcu.cmp5332.bookingsystem.model.Flight;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.Scanner;

/**
 * 
 * @author VishaljiODEDRA
 * @author DhananjayTiwari
 */
public class BookingDataManager implements DataManager {
    
    public final String RESOURCE = "./resources/data/bookings.txt";

    /**
     * This method is used to load data from a file into the {@link FlightBookingSystem} object. 
     * It reads each line from the file and creates a new {@link Booking} object using the data from the line. 
     * The booking is then added to the corresponding {@link Customer} and {@link Flight} objects.
     * @param fbs, the {@link FlightBookingSystem} object to which the data is to be loaded
     * @throws IOException if an error occurs while reading from the file
     * @throws FlightBookingSystemException if an error occurs while parsing the data from the file or 
     		* while adding the booking to the {@link Customer} and {@link Flight} objects
     */
    
    @Override
    public void loadData(FlightBookingSystem fbs) throws IOException, FlightBookingSystemException {
        // TODO: implementation here
    	try (Scanner sc1 = new Scanner(new File(RESOURCE))){
    		int line_id = 1;
    		while (sc1.hasNextLine()) {
    			String line = sc1.nextLine();
    			String[] proper = line.split(SEPARATOR, -1);
    			try {
    				int  bookingID = Integer.parseInt(proper[0]);
    				int  cusID = Integer.parseInt(proper[1]);
    				int flightId = Integer.parseInt(proper[2]);
    				Flight f1 = fbs.getFlightByID(flightId);
    				Customer c1 =fbs.getCustomerByID(cusID);
    				LocalDate date = LocalDate.parse(proper[3]);
    				Booking b1 = new Booking(bookingID, c1, f1, date);
    				c1.addBooking(b1);
					f1.addPassenger(c1);
    			} catch (NumberFormatException ex) {
    				throw new FlightBookingSystemException("Unable to parse customer id " + proper[0] + " on line " + line_id
                            + "\nError: " + ex);
        		}
    			line_id++;
    		}
    	} 
    }
    
    /**
     * This method stores the data in the FlightBookingSystem to a file.
     * The data includes the booking details such as the booking ID, customer ID, flight ID and booking date.
     * The data is stored in a TXT format, with each line representing a booking and the values separated by a '::'.
     * @param fbs The FlightBookingSystem object containing the data to be stored.
     * @throws IOException If an error occurs while writing to the file.
     */
    
    @Override
    public void storeData(FlightBookingSystem fbs) throws IOException {
        // TODO: implementation here
        try (PrintWriter out = new PrintWriter(new FileWriter(RESOURCE))){
    		for (Customer c1: fbs.getCustomers()) {
    			for(Booking b1: c1.getBookings()){
                    Customer customer=b1.getCustomer();
                    Flight flight=b1.getFlight();
                    out.print(b1.getId() + SEPARATOR);
                    out.print(customer.getId() + SEPARATOR);
                    out.print(flight.getId()+SEPARATOR);
                    out.print(b1.getBookingDate()+SEPARATOR);
    			    out.println();
				}
    		}
    	}
    }
}

