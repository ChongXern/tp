package customexceptions;
//@@author dylansiew
public class UserNotFoundException extends Exception {
    //@@author dylansiew
    public UserNotFoundException() {
        super("Username not found. Exiting program");
    }
}
