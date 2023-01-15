package bcu.cmp5332.bookingsystem.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

import bcu.cmp5332.bookingsystem.commands.Command;
import bcu.cmp5332.bookingsystem.commands.CancelBooking;
import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;

/**
 * 
 * @author VishaljiODEDRA
 * @author DhananjayTiwari
 */

/**
 * This class represents a GUI window that allows the user to delete a booking from the flight booking system.
 * The window consists of a form that takes in the customer ID and flight ID of the booking to be deleted, and has
 * buttons to delete the booking or cancel the delete operation. When the delete button is clicked, the
 * {@code delBook} method is called, which creates a {@code CancelBooking} command object and executes it on the
 * flight booking system, passing in the customer ID and flight ID entered by the user. If the delete operation is
 * successful, a success message is displayed to the user and the main window's customer list is refreshed. If an
 * error occurs, an error message is displayed to the user.
 */
public class RemoveBookingWindow extends JFrame implements ActionListener{
	private MainWindow mw;
    private JTextField customerIdText = new JTextField();
    private JTextField flightIdText = new JTextField();
   
    private JButton delBtn = new JButton("Delete");
    private JButton cancelBtn = new JButton("Cancel");
    
    /**
     * Constructs a new RemoveBookingWindow object and initializes the GUI.
     * @param mw the main window of the application
     */
    public RemoveBookingWindow(MainWindow mw) {
        this.mw = mw;
        initialize();
    }
    
    /**
     * This method initializes the RemoveBookingWindow and sets its properties. 
     * It sets the title, size, layout, and action listeners for the window and its components.
     * It also makes the window visible.
     */
    public void initialize() {
    	 try {
             UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
         } catch (Exception ex) {

         }

         setTitle("Delete a Existing Booking");

         setSize(350, 220);
         JPanel topPanel = new JPanel();
         topPanel.setLayout(new GridLayout(3, 2));
         topPanel.add(new JLabel("Customer Id : "));
         topPanel.add(customerIdText);
         topPanel.add(new JLabel("Flight Id : "));
         topPanel.add(flightIdText);
         
         JPanel bottomPanel = new JPanel();
         bottomPanel.setLayout(new GridLayout(1, 3));
         bottomPanel.add(new JLabel("     "));
         bottomPanel.add(delBtn);
         bottomPanel.add(cancelBtn);

         delBtn.addActionListener(this);
         cancelBtn.addActionListener(this);

         this.getContentPane().add(topPanel, BorderLayout.CENTER);
         this.getContentPane().add(bottomPanel, BorderLayout.SOUTH);
         setLocationRelativeTo(mw);

         setVisible(true);
    	
    }
    
    /**
     * This method is called whenever an action is performed on the delete button or the cancel button.
     * If the delete button is clicked, the delBook() method is called. If the cancel button is clicked,
     * the window is closed and made invisible.
     * @param ae the ActionEvent object that represents the action performed
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == delBtn) {
            delBook();
        } else if (ae.getSource() == cancelBtn) {
            this.setVisible(false);
        }

    }
    
    /**
     * The delBook method cancels an existing booking for the given customer ID and flight ID.
     * It first creates a new instance of the CancelBooking command, passing in the customer and flight IDs.
     * It then executes the command on the flight booking system, which removes the booking from the system.
     * The method then hides the RemoveBookingWindow and displays a success message to the user. It also refreshes the view of customers in the main window.
     * If an exception is thrown, it displays an error message to the user.
     */
    private void delBook() {
        try {
            int customerId = Integer.parseInt(customerIdText.getText());
            int flightId = Integer.parseInt(flightIdText.getText());
            
            Command removeBooking = new CancelBooking(customerId,flightId);
            removeBooking.execute(mw.getFlightBookingSystem());
            // refresh the view with the list of flights
                   
           
            // hide (close) the AddFlightWindow
            this.setVisible(false);
            JOptionPane.showMessageDialog(null,"Booking Successsfully cancelled for Customer ID " + customerId + " with Flight Id " + flightId + ".","Success",JOptionPane.INFORMATION_MESSAGE);
            mw.displayCustomers();
            
        } catch (FlightBookingSystemException ex) {
            JOptionPane.showMessageDialog(this, ex, "Error", JOptionPane.ERROR_MESSAGE);
        }catch (NumberFormatException ex) {
        	JOptionPane.showMessageDialog(this,"Please Enter a Number \n" +ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
        }
    }

}
