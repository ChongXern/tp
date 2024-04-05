package customexceptions;

public class InactivityTimeoutException extends Exception {
    private boolean isTimeOut = false; //Returns true if system is past 3 min, false otherwise
    private boolean isGracePeriod = false; //Returns true if system is past 2.5 min, false otherwise

    public InactivityTimeoutException(boolean isTimeOut, boolean isGracePeriod) {
        super("Session has timed out");
        this.isTimeOut = isTimeOut;
        this.isGracePeriod = isGracePeriod;
    }

    public boolean isTimeOut() {
        return isTimeOut;
    }
    public boolean isGracePeriod() {
        return isGracePeriod;
    }
}
