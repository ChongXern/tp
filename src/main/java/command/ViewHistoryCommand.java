package command;

import financialtransactions.TransactionManager;

public class ViewHistoryCommand extends BaseCommand {

    public ViewHistoryCommand(String[] commandParts) {
        super(false,commandParts);
    }

    public String execute(TransactionManager manager) throws Exception{
        //@@author Kishen271828
        String numTransactionsString = null;
        for (String part : commandParts) {
            if (part.startsWith("n/")) {
                numTransactionsString = part.substring(2);
            }
        }
        int numTransactions = 0;
        if (numTransactionsString != null) {
            numTransactions = Integer.parseInt(numTransactionsString);
        }
        return manager.showLastNTransactions(numTransactions);
    }
}
