package parkingapp;

import java.util.ArrayList;
import static parkingapp.TicketMachine.vehicleID;
import static parkingapp.TicketMachine.vehiclesInGarage;

/**
 *
 * @author tquigley1
 * 
 * This class describes the function of vehicle check-in to the Parking Garage.
 */
public class CheckIn {
    
    public CheckIn(ArrayList<Vehicle> vehicle) {
        TimeRoutine timeRoutine = new TimeRoutine();
        vehicleID++;
        vehicle.add(new Vehicle(vehicleID, timeRoutine.getCheckInTime()));
        vehiclesInGarage++;
    }
    
}
