package bcu.cmp5332.bookingsystem.gui;

import bcu.cmp5332.bookingsystem.commands.RemoveFlight;

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
 * 
 * The RemoveFlightWindow class represents a graphical user interface for deleting a flight in the flight booking system
 * It extends the JFrame class and implements the ActionListener interface
 * When the delete button is clicked, the delBook() method is called to delete the flight from the system
 * When the cancel button is clicked, the window is closed.
 */
public class RemoveFlightWindow extends JFrame implements ActionListener{
	private MainWindow mw;
    private JTextField flightIdText = new JTextField();
   
    private JButton delBtn = new JButton("Delete");
    private JButton cancelBtn = new JButton("Cancel");
    
    /**
     * 
     * Constructs a new RemoveFlightWindow object, which represents a GUI window for 
     * removing a flight from the flight booking system.
     * @param mw the main window of the flight booking system
     */
    public RemoveFlightWindow(MainWindow mw) {
        this.mw = mw;
        initialize();
    }
    
    /**
     * Initializes the RemoveFlightWindow by setting the look and feel, title, size, and layout of the window.
     * It also adds action listeners to the delete and cancel buttons and sets the window to be visible.
     */
    public void initialize() {
    	 try {
             UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
         } catch (Exception ex) {

         }

         setTitle("Delete a Existing Flight");

         setSize(350, 220);
         JPanel topPanel = new JPanel();
         topPanel.setLayout(new GridLayout(3, 2));
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
     * 
     * This method is called when the delete button or the cancel button is clicked.
     * If delete button is clicked, it calls the delBook() method to delete the flight.
     * If cancel button is clicked, it hides the RemoveFlightWindow.
     * @param ae ActionEvent object that represents the click event
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
     * 
     * This method deletes a flight from the system based on the flight id entered by the user.
     * It updates the system's data and refreshes the view with the updated list of flights.
     * If the flight id entered is not present in the system, it displays an error message.
     * @throws FlightBookingSystemException if there is an error in deleting the flight from the system
     */
    private void delBook() {
        try {
            int flightId = Integer.parseInt(flightIdText.getText());
            
            Command removeFlight = new RemoveFlight(flightId);
            removeFlight.execute(mw.getFlightBookingSystem());
            this.setVisible(false);
            JOptionPane.showMessageDialog(null,"Flight Successfully Removed with Flight ID " + flightId +".","Success",JOptionPane.INFORMATION_MESSAGE);
            mw.displayFlights();

        } catch (FlightBookingSystemException ex) {
            JOptionPane.showMessageDialog(this, ex, "Error", JOptionPane.ERROR_MESSAGE);
        }catch (NumberFormatException ex) {
        	JOptionPane.showMessageDialog(this,"Please Enter a Number \n" +ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
        }
    }

}
