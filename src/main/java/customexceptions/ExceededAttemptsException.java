package customexceptions;

public class ExceededAttemptsException extends Exception {
    public ExceededAttemptsException() {
        super("Attempts exceeded, exiting program");
    }
}
