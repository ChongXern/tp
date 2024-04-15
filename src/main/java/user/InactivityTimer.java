package user;

import customexceptions.InactivityTimeoutException;

//@@author ChongXern
public class InactivityTimer {
    private static final int INACTIVITY_TIME = 180_000;
    private static final int GRACE_TIME = 30_000;
    public long startTime;

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
            throw new InactivityTimeoutException(true, false);
        } else if (timeDifference >= INACTIVITY_TIME - GRACE_TIME) {
            throw new InactivityTimeoutException(false, true);
        }
    }

    public void checkTimeFromStart() {
        long timeDifference = System.currentTimeMillis() - startTime;
        System.out.println(timeDifference + "ms has passed");
    }
}
