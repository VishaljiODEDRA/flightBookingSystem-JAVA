package bcu.cmp5332.bookingsystem.gui;

import bcu.cmp5332.bookingsystem.commands.AddCustomer;

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
 * AddCustomerWindow is a JFrame class that allows users to add a new customer to the flight booking system
 * It displays a form where the user can enter the customer's name, phone, and email. When the user clicks the "Add"
 * button, the class creates an AddCustomer command and executes it using the flight booking system. If the command
 * is successful, the window is closed and a message is displayed to the user indicating that the customer 
 * successfully added. If there is an error, a message is displayed to the user indicating the error.
 */
public class AddCustomerWindow extends JFrame implements ActionListener {

    private MainWindow mw;
    private JTextField nameText = new JTextField();
    private JTextField phoneText = new JTextField();
    private JTextField emailText = new JTextField();
    

    private JButton addBtn = new JButton("Add");
    private JButton cancelBtn = new JButton("Cancel");

    /**
     * Constructs a new AddCustomerWindow and initializes the frame.
     * @param mw the MainWindow to which this AddCustomerWindow belongs
     */
    public AddCustomerWindow(MainWindow mw) {
        this.mw = mw;
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    
    /**
     * Initializes the components and layout of the Add Customer window.
     * The window allows the user to enter the name, phone and email of a customer,
     * and adds the customer to the flight booking system upon clicking the "Add" button.
     * The window can be closed by clicking the "Cancel" button.
     * If an error occurs during the addition of the customer, an error message is displayed.
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
        topPanel.add(new JLabel("Name : "));
        topPanel.add(nameText);
        topPanel.add(new JLabel("Phone : "));
        topPanel.add(phoneText);
        topPanel.add(new JLabel("Email : "));
        topPanel.add(emailText);
       
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
     * This is the event handler method for the addBtn and cancelBtn buttons.
     * When the addBtn button is clicked, the addBook() method is called.
     * When the cancelBtn button is clicked, the current frame is set to not be visible.
     * @param ae the ActionEvent object that contains information about the event that occurred
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
     * This method is used to add a new customer to the flight booking system.
     * It retrieves the customer name, phone number, and email from the text fields, 
     * creates a new AddCustomer command, and executes it using the flight booking system.
     * If the add customer operation is successful, a message is displayed and the main window's 
     * displayCustomers() method is called to refresh the list of customers.
     * If there is an exception, an error message is displayed.
     */

    private void addBook() {
        try {
            String name = nameText.getText();
            String phone = phoneText.getText();
            String email = emailText.getText();
            
            Command addCustomer = new AddCustomer(name,phone,email);
            addCustomer.execute(mw.getFlightBookingSystem());
          
            this.setVisible(false);
            JOptionPane.showMessageDialog(null,"Customer Successfully added with Customer name "+ name +".","Success",JOptionPane.INFORMATION_MESSAGE);
            mw.displayCustomers();

        } catch (FlightBookingSystemException ex) {
            JOptionPane.showMessageDialog(this, ex, "Error", JOptionPane.ERROR_MESSAGE);
        }catch (NumberFormatException ex) {
        	JOptionPane.showMessageDialog(this,"Please Enter a Number \n" +ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
        }
    }

}
