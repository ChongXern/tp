package command;

import customexceptions.DeleteTransactionException;
import customexceptions.IncorrectCommandSyntaxException;
import financialtransactions.TransactionManager;

public class DeleteInflowCommand extends BaseCommand {
    private int inflowIndex = -1;
    public DeleteInflowCommand(String[] commandParts) {
        super(false, commandParts);
    }

    public void createTransaction() throws Exception {
        if (commandParts[1].startsWith("i/")) {
            inflowIndex = Integer.parseInt(commandParts[1].substring(2));
        } else {
            throw new IncorrectCommandSyntaxException(commandParts[0]);
        }
        assert inflowIndex != -1 : "inflowIndex should not be null";
        if (inflowIndex <= 0 || inflowIndex > manager.getNumOfInflows()) {
            throw new DeleteTransactionException();
        }
        try {
            inflow = manager.getNthInflowFromList(inflowIndex);
        } catch (Exception e) {
            System.out.println("Sorry. " + e.getMessage());
        }
    }

    public String execute(TransactionManager manager) throws Exception {
        System.out.println("EXECUTE!");
        assert inflowIndex != -1 : "The inflowIndex should exist";
        manager.removeInflow(inflowIndex);
        return "Ok. Inflow " + inflow.getName() + " | " + inflow.getCategory().toString() + " deleted";
    }
}