package parkingapp;

import static java.lang.Character.toUpperCase;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author tquigley1
 * 
 * This class describes the check-in functions of the Parking Garage.
 */
public class CheckInStation {
    private char action;
    private boolean validAction = false;
    Scanner keyboard = new Scanner(System.in);
        
    public CheckInStation(ArrayList<Vehicle> vehicle) {
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

    private boolean verifyCheckInAction(char action) {
        boolean checkInAction;
        switch (action) {
            case '1':
            case '3':
                checkInAction = true;
                break;
            default:
                checkInAction = false;
                break;
        }
        return checkInAction;
    }
    
    private void processCheckInInput(char action, ArrayList<Vehicle> vehicle) {
        switch (action) {
            case '1':
                CheckIn checkIn = new CheckIn(vehicle);
                break;
            case '3':
                CloseGarage closeGarage = new CloseGarage(vehicle);
                break;
        }
    }

}
