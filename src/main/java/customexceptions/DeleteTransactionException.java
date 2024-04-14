package customexceptions;

public class DeleteTransactionException extends Exception {
    public DeleteTransactionException() {
        super("Please enter a valid index. Use the view-history command to see which transaction you wish to delete");
    }
}
