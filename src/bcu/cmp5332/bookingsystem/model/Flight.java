package bcu.cmp5332.bookingsystem.model;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 
 * @author VishaljiODEDRA
 * @author DhananjayTiwari
 */

/**

This class represents a flight in a flight booking system. It has various fields such as id, flight number, origin, destination, departure date, price, and capacity. It also has a set of passengers.
It has methods to add/remove passengers, set and get flight details, and check the availability of seats on the flight.
*/
public class Flight {
    
    private int id;
    private String flightNumber;
    private String origin;
    private String destination;
    private LocalDate departureDate;
    private double price;
    private int capacity;
    private boolean hidden=false;

    private final Set<Customer> passengers;
    
    /**
     * Constructor for the Flight class.
     * @param id the id of the flight
     * @param flightNumber the flight number of the flight
     * @param origin the origin of the flight
     * @param destination the destination of the flight
     * @param departureDate the departure date of the flight
     * @param price the price of the flight
     * @param capacity the capacity of the flight
     */

    public Flight(int id, String flightNumber, String origin, String destination, LocalDate departureDate, double price,int capacity) {
        this.id = id;
        this.flightNumber = flightNumber;
        this.origin = origin;
        this.destination = destination;
        this.departureDate = departureDate;
        this.price = price;
        this.capacity=capacity;
        
        passengers = new HashSet<>();
    }
    
    /**
     * Returns the current status of the object.
     * @return True if the object is hidden, false if it is visible.
     */
    public boolean getStatus() {
    	return this.hidden;
    }
    
    /**
     * Sets the current status of the object.
     * @param b The new status for the object. If true, the object will be hidden. If false, the object will be visible.
     */
    public  void setStatus(boolean b) {
    	this.hidden=b;	
    }
    
    /**
     * Returns the number of seats in the object.
     * @return The number of seats as an integer.
     */
    public int getNoOfSeats(){
    	return this.capacity;
    }
    
    /**
     * 
     * Sets the number of seats in the flight.
     * @param cap the number of seats in the flight
     */
    public void setNoOfSeats(int cap) {
    	this.capacity=cap;
    }
    
    /**
     * Gets the current price of the flight based on the number of available seats.
     * If fewer than 5% of the seats are available, the price is increased by 10%.
     * If fewer than 10% of the seats are available, the price is increased by 6%.
     * If fewer than 20% of the seats are available, the price is increased by 2%.
     * 
     * @return the current price of the flight
     */
    public double getPrice() {
    	int leftSeats=this.capacity-passengers.size();
    	if(leftSeats<=(0.05*this.capacity)) {
    		this.price=this.price+(0.1*this.price);
    	}else if(leftSeats<=(0.1*this.capacity)) {
    		this.price=this.price+(0.06*this.price);
    	}else if(leftSeats<=(0.2*this.capacity)) {
    		this.price=this.price+(0.02*this.price);
    		}
    	return this.price;
    }
    
    /**
     * Sets the price of the flight.
     * 
     * @param price the new price of the flight
     */
    public void setPrice(double price) {
    	this.price=price;
    }
    
    /**
     * Gets the unique ID of the flight.
     * 
     * @return the unique ID of the flight
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the unique ID of the flight.
     * 
     * @param id the new unique ID of the flight
     */
    public void setId(int id) {
        this.id = id;
    }
    
    /**
     * Gets the flight number of the flight.
     * 
     * @return the flight number of the flight
     */
    public String getFlightNumber() {
        return flightNumber;
    }

    /**
     * Sets the flight number of the flight.
     * 
     * @param flightNumber the new flight number of the flight
     */
    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }
    
    /**
     * Gets the origin of the flight's journey.
     * 
     * @return the origin of the flight's journey
     */
    public String getOrigin() {
        return origin;
    }
    
    /**
     * Sets the origin of the flight's journey.
     * 
     * @param origin the new origin of the flight's journey
     */
    public void setOrigin(String origin) {
        this.origin = origin;
    }

    /**
     * Gets the destination of the flight's journey.
     * 
     * @return the destination of the flight's journey
     */
    public String getDestination() {
        return destination;
    }

    /**
     * Sets the destination of the flight's journey.
     * 
     * @param destination the new destination of the flight's journey
     */
    public void setDestination(String destination) {
        this.destination = destination;
    }
    
    /**
     * Gets the departure date of the flight's journey.
     * 
     * @return the departure date of the flight's journey
     */
    public LocalDate getDepartureDate() {
        return departureDate;
    }

    /**
     * Sets the departure date of the flight's journey.
     * 
     * @param departureDate the new departure date of the flight's journey
     */
    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }

    /**
     * Gets the list of passengers on the flight.
     * 
     * @return the list of passengers on the flight
     */
    public List<Customer> getPassengers() {
        return new ArrayList<>(passengers);
    }
	
    /**
     * Gets a brief summary of the flight's details.
     * The summary includes the ID, flight number, origin, destination, departure date, and price.
     * 
     * @return a brief summary of the flight's details
     */
    public String getDetailsShort() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/YYYY");
        return "Flight #" + id + " - " + flightNumber + " - " + origin + " to " 
                + destination + " on " + departureDate.format(dtf)+" - Price: $"+this.getPrice();
    }
   
    /**
     * Gets a detailed summary of the flight's details and its passengers.
     * The summary includes the ID, flight number, origin, destination, departure date, price, capacity, and list of passengers.
     * 
     * @return a detailed summary of the flight's details and its passengers
     */
    public String getDetailsLong() {
    	int leftSeats=this.capacity-this.passengers.size();
    	
        String flightDetails="Flight #"+this.id+"\n"+
    "Flight No.: "+this.flightNumber+"\n"+
        		"Origin: "+this.origin+"\n"+
    "Destination: "+this.destination+"\n"+
        		"Departure Date: "+this.departureDate+"\n"+
    "Price: "+this.getPrice()+"\n"+
        		"Total No. of Seats: "+this.capacity+"\n"+
    "No. of Seats Left: "+leftSeats;
        String passengerDetails="";
        for(Customer p:passengers) {
        	passengerDetails=passengerDetails+"\n"+"* Id: "+p.getId()+" - "+p.getName()+" - "+p.getPhone();
        }
        return flightDetails+"\n"+"------------------------"+"\n"+"Passengers:"+passengerDetails;
        		
    }
    
    /**
     * Adds a passenger to the flight.
     * 
     * @param passenger the passenger to be added
     * @throws FlightBookingSystemException if the passenger is already present on the flight or if there are no seats available
     */
    public void addPassenger(Customer passenger)throws FlightBookingSystemException {
        for(Customer p:passengers) {
        	if(p==passenger) {
        		throw new FlightBookingSystemException("Passenger Already Exists");
        	}
        	
        }
        if(passengers.size()>=this.capacity) {
        	 throw new FlightBookingSystemException("Seats Not Available Flight is Full");
        }
        passengers.add(passenger);     
    }
    
    /**
     * Removes a passenger from the flight.
     * 
     * @param passenger the passenger to be removed
     * @throws FlightBookingSystemException if the passenger is not found on the flight
     */
    public void removePassenger(Customer passenger)throws FlightBookingSystemException{
    	int n=0;
    	for (Customer p:passengers) {
    		if(p==passenger) {
    			n+=1;
    			passengers.remove(p);
    			break;
    		}
    	}
    	if(n<=0) {
    		throw new FlightBookingSystemException("passenger not Found");
    	}
    }
}
