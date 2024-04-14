package command;

import customexceptions.EditTransactionException;
import customexceptions.IncorrectCommandSyntaxException;
import financialtransactions.Outflow;
import financialtransactions.TransactionManager;

public class EditOutflowCommand extends BaseCommand {
    public EditOutflowCommand(String[] commandParts) {
        super(false, commandParts);
    }

    public String execute(TransactionManager manager) throws Exception {
        //@@author Kishen271828
        int outflowIndex = -1;
        String outflowName = null;
        double outflowAmount = 0.0;
        String outflowDate = null;
        String outflowTime = null;
        String outflowCategory = null;

        /* Iterates through the parts of the original command string that checks and updates
        relevant outflow information. */
        for (int i = 1; i < commandParts.length; i++) {
            String part = commandParts[i];
            if (part.startsWith("i/")) {
                outflowIndex = Integer.parseInt(part.substring(2));
                if (outflowIndex <= 0 || outflowIndex > manager.getNumOfOutflows()) {
                    throw new EditTransactionException();
                }
            } else if (part.startsWith("n/")) {
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
        Outflow updatedOutflow = new Outflow(outflowName, outflowAmount, outflowDateTime);
        assert outflowCategory != null : "outflowCategory should not be null";
        updatedOutflow.setCategory(outflowCategory);
        if (!canExecute) {
            return "Sorry, outflow not edited.";
        }
        manager.editOutflow(outflowIndex, updatedOutflow);
        return "Ok. Edited outflow";
    }
}
