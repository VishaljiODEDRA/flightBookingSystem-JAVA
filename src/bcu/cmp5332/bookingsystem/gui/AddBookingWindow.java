package bcu.cmp5332.bookingsystem.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

import bcu.cmp5332.bookingsystem.commands.AddBooking;
import bcu.cmp5332.bookingsystem.commands.Command;
import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;

/**
 * 
 * @author VishaljiODEDRA
 * @author DhananjayTiwari
 */


/**
 * 
 * The class AddBookingWindow represents a window in a GUI application for adding a new booking to the flight booking system. 
 * It contains text fields for the user to input the flightId and customerId, as well as buttons for adding and cancelling the booking.
 * When the user clicks the "Add" button, the addBook method is called to create and execute an 
 * AddBooking command using the input flightId and customerId, and to refresh the view with the updated list of customers. 
 * If an exception is thrown, an error message is displayed. When the user clicks the "Cancel" button, the window is closed.
 *
 */
public class AddBookingWindow extends JFrame implements ActionListener {

    private MainWindow mw;
    private JTextField flightIdText = new JTextField();
    private JTextField customerIdText = new JTextField();
    

    private JButton addBtn = new JButton("Add");
    private JButton cancelBtn = new JButton("Cancel");

    /**
     * Constructs an AddBookingWindow object with the specified MainWindow object.
     * @param mw the MainWindow object that the AddBookingWindow object is associated with
     */
    public AddBookingWindow(MainWindow mw) {
        this.mw = mw;
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    
    /**
     * Initializes the AddBookingWindow frame and sets up the user interface.
     * The frame includes a top panel containing input fields for flight and customer IDs,
     * and a bottom panel containing buttons for adding and canceling a booking.
     * The look and feel of the frame is set to the system's look and feel.
     */
    private void initialize() {

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {

        }

        setTitle("Add a New Flight");

        setSize(400, 300);
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(4, 2));
        topPanel.add(new JLabel("Flight Id : "));
        topPanel.add(flightIdText);
        topPanel.add(new JLabel("Customer Id : "));
        topPanel.add(customerIdText);
        
       
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
     * This method is called when the "addBtn" button is clicked. It creates and executes an AddBooking command
     * with the entered flight id and customer id, and closes the AddBookingWindow.
     * @param ae, take an ae as ActionEvent, to perform certain task on click of button
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
     * The addBook method is used to add a new booking to the FlightBookingSystem using the AddBooking command.
     * It first parses the flightId and customerId from the flightIdText and customerIdText fields respectively.
     * It also gets the current date as the booking date.
     * It then creates an AddBooking command with the given customerId, flightId, and bookingDate and executes it on the FlightBookingSystem.
     * If the execution is successful, it hides the AddBookingWindow and displays a success message to the user.
     * If there is any exception during the execution, it displays an error message to the user.
     * Finally, it refreshes the main window to display the updated list of customers.
     */
    private void addBook() {
        try {
            int flightId = Integer.parseInt(flightIdText.getText());
            int customerId = Integer.parseInt(customerIdText.getText());
            LocalDate bookingDate = LocalDate.now();
    
            Command addBooking = new AddBooking(customerId,flightId,bookingDate);
            addBooking.execute(mw.getFlightBookingSystem());
            this.setVisible(false);
            JOptionPane.showMessageDialog(this,"Booking Issued Successsfully to Customer Id " + customerId + " for Flight Id " + flightId + ".","Success",JOptionPane.INFORMATION_MESSAGE);
            mw.displayCustomers();
        } catch (FlightBookingSystemException ex) {
            JOptionPane.showMessageDialog(this, ex, "Error", JOptionPane.ERROR_MESSAGE);
        }catch (NumberFormatException ex) {
        	JOptionPane.showMessageDialog(this,"Please Enter a Number \n" +ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
        }
    }
}
