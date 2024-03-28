package storage;

import financialtransactions.Transaction;
import financialtransactions.TransactionList;

import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class CSVFileReader {
    public static void printCSVItems(String filePath) throws FileNotFoundException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            System.out.println(s.nextLine());
        }
    }

    // Assumes format of CSV is e.g. (Refund|100.00|Jun 23 2023 05:00PM|REFUND).
    // CSV file can be created using excel
    public void createTransactionListFromCSV (String filePath) throws FileNotFoundException {
        TransactionList<Transaction<?>> newTransactionList;
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            String currLine = s.nextLine();
            String[] splitData = currLine.split(",");
            
        }
    }
}
