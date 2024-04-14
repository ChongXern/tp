package command;

import customexceptions.IncorrectCommandSyntaxException;
import financialtransactions.TransactionManager;
import user.InactivityTimer;

public class DeleteInflowCommand extends BaseCommand {
    public DeleteInflowCommand(String[] commandParts) {
        super(false, commandParts);
        System.out.println("CREATE DELETE INFLOW COMMAND");
    }

    public String execute(TransactionManager manager) throws Exception {
        String inflowIndex;
        if (commandParts[1].startsWith("i/")) {
            inflowIndex = commandParts[1].substring(2);
            System.out.println(inflowIndex);
        } else {
            System.out.println("nvm");
            throw new IncorrectCommandSyntaxException(commandParts[0]);
        }
        assert inflowIndex != null : "inflowIndex should not be null";
        int inflowIndexParsed = Integer.parseInt(inflowIndex);
        inflow = manager.removeInflow(inflowIndexParsed);
        //inflow = manager.getNthInflowFromList(inflowIndexParsed);
        return "Ok. Inflow " + inflow.getName() + " | " + inflow.getCategory().toString() + " deleted";
    }
}
