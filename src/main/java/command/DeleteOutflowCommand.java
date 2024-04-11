package command;

import customexceptions.IncorrectCommandSyntaxException;
import financialtransactions.TransactionManager;
import user.InactivityTimer;

public class DeleteOutflowCommand extends BaseCommand {
    public DeleteOutflowCommand(String[] commandParts) {
        super(false, commandParts);
        timer = new InactivityTimer();
    }

    public String execute(TransactionManager manager) throws Exception {
        //@@author dylansiew
        String outflowIndex = null;
        if (commandParts[1].startsWith("i/")) {
            outflowIndex = commandParts[1].substring(2);
        } else {
            throw new IncorrectCommandSyntaxException(commandParts[0]);
        }
        assert outflowIndex != null : "outflowIndex should not be null";
        int outflowIndexParsed = Integer.parseInt(outflowIndex);
        outflow = manager.removeOutflow(outflowIndexParsed);
        //outflow = manager.getNthOutflowFromList(outflowIndexParsed);
        return "Ok. Outflow " + outflow.getName() + " " + outflow.getCategory().toString() + " deleted";
    }
}
