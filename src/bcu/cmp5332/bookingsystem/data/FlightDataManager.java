package bcu.cmp5332.bookingsystem.data;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;

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

public class FlightDataManager implements DataManager {
    
    private final String RESOURCE = "./resources/data/flights.txt";
    
    /**
     * This method is used to load flight data from a file into the {@link FlightBookingSystem} object.
     * It reads each line from the file and creates a new {@link Flight} object with the data from that line.
     * The {@link Flight} object is then added to the {@link FlightBookingSystem}.
     * @param fbs the {@link FlightBookingSystem} to which the flight data will be loaded
     * @throws IOException if there is an error reading from the file
     * @throws FlightBookingSystemException if there is an error creating the Flight object or adding it to the flight booking system
     */
    @Override
    public void loadData(FlightBookingSystem fbs) throws IOException, FlightBookingSystemException {
        try (Scanner sc = new Scanner(new File(RESOURCE))) {
            int line_idx = 1;
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] properties = line.split(SEPARATOR, -1);
                try {
                    int id = Integer.parseInt(properties[0]);
                    String flightNumber = properties[1];
                    String origin = properties[2];
                    String destination = properties[3];
                    LocalDate departureDate = LocalDate.parse(properties[4]);
                    double price=Double.parseDouble(properties[5]);
                    int capacity=Integer.parseInt(properties[6]);
                    boolean hidden=Boolean.parseBoolean(properties[7]);
                    Flight flight = new Flight(id, flightNumber, origin, destination, departureDate,price,capacity);
                    flight.setStatus(hidden);
                    fbs.addFlight(flight);
                } catch (NumberFormatException ex) {
                    throw new FlightBookingSystemException("Unable to parse book id " + properties[0] + " on line " + line_idx
                        + "\nError: " + ex);
                }
                line_idx++;
            }
        }
    }
    
    /**
     * It stores the data of the flights present in the {@link FlightBookingSystem} to a file.
     * it includes the flight details such as, Id, number, origin, destination, date, price and No of seats.
     * The data is stored in a TXT format, with each representing a customer and the value separated by '::'.
     * @param fbs The flight booking system for which data needs to be stored
     * @throws IOException If an I/O error occurs while opening or writing to the file
    */
    
    @Override
    public void storeData(FlightBookingSystem fbs) throws IOException {
        try (PrintWriter out = new PrintWriter(new FileWriter(RESOURCE))) {
            for (Flight flight : fbs.getFlights()) {
                out.print(flight.getId() + SEPARATOR);
                out.print(flight.getFlightNumber() + SEPARATOR);
                out.print(flight.getOrigin() + SEPARATOR);
                out.print(flight.getDestination() + SEPARATOR);
                out.print(flight.getDepartureDate() + SEPARATOR);
                out.print(flight.getPrice()+SEPARATOR);
                out.print(flight.getNoOfSeats()+SEPARATOR);
                out.print(flight.getStatus()+SEPARATOR);
                out.println();
            }
        }
    }
}
