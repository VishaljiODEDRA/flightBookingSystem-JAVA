package bcu.cmp5332.bookingsystem.commands;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;

import bcu.cmp5332.bookingsystem.model.*;

/**
 * 
 * @author VishaljiODEDRA
 * @author DhananjayTiwari
 */

public class AddCustomer implements Command {
	

    private final String name;
    private final String phone;
    private final String email;
    
    /**
	 * 
	 * This Method adds a customer to FlightBookingSystem and it takes 3 parameters
	 * @param name, takes name in form of String
	 * @param phone, 10 or 11 digit phone number in form of String
	 * @param email, take email of the person (format: name@gmail.com)
	 * 
	 */

    public AddCustomer(String name, String phone,String email) {
        this.name = name;
        this.phone = phone;		
        this.email=email;
    }
    
    /**
     * Adds a new customer to the flight booking system.
     *
     * @param flightBookingSystem, the flight booking system
     * @throws FlightBookingSystemException if an error occurs while adding the customer
     */

    @Override
    public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException {
        int maxId =0;
        if(flightBookingSystem.getCustomers().size()>0) {
        	int lastIndex=flightBookingSystem.getCustomers().size()-1;
        	maxId=flightBookingSystem.getCustomers().get(lastIndex).getId();
        }
        Customer customer=new Customer(++maxId,this.name,this.phone,this.email);
        flightBookingSystem.addCustomer(customer);
        System.out.println("Customer Successfully added with Customer ID "+customer.getId()+".");
        
    }
}
