package storage;

import financialtransactions.Transaction;
import financialtransactions.TransactionList;
import java.util.HashMap;
import java.util.Map;

//@@author ChongXern
public class BarChart<T extends Transaction<?>> {
    private static final int TOTAL_BAR_SIZE = 50;
    private static final int LEGEND_SIZE = 10;
    private TransactionList<T> transactionList;
    private int totalTransactionsCount;
    private HashMap<String, Integer> categoryFrequencies;

    public BarChart(TransactionList<T> transactionList) {
        this.transactionList = transactionList;
        this.totalTransactionsCount = transactionList.getTransactionListSize();
        this.categoryFrequencies = new HashMap<>();
        //this.categoryPercentages = new HashMap<>();
    }

    public void computeTransactionFrequencies() throws Exception {
        for (int i = 0; i < totalTransactionsCount; i++) {
            String category = transactionList.getNthTransaction(i).getCategory().toString();
            if (categoryFrequencies.containsKey(category)) {
                int categoryCount = categoryFrequencies.get(category);
                categoryFrequencies.put(category, categoryCount + 1);
            } else {
                categoryFrequencies.put(category, 1);
            }
        }
    }

    public void printBarChart() throws Exception {
        computeTransactionFrequencies();
        for (Map.Entry<String, Integer> entry : categoryFrequencies.entrySet()) {
            String category = entry.getKey();
            Integer frequency = entry.getValue();
            if (frequency == 0) {
                continue;
            }
            double percentage = (double)frequency / totalTransactionsCount;
            int barSize = (int)(percentage * TOTAL_BAR_SIZE);
            System.out.print(category);
            for (int i = 0; i < LEGEND_SIZE - category.length(); i++) {
                System.out.print(" ");
            }
            System.out.print(": ");
            for (int i = 0; i < barSize; i++) {
                System.out.print("|");
            }
            percentage = Math.round(percentage * 10000.0) / 100.0;
            System.out.println(" " + percentage + "%");
        }
    }
}
