//@@author Kishen271828
package financialtransactions;

import java.util.Comparator;

public class TransactionComparator implements Comparator<Transaction<?>> {
    @Override
    public int compare(Transaction<?> t1, Transaction<?> t2) {
        // Compare the dates of the transactions
        return t1.getDate().compareTo(t2.getDate());
    }
}
