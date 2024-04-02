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

    public String toSave() {
        StringBuilder baseString = new StringBuilder();
        for (T transaction : transactionList) {
            baseString.append(transaction.toSave());
        }
        return baseString.toString();
    }

    protected void printTransactionsSafeInfo() {
        int index = 1;
        for (T transaction : transactionList) {
            System.out.print(index++);
            System.out.print(" " + transaction.getName() + " | " + transaction.getCategory());
            System.out.println(" | " + transaction.getClass());
        }
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
    
    public double totalSpentInPastMonth() {
        double amount = 0;
        for (T transaction : transactionList) {
            if (transaction.getDate().getDateTime().getMonth() == LocalDateTime.now().getMonth()) {
                amount += transaction.getAmount();
            }
        }
        return amount;
    }
}
