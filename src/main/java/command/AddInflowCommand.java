package command;

import customexceptions.CategoryNotFoundException;
import customexceptions.IncorrectCommandSyntaxException;
import financialtransactions.Inflow;
import financialtransactions.TransactionManager;
import user.InactivityTimer;

public class AddInflowCommand extends BaseCommand {

    public AddInflowCommand(String[] commandParts) {
        super(false, commandParts);
        timer = new InactivityTimer();
        try {
            createInflow();
        } catch (IncorrectCommandSyntaxException e) {
            System.out.println(e.getMessage());
        }
    }

    private void createInflow() throws IncorrectCommandSyntaxException {
        //@@author Kishen271828
        String inflowName = null;
        double inflowAmount = 0;
        String inflowDate = null;
        String inflowTime = null;
        String inflowCategory = null;

        /* Iterates through the parts of the original command string that checks and updates
        relevant inflow information. */
        for (int i = 1; i < commandParts.length; i++) {
            String part = commandParts[i];
            if (part.startsWith("n/")) {
                inflowName = part.substring(2);
            } else if (part.startsWith("a/")) {
                inflowAmount = Double.parseDouble(part.substring(2));
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
        assert inflowCategory != null;
        try {
            inflow.setCategory(inflowCategory);
        } catch (CategoryNotFoundException e) {
            System.out.println(e.getMessage());
            e.disableExecute(this);
        }
    }

    public String execute(TransactionManager manager) {
        //@@author Kishen271828
        if (!canExecute) {
            return "Sorry, inflow not added.";
        }
        manager.addTransaction(inflow);
        return "Ok. Added inflow";
    }
}
