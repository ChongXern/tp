package command;

import customexceptions.IncorrectCommandSyntaxException;
import financialtransactions.TransactionManager;

public class ViewHistoryCommand extends BaseCommand {

    public ViewHistoryCommand(String[] commandParts) {
        super(false,commandParts);
    }

    public String execute(TransactionManager manager) throws Exception {
        //@@author Kishen271828
        int numTransactions = 0;
        if (commandParts[1].startsWith("n/")) {
            String numTransactionsString = commandParts[1].substring(2);
            try {
                numTransactions = Integer.parseInt(numTransactionsString);
                if (numTransactions <= 0) {
                    throw new IllegalArgumentException("Sorry, please input a positive index.");
                }
            } catch (NumberFormatException e) {
                throw new IncorrectCommandSyntaxException(commandParts[0]);
            }
        } else if (commandParts[1].equals("all")) {
            numTransactions = manager.getTransactionListSize();
        } else {
            throw new IncorrectCommandSyntaxException(commandParts[0]);
        }
        boolean isIncludeBarChart = commandParts.length == 3 && commandParts[2].equalsIgnoreCase("w/chart");
        return manager.showLastNTransactions(numTransactions, isIncludeBarChart);
    }
}
