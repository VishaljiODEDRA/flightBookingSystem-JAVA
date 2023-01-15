package bcu.cmp5332.bookingsystem.commands;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Flight;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;
import java.time.LocalDate;
import java.time.Period;

/**
 * 
 * @author VishaljiODEDRA
 * @author DhananjayTiwari
 */
public class AddFlight implements  Command {
	
	/**
	 * This method adds flight to the FlightBookingSystem, based on certain circumstances,
	 * It takes 6 parameters
	 * @param flightNumber, takes flight number as String
	 * @param origin, takes the starting location as String
	 * @param destination, takes final destination location as String
	 * @param departureDate, takes the date of departing from the starting location as LocalDate
	 * @param price, takes ticket price of the flight as int
	 * @param capacity, takes number of seats in flight as int
	 */

    private final String flightNumber;
    private final String origin;
    private final String destination;
    private final LocalDate departureDate;
    private double price;
    private final int capacity;

    public AddFlight(String flightNumber, String origin, String destination, LocalDate departureDate,double price,int capacity) {
        this.flightNumber = flightNumber;
        this.origin = origin;
        this.destination = destination;
        this.departureDate = departureDate;
        this.price=price;
        this.capacity=capacity;
    }
    
    /**
     * Adds a new flight to the flight booking system. The price of the flight is adjusted
     * based on the number of days remaining until the departure date.
     *
     * @param flightBookingSystem the flight booking system
     * @throws FlightBookingSystemException if an error occurs while adding the flight
     */
    
    @Override
    public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException {
        int maxId = 0;
        if (flightBookingSystem.getFlights().size() > 0) {
            int lastIndex = flightBookingSystem.getFlights().size() - 1;
            maxId = flightBookingSystem.getFlights().get(lastIndex).getId();
        }
        LocalDate today=LocalDate.now();
        Period diff=Period.between(today, this.departureDate);
        if(diff.getDays()==3) {
        	this.price=this.price+(0.03*this.price);
        }else if(diff.getDays()==2) {
        	this.price=this.price+(0.05*this.price);
        }else if(diff.getDays()==1) {
        	this.price=this.price+(0.1*this.price);
        }else if(diff.getDays()==0) {
        	this.price=this.price+(0.15*this.price);
        }
        
        Flight flight = new Flight(++maxId, flightNumber, origin, destination, departureDate,price,capacity);
        flightBookingSystem.addFlight(flight);
        System.out.println("Flight Successfully added with Flight ID " + flight.getId() + ".");
    }
}
