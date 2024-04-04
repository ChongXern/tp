package customexceptions;

public class IncorrectCommandSyntaxException extends Exception {
    public IncorrectCommandSyntaxException(String command) {
        super("Sorry, please use the correct syntax for " + command);
    }
}
