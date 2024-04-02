package command;

import financialtransactions.Outflow;
import financialtransactions.Transaction;
import financialtransactions.TransactionManager;

public class DeleteOutflowCommand extends BaseCommand {
    public DeleteOutflowCommand(String[] commandParts) {
        super(false, commandParts);
    }

    public String execute(TransactionManager manager) throws Exception {
        String outflowIndex = null;
        if (commandParts[1].startsWith("i/")) {
            outflowIndex = commandParts[1].substring(2);
        }
        assert outflowIndex != null : "outflowIndex should not be null";
        int outflowIndexParsed = Integer.parseInt(outflowIndex);
        outflow = manager.removeOutflow(outflowIndexParsed);
        //outflow = manager.getNthOutflowFromList(outflowIndexParsed);
        return "Ok. Outflow " + outflow.getName() + " " + outflow.getCategory().toString() + " deleted";
    }
}
