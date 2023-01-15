package bcu.cmp5332.bookingsystem.test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import bcu.cmp5332.bookingsystem.model.Booking;
import bcu.cmp5332.bookingsystem.model.Customer;
import bcu.cmp5332.bookingsystem.model.Flight;

/**
 * 
 * @author VishaljiODEDRA
 * @author DhananjayTiwari
 */

class CustomerTest {

	Customer customer = new Customer(1, "Vish ODEDRA", "9876543210", "vish@microsoft.com");
	Flight flight = new Flight(1, "FL007", "London", "Leeds", LocalDate.parse("2022-12-24"), 350.0, 100);
	Booking booking = new Booking(1, customer, flight, LocalDate.parse("2023-01-05"));
	
	@Test
	public void testGetId() throws Exception {
		assertEquals(1, customer.getId());
	}
	
	@Test
	public void testGetName() throws Exception {
		assertEquals("Vish ODEDRA", customer.getName());
	}
	
	@Test
	public void testGetPhone() throws Exception {
		assertEquals("9876543210", customer.getPhone());
	}
	
	@Test
	public void testGetEmail() throws Exception {
		assertEquals("vish@microsoft.com", customer.getEmail());
	}
	
	@Test
	public void testSetName() throws Exception {
		customer.setName("Vishalji ODEDRA");
		assertEquals("Vishalji ODEDRA", customer.getName());
	}
	
	@Test
	public void testSetPhone() throws Exception {
		customer.setPhone("9999");
		assertEquals("9999", customer.getPhone());
	}
	
	@Test 
	public void testSetEmail() throws Exception {
		customer.setEmail("vishalji@tesla.com");
		assertEquals("vishalji@tesla.com", customer.getEmail());
	}
	
	@Test
	public void testGetBooking() throws Exception {
		List<Booking> c1 = new ArrayList<>();
		customer.addBooking(booking);
		c1.add(booking);
		assertEquals(c1, customer.getBookings());	
	}
	
	@Test
	public void testGetDetailsShort() throws Exception {
		String shortDetails = "Customer #"+customer.getId()+" - Name: "+customer.getName()+"- Phone: "+customer.getPhone()+"- Email: "+customer.getEmail();
		assertEquals(shortDetails, customer.getDetailsShort());
	}
	
	@Test
	public void testGetDetailsLong() throws Exception {
		String personalDetails="Customer #"+customer.getId()+"\n"+"Name: "+customer.getName()+"\n"+"Phone: "+customer.getPhone()+"\n"+"Email: "+customer.getEmail();
    	String bookingDetails="";
    	int numOfBookings=0;
    	for (Booking b: customer.getBookings()) {
    		bookingDetails=bookingDetails+"\n"+"* Booking date: "+b.getBookingDate()+" for Flight #"+b.getFlight().getId()+" - "+b.getFlight().getFlightNumber()+" - "+b.getFlight().getOrigin()+" to "+b.getFlight().getDestination()+" on "+b.getFlight().getDepartureDate();
    		numOfBookings+=1;
    	}
    	String longDetails = personalDetails+"\n"+"------------------------"+"\n"+"Bookings:\n"+bookingDetails+"\n"+numOfBookings+" booking(s)";
    	assertEquals(longDetails, customer.getDetailsLong());
	}
	
	@Test
	public void testAddBooking() throws Exception {
		List<Booking> c1 = new ArrayList<>();
		customer.addBooking(booking);
		c1.add(booking);
		assertEquals(c1, customer.getBookings());
	}
	
	@Test
	public void testCancelBookingForFlight() throws Exception {
		List<Booking> c1 = new ArrayList<>();
		customer.addBooking(booking);
		customer.cancelBookingForFlight(flight);
		assertEquals(c1, customer.getBookings());
	}
}
