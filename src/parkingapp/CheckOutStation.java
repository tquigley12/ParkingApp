package parkingapp;

import static java.lang.Character.toUpperCase;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author tquigley1
 * 
 * This class describes the check-out functions of the Parking Garage.
 */
public class CheckOutStation {
    private char action;
    private boolean validAction = false;
    Scanner keyboard = new Scanner(System.in);
    
    public CheckOutStation(ArrayList<Vehicle> vehicle) {
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

    private boolean verifyCheckOutAction(char action) {
        switch (action) {
            case '1':
            case '2':
                return true;
            default:
                return false;
        }
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
        CheckOut checkOut = new CheckOut(vehicle, lostTicket);
    }

}
