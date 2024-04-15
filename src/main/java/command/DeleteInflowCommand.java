package command;

import customexceptions.IncorrectCommandSyntaxException;
import financialtransactions.TransactionManager;

public class DeleteInflowCommand extends BaseCommand {
    private int inflowIndex = -1;
    public DeleteInflowCommand(String[] commandParts) {
        super(false, commandParts);
        System.out.println("CREATE DELETE INFLOW COMMAND");
    }

    public void createTransaction() throws IncorrectCommandSyntaxException {
        if (commandParts[1].startsWith("i/")) {
            inflowIndex = Integer.parseInt(commandParts[1].substring(2));
        } else {
            throw new IncorrectCommandSyntaxException(commandParts[0]);
        }
        assert inflowIndex != -1 : "The inflowIndex should exist";
        try {
            inflow = manager.getNthInflowFromList(inflowIndex);
            System.out.println("TRANSACTION INFLOW CREATED");
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
