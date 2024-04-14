package customexceptions;

public class EditTransactionException extends Exception {
    public EditTransactionException() {
        super("Please enter a valid index. Use the view-history command to see which transaction you wish to edit");
    }
}
