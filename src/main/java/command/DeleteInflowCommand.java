package command;

import customexceptions.DeleteTransactionException;
import customexceptions.IncorrectCommandSyntaxException;
import financialtransactions.TransactionManager;
import user.InactivityTimer;

public class DeleteInflowCommand extends BaseCommand {
    public DeleteInflowCommand(String[] commandParts) {
        super(false, commandParts);
        timer = new InactivityTimer();
    }

    public String execute(TransactionManager manager) throws Exception {
        String inflowIndex = null;
        if (commandParts[1].startsWith("i/")) {
            inflowIndex = commandParts[1].substring(2);
        } else {
            throw new IncorrectCommandSyntaxException(commandParts[0]);
        }
        assert inflowIndex != null : "inflowIndex should not be null";
        int inflowIndexParsed = Integer.parseInt(inflowIndex);
        if (inflowIndexParsed <= 0 || inflowIndexParsed > manager.getNumOfInflows()) {
            throw new DeleteTransactionException();
        }
        inflow = manager.removeInflow(inflowIndexParsed);
        return "Ok. Inflow " + inflow.getName() + " | " + inflow.getCategory().toString() + " deleted";
    }
}
