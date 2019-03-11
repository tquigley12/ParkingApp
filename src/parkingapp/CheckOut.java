package parkingapp;

import java.util.ArrayList;
import static parkingapp.TicketMachine.vehiclesInGarage;

/**
 *
 * @author tquigley1
 * 
 * This class describes the function of vehicle check-out from the Parking Garage.
 * Both with processed ticket and lost ticket.
 */
public class CheckOut {
    
    public CheckOut(ArrayList<Vehicle> vehicle, boolean lostTicket) {
        TimeRoutine timeRoutine = new TimeRoutine();
        int checkOutIndex = getCheckOutIndex(vehicle);
        int localVehicleID = vehicle.get(checkOutIndex).getVehicleID();
        int localCheckInTime = vehicle.get(checkOutIndex).getCheckInTime();
        int localCheckOutTime = timeRoutine.getCheckOutTime();
        vehicle.get(checkOutIndex).setCheckOutTime(localCheckOutTime);
        double localVehicleCharge = vehicle.get(checkOutIndex).getCharge(lostTicket);
        vehicle.get(checkOutIndex).setVehicleCharge(localVehicleCharge);
        vehicle.get(checkOutIndex).setLostTicket(lostTicket);
        System.out.println("");
        System.out.println("Best Value Parking Garage");
        System.out.println("");
        System.out.println("=========================");
        System.out.println("");
        System.out.println("Receipt for a vehicle id " + localVehicleID);
        System.out.println("");
        System.out.println("");
        if (lostTicket) {
            System.out.println("Lost Ticket");
        } else {
            System.out.println((localCheckOutTime - localCheckInTime) + " hours parked  " +
                   (localCheckInTime <= 12 ? localCheckInTime : (localCheckInTime - 12)) +
                   (localCheckInTime < 12 ? "am" : "pm") + " - " +
                   (localCheckOutTime <= 12 ? localCheckOutTime : (localCheckOutTime - 12)) +
                   (localCheckOutTime < 12 ? "am" : "pm"));
        }
        System.out.println("");
        System.out.println(String.format("$%,.2f", localVehicleCharge));
        System.out.println("");
        System.out.println("");
        vehiclesInGarage--;  
    }
    
    private int getCheckOutIndex(ArrayList<Vehicle> vehicle) {
        int checkOutIndex = 0;
        int vehicleCheckOut = (int) (Math.random() * vehiclesInGarage + 1);
        int searchIndex = 0;
        int numberOfVehicleHits = 0;
        do {
            if (vehicle.get(searchIndex).getCheckOutTime() == 0) {
                numberOfVehicleHits++;
            }
            if (numberOfVehicleHits != vehicleCheckOut) {
                searchIndex++;
            }
        } while (numberOfVehicleHits != vehicleCheckOut);
        checkOutIndex = searchIndex;
        return checkOutIndex;
    }
    
}
