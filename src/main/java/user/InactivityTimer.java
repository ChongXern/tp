package user;

import customexceptions.InactivityTimeoutException;

//@@author ChongXern
public class InactivityTimer {
    public static final int INACTIVITY_TIME = 180_000;
    public static final int GRACE_TIME = 30_000;
    public static final int PERMITTED_UNDO_TIME = 10_000;
    private long startTime;

    public InactivityTimer() {
        startTime = System.currentTimeMillis();
    }

    public void resetTimer() {
        assert startTime != System.currentTimeMillis();
        startTime = System.currentTimeMillis();
    }
    
    public void checkTimeElapsed() throws InactivityTimeoutException {
        long timeDifference = System.currentTimeMillis() - startTime;
        if (timeDifference >= INACTIVITY_TIME) {
            System.out.println("Sorry, your session has ended. Please log in again.");
            throw new InactivityTimeoutException(true, false);
        } else if (timeDifference >= INACTIVITY_TIME - GRACE_TIME) {
            throw new InactivityTimeoutException(false, true);
        }
    }

    public boolean canUndo() {
        long timeDifference = System.currentTimeMillis() - startTime;
        return timeDifference <= PERMITTED_UNDO_TIME;
    }
}
