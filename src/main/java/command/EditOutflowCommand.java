package command;

import customexceptions.IncorrectCommandSyntaxException;
import financialtransactions.Outflow;
import financialtransactions.TransactionManager;

public class EditOutflowCommand extends BaseCommand {
    public EditOutflowCommand(String[] commandParts) {
        super(false, commandParts);
    }

    public String execute(TransactionManager manager) throws Exception {
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
            } else if (part.startsWith("n/")) {
                outflowName = part.substring(2);
            } else if (part.startsWith("a/")) {
                outflowAmount = Double.parseDouble(part.substring(2));
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
        updatedOutflow.setCategory(Outflow.Category.valueOf(outflowCategory.toUpperCase()));
        manager.editOutflow(outflowIndex, updatedOutflow);
        return "Ok. Edited outflow";
    }
}
