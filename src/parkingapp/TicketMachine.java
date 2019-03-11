package parkingapp;

import java.util.ArrayList;

/**
 *
 * @author tquigley1
 * 
 * This class performs functions related to the ticket machine in the parking garage.
 */
public class TicketMachine {
    public static final int GARAGE_CAPACITY = 25;
    public static int vehiclesInGarage;
    public static int vehicleID;
    public static boolean endLoop = false;

public TicketMachine(ArrayList<Vehicle> vehicle) {
    if (isCheckIn()) {
        CheckInStation checkInStation = new CheckInStation(vehicle);
    } else {
        CheckOutStation checkOutStation = new CheckOutStation(vehicle);
    }
}

    /**
    * The following method determines whether it is a check-in or check-out and returns
    * a boolean value (true if check-in, and false if check-out).
    */
    private boolean isCheckIn() {
        boolean checkIn;
        final int RANDOM_VALUE_RANGE = 10;
        final int CHECK_IN_VALUE_MAX = 6;
        if (isGarageEmpty()) {
            checkIn = true;
        } else if (isGarageFull()) {
            checkIn = false;
        } else if ((int) (Math.random() * RANDOM_VALUE_RANGE + 1) <= CHECK_IN_VALUE_MAX) {
            checkIn = true;
        } else {
            checkIn = false;
        }
        return checkIn;
    }

    private boolean isGarageEmpty() {
        boolean garageEmpty;
        if (vehiclesInGarage == 0) {
            garageEmpty = true;
        } else {
            garageEmpty = false;
        }
        return garageEmpty;
    }

    private boolean isGarageFull() {
        boolean garageFull;
        if (vehiclesInGarage == GARAGE_CAPACITY) {
            garageFull = true;
        } else {
            garageFull = false;
        }
            return garageFull;
    }

}