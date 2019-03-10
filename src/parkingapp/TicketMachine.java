package parkingapp;

import static java.lang.Character.toUpperCase;
import java.util.ArrayList;
import java.util.Scanner;

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
    private boolean validAction = false;
    private char action;
    private TimeRoutine timeRoutine = new TimeRoutine();
    Scanner keyboard = new Scanner(System.in);

public TicketMachine(ArrayList<Vehicle> vehicle) {
    if (isCheckIn()) {
        checkInMenu(vehicle);
    } else {
        checkOutMenu(vehicle);
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

    private void checkInMenu(ArrayList<Vehicle> vehicle) {
        writeCheckInMenu();
        action = toUpperCase(keyboard.nextLine().charAt(0));
        validAction = verifyCheckInAction(action);
        while (!validAction) {
            System.out.println("Invalid action.  Please re-enter. ");
            System.out.println("");
            writeCheckInMenu();
            action = toUpperCase(keyboard.nextLine().charAt(0));
            validAction = verifyCheckInAction(action);
        }
        processCheckInInput(action, vehicle);
    }

    private boolean verifyCheckInAction(char action) {
        boolean validAction;
        switch (action) {
            case '1':
            case '3':
                validAction = true;
                break;
            default:
                validAction = false;
                break;
        }
        return validAction;
    }

    private void writeCheckInMenu() {
        System.out.println("");
        System.out.println("Best Value Parking Garage");
        System.out.println("");
        System.out.println("=========================");
        System.out.println("");
        System.out.println("1 - Check/In");
        System.out.println("");
        System.out.println("3 - Close Garage");
        System.out.println("");
        System.out.print("==> ");
    }

    private void processCheckInInput(char action, ArrayList<Vehicle> vehicle) {
        switch (action) {
            case '1':
                checkIn(vehicle);
                break;
            case '3':
                closeGarage(vehicle);
                break;
        }
    }

    private void checkIn(ArrayList<Vehicle> vehicle) {
        vehicleID++;
        vehicle.add(new Vehicle(vehicleID, timeRoutine.getCheckInTime()));
        vehiclesInGarage++;
    }

    private void closeGarage(ArrayList<Vehicle> vehicle) {
        int checkIns = 0;
        int lostTickets = 0;
        int loopIndex;
        double collectedFunds = 0.00;
        double collectedFromCheckIns = 0.00;
        double collectedFromLostTickets = 0.00;
        double collectedOverall = 0.00;
        for (loopIndex = 0; loopIndex < vehicle.size(); loopIndex++) {
            collectedFunds = vehicle.get(loopIndex).getVehicleCharge();
            if (collectedFunds > 0.00) {
                if (vehicle.get(loopIndex).getLostTicket()) {
                    lostTickets = lostTickets + 1;
                    collectedFromLostTickets = collectedFromLostTickets + collectedFunds;
                } else {
                    checkIns = checkIns + 1;
                    collectedFromCheckIns = collectedFromCheckIns + collectedFunds;
                }
                collectedOverall = collectedOverall + collectedFunds;
            }
        }
        System.out.println("");
        System.out.println("Best Value Parking Garage");
        System.out.println("");
        System.out.println("=========================");
        System.out.println("");
        System.out.println("Activity to Date");
        System.out.println("");
        System.out.println("");
        System.out.println(String.format("$%,.0f", collectedFromCheckIns) + " was collected from " +
                    checkIns + " Check Ins");
        System.out.println("");
        System.out.println(String.format("$%,.0f", collectedFromLostTickets) + " was collected from " +
                    lostTickets + " Lost Tickets");
        System.out.println("");
        System.out.println("");
        System.out.println(String.format("$%,.0f", collectedOverall) + " was collected overall");
        endLoop = true;
    }

    private void checkOutMenu(ArrayList<Vehicle> vehicle) {
        writeCheckOutMenu();
        action = toUpperCase(keyboard.nextLine().charAt(0));
        validAction = verifyCheckOutAction(action);
        while (!validAction) {
            System.out.println("Invalid action.  Please re-enter. ");
            System.out.println("");
            writeCheckOutMenu();
            action = toUpperCase(keyboard.nextLine().charAt(0));
            validAction = verifyCheckOutAction(action);
        }
        processCheckOutInput(action, vehicle);
    }

    private boolean verifyCheckOutAction(char action) {
        switch (action) {
            case '1':
            case '2':
                return true;
            default:
                return false;
        }
    }

    private void writeCheckOutMenu() {
        System.out.println("");
        System.out.println("Best Value Parking Garage");
        System.out.println("");
        System.out.println("=========================");
        System.out.println("");
        System.out.println("1 - Check/Out");
        System.out.println("");
        System.out.println("2 - Lost Ticket");
        System.out.println("");
        System.out.print("==> ");
    }

    private void processCheckOutInput(char action, ArrayList<Vehicle> vehicle) {
        boolean lostTicket;
        switch (action) {
            case '1':
                lostTicket = false;
                break;
            case '2':
                lostTicket = true;
                break;
            default:
                lostTicket = false;
                break;
        }
        checkOut(vehicle, lostTicket);
    }

    private void checkOut(ArrayList<Vehicle> vehicle, boolean lostTicket) {
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
        //            (localCheckInTime < 12 ? localCheckInTime + "am" : (localCheckInTime - 12) + "pm") + " - " +
        //            (localCheckOutTime < 12 ? localCheckOutTime + "am" : (localCheckOutTime - 12) + "pm"));
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
