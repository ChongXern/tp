package command;

import customexceptions.CategoryNotFoundException;
import customexceptions.IncorrectCommandSyntaxException;
import financialtransactions.Outflow;
import financialtransactions.TransactionManager;

public class AddOutflowCommand extends BaseCommand {
    String outflowName = null;
    double outflowAmount = 0.0;
    String outflowDate = null;
    String outflowTime = null;
    String outflowCategory = null;

    public AddOutflowCommand(String[] commandParts) throws CategoryNotFoundException {
        super(false, commandParts);
        try {
            createTransaction();
        } catch (IncorrectCommandSyntaxException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        assert outflowCategory != null;
        outflow.setCategory(outflowCategory);
    }

    public void createTransaction() throws IncorrectCommandSyntaxException {
        /* Iterates through the parts of the original command string that checks and updates
        relevant outflow information. */
        for (int i = 1 ; i < commandParts.length; i++) {
            String part = commandParts[i];
            if (part.startsWith("n/")) {
                outflowName = part.substring(2);
            } else if (part.startsWith("a/")) {
                outflowAmount = Double.parseDouble(part.substring(2));
                if (outflowAmount <= 0) {
                    throw new IllegalArgumentException("Sorry, inflow amount must be positive.");
                }
            } else if (part.startsWith("d/")) {
                outflowDate = part.substring(2);
            } else if (part.startsWith("t/")) {
                outflowTime = part.substring(2);
            } else if (part.startsWith("c/")) {
                outflowCategory = part.substring(2);
            } else {
                throw new IncorrectCommandSyntaxException(commandParts[0]);
            }
        }
        String outflowDateTime = outflowDate + " " + outflowTime;

        outflow = new Outflow(outflowName, outflowAmount, outflowDateTime);
    }

    public String execute(TransactionManager manager) {
        if (!canExecute) {
            return "Sorry, outflow not added.";
        }
        if (outflow.getDate().getDateTime() == null) {
            return "Please enter a valid date or time";
        }
        manager.addTransaction(outflow);
        return "Ok. Added outflow";
    }
}
