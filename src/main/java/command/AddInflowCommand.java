package command;

import customexceptions.CategoryNotFoundException;
import customexceptions.IncorrectCommandSyntaxException;
import financialtransactions.Inflow;
import financialtransactions.TransactionManager;

public class AddInflowCommand extends BaseCommand {
    String inflowName = null;
    double inflowAmount = 0;
    String inflowDate = null;
    String inflowTime = null;
    String inflowCategory = null;

    public AddInflowCommand(String[] commandParts) throws CategoryNotFoundException, IllegalArgumentException {
        super(false, commandParts);
        try {
            createTransaction();
        } catch (IncorrectCommandSyntaxException e) {
            System.out.println(e.getMessage());
        }
        assert inflowCategory != null;
        inflow.setCategory(inflowCategory);
    }

    public void createTransaction() throws IncorrectCommandSyntaxException {
        //@@author Kishen271828
        /* Iterates through the parts of the original command string that checks and updates
        relevant inflow information. */
        for (int i = 1; i < commandParts.length; i++) {
            String part = commandParts[i];
            if (part.startsWith("n/")) {
                inflowName = part.substring(2);
            } else if (part.startsWith("a/")) {
                inflowAmount = Double.parseDouble(part.substring(2));
                if (inflowAmount <= 0) {
                    throw new IllegalArgumentException("Sorry, inflow amount must be positive.");
                }
            } else if (part.startsWith("d/")) {
                inflowDate = part.substring(2);
            } else if (part.startsWith("t/")) {
                inflowTime = part.substring(2);
            } else if (part.startsWith("c/")) {
                inflowCategory = part.substring(2);
            } else {
                throw new IncorrectCommandSyntaxException(commandParts[0]);
            }
        }
        String inflowDateTime = inflowDate + " " + inflowTime;
        inflow = new Inflow(inflowName, inflowAmount, inflowDateTime);
    }

    public String execute(TransactionManager manager) {
        //@@author Kishen271828
        if (!canExecute) {
            return "Sorry, inflow not added.";
        }
        if (inflow.getDate().getDateTime() == null) {
            return "Please enter a valid date or time";
        }
        manager.addTransaction(inflow);
        return "Ok. Added inflow";
    }

}
