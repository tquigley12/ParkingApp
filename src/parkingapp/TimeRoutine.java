package parkingapp;

/**
 *
 * @author tquigley1
 * 
 * This class contains methods for time related activities for the parking garage application.
 */
public class TimeRoutine {
    private final int CHECK_IN_BEGIN_TIME = 7;
    private final int CHECK_IN_END_TIME = 12;
    private final int CHECK_OUT_BEGIN_TIME = 13;
    private final int CHECK_OUT_END_TIME = 23;
    
public TimeRoutine() {
    
}

    public int getCheckInTime() {
        int checkInTime = (int) (Math.random() * (CHECK_IN_END_TIME - CHECK_IN_BEGIN_TIME + 1) + CHECK_IN_BEGIN_TIME);
        return checkInTime;
    }
    
    public int getCheckOutTime() {
        int checkOutTime = (int) (Math.random() * (CHECK_OUT_END_TIME - CHECK_OUT_BEGIN_TIME + 1) + CHECK_OUT_BEGIN_TIME);
        return checkOutTime;
    }
    
    public int getClosingTime() {
        return CHECK_OUT_END_TIME;
    }
    
}
