package command;

import financialtransactions.TransactionManager;

public class SetBudgetCommand extends BaseCommand{
    public SetBudgetCommand(String[] commandParts) {
        super(false, commandParts);
    }
    
    public String execute(TransactionManager manager) throws Exception{
        String budgetString = null;
        for (String part : commandParts) {
            if (part.startsWith("a/")) {
                budgetString = part.substring(2);
            }
        }       
        double budget = Double.parseDouble(budgetString);
        manager.setBudget(budget);
        return "Ok. Budget set.";
    }
}
