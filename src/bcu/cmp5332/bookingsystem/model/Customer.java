package bcu.cmp5332.bookingsystem.model;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 
 * @author VishaljiODEDRA
 * @author DhananjayTiwari
 */

/**
 * The Customer class represents a customer of the flight booking system.
 * It includes the customer's ID, name, phone number, email, and a list of their bookings.
 */
public class Customer {
    
    private int id;
    private String name;
    private String phone;
    private String email; 
    private final List<Booking> bookings = new ArrayList<>();
    private boolean hidden = false;
    
    /**
     * Constructs a new Customer object with the given details.
     * @param id the ID of the customer
     * @param name the name of the customer
     * @param phone the phone number of the customer
     * @param email the email address of the customer
     */
    
    // TODO: implement constructor here
    public Customer(int id,String name,String phone,String email) {
    	this.id=id;
    	this.name=name;
    	this.phone=phone;
    	this.email=email;
    }
    
    /**
     * Sets the name of the customer.
     * @param nm the new name of the customer
     */
    
    public void setName(String nm) {
    	this.name=nm;
    }
    
    /**
     * Sets the phone number of the customer.
     * @param phn the new phone number of the customer
     */
    public void setPhone(String phn) {
    	this.phone=phn;
    }
    
    /**
     * Sets the email address of the customer.
     * @param em the new email address of the customer
     */
    public void setEmail(String em) {
    	this.email=em;
    }
    
    /**
     * Returns the ID of the customer.
     * @return the ID of the customer
     */
    public int getId() {
    	return this.id;
    }
    
    /**
     * Returns the name of the customer.
     * @return the name of the customer
     */
    public String getName() {
    	return this.name;
    }
    
    /**
     * 
     * Returns the phone number of the customer.
     * @return the phone number of the customer
     */
    public String getPhone() {
    	return this.phone;
    }
    
    /**
     * Returns the email address of the customer.
     * @return the email address of the customer
     */
    public String getEmail() {
    	return this.email;
    }
    
    /**
     * Returns the hidden status of the customer.
     * @return the hidden status of the customer
     */
     public boolean getStatus() {
    	return this.hidden;
    }
     
     /**
      * Sets the hidden status of the customer.
      * @param b the new hidden status of the customer
      */
    public void setStatus(boolean b) {
    	this.hidden =b;
    }
    
    /**
     * Returns an unmodifiable list of the customer's bookings.
     * @return an unmodifiable list of the customer's bookings
     */
    public List<Booking> getBookings() {
    	return Collections.unmodifiableList(bookings);
    }
    
    /**
     * Returns a short string representation of the customer's details.
     * @return a short string representation of the customer's details
     */
    public String getDetailsShort() {
    	return "Customer #"+this.id+" - Name: "+this.name+"- Phone: "+this.phone+"- Email: "+this.email;
    }
    
    /**
     * Returns a long string representation of the customer's details, including a list of their bookings.
     * @return a long string representation of the customer's details
     */
    public String getDetailsLong() {
    	String personalDetails="Customer #"+this.id+"\n"+"Name: "+this.name+"\n"+"Phone: "+this.phone+"\n"+"Email: "+this.email;
    	String bookingDetails="";
    	int numOfBookings=0;
    	for (Booking x:bookings) {
    		bookingDetails=bookingDetails+"\n"+"* Booking date: "+x.getBookingDate()+" for Flight #"+x.getFlight().getId()+" - "+x.getFlight().getFlightNumber()+" - "+x.getFlight().getOrigin()+" to "+x.getFlight().getDestination()+" on "+x.getFlight().getDepartureDate();
    		numOfBookings+=1;
    	
    	}
    	return personalDetails+"\n"+"------------------------"+"\n"+"Bookings:\n"+bookingDetails+"\n"+numOfBookings+" booking(s)";
    	
    	
    }
   
    /**
     * 
     * Adds a booking to the customer's list of bookings.
     * @param booking the booking to be added
     * @throws FlightBookingSystemException if the booking already exists in the customer's list of bookings
     */
    
    public void addBooking(Booking booking)throws FlightBookingSystemException {
        for (Booking bk:bookings) {
        	if(bk==booking) {
        		throw new FlightBookingSystemException("Booking Exists");
        		
        	}
        }
        bookings.add(booking);
    	
    }
    
    /**
     * 
     * Removes a booking associated with a given flight from the customer's list of bookings.
     * @param flight the flight associated with the booking to be removed
     * @throws FlightBookingSystemException if the flight is not found in the customer's list of bookings
     */
    public void cancelBookingForFlight(Flight flight)throws FlightBookingSystemException {
    	int n=0;
    	for(int i=0;i<bookings.size();i++) {
    		if (bookings.get(i).getFlight()==flight) {
    			bookings.remove(i);
    			n+=1;
    			break;
    		}
    	}
    	if (n<=0) {
    		throw new FlightBookingSystemException("flight Not Found");
    	}
    }
}
