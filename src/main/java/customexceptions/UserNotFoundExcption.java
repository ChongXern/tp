package customexceptions;

public class UserNotFoundExcption extends Exception {
    public UserNotFoundExcption() {
        super("Username not found. Exiting program");
    }
}
