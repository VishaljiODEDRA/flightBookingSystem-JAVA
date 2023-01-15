package bcu.cmp5332.bookingsystem.model;

import java.time.LocalDate;

/**
 * 
 * @author VishaljiODEDRA
 * @author DhananjayTiwari
 */

/**
 * The Booking class represents a booking made by a customer for a flight.
 * It includes the booking ID, customer details, flight details, and booking date.
 */

public class Booking {
    private int bookingId;
    private Customer customer;
    private Flight flight;
    private LocalDate bookingDate;
    
    /**
     * Constructs a new Booking object with the given details.
     * @param bookingId the ID of the booking
     * @param customer the customer who made the booking
     * @param flight the flight being booked
     * @param bookingDate the date of the booking
     */

    public Booking(int bookingId,Customer customer, Flight flight, LocalDate bookingDate) {
        // TODO: implementation here
    	this.bookingId =bookingId;
    	this.customer=customer;
    	this.flight=flight;
    	this.bookingDate=bookingDate;
        
    }
    
    /**
     * Sets the ID of the booking.
     * @param id the new ID of the booking
     */
    public void setId(int id){
        this.bookingId=id;
    }
    
    /**
     * Sets the customer who made the booking.
     * @param cst the customer who made the booking
     */
    public void setCustomer(Customer cst) {
    	this.customer=cst;
    }
    
    /**
     * Sets the flight being booked.
     * @param flg the flight being booked
     */
    public void setFlight(Flight flg) {
    	this.flight=flg;
    }
    
    /**
     * Sets the date of the booking.
     * @param bkdt the date of the booking
     */
    public void setBookingDate(LocalDate bkdt) {
    	this.bookingDate=bkdt;
    	
    }
    
    /**
     * Returns the ID of the booking.
     * @return the ID of the booking
     */
    public int getId(){
        return this.bookingId;
    }
    
    /**
     * Returns the customer who made the booking.
     * @return the customer who made the booking
     */
    public Customer getCustomer() {
    	return this.customer;
    }
    
    /**
     * Returns the flight being booked.
     * @return the flight being booked
     */
    public Flight getFlight() {
    	return this.flight;
    }
    
    /**
     * Returns the date of the booking.
     * @return the date of the booking
     */
    public LocalDate getBookingDate() {
    	return this.bookingDate;
    }
    
       
}
