package financialtransactions;


import storage.BarChart;

public class TransactionManager {
    private TransactionList<Transaction<?>> transactionList;
    private TransactionList<Inflow> inflows;
    private TransactionList<Outflow> outflows;
    private TransactionList<Reminder> reminders;
    
    private double budget = 0.00;

    public TransactionManager() {
        this.transactionList = new TransactionList<>();
        this.inflows = new TransactionList<>();
        this.outflows = new TransactionList<>();
        this.reminders = new TransactionList<>();
    }
    
    public void setBudget(double budget) {
        this.budget = budget;
    }
    
    public boolean addTransaction(Transaction<?> transaction) {
        transactionList.addTransaction(transaction);
        // transactionList.sortListByName();
        if (transaction instanceof Inflow) {
            Inflow inflow = (Inflow) transaction;
            transactionList.setTransactionsType("Inflow");
            return inflows.addTransaction(inflow);
        }
        if (transaction instanceof Outflow) {
            Outflow outflow = (Outflow) transaction;
            transactionList.setTransactionsType("Outflow");
            return outflows.addTransaction(outflow);
        }
        if (transaction instanceof Reminder) {
            Reminder reminder = (Reminder) transaction;
            transactionList.setTransactionsType("Reminder");
            return reminders.addTransaction(reminder);
        }
        System.out.println("Invalid transaction type.");
        return false;
    }

    public boolean removeTransaction(int index) throws Exception{
        transactionList.removeTransactionIndex(index);
        Transaction<?> transactionRemoved = transactionList.getNthTransaction(index);
        if (transactionRemoved instanceof Inflow) {
            return inflows.removeTransactionIndex(index);
        }
        if (transactionRemoved instanceof Outflow) {
            return outflows.removeTransactionIndex(index);
        }
        if (transactionRemoved instanceof Reminder) {
            return reminders.removeTransactionIndex(index);
        }
        return false;
    }

    public boolean removeInflow(int index) throws Exception {
        int numOfInflows = inflows.getTransactionListSize();
        Transaction<?> transactionRemoved = inflows.getNthTransaction(numOfInflows - index);
        transactionList.removeTransactionIndex(transactionList.getIndexOfParticularTransaction(transactionRemoved));
        return inflows.removeTransactionIndex(numOfInflows - index);
    }

    public boolean removeOutflow(int index) throws Exception {
        int numOfOutflows = outflows.getTransactionListSize();
        Transaction<?> transactionRemoved = outflows.getNthTransaction(numOfOutflows - index);
        transactionList.removeTransactionIndex(transactionList.getIndexOfParticularTransaction(transactionRemoved));
        return outflows.removeTransactionIndex(numOfOutflows - index);
    }

    public boolean removeReminder(int index) throws Exception {
        int numOfReminders = reminders.getTransactionListSize();
        Transaction<?> transactionRemoved = reminders.getNthTransaction(numOfReminders - index);
        transactionList.removeTransactionIndex(transactionList.getIndexOfParticularTransaction(transactionRemoved));
        return reminders.removeTransactionIndex(numOfReminders - index);
    }

    public boolean editInflow(int index, Transaction<?> updatedTransaction) throws Exception {
        int numOfInflows = inflows.getTransactionListSize();
        Transaction<?> transactionEdited = inflows.getNthTransaction(numOfInflows - index);
        transactionList.editTransactionIndex(transactionList.getIndexOfParticularTransaction(transactionEdited),
                updatedTransaction);
        return inflows.editTransactionIndex(numOfInflows - index, (Inflow) updatedTransaction);
    }

    public boolean editOutflow(int index, Transaction<?> updatedTransaction) throws Exception {
        int numOfOutflows = outflows.getTransactionListSize();
        Transaction<?> transactionEdited = outflows.getNthTransaction(numOfOutflows - index);
        transactionList.editTransactionIndex(transactionList.getIndexOfParticularTransaction(transactionEdited),
                updatedTransaction);
        return outflows.editTransactionIndex(numOfOutflows - index, (Outflow) updatedTransaction);
    }

    public boolean editReminder(int index, Transaction<?> updatedTransaction) throws Exception {
        int numOfReminders = reminders.getTransactionListSize();
        Transaction<?> transactionEdited = reminders.getNthTransaction(numOfReminders - index);
        transactionList.editTransactionIndex(transactionList.getIndexOfParticularTransaction(transactionEdited),
                updatedTransaction);
        return reminders.editTransactionIndex(numOfReminders - index, (Reminder) updatedTransaction);
    }

    public double getTotalBalance() {
        double inflowBalance = inflows.getBalance();
        double outflowBalance = outflows.getBalance();
        return inflowBalance + outflowBalance;
    }

    @Override
    public String toString() {
        return "Inflows:\n" + inflows.toString() + "\nOutflows:\n" + outflows.toString();
    }

    public String showLastNTransactions(int n, boolean isIncludeBarChart) throws Exception {
        int listSize = transactionList.getTransactionListSize();
        if (n > listSize) {
            throw new Exception("Invalid index");
        }
        int index = 1;
        String returnedText = "Inflows:\nTransactions:\n";
        for (int i = listSize - 1; i > listSize - n - 1; i--) {
            Transaction<?> transaction = transactionList.getNthTransaction(i);
            if (transaction instanceof Inflow) {
                returnedText += String.format("%d)  %s\n", index, transactionList.getNthTransaction(i).toString());
                index++;
            }
        }

        index = 1;
        returnedText += "\nOutflows:\nTransactions:\n";
        for (int i = listSize - 1; i > listSize - n - 1; i--) {
            Transaction<?> transaction = transactionList.getNthTransaction(i);
            if (transaction instanceof Outflow) {
                returnedText += String.format("%d)  %s\n", index, transactionList.getNthTransaction(i).toString());
                index++;
            }
        }

        index = 1;
        returnedText += "\nReminders:\nTransactions:\n";
        for (int i = listSize - 1; i > listSize - n - 1; i--) {
            Transaction<?> transaction = transactionList.getNthTransaction(i);
            if (transaction instanceof Reminder) {
                returnedText += String.format("%d)  %s\n", index, transactionList.getNthTransaction(i).toString());
                index++;
            }
        }

        if (isIncludeBarChart) {
            BarChart<Transaction<?>> barChart = new BarChart<>(transactionList);
            barChart.printBarChart();
        }
        
        return returnedText;
    }

    public String toSave() {
        return String.format("%.2f\n", budget) + inflows.toSave() + outflows.toSave() + reminders.toSave();
    }
    
    public String generateQuickReport() {
        String baseString = "";
        baseString += String.format("You have spent " +
                "%.2f in the current month.\n", outflows.totalSpentInPastMonth());
        baseString += String.format("With a budget of " +
                "%.2f, you have %.2f left to spend.\n", budget, budget - outflows.totalSpentInPastMonth());
        baseString += String.format("You have " +
                "%d upcoming payments that require your attention", reminders.getTransactionListSize());
        return baseString;
    }
}
