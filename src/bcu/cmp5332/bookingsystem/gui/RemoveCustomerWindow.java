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
import bcu.cmp5332.bookingsystem.commands.RemoveCustomer;
import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;

/**
 * 
 * @author VishaljiODEDRA
 * @author DhananjayTiwari
 */

/**
 * This class represents a window for deleting a customer from the flight booking system. It implements the ActionListener
 * interface to handle the events triggered by the delete and cancel buttons. It has a main window reference to refresh the
 * view with the updated list of customers after a successful delete operation.
 */

public class RemoveCustomerWindow extends JFrame implements ActionListener{
	private MainWindow mw;
    private JTextField customerIdText = new JTextField();
   
    private JButton delBtn = new JButton("Delete");
    private JButton cancelBtn = new JButton("Cancel");
    
    /**
     * 
     * Constructor to create a RemoveCustomerWindow object and initialize the window.
     * @param mw the main window reference
     */
    public RemoveCustomerWindow(MainWindow mw) {
        this.mw = mw;
        initialize();
    }
    
    /**
     * 
     * This method initializes the RemoveCustomerWindow and sets the look and feel to the system look and feel.
     * It sets the title of the window and its size. It also creates panels for the top and bottom of the window,
     * adds components to the panels and adds the panels to the window. It also adds action listeners to the buttons
     * and sets the window to be visible.
     */
    public void initialize() {
    	 try {
             UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
         } catch (Exception ex) {

         }

         setTitle("Delete a Existing Customer");

         setSize(350, 220);
         JPanel topPanel = new JPanel();
         topPanel.setLayout(new GridLayout(3, 2));
         topPanel.add(new JLabel("Customer Id : "));
         topPanel.add(customerIdText);
         
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
     * The actionPerformed method is called when an action event occurs.
     * @param ae the action event
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
     * This class represents the window for deleting an existing customer from the flight booking system.
     * It extends the JFrame class and implements the ActionListener interface.
     * It contains a text field for entering the customer id and two buttons for deleting the customer or cancelling the action.
     * When the delete button is clicked, the deleteBook() method is called which removes the customer with the specified id from the system.
     * If the operation is successful, the customer is removed and a success message is displayed.
     * Otherwise, an error message is displayed.
     * When the cancel button is clicked, the window is closed.
     */
    private void delBook() {
        try {
            int customerId = Integer.parseInt(customerIdText.getText());
            
            Command removeCustomer = new RemoveCustomer(customerId);
            removeCustomer.execute(mw.getFlightBookingSystem());
            this.setVisible(false);
            JOptionPane.showMessageDialog(null,"Customer Successfully Removed with Customer ID " + customerId+".","Success",JOptionPane.INFORMATION_MESSAGE);
            mw.displayCustomers();
            
        } catch (FlightBookingSystemException ex) {
            JOptionPane.showMessageDialog(this, ex, "Error", JOptionPane.ERROR_MESSAGE);
        }catch (NumberFormatException ex) {
        	JOptionPane.showMessageDialog(this,"Please Enter a Number \n" +ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
        }
    }

}
