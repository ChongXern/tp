package command;

import financialtransactions.TransactionManager;

public class DeleteInflowCommand extends BaseCommand {
    private String inflowIndex = null;
    public DeleteInflowCommand(String[] commandParts) {
        super(false, commandParts);
    }

    public String execute(TransactionManager manager) throws Exception {
        if (commandParts[1].startsWith("i/")) {
            inflowIndex = commandParts[1].substring(2);
        }
        assert inflowIndex != null : "inflowIndex should not be null";
        int inflowIndexParsed = Integer.parseInt(inflowIndex);
        inflow = manager.removeInflow(inflowIndexParsed);
        //inflow = manager.getNthInflowFromList(inflowIndexParsed);
        return "Ok. Inflow " + inflow.getName() + " | " + inflow.getCategory().toString() + " deleted";
    }
}
