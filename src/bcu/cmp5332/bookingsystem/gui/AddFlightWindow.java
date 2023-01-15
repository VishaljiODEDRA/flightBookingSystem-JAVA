package bcu.cmp5332.bookingsystem.gui;

import bcu.cmp5332.bookingsystem.commands.AddFlight;

import bcu.cmp5332.bookingsystem.commands.Command;
import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;


/**
 * 
 * @author VishaljiODEDRA
 * @author DhananjayTiwari
 */


/**
 * The AddFlightWindow class is a GUI window that allows the user to add a new flight to the flight booking system.
 * It implements the ActionListener interface to handle user actions such as clicking the "Add" or "Cancel" button.
 * The window contains text fields for the user to input the flight number, origin, destination, departure date,
 * price in dollars, and number of seats. When the user clicks the "Add" button, the input values are used to create
 * a new AddFlight command object, which is then executed on the flight booking system. If the operation is successful,
 * the window is closed and a success message is displayed to the user. If an exception is thrown, an error message is
 * displayed to the user.
 */
public class AddFlightWindow extends JFrame implements ActionListener {

    private MainWindow mw;
    private JTextField flightNoText = new JTextField();
    private JTextField originText = new JTextField();
    private JTextField destinationText = new JTextField();
    private JTextField depDateText = new JTextField();
    private JTextField priceText=new JTextField();
    private JTextField capacityText=new JTextField();

    private JButton addBtn = new JButton("Add");
    private JButton cancelBtn = new JButton("Cancel");

    /**
     * Constructs a new AddFlightWindow object and initializes the GUI window.
     * @param mw the MainWindow object that the AddFlightWindow will be displayed in
     */
    public AddFlightWindow(MainWindow mw) {
        this.mw = mw;
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    
    /**
     * Initializes the GUI window for adding a new flight. This includes setting up the labels, text fields, and buttons for
     * the user to input the flight information and adding action listeners to the buttons. 
     * The window is then displayed to the user.
     */
    private void initialize() {

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {

        }

        setTitle("Add a New Flight");

        setSize(400, 300);
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(7, 2));
        topPanel.add(new JLabel("Flight No : "));
        topPanel.add(flightNoText);
        topPanel.add(new JLabel("Origin : "));
        topPanel.add(originText);
        topPanel.add(new JLabel("Destination : "));
        topPanel.add(destinationText);
        topPanel.add(new JLabel("Departure Date (YYYY-MM-DD) : "));
        topPanel.add(depDateText);
        topPanel.add(new JLabel("Price in $: "));
        topPanel.add(priceText);
        topPanel.add(new JLabel("No. of Seats: "));
        topPanel.add(capacityText);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(1, 3));
        bottomPanel.add(new JLabel("     "));
        bottomPanel.add(addBtn);
        bottomPanel.add(cancelBtn);

        addBtn.addActionListener(this);
        cancelBtn.addActionListener(this);

        this.getContentPane().add(topPanel, BorderLayout.CENTER);
        this.getContentPane().add(bottomPanel, BorderLayout.SOUTH);
        setLocationRelativeTo(mw);

        setVisible(true);

    }
    
    
    /**
     * 
     * Handles user actions such as clicking the "Add" or "Cancel" button in the GUI window. If the "Add" button is clicked,
     * the addBook method is called to add a new flight to the flight booking system. If the "Cancel" button is clicked, the
     * window is simply hidden from view.
     * @param ae the ActionEvent object that represents the user action
     */
   
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == addBtn) {
            addBook();
        } else if (ae.getSource() == cancelBtn) {
            this.setVisible(false);
        }

    }

    /**
     * Adds a new flight to the flight booking system based on the input values entered by the user in the GUI window. The input
     * values include the flight number, origin, destination, departure date, price, and capacity. The method performs the following steps:
     * Retrieves the input values from the text fields: flight number, origin, destination, departure date, price, and capacity.
     * Parses the price and capacity values from strings to double and int respectively.
     * Attempts to parse the departure date from a string to a LocalDate object. If this fails, it throws a FlightBookingSystemException
     * with the message "Date must be in YYYY-DD-MM format".
     * Creates a new AddFlight command object with the input values.
     * Executes the AddFlight command on the flight booking system.
     * Hides the window from view.
     * Displays a success message to the user.
     * Calls the displayFlights method on the main window to update the list of flights.
     * If an exception is thrown at any point, it displays an error message to the user.
     * @throws FlightBookingSystemException if the departure date cannot be parsed from a string to a LocalDate object 
     			*or if there is any other issue with adding the flight to the flight booking system
     */
    private void addBook() {
        try {
            String flightNumber = flightNoText.getText();
            String origin = originText.getText();
            String destination = destinationText.getText();
            double price=Double.parseDouble(priceText.getText());
            int capacity=Integer.parseInt(capacityText.getText());
            LocalDate departureDate = null;
            try {
                departureDate = LocalDate.parse(depDateText.getText());
            }
            catch (DateTimeParseException dtpe) {
                throw new FlightBookingSystemException("Date must be in YYYY-DD-MM format");
            }
 
            Command addFlight = new AddFlight(flightNumber, origin, destination, departureDate,price,capacity);
            
            addFlight.execute(mw.getFlightBookingSystem());
            this.setVisible(false);
            JOptionPane.showMessageDialog(null,"Flight Successfully added with Flight Number " + flightNumber + ".","Success",JOptionPane.INFORMATION_MESSAGE);
            mw.displayFlights();
        } catch (FlightBookingSystemException ex) {
            JOptionPane.showMessageDialog(this, ex, "Error", JOptionPane.ERROR_MESSAGE);
        }catch (NumberFormatException ex) {
        	JOptionPane.showMessageDialog(this,"Please Enter a Number \n" +ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
        }
    }

}
