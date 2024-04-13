package customexceptions;

public class ExitLoginException extends Exception {
    public ExitLoginException(){
        super("Exiting Login session");
    }
}
