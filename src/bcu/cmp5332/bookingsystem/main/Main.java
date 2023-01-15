package bcu.cmp5332.bookingsystem.main;

import bcu.cmp5332.bookingsystem.data.FlightBookingSystemData;
import bcu.cmp5332.bookingsystem.commands.Command;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

import java.io.*;

/**
 * 
 * @author VishaljiODEDRA
 * @author DhananjayTiwari
 */

public class Main {

    public static void main(String[] args) throws IOException, FlightBookingSystemException {
    	try {
       
        FlightBookingSystem fbs = FlightBookingSystemData.load();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Flight Booking System");
        System.out.println("Enter 'help' to see a list of available commands.");
        while (true) {
            System.out.print("> ");
            String line = br.readLine();
            if (line.equals("exit")) {
                break;
            }

            try {
                Command command = CommandParser.parse(line);
                command.execute(fbs);
                FlightBookingSystemData.store(fbs);
            } catch (FlightBookingSystemException ex) {
                System.out.println(ex.getMessage());
            } 
        }
     
        System.exit(0);
    	}catch(NumberFormatException ex) {
    		System.out.print(ex.getMessage());
    	}catch (FlightBookingSystemException ex) {
            System.out.println("File is Already in Use or Corrupted !!.");
            System.exit(0);
            
        }
    }
}
