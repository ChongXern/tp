package command;

import customexceptions.EditTransactionException;
import customexceptions.CategoryNotFoundException;
import customexceptions.IncorrectCommandSyntaxException;
import financialtransactions.Inflow;
import financialtransactions.TransactionManager;

public class EditInflowCommand extends BaseCommand {
    private int inflowIndex = -1;
    private String inflowName = null;
    private double inflowAmount = 0;
    private String inflowDate = null;
    private String inflowTime = null;
    private String inflowCategory = null;
    private Inflow updatedInflow;

    public EditInflowCommand(String[] commandParts) {
        super(false, commandParts);
    }

    @Override
    public void createTransaction() throws Exception {
        //@@author Kishen271828
        /* Iterates through the parts of the original command string that checks and updates
        relevant inflow information. */
        for (int i = 1; i < commandParts.length; i++) {
            String part = commandParts[i];
            if (part.startsWith("i/")) {
                inflowIndex = Integer.parseInt(part.substring(2));
                if (inflowIndex <= 0 || inflowIndex > manager.getNumOfInflows()) {
                    throw new EditTransactionException();
                }
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
        assert inflowIndex != -1 : "inflow index should exist";
        assert inflowCategory != null : "inflow category should not be null";
        try {
            inflow = manager.getNthInflowFromList(inflowIndex);
        } catch (Exception e) {
            System.out.println("Sorry, something went wrong: " + e.getMessage());
        }

        updatedInflow = new Inflow(inflowName, inflowAmount, inflowDate + " " + inflowTime);
        try {
            updatedInflow.setCategory(inflowCategory);
        } catch (CategoryNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public String execute(TransactionManager manager) throws Exception {
        if (!canExecute) {
            return "Sorry, inflow not edited.";
        }
        manager.editInflow(inflowIndex, updatedInflow);
        return "Ok. Edited inflow";
    }
}
