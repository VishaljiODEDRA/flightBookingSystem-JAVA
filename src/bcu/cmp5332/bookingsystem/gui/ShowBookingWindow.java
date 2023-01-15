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

import bcu.cmp5332.bookingsystem.commands.AddCustomer;
import bcu.cmp5332.bookingsystem.commands.Command;
import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;

/**
 * 
 * @author VishaljiODEDRA
 * @author DhananjayTiwari
 */


/**
 * 
 * This class represents a window for showing bookings. 
 * It allows the user to enter a customer ID and displays the bookings made by the customer.
 * A text field for entering the customer ID.
 * A button for showing the bookings.
 * A button for cancelling the operation.
 */
public class ShowBookingWindow extends JFrame implements ActionListener {

    private MainWindow mw;
    private JTextField customerIdText= new JTextField(); 
    
    private JButton showBtn = new JButton("Show");
    private JButton cancelBtn = new JButton("Cancel");

    /**
     * 
     * Constructs a ShowBookingWindow with the specified main window.
     * @param mw the main window
     */
    public ShowBookingWindow(MainWindow mw) {
        this.mw = mw;
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    
    /**
     * 
     * Initialize the window to display the bookings made by a customer.
     * The window allows the user to enter the customer id and view the bookings made by the customer.
     * It also has buttons to cancel the operation and show the bookings.
     */
    private void initialize() {

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {

        }

        setTitle("Show Bookings");

        setSize(400, 300);
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(4, 2));
        topPanel.add(new JLabel("Customer Id : "));
        topPanel.add(customerIdText);
        
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(1, 3));
        bottomPanel.add(new JLabel("     "));
        bottomPanel.add(showBtn);
        bottomPanel.add(cancelBtn);

        showBtn.addActionListener(this);
        cancelBtn.addActionListener(this);

        this.getContentPane().add(topPanel, BorderLayout.CENTER);
        this.getContentPane().add(bottomPanel, BorderLayout.SOUTH);
        setLocationRelativeTo(mw);

        setVisible(true);

    }
    
    /**
     * 
     * This method is called when the show button is clicked. It retrieves the customer ID entered in the text field,
     * and calls the 'displayBookings' method in the MainWindow class to show the bookings for the specified customer.
     * The ShowBookingWindow window is then closed.
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == showBtn) {
            showBook();
        } else if (ae.getSource() == cancelBtn) {
            this.setVisible(false);
        }

    }
    
    /**
     * 
     * This method shows the bookings of a customer with the given customer id.
     * If a booking is not found for the given customer id, an exception is thrown.
     * Once the bookings are displayed, the window is closed.
     */
    private void showBook(){
        try {
            int customerId = Integer.parseInt(customerIdText.getText());
            this.setVisible(false);
            mw.displayBookings(customerId);
        
            
        }catch (NumberFormatException ex) {
        	JOptionPane.showMessageDialog(this,"Please Enter a Number \n" +ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
        }catch (FlightBookingSystemException ex) {
            JOptionPane.showMessageDialog(this, ex, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
