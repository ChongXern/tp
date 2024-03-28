package command;

import financialtransactions.Inflow;
import financialtransactions.TransactionManager;

public class AddInflowCommand extends BaseCommand {

    public AddInflowCommand(String[] commandParts) {
        super(false, commandParts);
    }

    public String execute(TransactionManager manager) {
        String inflowName = null;
        double inflowAmount = 0;
        String inflowDate = null;
        String inflowTime = null;
        String inflowCategory = null;

        for (String part : commandParts) {
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
            }
        }
        String inflowDateTime = inflowDate + " " + inflowTime;
        Inflow inflow = new Inflow(inflowName, inflowAmount, inflowDateTime);
        assert inflowCategory != null;
        inflow.setCategory(Inflow.Category.valueOf(inflowCategory.toUpperCase()));
        manager.addTransaction(inflow);
        return "Ok. Added inflow";
    }
}
