package customexceptions;

public class InvalidDateFormatException extends Exception {
    public InvalidDateFormatException() {
        super("Sorry, please enter a valid date");
    }
}
