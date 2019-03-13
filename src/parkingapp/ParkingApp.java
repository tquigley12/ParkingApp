package parkingapp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import static parkingapp.TicketMachine.vehicleID;
import static parkingapp.TicketMachine.vehiclesInGarage;
import static parkingapp.TicketMachine.endLoop;

/**
 *
 * @author tquigley1
 * 
 * This is the driver class of a Parking Garage application.  The objective is to
 * simulate an automated garage where a vehicle operator drives up to a check-in ticket booth,
 * gets an "entrance" ticket, and the time of arrival is recorded.  When the vehicle exits the 
 * garage, the operator drives up to a check-out ticket booth, gets an "exit" ticket,
 * and pays via the automated teller.  The payment amount, arrival and departure time, are 
 * recorded.
 * 
 * There are two options at the check-in booth:
 * 1.  Check/In
 * 3.  Close Garage
 * 
 * There are two options at the check-out booth:
 * 1.  Check/Out
 * 2.  Lost Ticket
 * 
 * The results from each of the above 4 actions are described in the Unit 1 challenge
 * description.
 * 
 * Following are a few assumptions that have been made to fill in some gaps in the 
 * narrative, or to clarify my interpretation and add features to the Unit 1 challenge:
 * a.  Because there is no accommodation made for a "driver menu" in the specifications
 *     we need a way to determine if a vehicle is checking in or out.  To accomplish
 *     this, I have created a simple random determination.  The number I have used is
 *     a 60% chance of checking in and a 40% chance of checking out.  This is customizable
 *     in class TimeRoutine.
 * b.  There are two exceptions to the random determination of check in or out.  If there
 *     are zero vehicles currently in the garage, it is a check in automatically.  If
 *     the garage is full to capacity, it is a check out automatically.
 * c.  I have expanded the garage to house up to 25 vehicles maximum capacity.  This is
 *     set via a constant in the TicketMachine class.  There is no designation tracked
 *     by the application for specific spaces (e.g. there is no "space 1", "space 2", etc.).
 * d.  For the initial run of the parking garage application, there is no data present.
 *     To be specific, there is no historical data of past transactions, nor are there
 *     any vehicles in the garage.
 * e.  Historical data of transactions will be retained indefinitely.  This will 
 *     appear on the Activity to Date report.
 * f.  Each time a vehicle arrives in the garage, it will be assigned a new vehicle
 *     id.  The vehicle id is a sequential integer beginning with 1 and incrementing
 *     by 1.  The application will not care whether the same vehicle parks multiple
 *     times, as no record is kept on specific vehicles.
 * g.  When the "Close Garage" option is chosen, the application will create an activity
 *     report and close the program.  All vehicles currently in the garage will remain 
 *     in the garage.
 */
public class ParkingApp {

    public static void main(String[] args) throws FileNotFoundException {
        String inputRecord;
        int loadVehicleID = 0;
        int checkInTime = 0;
        int checkOutTime = 0;
        double vehicleCharge;
        String lostTicketString;
        boolean lostTicket;

        // Populate initial Parking Garage using input file
        
        // Open input file
        String filename = "ParkingApp.txt";
        File file = new File(filename);
        Scanner inputFile = new Scanner(file);
        
        // Read through input file and populate ArrayList
        ArrayList<Vehicle> vehicle = new ArrayList<Vehicle>();
        vehiclesInGarage = 0;
        while (inputFile.hasNextLine()) {
            inputRecord = inputFile.nextLine();
            loadVehicleID = Integer.parseInt(inputRecord.substring(0,4).trim());
            checkInTime = Integer.parseInt(inputRecord.substring(4,8).trim());
            checkOutTime = Integer.parseInt(inputRecord.substring(8,12).trim());
            if (checkOutTime == 0) {
                vehiclesInGarage++;
            }
            lostTicketString = inputRecord.substring(12,17);
            if (lostTicketString.equals("false")) {
                lostTicket = false;
            } else {
                lostTicket = true;
            }
            vehicleCharge = Double.parseDouble(inputRecord.substring(17).trim());
            vehicle.add(new Vehicle(loadVehicleID, checkInTime, checkOutTime, vehicleCharge, lostTicket));
        }
        // Close input file
        inputFile.close();

        // Execute main loop of Parking Garage application
        vehicleID = loadVehicleID;
        do {
            TicketMachine ticketMachine = new TicketMachine(vehicle);
        } while (!endLoop);

        // Open output file
        PrintWriter outputFile = new PrintWriter(filename);
        
        // Write all members of vehicle to output file
        if (!vehicle.isEmpty()) {
            for (int loop = 0; loop < vehicle.size(); loop++) {
                outputFile.printf("%-4d%-4d%-4d%-5b%-20f\n", vehicle.get(loop).getVehicleID(), vehicle.get(loop).getCheckInTime(), 
                        vehicle.get(loop).getCheckOutTime(), vehicle.get(loop).getLostTicket(), vehicle.get(loop).getVehicleCharge());
            }
        }
        
        // Close output file
        outputFile.close();
        
    }

}
