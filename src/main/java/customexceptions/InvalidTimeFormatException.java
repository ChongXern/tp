package customexceptions;

public class InvalidTimeFormatException extends Exception {
    public InvalidTimeFormatException() {
        super("Sorry, please enter a valid time");
    }
}
