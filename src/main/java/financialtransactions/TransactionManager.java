package financialtransactions;


import storage.BarChart;

public class TransactionManager {
    private TransactionList<Transaction<?>> transactionList;
    private TransactionList<Inflow> inflows;
    private TransactionList<Outflow> outflows;

    public TransactionManager() {
        this.transactionList = new TransactionList<>();
        this.inflows = new TransactionList<>();
        this.outflows = new TransactionList<>();
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
        if (isIncludeBarChart) {
            BarChart<Transaction<?>> barChart = new BarChart<>(transactionList);
            barChart.printBarChart();
        }
        return returnedText;
    }

    public String toSave() {
        return inflows.toSave() + outflows.toSave();
    }
}
