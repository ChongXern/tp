package financialtransactions;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;

public class TransactionList<T extends Transaction<?>> {
    private ArrayList<T> transactionList;
    private String transactionsType;

    public TransactionList(){
        this.transactionList = new ArrayList<>();
    }

    public int getTransactionListSize() {
        return this.transactionList.size();
    }

    public Transaction<?> getNthTransaction(int n) throws Exception {
        if (n >= this.transactionList.size()) {
            throw new Exception("Invalid index");
        }
        return this.transactionList.get(n);
    }

    public int getIndexOfParticularTransaction(T particularTransaction) {
        return this.transactionList.indexOf(particularTransaction);
    }

    public boolean addTransaction(T newTransaction) {
        if (newTransaction != null) {
            transactionList.add(newTransaction);
            return true;
        }
        return false;
    }

    public boolean removeTransactionIndex (int index) {
        transactionList.remove(index);
        return true;
    }

    public boolean editTransactionIndex (int index, T transaction) {
        transactionList.set(index, transaction);
        return true;
    }

    public double getBalance(){
        double balance = 0.00;
        for(Transaction<?> transaction : transactionList){
            balance += transaction.getAmount();
        }
        return balance;
    }

    @Override
    public String toString(){
        StringBuilder baseString = new StringBuilder("Transactions: \n");
        int index = 1;
        for(T transaction : transactionList){
            baseString.append(String.format("%d)  %s\n", index, transaction.toString()));
            index += 1;
        }
        return baseString.toString();
    }

    //@@author chenhowy

    /**
     * Returns a string of all the past transactions.
     * Going through each transaction, the method calls toSave for each of them.
     * This generates one line for each transaction that will be appended to baseString.
     * 
     * @return A string of all past transactions.
     */
    public String toSave() {
        StringBuilder baseString = new StringBuilder();
        for (T transaction : transactionList) {
            baseString.append(transaction.toSave());
        }
        return baseString.toString();
    }
    //@@author

    protected String printTransactionsSafeInfo() {
        int index = 1;
        String response = "";
        for (T transaction : transactionList) {
            response += String.format("%d\t%s | %s | %s", index++, transaction.getName(), 
            transaction.getCategory(), transaction.getClass());
        }
        return response;
    }

    public String getTransactionsType() {
        return transactionsType;
    }

    public void setTransactionsType(String transactionsType) {
        if (transactionsType.equals("Mixed")) {
            return;
        }
        if (this.transactionsType == null) {
            this.transactionsType = transactionsType;
        } else if (!this.transactionsType.equals(transactionsType)) {
            this.transactionsType = "Mixed";
        }
    }

    //@@author Kishen271828
    public void sortTransactions() {
        transactionList.sort(new TransactionComparator());
    }
    //@@author

    public class NameComparator<T extends Transaction<?>> implements Comparator<T> {
        @Override
        public int compare(T o1, T o2) {
            return o2.getName().compareToIgnoreCase(o1.getName());
        }
    }

    public class DateComparator<T extends Transaction<?>> implements Comparator<T> {
        @Override
        public int compare(T o1, T o2) {
            return o2.getDate().getDateTime().compareTo(o1.getDate().getDateTime());
        }
    }

    public class FlowComparator<T extends Transaction<?>> implements Comparator<T> {
        @Override
        public int compare(T o1, T o2) {
            return o2.getClass().toString().compareTo(o1.getClass().toString());
        }
    }

    public void sortList() {
        this.transactionList.sort(new FlowComparator<>());
        this.transactionList.sort(new NameComparator<>());
    }

    public void sortListByDate() {
        this.transactionList.sort(new DateComparator<>());
    }

    //@@author chenhowy

    /**
     * Returns the amount of money spent in the current month.
     * The method checks if the transaction happened from the start of the current calendar month till today.
     * If it falls in the time period, it will be added to the total amount.
     * 
     * @return A double of the amount of money spent in the current month.
     */
    public double totalSpentInCurrentMonth() {
        double amount = 0;
        for (T transaction : transactionList) {
            if ((transaction.getDate().getDateTime().getMonth() == LocalDateTime.now().getMonth()) && 
                    transaction.getDate().getDateTime().isBefore(LocalDateTime.now())) {
                amount += transaction.getAmount();
            }
        }
        return amount;
    }

    /**
     * Returns the amount of money spent in a certain month of a certain year.
     * This method can also sum up the total amount of money earned in that month.
     * 
     * @param month The month which the user wants to query.
     * @param year The year which the user wants to query.
     * @return A double of the amount of money spent in the certain month.
     */
    public double getTotalSpentInMonth(int month, int year) {
        double amount = 0;
        for (T transaction : transactionList) {
            if (transaction.getDate().getDateTime().getMonthValue() == month && 
                    transaction.getDate().getDateTime().getYear() == year) {
                amount += transaction.getAmount();
            }
        }
        return amount;
    }

    /**
     * Returns the number of reminders that have yet to happen.
     * 
     * @return An integer of the number of reminders that have not occurred. 
     */
    public int getRemindersAfterToday() {
        int numberOfTransactions = 0;
        LocalDateTime today = LocalDateTime.now();
        for (T transaction : transactionList) {
            if (transaction.getDate().getDateTime().isAfter(today)) {
                numberOfTransactions++;
            }
        }
        return numberOfTransactions;
    }
}
