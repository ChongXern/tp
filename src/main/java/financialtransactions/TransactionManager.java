package financialtransactions;


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
        if (transaction instanceof Inflow) {
            Inflow inflow = (Inflow) transaction;
            return inflows.addTransaction(inflow);
        } else if (transaction instanceof Outflow) {
            Outflow outflow = (Outflow) transaction;
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
        if (transactionRemoved instanceof Outflow){
            return outflows.removeTransactionIndex(index);
        }
        return false;
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

    public String showLastNTransactions(int n) throws Exception{
        int listSize = transactionList.getTransactionListSize();
        if (n > listSize) {
            throw new Exception("Invalid index");
        }
        int index = 1;
        String returnedText = "Inflows:\nTransactions:";
        for (int i = listSize - 1; i > listSize - n - 1; i--) {
            Transaction<?> transaction = transactionList.getNthTransaction(i);
            if (transaction instanceof Inflow) {
                returnedText += String.format("%d)  %s\n", index, transactionList.getNthTransaction(i).toString());
                index++;
            }
        }

        index = 1;
        returnedText += "\nOutflows:\nTransactions:";
        for (int i = listSize - 1; i > listSize - n - 1; i--) {
            Transaction<?> transaction = transactionList.getNthTransaction(i);
            if (transaction instanceof Outflow) {
                returnedText += String.format("%d)  %s\n", index, transactionList.getNthTransaction(i).toString());
                index++;
            }
        }
        return returnedText;
    }

    public String toSave() {
        return inflows.toSave() + outflows.toSave();
    }
}
