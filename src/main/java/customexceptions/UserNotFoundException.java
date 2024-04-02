package customexceptions;

public class UserNotFoundException extends Exception {
    public UserNotFoundException() {
        super("Username not found. Exiting program");
    }
}
