package command;

import customexceptions.CategoryNotFoundException;
import customexceptions.IncorrectCommandSyntaxException;
import financialtransactions.Outflow;
import financialtransactions.TransactionManager;
import user.InactivityTimer;

public class AddOutflowCommand extends BaseCommand {
    public AddOutflowCommand(String[] commandParts) throws IncorrectCommandSyntaxException, CategoryNotFoundException {
        super(false, commandParts);
        timer = new InactivityTimer();
        try {
            createOutflow();
        } catch (IncorrectCommandSyntaxException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private void createOutflow() throws IncorrectCommandSyntaxException, CategoryNotFoundException {
        //@@author Kishen271828
        String outflowName = null;
        double outflowAmount = 0.0;
        String outflowDate = null;
        String outflowTime = null;
        String outflowCategory = null;

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
        assert outflowCategory != null;
        outflow.setCategory(outflowCategory);
    }

    public String execute(TransactionManager manager) {
        //@@author Kishen271828
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
