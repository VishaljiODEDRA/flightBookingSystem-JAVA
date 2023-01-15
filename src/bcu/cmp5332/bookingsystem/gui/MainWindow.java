package bcu.cmp5332.bookingsystem.gui;

import bcu.cmp5332.bookingsystem.data.FlightBookingSystemData;
import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.*;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.*;
import javax.swing.table.TableColumn;

/**
 * 
 * @author VishaljiODEDRA
 * @author DhananjayTiwari
 */
	
/**
 * 
 * The MainWindow class appears to be a graphical user interface (GUI) for a flight booking system. 
 * It has a JMenuBar with several menus, including adminMenu, flightsMenu, bookingsMenu, and customersMenu. 
 * Each of these menus has several JMenuItems that represent different actions that can be performed within the application. 
 * The MainWindow class also has several buttons, including showPassengersBtn and showBookingsBtn, 
 * which allow the user to view information about passengers and bookings.
 * The FlightBookingSystem class is a system that allows users to view and modify 
 * information about flights, bookings, and customers. The MainWindow class provides options to view and add flights, 
 * view and add customers, and make and cancel bookings. The MainWindow class also has 
 * a menu with options to exit the program and a button to display a list of passengers on a flight. 
 * The MainWindow class implements the ActionListener interface, which means it has a method for handling 
 * actions performed by the user, such as clicking a button or selecting a menu item.
 *
 */

public class MainWindow extends JFrame implements ActionListener {

    private JMenuBar menuBar;
    
    private JMenu adminMenu;
    private JMenu flightsMenu;
    private JMenu bookingsMenu;
    private JMenu customersMenu;

    private JMenuItem adminExit;

    private JMenuItem flightsView;
    private JMenuItem flightsAdd;
    private JMenuItem flightsDel;
    
    private JMenuItem bookingsIssue;
    private JMenuItem bookingsUpdate;
    private JMenuItem bookingsCancel;

    private JMenuItem custView;
    private JMenuItem custAdd;
    private JMenuItem custDel;
    private JButton showPassengersBtn = new JButton("Display Passengers");
    private JButton showBookingsBtn = new JButton("Display Bookings");

    private FlightBookingSystem fbs;
    
    LocalDate today=LocalDate.now();
    /**
     * 
     * Constructor for MainWindow class. Initializes the main window frame and sets the FlightBookingSystem instance.
     * @param fbs FlightBookingSystem instance to be used for the MainWindow
    */
    public MainWindow(FlightBookingSystem fbs) {

        initialize();
        this.fbs = fbs;
    }
    
    /**
     * 
     * Gets the Flight Booking System instance
     * @return the Flight Booking System instance
     */
    public FlightBookingSystem getFlightBookingSystem() {
        return fbs;
    }

    /**
     * Initialize the contents of the frame.
     */
    
    /**
     * 
     * Initializes the main window of the Flight Booking Management System. 
     * This includes setting the look and feel of the window to the system's look and feel, 
     * setting the title of the window, creating and adding the menu bar and its menus and menu items,
     * and setting the size and visibility of the window. The main window also has 
     * default close operations set to exit the application on close and dispose on close.
     * 
     */
    private void initialize() {

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {

        }

        setTitle("Flight Booking Management System");

        menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        //adding adminMenu menu and menu items
        adminMenu = new JMenu("Admin");
        menuBar.add(adminMenu);

        adminExit = new JMenuItem("Exit");
        adminMenu.add(adminExit);
        adminExit.addActionListener(this);

        // adding Flights menu and menu items
        flightsMenu = new JMenu("Flights");
        menuBar.add(flightsMenu);

        flightsView = new JMenuItem("View");
        flightsAdd = new JMenuItem("Add");
        flightsDel = new JMenuItem("Delete");
        flightsMenu.add(flightsView);
        flightsMenu.add(flightsAdd);
        flightsMenu.add(flightsDel);
        // adding action listener for Flights menu items
        for (int i = 0; i < flightsMenu.getItemCount(); i++) {
            flightsMenu.getItem(i).addActionListener(this);
        }
        // adding Bookings menu and menu items
        bookingsMenu = new JMenu("Bookings");
        menuBar.add(bookingsMenu);
        bookingsIssue = new JMenuItem("Issue");
        bookingsUpdate = new JMenuItem("Update");
        bookingsCancel = new JMenuItem("Cancel");
        bookingsMenu.add(bookingsIssue);
        bookingsMenu.add(bookingsUpdate);
        bookingsMenu.add(bookingsCancel);
        
        bookingsIssue.addActionListener(this);
        bookingsUpdate.addActionListener(this);
        bookingsCancel.addActionListener(this);
        // adding action listener for Bookings menu items
        for (int i = 0; i < bookingsMenu.getItemCount(); i++) {
            bookingsMenu.getItem(i).addActionListener(this);
        }

        // adding Customers menu and menu items
        customersMenu = new JMenu("Customers");
        menuBar.add(customersMenu);

        custView = new JMenuItem("View");
        custAdd = new JMenuItem("Add");
        custDel = new JMenuItem("Delete");

        customersMenu.add(custView);
        customersMenu.add(custAdd);
        customersMenu.add(custDel);
        // adding action listener for Customers menu items
        custView.addActionListener(this);
        custAdd.addActionListener(this);
        custDel.addActionListener(this);

        setSize(800, 500);

        setVisible(true);
        setAutoRequestFocus(true);
        toFront();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);        

    }	


    
    /**
     * 
     * This method handles the actions performed when certain buttons are clicked.
     * If the source of the action is the adminExit button, the FlightBookingSystemData is stored and the program is closed.
     * If the source of the action is the flightsView button, the flights are displayed.
     * If the source of the action is the flightsAdd button, the AddFlightWindow is displayed.
     * If the source of the action is the flightsDel button, the RemoveFlightWindow is displayed.
     * If the source of the action is the bookingsIssue button, the AddBookingWindow is displayed.
     * If the source of the action is the bookingsUpdate button, the UpdateBookingWindow is displayed.
     * If the source of the action is the bookingsCancel button, the RemoveBookingWindow is displayed.
     * If the source of the action is the custView button, the customers are displayed.
     * If the source of the action is the custAdd button, the AddCustomerWindow is displayed.
     * If the source of the action is the custDel button, the RemoveCustomerWindow is displayed.
     * If the source of the action is the showPassengersBtn button, the ShowPassengersWindow is displayed.
     * If the source of the action is the showBookingsBtn button, the ShowBookingWindow is displayed.
     * @param ae the action event that occurred
     * @throws IOException if there is an error storing the FlightBookingSystemData
     */

    @Override
    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == adminExit) {
            try {
                FlightBookingSystemData.store(fbs);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, ex, "Error", JOptionPane.ERROR_MESSAGE);
            }
            System.exit(0);
        } else if (ae.getSource() == flightsView) {
            displayFlights();
            
        } else if (ae.getSource() == flightsAdd) {
            new AddFlightWindow(this);
            
        } else if (ae.getSource() == flightsDel) {
            new RemoveFlightWindow(this);
            
        } else if (ae.getSource() == bookingsIssue) {
            new AddBookingWindow(this);
            
        }else if(ae.getSource()==bookingsUpdate) {
        	new UpdateBookingWindow(this);
        	
        }else if (ae.getSource() == bookingsCancel) {
        	new RemoveBookingWindow(this);
            
        } else if (ae.getSource() == custView) {
            displayCustomers();
            
        } else if (ae.getSource() == custAdd) {
           new AddCustomerWindow(this); 
            
        } else if (ae.getSource() == custDel) {
           new RemoveCustomerWindow(this); 
            
        }else if (ae.getSource()==showPassengersBtn) {
        	new ShowPassengersWindow(this);
        	
        }else if(ae.getSource()==showBookingsBtn) {
        	new ShowBookingWindow(this);
        }
    }
    
    /**
     * 
     * This method displays a list of flights in a JTable to the user.
     * It obtains a list of flights from the Flight Booking System (fbs) and populates a 2D array with the flight data.
     * It then creates a table with column headers "Name","phone" and "Email", and initializes the 
     * data for the table with the flight data from the list.   
     * This array is then used to create a JTable which is displayed to the user in a scrollable panel.
     * A button is also added to the bottom panel of the window to allow the user to view the passengers of a selected flight.
     */
    public void displayFlights() {
        List<Flight> flightsList = fbs.getFlights();
        // headers for the table
        String[] columns = new String[]{"ID","Flight No", "Origin", "Destination", "Departure Date"};
        Object[][] data = new Object[flightsList.size()][6];
   
        for (int i = 0; i < flightsList.size(); i++) {
            Flight flight = flightsList.get(i);
            if(flight.getStatus()==false) {
            	if(flight.getDepartureDate().isAfter(today)||flight.getDepartureDate().isEqual(today)) {
                data[i][0]=flight.getId();
            	data[i][1] = flight.getFlightNumber();
                data[i][2] = flight.getOrigin();
                data[i][3] = flight.getDestination();
                data[i][4] = flight.getDepartureDate();
            	}
              }
        }
        JTable table = new JTable(data, columns);
        this.getContentPane().removeAll();
        this.getContentPane().add(new JScrollPane(table));

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(1, 3));
        bottomPanel.add(new JLabel("     "));
        bottomPanel.add(this.showPassengersBtn);

        showPassengersBtn.addActionListener(this);

        this.getContentPane().add(bottomPanel, BorderLayout.SOUTH);

        setVisible(true);
            this.revalidate();
    }	
    
    /**
     * 
     * This method displays the list of customers in a table.
     * It first retrieves the list of customers from the Flight Booking System object and stores it in a list.
     * It then creates a table with column headers "ID", "Name", "Phone", and "Email" and initializes 
     * the data for the table with the customer data from the list.
     * The table is then displayed in a scroll pane and added to the main window.
     * A panel is added to the bottom of the window with a button to show bookings for each customer.
     * The action listener for the button is also added.
     */
    
    public void displayCustomers() {
        List<Customer> customersList = fbs.getCustomers();
        // headers for the table
        String[] columns = new String[]{"ID","Name", "Phone", "Email"};

        Object[][] data = new Object[customersList.size()][6];
        for (int i = 0; i < customersList.size(); i++) {
            Customer customer = customersList.get(i);
           if(customer.getStatus()==false) {
               data[i][0]=customer.getId();
        	   data[i][1] = customer.getName();
               data[i][2] = customer.getPhone();
               data[i][3] = customer.getEmail();     
           } 
        }
        JTable table = new JTable(data, columns);
        this.getContentPane().removeAll();
        this.getContentPane().add(new JScrollPane(table));
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(1, 3));
        bottomPanel.add(new JLabel("     "));
        bottomPanel.add(this.showBookingsBtn);

        showBookingsBtn.addActionListener(this);

        this.getContentPane().add(bottomPanel, BorderLayout.SOUTH);
        this.revalidate();
    }
    
    /**
     * 
     * Displays a table of bookings for a particular customer with the given ID.
     * The table includes the following columns:
     * <ul>
     * <li>Booking Id: the unique identifier for the booking</li>
     * <li>Flight Number: the flight number of the booked flight</li>
     * <li>Booking Date: the date when the booking was made</li>
     * </ul>
     * @param id the ID of the customer whose bookings are to be displayed
     */
    public void displayBookings(int id)throws FlightBookingSystemException {
    	
    	
    	String[] columns=new String[] {"Booking Id","Flight Number","Booking Date"};
    	Object[][] data=null ;
    	Customer customerCheck=fbs.getCustomerByID(id);
    	List<Booking> bookingList=customerCheck.getBookings();
         	    data =new Object[bookingList.size()][6];
         		for (int j=0;j<bookingList.size();j++) {
         			Booking booking=bookingList.get(j);
         			if(booking.getBookingDate().isAfter(today)||booking.getBookingDate().isEqual(today)) {
         			data[j][0]=booking.getId();
         			data[j][1]=booking.getFlight().getFlightNumber();
         			data[j][2]=booking.getBookingDate();	
         		} 
         	}   
         
    	 JTable table = new JTable(data, columns);
         this.getContentPane().removeAll();
         this.getContentPane().add(new JScrollPane(table));
         this.revalidate();
    	
    	
    }
    
   
    /**
     * It retrieves the list of flights and gets all the file for particular passenger using Customer ID.
     * it form new table in GUI of column "ID", "Name", "Phone" and "Email"
     * Displays a table of passengers for a given flight ID.
     *
     * @param id the ID of the flight
     */
 
    public void displayPassengers(int id) throws FlightBookingSystemException{
    
         // headers for the table
         String[] columns = new String[]{"ID","Name", "Phone", "Email"};
         Flight flightCheck=fbs.getFlightByID(id);

         Object[][] data=null;
            	 List<Customer> passengersList=flightCheck.getPassengers();
             	 data =new Object[passengersList.size()][6];
             		for (int j=0;j<passengersList.size();j++) {
             			Customer customer=passengersList.get(j);
             			data[j][0]=customer.getId();
             			data[j][1]=customer.getName();
             			data[j][2]=customer.getPhone();
             			data[j][3]=customer.getEmail();
             		
             		}
             
         
         JTable table = new JTable(data, columns);
         this.getContentPane().removeAll();
         this.getContentPane().add(new JScrollPane(table));
         this.revalidate(); 
    	
    } 
}
