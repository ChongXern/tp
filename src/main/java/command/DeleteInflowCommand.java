package command;

import customexceptions.IncorrectCommandSyntaxException;
import financialtransactions.TransactionManager;

public class DeleteInflowCommand extends BaseCommand {
    public DeleteInflowCommand(String[] commandParts) {
        super(false, commandParts);
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
        inflow = manager.removeInflow(inflowIndexParsed);
        //inflow = manager.getNthInflowFromList(inflowIndexParsed);
        return "Ok. Inflow " + inflow.getName() + " | " + inflow.getCategory().toString() + " deleted";
    }
}
