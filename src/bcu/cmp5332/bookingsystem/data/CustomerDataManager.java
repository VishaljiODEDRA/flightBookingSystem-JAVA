package bcu.cmp5332.bookingsystem.data;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Customer;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * 
 * @author VishaljiODEDRA
 * @author DhananjayTiwari
 */

public class CustomerDataManager implements DataManager {

    private final String RESOURCE = "./resources/data/customers.txt";
    
    /**
     * This method is used to load data from a file into the {@link FlightBookingSystem} object.
     * The method reads each line of the file, and creates a new {@link Customer} object using the data from the line.
     * The {@link Customer} is then added to the {@link FlightBookingSystem}.
     * @param fbs the {@link FlightBookingSystem} object to which the data is to be loaded
     * @throws IOException if there is an error reading the file
     * @throws FlightBookingSystemException if there is an error parsing the data in the file
     */
    
    @Override
    public void loadData(FlightBookingSystem fbs) throws IOException, FlightBookingSystemException {
        // TODO: implementation here
        try (Scanner sc = new Scanner(new File(RESOURCE))){
    		int line_idx = 1;
    		while (sc.hasNextLine()) {
    			String line = sc.nextLine();
    			String[] properties1 = line.split(SEPARATOR, -1);
    			try {
    				int id = Integer.parseInt(properties1[0]);
    				String customerName = properties1[1];
    				String customerNumber = properties1[2];
    				String customerEmail = properties1[3];
    				boolean hidden=Boolean.parseBoolean(properties1[4]);
    				Customer c1 = new Customer(id, customerName, customerNumber, customerEmail);
    				c1.setStatus(hidden);
    				fbs.addCustomer(c1);
    			} catch (NumberFormatException ex) {
    				throw new FlightBookingSystemException("Unable to parse book id " + properties1[0] + " on line " + line_idx
                            + "\nError: " + ex);
    			}
    			line_idx++;
    		}
    	}
    }
    
    /**
     * This method stores the data of the customers present in the {@link FlightBookingSystem} to a file.
     * It includes the customer details such as, Id, name, number and email Id.
     * The data is stored in a TXT format, with each representing a customer and the value separated by '::'.
     * @param fbs The {@link FlightBookingSystem} for which data needs to be stored
     * @throws IOException If an I/O error occurs while opening or writing to the file
    */

    @Override
    public void storeData(FlightBookingSystem fbs) throws IOException {
        // TODO: implementation here
        try (PrintWriter out = new PrintWriter(new FileWriter(RESOURCE))){
    		for (Customer c1 : fbs.getCustomers()) {
    			out.print(c1.getId() + SEPARATOR);
    			out.print(c1.getName() + SEPARATOR);
    			out.print(c1.getPhone() + SEPARATOR);
    			out.print(c1.getEmail() + SEPARATOR);
    			out.print(c1.getStatus()+SEPARATOR);
    			out.println();
    		}
    	}
    }
}

