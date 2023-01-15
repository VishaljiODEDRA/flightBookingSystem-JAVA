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

import bcu.cmp5332.bookingsystem.commands.EditBooking;
import bcu.cmp5332.bookingsystem.commands.Command;
import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;

/**
 * 
 * @author VishaljiODEDRA
 * @author DhananjayTiwari
 */

/**
 * 
 * UpdateBookingWindow is a GUI class that allows the user to update a booking by providing the booking ID and the flight ID
 * It provides text fields to input these values and buttons to submit the update or cancel the operation
 * The updateBook() method is called when the update button is clicked, and it uses the EditBooking command to update the booking
 * The actionPerformed() method handles the button clicks and calls the appropriate methods.
 */

public class UpdateBookingWindow extends JFrame implements ActionListener {

    private MainWindow mw;
    private JTextField flightIdText= new JTextField();
    private JTextField bookingIdText = new JTextField();
    

    private JButton updateBtn = new JButton("Add");
    private JButton cancelBtn = new JButton("Cancel");
    
    /**
     * 
     * Constructor for the UpdateBookingWindow class. This creates a new window for updating a booking.
     * @param mw the MainWindow object that is the parent of this window
     */
    public UpdateBookingWindow(MainWindow mw) {
        this.mw = mw;
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    
    /**
     * 
     * Initializes the UpdateBookingWindow by creating and displaying the GUI components.
     * The window allows the user to update an existing booking with a new flight.
     * It includes text fields for the booking id and flight id, and buttons to update the booking or cancel the operation.
     * The look and feel of the window is set to the system look and feel.
     * The window is centered relative to the main window.
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
        topPanel.add(new JLabel("Booking Id : "));
        topPanel.add(bookingIdText);
        topPanel.add(new JLabel("Flight Id : "));
        topPanel.add(flightIdText);
        
       
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(1, 3));
        bottomPanel.add(new JLabel("     "));
        bottomPanel.add(updateBtn);
        bottomPanel.add(cancelBtn);

        updateBtn.addActionListener(this);
        cancelBtn.addActionListener(this);

        this.getContentPane().add(topPanel, BorderLayout.CENTER);
        this.getContentPane().add(bottomPanel, BorderLayout.SOUTH);
        setLocationRelativeTo(mw);

        setVisible(true);

    }
    
    /**
     * 
     * Action listener for the update booking window.
     * @param ae ActionEvent object that is triggered when an action is performed
     * on a GUI component.
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == updateBtn) {
            updateBook();
        } else if (ae.getSource() == cancelBtn) {
            this.setVisible(false);
        }

    }
    
    /**
     *
     * This method updates a booking by modifying the flight associated with the booking.
     * It takes in the flight ID and booking ID and uses the EditBooking command to update the booking in the
     * FlightBookingSystem. If the update is successful, it closes the window and displays a success message.
     * If there is an exception during the update process, it displays an error message.
     * @param flightId the ID of the flight to be associated with the booking
     * @param bookingId the ID of the booking to be updated
     */
    private void updateBook() {
        try {
            int flightId = Integer.parseInt(flightIdText.getText());
            int bookingId = Integer.parseInt(bookingIdText.getText());
           
            Command updateBooking = new EditBooking(bookingId,flightId);
            updateBooking.execute(mw.getFlightBookingSystem());
          
            this.setVisible(false);
            JOptionPane.showMessageDialog(null,"Booking Successfully Updated with Booking ID " + bookingId + ".","Success",JOptionPane.INFORMATION_MESSAGE);
            mw.displayCustomers();

        } catch (FlightBookingSystemException ex) {
            JOptionPane.showMessageDialog(this, ex, "Error", JOptionPane.ERROR_MESSAGE);
        }catch (NumberFormatException ex) {
        	JOptionPane.showMessageDialog(this,"Please Enter a Number \n" +ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
        }
    }
}
