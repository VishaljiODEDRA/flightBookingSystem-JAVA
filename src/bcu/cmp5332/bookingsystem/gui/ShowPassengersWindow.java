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

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;

/**
 * 
 * @author VishaljiODEDRA
 * @author DhananjayTiwari
 */

/**
 * 
 * ShowPassengersWindow is a JFrame class that allows the user to view a list of passengers on a specific flight.
 * It contains a text field for the user to input the flight id and a "Show" button to display the list of passengers.
 * It also contains a "Cancel" button to close the window.
 * The class implements the ActionListener interface to handle the button clicks. When the "Show" button is clicked,
 * the showBook() method is called which retrieves the flight id from the text field and calls the displayPassengers()
 * method of the MainWindow class to display the list of passengers. When the "Cancel" button is clicked, the window
 * is closed.
 */
public class ShowPassengersWindow extends JFrame implements ActionListener {

    private MainWindow mw;
    private JTextField flightIdText= new JTextField();
    
    private JButton showBtn = new JButton("Show");
    private JButton cancelBtn = new JButton("Cancel");
    
    /**
     * 
     * Constructs a new ShowPassengersWindow with the specified MainWindow.
     * @param mw the MainWindow to associate with this ShowPassengersWindow
     */
    public ShowPassengersWindow(MainWindow mw) {
        this.mw = mw;
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    
    /**
     * 
     * Initializes the frame and sets up the UI.
     * Sets the title, size, and layout of the frame. Adds the necessary components
     * to the frame and sets their layout. Adds action listeners to the buttons and
     * sets the frame to be visible.
     */
    private void initialize() {

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {

        }

        setTitle("Show Passengers");

        setSize(400, 300);
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(4, 2));
        topPanel.add(new JLabel("Flight Id : "));
        topPanel.add(flightIdText);
        
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
     * This method is called when the "Show" button is clicked. 
     * It retrieves the flight id from the flightIdText field and calls the MainWindow's displayPassengers() 
     * method with the retrieved flight id. Finally, it hides the ShowPassengersWindow.
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
     * Action listener for the showBtn and cancelBtn buttons in the ShowPassengersWindow class.
     * If showBtn is clicked, the method showBook() is called which displays the list of passengers for the specified flight.
     * If cancelBtn is clicked, the window is closed.
     * @param ae the ActionEvent object that triggered the listener
     */
    private void showBook(){
        try {
            int flightId = Integer.parseInt(flightIdText.getText());
            this.setVisible(false);
            mw.displayPassengers(flightId);
            
        }catch (NumberFormatException ex) {
        	JOptionPane.showMessageDialog(this,"Please Enter a Number \n" +ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
        }catch (FlightBookingSystemException ex) {
            JOptionPane.showMessageDialog(this, ex, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
