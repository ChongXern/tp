package command;

import financialtransactions.TransactionManager;

public class ViewHistoryCommand extends BaseCommand {

    public ViewHistoryCommand(String[] commandParts) {
        super(false,commandParts);
    }

    public String execute(TransactionManager manager) throws Exception{
        //@@author Kishen271828
        //String numTransactionsString = null;
        int numTransactions = 0;
        if (commandParts[1].startsWith("n/")) {
            String numTransactionsString = commandParts[1].substring(2);
            numTransactions = Integer.parseInt(numTransactionsString);
        } else if (commandParts[1].equals("all")) {
            numTransactions = manager.getTransactionListSize();
        }
        return manager.showLastNTransactions(numTransactions);
    }
}
