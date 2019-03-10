package parkingapp;

/**
 *
 * @author tquigley1
 * 
 * This class describes the vehicles that enter and leave the parking garage.
 */
public class Vehicle {
    private final int vehicleID;
    private final int checkInTime;
    private int checkOutTime;
    private double vehicleCharge;
    private boolean lostTicket;
    private final int MINIMUM_TIME = 3;
    private final double MINIMUM_CHARGE = 5.00;
    private final double PER_HOUR_CHARGE = 1.00;
    private final double MAXIMUM_CHARGE_WITH_TICKET = 15.00;
    private final double LOST_TICKET_CHARGE = 25.00;
    
public Vehicle(int vehicleID, int checkInTime) {
    this.vehicleID = vehicleID;
    this.checkInTime = checkInTime;
    checkOutTime = 0;
    vehicleCharge = 0.00;
    lostTicket = false;
}

public Vehicle(int vehicleID, int checkInTime, int checkOutTime, double vehicleCharge, boolean lostTicket) {
    this.vehicleID = vehicleID;
    this.checkInTime = checkInTime;
    this.checkOutTime = checkOutTime;
    this.vehicleCharge = vehicleCharge;
    this.lostTicket = lostTicket;
}

    public int getVehicleID() {
        return vehicleID;
    }

    public int getCheckInTime() {
        return checkInTime;
    }

    public int getCheckOutTime() {
        return checkOutTime;
    }

    public double getVehicleCharge() {
        return vehicleCharge;
    }

    public boolean getLostTicket() {
        return lostTicket;
    }
    
    public void setCheckOutTime(int checkOutTime) {
        this.checkOutTime = checkOutTime;
    }

    public void setVehicleCharge(double vehicleCharge) {
        this.vehicleCharge = vehicleCharge;
    }

    public void setLostTicket(boolean lostTicket) {
        this.lostTicket = lostTicket;
    }
    
    public double getCharge(boolean lostTicket) {
        if (lostTicket) {
            vehicleCharge = LOST_TICKET_CHARGE;
        } else if ((checkOutTime - checkInTime) <= MINIMUM_TIME) {
            vehicleCharge = MINIMUM_CHARGE;
        } else {
            vehicleCharge = ((checkOutTime - checkInTime - MINIMUM_TIME) * PER_HOUR_CHARGE) + MINIMUM_CHARGE;
            if (vehicleCharge > MAXIMUM_CHARGE_WITH_TICKET) {
                vehicleCharge = MAXIMUM_CHARGE_WITH_TICKET;
            }
        }
        return vehicleCharge;
    }
    
}
