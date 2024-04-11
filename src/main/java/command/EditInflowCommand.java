package command;

import customexceptions.CategoryNotFoundException;
import customexceptions.IncorrectCommandSyntaxException;
import financialtransactions.Inflow;
import financialtransactions.TransactionManager;

public class EditInflowCommand extends BaseCommand {
    public EditInflowCommand(String[] commandParts) {
        super(false, commandParts);
    }

    public String execute(TransactionManager manager) throws Exception {
        int inflowIndex = -1;
        String inflowName = null;
        double inflowAmount = 0;
        String inflowDate = null;
        String inflowTime = null;
        String inflowCategory = null;

        /* Iterates through the parts of the original command string that checks and updates
        relevant inflow information. */
        for (int i = 1; i < commandParts.length; i++) {
            String part = commandParts[i];
            if (part.startsWith("i/")) {
                inflowIndex = Integer.parseInt(part.substring(2));
            } else if (part.startsWith("n/")) {
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
        Inflow updatedInflow = new Inflow(inflowName, inflowAmount, inflowDateTime);
        assert inflowCategory != null : "inflowCategory should not be null";
        updatedInflow.setCategory(inflowCategory);
        if (!canExecute) {
            return "Sorry, inflow not edited.";
        }
        manager.editInflow(inflowIndex, updatedInflow);
        return "Ok. Edited inflow";
    }
}
