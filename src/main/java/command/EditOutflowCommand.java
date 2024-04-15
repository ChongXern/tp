package command;

import customexceptions.CategoryNotFoundException;
import customexceptions.IncorrectCommandSyntaxException;
import financialtransactions.Outflow;
import financialtransactions.TransactionManager;

public class EditOutflowCommand extends BaseCommand {
    private int outflowIndex = -1;
    private String outflowName = null;
    private double outflowAmount = 0.0;
    private String outflowDate = null;
    private String outflowTime = null;
    private String outflowCategory = null;
    private Outflow updatedOutflow;

    public EditOutflowCommand(String[] commandParts) {
        super(false, commandParts);
    }

    @Override
    public void createTransaction() throws IncorrectCommandSyntaxException {
        /* Iterates through the parts of the original command string that checks and updates
        relevant outflow information. */
        for (int i = 1; i < commandParts.length; i++) {
            String part = commandParts[i];
            if (part.startsWith("i/")) {
                outflowIndex = Integer.parseInt(part.substring(2));
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
        assert outflowIndex != -1 : "outflow index should exist.";
        assert outflowCategory != null : "outflow category should not be null";
        try {
            outflow = manager.getNthOutflowFromList(outflowIndex);
        } catch (CategoryNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("Sorry. " + e.getMessage());
        }
        updatedOutflow = new Outflow(outflowName, outflowAmount, outflowDate + " " + outflowTime);
        try {
            updatedOutflow.setCategory(outflowCategory);
        } catch (CategoryNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public String execute(TransactionManager manager) throws Exception {
        if (!canExecute) {
            return "Sorry, outflow not edited.";
        }
        manager.editOutflow(outflowIndex, updatedOutflow);
        return "Ok. Edited outflow";
    }
}
