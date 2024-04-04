package command;

import customexceptions.IncorrectCommandSyntaxException;
import financialtransactions.TransactionManager;

public class SetBudgetCommand extends BaseCommand{
    public SetBudgetCommand(String[] commandParts) {
        super(false, commandParts);
    }
    
    public String execute(TransactionManager manager) throws Exception{
        String budgetString = null;
        if (commandParts[1].startsWith("a/")) {
            budgetString = commandParts[1].substring(2);
        } else {
            throw new IncorrectCommandSyntaxException(commandParts[0]);
        }
        double budget = Double.parseDouble(budgetString);
        manager.setBudget(budget);
        return "Ok. Budget set.";
    }
}
