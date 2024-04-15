package command;

import customexceptions.DeleteTransactionException;
import customexceptions.IncorrectCommandSyntaxException;
import financialtransactions.TransactionManager;

public class DeleteOutflowCommand extends BaseCommand {
    private int outflowIndex = -1;

    public DeleteOutflowCommand(String[] commandParts) {
        super(false, commandParts);
    }

    public void createTransaction() throws Exception {
        //@@author Kishen271828
        if (commandParts[1].startsWith("i/")) {
            outflowIndex = Integer.parseInt(commandParts[1].substring(2));
        } else {
            throw new IncorrectCommandSyntaxException(commandParts[0]);
        }

        assert outflowIndex != -1 : "outflowIndex should not be null";
        if (outflowIndex <= 0 || outflowIndex > manager.getNumOfOutflows()) {
            throw new DeleteTransactionException();
        }
        //@@author
        try {
            outflow = manager.getNthOutflowFromList(outflowIndex);
        } catch (Exception e) {
            System.out.println("Sorry. " + e.getMessage());
        }
    }

    public String execute(TransactionManager manager) throws Exception {
        assert outflowIndex != -1 : "The outflowIndex should exist";
        manager.removeOutflow(outflowIndex);
        return "Ok. Outflow " + outflow.getName() + " " + outflow.getCategory().toString() + " deleted";
    }
}
